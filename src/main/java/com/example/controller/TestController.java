package com.example.controller;

import com.example.common.Result;
import com.example.controller.param.SubmitTradeSubmitOrderParam;
import com.example.persistence.mapper.C2COrderMapper;
import com.example.persistence.mapper.UserMapper;
import com.example.persistence.model.C2COrder;
import com.example.persistence.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final RedisTemplate redisTemplate;
    private final UserMapper userMapper;
    private final C2COrderMapper c2COrderMapper;

    @GetMapping("/testRedis")
    public String testRedis() {
//        redisTemplate.opsForValue().set("user", "test");
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "1");
        log.info("lock = {}", lock);
        return "user-test";
    }

    @PostMapping("/testOrder")
    public Result testOrder(@RequestBody SubmitTradeSubmitOrderParam param) {
        return Result.genSuccessResult(param);
    }

    @GetMapping("/testUser")
    public Result testUser() {
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

    @GetMapping("/testOrder")
    public Result testOrder() {
        C2COrder c2COrder = new C2COrder();
        c2COrder.setOrderId("B1234");
        c2COrder.setUserId(1);
        c2COrder.setProtocol("ERC20");
        c2COrder.setCoin("USDT");
        c2COrder.setAmount(BigDecimal.valueOf(100));
        c2COrder.setStatus(0);
        c2COrder.setCreateDate(new Date());
        c2COrder.setUpdateDate(new Date());
        c2COrder.setUpdateBy("Andy");
        int insertResult = c2COrderMapper.insertOrder(c2COrder);
        log.info("insertResult = {}", insertResult);

        C2COrder c2COrder2 = c2COrderMapper.findByOrderId("B1234");
        log.info("c2COrder2 = {}", c2COrder2);

        C2COrder update = new C2COrder();
        update.setId(c2COrder2.getId());
        update.setOrderId("A1234");
        update.setUserId(2);
        update.setProtocol("TRC20");
        update.setCoin("BTC");
        update.setAmount(BigDecimal.valueOf(1));
        update.setStatus(1);
        update.setCreateDate(new Date());
        update.setUpdateDate(new Date());
        update.setUpdateBy("John");
        int updateResult = c2COrderMapper.updateOrder(update);
        log.info("updateResult = {}", updateResult);

        return Result.genSuccessResult();
    }

    @GetMapping("/createSellOrder")
    public Result createSellOrder() {
        String orderId = "S20240714180000AAA";
        Integer amount = 100;
        SellOrder sellOrder = new SellOrder();
        sellOrder.setOrderId(orderId);
        sellOrder.setAmount(amount);
        redisTemplate.opsForValue().set(orderId, sellOrder);
        return Result.genSuccessResult(sellOrder);
    }

    @GetMapping("/createBuyOrder")
    public Result createBuyOrder() {
        String orderId = "S20240714180000AAA";
        SellOrder sellOrder = (SellOrder) redisTemplate.opsForValue().get(orderId);
        if (sellOrder == null) {
            return Result.newBuilder().fail(1001, "不存在").build();
        }

        String matchLock = "LOCK_" + orderId;
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(matchLock, "1");
        if (Boolean.TRUE.equals(lock)) {
            Boolean deleteResult = redisTemplate.delete(matchLock);
            log.info("deleteResult = {}", deleteResult);
            return Result.genSuccessResult();
        }
        return Result.newBuilder().fail(1001, "已被撮和").build();
    }
}

@Data
class SellOrder {
    private String orderId;
    private Integer amount;
}