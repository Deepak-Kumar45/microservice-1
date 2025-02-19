package com.example;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StdAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StdAppApplication.class, args);
		System.out.println("\n\n\t\t!!..Student Microservice has been started..!!");
	}
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
