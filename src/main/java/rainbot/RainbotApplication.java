package rainbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@Controller
public class RainbotApplication {
	public static void main(String[] args) {
		SpringApplication.run(RainbotApplication.class, args);
	}	

	@RequestMapping(value = {"/", "/{path:^(?!gameState).*$}/**"})
    public String index() {
        return "index.html";
    }
}
