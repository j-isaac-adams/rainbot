package com.jiadams.rainbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class RainbotApplication {
	public static void main(String[] args) {
		SpringApplication.run(RainbotApplication.class, args);
	}	

	@GetMapping()
	public String home() {
		return "index";
	}
}
