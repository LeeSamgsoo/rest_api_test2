package com.example.rest_api_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestApiTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiTestApplication.class, args);
	}

}
