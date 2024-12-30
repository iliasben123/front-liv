package com.fooddelivery.fooddeliveryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // Active le planificateur de tâches
public class FooddeliveryappApplication {
	public static void main(String[] args) {
		SpringApplication.run(FooddeliveryappApplication.class, args);
	}
}
