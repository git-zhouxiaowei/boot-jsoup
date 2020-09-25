package com.zhou.jsoup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JsoupApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsoupApplication.class, args);
    }
}
