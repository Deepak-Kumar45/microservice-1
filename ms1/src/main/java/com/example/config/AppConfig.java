package com.example.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

	@Bean
	WebClient webClient(@Value("${std-app.url}") String BASE_URL) {
		return WebClient.builder().baseUrl(BASE_URL).build();
	}
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
