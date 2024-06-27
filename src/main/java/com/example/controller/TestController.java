package com.example.controller;

import com.example.common.Result;
import com.example.controller.param.SubmitTradeSubmitOrderParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final RedisTemplate redisTemplate;

    @GetMapping("/testRedis")
    public String testRedis() {
        redisTemplate.opsForValue().set("user", "mario");
        return "e";
    }

    @PostMapping("/testOrder")
    public Result testOrder(@RequestBody SubmitTradeSubmitOrderParam param) {
        return Result.genSuccessResult(param);
    }
}
