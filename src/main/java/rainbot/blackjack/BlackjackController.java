package rainbot.blackjack;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rainbot.GameStateRequest;

@RestController
public class BlackjackController implements GameStateRequest {
    @RequestMapping("/gameState_blackjack")
    public BlackjackRequest returnGameState() {
        return new BlackjackRequest();
    }
}
