package com.example.inhousenavigationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InHouseNavigationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(InHouseNavigationSystemApplication.class, args);
    }

}
