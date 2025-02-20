package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShopkeeperAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopkeeperAppApplication.class, args);
		System.err.println("\n\n\t\t!!..Shopkeeper microservice started..!!");
	}
}
