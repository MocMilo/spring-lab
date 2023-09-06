package com.sandystack.exp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLabApplication.class, args);
    }
}
