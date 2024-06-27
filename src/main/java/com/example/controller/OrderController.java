package com.example.controller;

import com.example.common.order.Result;
import com.example.controller.param.OrderParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * TODO:
 * 功能需求:
 * 1. 市價單/限價單 OrderTypeEnum
 * 2. 單純C2C撮合
 */

@RestController
@RequestMapping("/order")
public class OrderController {
    @PostMapping("/submit")
    public Result testOrder(@RequestBody OrderParam param) {
        return Result.genSuccessResult(param);
    }
}
