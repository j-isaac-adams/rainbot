package rainbot.components;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;

public class GameSocket extends WebSocketClient {

    public GameSocket(URI serverUri) {
        super(serverUri);
        this.connect();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
    }

    @Override
    public void onMessage(String message) {
        // This method is called every time a message is received from the server.
        System.out.println("📩 Received message: " + message);
        // Add your logic here to process the incoming data.
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // This method is called when the connection is closed.
        System.out.println("❌ Connection closed by " + (remote ? "remote peer" : "us") + ". Code: " + code + ", Reason: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        // This method is called when an error occurs.
        System.err.println("🔥 An error occurred: " + ex.getMessage());
        ex.printStackTrace();
    }

    public int returnStatus() {
        return 204;
    }

}
