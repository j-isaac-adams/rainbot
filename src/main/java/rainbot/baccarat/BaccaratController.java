package rainbot.baccarat;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rainbot.components.GameSocket;
import rainbot.interfaces.GameStateRequest;

@RestController
public class BaccaratController implements GameStateRequest {

    @GetMapping("/gameState_baccarat")
    public BaccaratRequest returnGameState() {
        return new BaccaratRequest();
    }

    @PostMapping("/gameSession_baccarat")
    public int connectGameSession(@RequestBody GameSocket socket) {
        return handleConnect(socket.getURI());
    }
}

