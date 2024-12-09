package com.example.webapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.webapp.mapper")
//@ComponentScan("com.example.webapp.service")
public class WebappApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(WebappApplication.class, args);
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        System.out.println(run.getClass());
    }

}
