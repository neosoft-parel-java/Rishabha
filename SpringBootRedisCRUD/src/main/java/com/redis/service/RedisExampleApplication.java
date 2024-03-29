package com.redis.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
public class RedisExampleApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RedisExampleApplication.class, args);
    }
}
