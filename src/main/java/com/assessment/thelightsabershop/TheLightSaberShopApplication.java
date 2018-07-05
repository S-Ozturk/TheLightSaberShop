package com.assessment.thelightsabershop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.assessment.thelightsabershop.controller.UserService;

@SpringBootApplication
public class TheLightSaberShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheLightSaberShopApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(UserService userService) {
        return (args) -> {
            userService.initUsers();
        };
    }
}
