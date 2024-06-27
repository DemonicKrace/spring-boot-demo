package com.example.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

// TODO:
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RedisUtils {
    private final RedisTemplate redisTemplate;

}
