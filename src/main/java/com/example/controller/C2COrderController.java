package com.example.controller;

import com.example.business.C2COrderBusiness;
import com.example.common.Result;
import com.example.controller.param.c2c.C2CSubmitBuyOrderParam;
import com.example.controller.param.c2c.C2CSubmitSellOrderParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/c2c/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class C2COrderController {

    private final C2COrderBusiness c2COrderBusiness;

    /**
     * 建立C2C賣單
     *
     * @param param 提交C2C賣單的請求
     * @return Result 統一的的回傳結果
     */
    @PostMapping("/submitSellOrder")
    public Result submitSellOrder(@RequestBody C2CSubmitSellOrderParam param) {
        return c2COrderBusiness.submitSellOrder(param);
    }


    /**
     * 建立C2C買單
     *
     * @param param 提交C2C買單的請求
     * @return Result 統一的的回傳結果
     */
    @PostMapping("/submitBuyOrder")
    public Result submitBuyOrder(@RequestBody C2CSubmitBuyOrderParam param) {
        return c2COrderBusiness.submitBuyOrder(param);
    }
}
