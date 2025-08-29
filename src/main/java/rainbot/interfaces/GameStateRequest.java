package rainbot.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import rainbot.components.GameSocket;
import rainbot.components.SocketPayload;

public interface GameStateRequest {

    // Return JSON game data -> REST API.
    public Object returnGameState();

    // Return HTTP Status code -> Game Session link.
    public ResponseEntity<Void> connectGameSession(@RequestBody SocketPayload socket);

    //Handles connection to websocket.
    default ResponseEntity<Void> handleConnect(URI uri, String requestType) {
        try {
            GameSocket socket = GameSocket.get(uri, requestType);
            socket.connect();
            boolean connectionEstablished = socket.awaitConnection(10, TimeUnit.SECONDS);
            if (connectionEstablished && socket.isOpen()) {
                return ResponseEntity.noContent().build(); // HTTP 204
            } else {
                return ResponseEntity.status(504).build(); // HTTP 504
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // HTTP 500
        }
    }
}
