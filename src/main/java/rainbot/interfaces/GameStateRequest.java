package rainbot.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import java.net.URI;

import rainbot.components.GameSocket;
import rainbot.components.Request;
import rainbot.components.SocketPayload;

public interface GameStateRequest {

    // Return JSON game data -> REST API.
    public Request returnGameState();

    // Return HTTP Status code -> Game Session link.
    public int connectGameSession(@RequestBody SocketPayload socket);

    //Handles connection to websocket.
    default int handleConnect(URI uri) {
        GameSocket client = new GameSocket(uri);
        return client.returnStatus();
    }
}
