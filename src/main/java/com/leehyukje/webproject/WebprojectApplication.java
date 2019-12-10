package com.leehyukje.webproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("config.properties")
public class WebprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebprojectApplication.class, args);
    }

}
