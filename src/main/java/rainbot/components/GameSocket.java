package rainbot.components;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import rainbot.baccarat.BaccaratRequest;

import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GameSocket extends WebSocketClient {

    private static volatile GameSocket instance;
    private static final ObjectMapper mapper = new ObjectMapper();

    private String type;
    private Request gameState;

    private final CountDownLatch connectionLatch = new CountDownLatch(1);

    private GameSocket(URI serverUri, String requestType) {
        super(serverUri);
        this.type = requestType;
    }

    public static GameSocket get() {
        return instance == null ? null : instance;
    }

    public static GameSocket get(URI serverUri, String requestType) {
        if (instance == null) {
            synchronized (GameSocket.class) {
                if (instance == null) {
                    instance = new GameSocket(serverUri, requestType);
                }
            }
        }
        return instance;
    }

    public String getType() {
        return this.type;
    }

    public Request getState() {
        return this.gameState;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        this.gameState = new BaccaratRequest();
        connectionLatch.countDown();
    }

    @Override
    public void onMessage(String message) {
        switch (this.type) {
            case "baccarat":
                try {
                    JsonNode rootNode = mapper.readTree(message);
                    BaccaratRequest request = mapper.treeToValue(rootNode, BaccaratRequest.class);
                    if (request.getShoeSummary() != null) {
                        ((BaccaratRequest)this.gameState).setShoeSummary(request.getShoeSummary());
                        System.out.println("ShoeSummary");
                    } else if (request.getBetstats() != null) {
                        ((BaccaratRequest)this.gameState).setBetstats(request.getBetstats());
                        System.out.println("BetStats");
                    } else if (request.getGame() != null) {
                        ((BaccaratRequest)this.gameState).setGame(request.getGame());
                        System.out.println("Game");
                    } else if (request.getGameResult() != null) {
                        ((BaccaratRequest)this.gameState).setGameResult(request.getGameResult());
                        System.out.println("GameResult");
                    } else if (request.getBetsOpen() != null) {
                        BaccaratRequest tempGameState = ((BaccaratRequest)this.gameState);
                        tempGameState.setBetsOpen(request.getBetsOpen());
                        tempGameState.setBetsClosed(null);
                        tempGameState.setStartDealing(null);
                        tempGameState.clearCards();
                        this.gameState = tempGameState;
                        System.out.println("BetsOpen");
                    } else if (request.getBetsClosed() != null) {
                        BaccaratRequest tempGameState = ((BaccaratRequest)this.gameState);
                        tempGameState.setBetsClosed(request.getBetsClosed());
                        tempGameState.setBetsOpen(null);
                        tempGameState.setBetsClosingSoon(null);
                        this.gameState = tempGameState;
                        System.out.println("BetsClosed");
                    } else if (request.getBetsClosingSoon() != null) {
                        ((BaccaratRequest)this.gameState).setBetsClosingSoon(request.getBetsClosingSoon());
                        System.out.println("BetsClosingSoon");
                    } else if (request.getStartDealing() != null) {
                        ((BaccaratRequest)this.gameState).setStartDealing(request.getStartDealing());
                        System.out.println("StartDealing");
                    } else if (request.getCard() != null) {
                        ((BaccaratRequest)this.gameState).addCard(request.getCard());
                        System.out.println("Card");
                    }
                } catch (Exception e) {
                    System.out.println("BadPacket");
                }
                break;
            case "blackjack":
                break;
            case "poker":
                break;
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("❌ Connection closed by " + (remote ? "remote peer" : "us") + ". Code: " + code + ", Reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("🔥 An error occurred: " + ex.getMessage());
        ex.printStackTrace();
    }

    public boolean awaitConnection(long timeout, TimeUnit unit) throws InterruptedException {
        return connectionLatch.await(timeout, unit);
    }

}
