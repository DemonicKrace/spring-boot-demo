package com.example.controller;

import com.example.common.order.Result;
import com.example.controller.param.OrderParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @PostMapping("/submit")
    public Result testOrder(@RequestBody OrderParam param) {
        return Result.genSuccessResult(param);
    }
}
