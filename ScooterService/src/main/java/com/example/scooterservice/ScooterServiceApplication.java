package com.example.scooterservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "Scooter Service API", version = "1.0", description = "Documentation Scooter Service API v1.0"))
public class ScooterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScooterServiceApplication.class, args);
    }

}
