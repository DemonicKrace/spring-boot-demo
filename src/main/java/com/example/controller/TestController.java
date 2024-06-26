package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/testRedis")
    public String testRedis() {
        redisTemplate.opsForValue().set("user", "mario");
        return "e";
    }


    @GetMapping("/")
    public String test() {
        return "1";
    }
}
