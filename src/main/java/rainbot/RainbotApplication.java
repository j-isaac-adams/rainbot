package rainbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class RainbotApplication {
	public static void main(String[] args) {
		SpringApplication.run(RainbotApplication.class, args);
	}

	@RequestMapping(value = { "/", "/{path:[^\\.]*}"})
    public String forward() {
        return "forward:/index.html";
    }
}
