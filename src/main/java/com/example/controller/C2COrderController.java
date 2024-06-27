package com.example.controller;

import com.example.common.Result;
import com.example.controller.param.SubmitC2CSubmitOrderParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/c2c/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class C2COrderController {

    public Result submit(@RequestBody SubmitC2CSubmitOrderParam submitC2COrderParam) {

        return Result.genSuccessResult();
    }
}
