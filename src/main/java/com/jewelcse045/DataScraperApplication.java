package com.jewelcse045;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DataScraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataScraperApplication.class, args);
	}

	@GetMapping
	public String displayWelcomeMessage(){
		return "Welcome Guys! Now, Scrap Anything, what do you want.........";
	}


}
