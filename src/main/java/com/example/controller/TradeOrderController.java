package com.example.controller;

import com.example.common.Result;
import com.example.controller.param.TradeOrderParam;
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
@RequestMapping("/trade/order")
public class TradeOrderController {

    /**
     *
     * @param param
     * @return
     */
    @PostMapping("/submit")
    public Result testOrder(@RequestBody TradeOrderParam param) {
        return Result.genSuccessResult(param);
    }
}