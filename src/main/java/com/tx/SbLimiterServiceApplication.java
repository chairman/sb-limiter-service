package com.tx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * redis 中使用 lua脚本
 */
@SpringBootApplication
public class SbLimiterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbLimiterServiceApplication.class, args);
    }

}

