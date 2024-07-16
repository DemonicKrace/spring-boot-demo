package com.example.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

// TODO:
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RedisUtils {
    private final RedisTemplate redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper() {
        {
            this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        }
    };

    public void set(String key, Object obj) {
        set(key, obj, -1);
    }

    public void set(String key, Object obj, int expireTimeMills) {
        if (!StringUtil.isNullOrEmpty(key) &&  obj != null) {
            try {
                if (expireTimeMills <= 0) {
                    redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(obj));
                } else {
                    redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(obj), Duration.ofMillis(expireTimeMills));
                }
            } catch (IOException e) {
                log.error("RedisUtils.set, error, key = {}, obj = {}", key, obj, e);
            }
        }
    }

    public <T> T get(String key, Class<T> clazz) {
        if (StringUtil.isNullOrEmpty(key)) {
            return null;
        }
        if (clazz == null) {
            return null;
        }
        Object value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        T obj = null;
        try {
            obj = objectMapper.readValue(value.toString(), clazz);
        } catch (IOException e) {
            log.error("RedisUtils.get, error, key = {}, class = {}, value = {}", key, clazz.getName(), value);
        }
        return obj;
    }
}
