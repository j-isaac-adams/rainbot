package rainbot.interfaces;

import org.springframework.web.bind.annotation.RequestBody;

import rainbot.components.GameSocket;
import rainbot.components.Request;

public interface GameStateRequest {

    // Return JSON game data -> REST API.
    public Request returnGameState();

    // Return HTTP Status code -> Game Session link.
    public int connectGameSession(@RequestBody GameSocket socket);

    //Handles connection to websocket.
    default int handleConnect(String uri){
        return connect(uri);
    }

    private int connect(String uri) {
        return 204;
    }
}
