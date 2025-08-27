package rainbot.components;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GameSocket extends WebSocketClient {

    private static volatile GameSocket instance;
    private String type;
    private String gameState;

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

    public String getState() {
        return this.gameState;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        connectionLatch.countDown();
    }

    @Override
    public void onMessage(String message) {
        this.gameState = message;
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
