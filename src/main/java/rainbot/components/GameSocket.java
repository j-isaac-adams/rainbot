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
        connectionLatch.countDown();
    }

    @Override
    public void onMessage(String message) {
        try {
            JsonNode rootNode = mapper.readTree(message);

            if (rootNode.has("betstats")) {
                BaccaratRequest request = mapper.treeToValue(rootNode, BaccaratRequest.class);
                this.gameState = request;
            } else if (rootNode.has("data")) {
                // You would convert to your GameStatePacket class here
                // GameStatePacket packet = mapper.treeToValue(rootNode, GameStatePacket.class);
                // System.out.println("Game UUID: " + packet.getData().getTable().getUuid());

            } else {
                //TODO
            }

        } catch (Exception e) {
            System.err.println("Failed to parse packet: " + e.getMessage());
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
