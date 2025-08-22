package rainbot.poker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rainbot.GameStateRequest;

@RestController
public class PokerController implements GameStateRequest {
    @RequestMapping("/gameState_poker")
    public PokerRequest returnGameState() {
        return new PokerRequest();
    }
}
