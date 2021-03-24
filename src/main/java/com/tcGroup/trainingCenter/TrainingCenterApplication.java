package com.tcGroup.trainingCenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TrainingCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainingCenterApplication.class, args);
	}
}
