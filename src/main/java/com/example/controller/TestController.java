package com.example.controller;

import com.example.controller.param.OrderParam;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/testOrder")
    public String testOrder(@RequestBody OrderParam param) {
        System.out.println(param);
        return "1";
    }
}
