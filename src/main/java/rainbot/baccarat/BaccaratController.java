package rainbot.baccarat;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rainbot.components.GameSocket;
import rainbot.components.SocketPayload;
import rainbot.interfaces.GameStateRequest;

@RestController
public class BaccaratController implements GameStateRequest {

    @GetMapping("/gameState_baccarat")
    public BaccaratRequest returnGameState() {
        GameSocket socket = GameSocket.get();
        BaccaratRequest request = new BaccaratRequest(socket.getState());
        return request;
    }

    @PostMapping("/gameSession_baccarat")
    public ResponseEntity<Void> connectGameSession(@RequestBody SocketPayload socket) {
        return handleConnect(socket.getURI(), "baccarat");
    }
}

