package rainbot.poker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rainbot.components.SocketPayload;
import rainbot.interfaces.GameStateRequest;

@RestController
public class PokerController implements GameStateRequest {
    @GetMapping("/gameState_poker")
    public PokerRequest returnGameState() {
        return new PokerRequest();
    }

    @PostMapping("/gameSession_poker")
    public int connectGameSession(@RequestBody SocketPayload socket) {
        return handleConnect(socket.getURI());
    }
}
