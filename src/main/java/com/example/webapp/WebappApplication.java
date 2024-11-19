package com.example.webapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.example.webapp.mapper")
public class WebappApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(WebappApplication.class, args);
    }

}
