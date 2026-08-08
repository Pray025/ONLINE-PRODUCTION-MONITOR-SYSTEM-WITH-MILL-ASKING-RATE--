package com.tatasteel.production;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductionMonitoringSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductionMonitoringSystemApplication.class, args);
	}	
}
