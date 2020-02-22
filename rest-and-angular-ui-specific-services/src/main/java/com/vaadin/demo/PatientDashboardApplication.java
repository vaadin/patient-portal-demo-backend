package com.vaadin.demo;

import com.vaadin.demo.service.DBInitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientDashboardApplication.class, args);
    }

    @Bean
    CommandLineRunner init(DBInitService s) {
        return (String[] str) -> {
            s.initDatabase();
        };
    }

}
