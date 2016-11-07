package com.vaadin.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class PatientDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientDashboardApplication.class, args);
	}

}
