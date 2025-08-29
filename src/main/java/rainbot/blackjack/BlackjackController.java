package rainbot.blackjack;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rainbot.components.SocketPayload;
import rainbot.interfaces.GameStateRequest;

@RestController
public class BlackjackController implements GameStateRequest {
    @GetMapping("/gameState_blackjack")
    public Object returnGameState() {
        return new BlackjackRequest().formatReturn();
    }

    @PostMapping("/gameSession_blackjack")
    public ResponseEntity<Void> connectGameSession(@RequestBody SocketPayload socket) {
        return handleConnect(socket.getURI(), "blackjack");
    }
}
