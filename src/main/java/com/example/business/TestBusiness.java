package com.example.business;


import com.example.common.Result;
import com.example.persistence.mapper.C2COrderMapper;
import com.example.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestBusiness {

    private final RedisTemplate redisTemplate;

    public Result createBuyOrder() {
        String orderId = "S20240714180000AAA";
        String sellOrder = (String) redisTemplate.opsForValue().get(orderId);
        if (StringUtils.isEmpty(sellOrder)) {
            return Result.newBuilder().fail(1001, "不存在").build();
        }

        // 原子性的操作
        String matchLock = "LOCK_" + orderId;
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(matchLock, "1", Duration.ofSeconds(1));
        if (Boolean.TRUE.equals(lock)) {
            Boolean deleteResult = redisTemplate.delete(orderId);
            log.info("deleteResult = {}", deleteResult);
            return Result.genSuccessResult();
        }
        return Result.newBuilder().fail(1001, "已被撮和").build();
    }
}
