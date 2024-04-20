package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages= "com.example")
public class RestfulWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebApplication.class, args);
	}

}
