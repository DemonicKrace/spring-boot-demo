package com.example.controller;

import com.example.common.order.Result;
import com.example.controller.param.OrderParam;
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
    public Result testOrder(@RequestBody OrderParam param) {
        return Result.genSuccessResult(param);
    }
}
