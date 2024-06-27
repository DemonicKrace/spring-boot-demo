package com.example.controller;

import com.example.common.Result;
import com.example.controller.param.C2COrderParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/c2c/order")
public class C2COrderController {

    public Result submit(@RequestBody C2COrderParam c2COrderParam) {

        return Result.genSuccessResult();
    }
}
