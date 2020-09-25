package com.zhou.agriculture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AgricultureApplication{

    public static void main(String[] args) {
        SpringApplication.run(AgricultureApplication.class, args);
    }
}
