package rainbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class RainbotApplication {
	public static void main(String[] args) {
		SpringApplication.run(RainbotApplication.class, args);
	}	

    public String index() {
        return "index.html";
    }
}
