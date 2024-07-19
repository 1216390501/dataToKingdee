package com.example.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebappApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(WebappApplication.class, args);
    }

}
