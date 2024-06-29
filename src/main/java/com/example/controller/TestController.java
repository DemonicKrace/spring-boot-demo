package com.example.controller;

import com.example.common.Result;
import com.example.controller.param.SubmitTradeSubmitOrderParam;
import com.example.persistence.mapper.UserMapper;
import com.example.persistence.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final RedisTemplate redisTemplate;
    private final UserMapper userMapper;

    @GetMapping("/testRedis")
    public String testRedis() {
        redisTemplate.opsForValue().set("user", "test");
        return "user-test";
    }

    @PostMapping("/testOrder")
    public Result testOrder(@RequestBody SubmitTradeSubmitOrderParam param) {
        return Result.genSuccessResult(param);
    }

    @GetMapping("/testMysql")
    public Result testOrder() {
        User insert1 = new User();
        insert1.setUsername("username1");
        insert1.setPassword("password1");
        int insert1Result = userMapper.insertUser(insert1);
        log.info("insert1Result = {}", insert1Result);

        User insert2 = new User();
        insert2.setUsername("username2");
        insert2.setPassword("password2");
        int insert2Result = userMapper.insertUser(insert2);
        log.info("insert2Result = {}", insert2Result);

        User user = userMapper.findById(1);
        log.info("user = {}", user);

        List<User> users = userMapper.findAll();
        log.info("users = {}", users);

        User update = new User();
        update.setId(1);
        update.setUsername("update");
        update.setPassword("update");
        int updateResult = userMapper.updateUser(update);
        log.info("updateResult = {}", updateResult);

        int deleteResult = userMapper.deleteUser(2);
        log.info("deleteResult = {}", deleteResult);

        return Result.genSuccessResult();
    }
}
