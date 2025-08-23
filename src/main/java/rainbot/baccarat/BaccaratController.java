package rainbot.baccarat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rainbot.GameStateRequest;

@RestController
public class BaccaratController implements GameStateRequest {

    @RequestMapping("/gameState_baccarat")
    public BaccaratRequest returnGameState() {
        return new BaccaratRequest();
    }
}

