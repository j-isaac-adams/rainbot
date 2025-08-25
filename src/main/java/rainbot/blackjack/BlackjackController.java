package rainbot.blackjack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rainbot.components.GameSocket;
import rainbot.interfaces.GameStateRequest;

@RestController
public class BlackjackController implements GameStateRequest {
    @GetMapping("/gameState_blackjack")
    public BlackjackRequest returnGameState() {
        return new BlackjackRequest();
    }

    @PostMapping("/gameSession_blackjack")
    public int connectGameSession(@RequestBody GameSocket socket) {
        return handleConnect(socket.getURI());
    }
}
