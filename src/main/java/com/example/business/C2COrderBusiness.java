package com.example.business;

import com.example.common.Result;
import com.example.controller.param.c2c.C2CQuerySellOrderParam;
import com.example.controller.param.c2c.C2CSubmitBuyOrderParam;
import com.example.controller.param.c2c.C2CSubmitSellOrderParam;
import com.example.persistence.mapper.C2COrderMapper;
import com.example.persistence.model.C2COrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * 簡易的C2C撮合業務邏輯
 * 版本1.
 * 敘述: 以賣方為主的撮合，需要有對應的賣單(Sell Order)，買單才可以成功提交且撮合上
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class C2COrderBusiness {

    private final C2COrderMapper c2COrderMapper;

    /**
     * 建立C2C賣單
     *
     * @param param 提交C2C賣單的請求
     * @return Result 統一的的回傳結果
     */
    public Result submitSellOrder(C2CSubmitSellOrderParam param) {
        // TODO: 業務邏輯實作

        // 插入賣單
        // 賣單 在 DB的數據結構要先定義

        // 由param 去新增一個 C2COrder 物件
        C2COrder c2COrder = new C2COrder();
        // SE yyyyMMdd hhmmss SSS  亂數N位/ 用戶ID
//        c2COrder.setOrderId();
        c2COrder.setCreateDate(new Date());

        c2COrderMapper.insertOrder(c2COrder);


        return Result.genSuccessResult();
    }

    /**
     * 建立C2C買單
     *
     * @param param 提交C2C買單的請求
     * @return Result 統一的的回傳結果
     */
    public Result submitBuyOrder(C2CSubmitBuyOrderParam param) {
        // TODO: 業務邏輯實作


        return Result.genSuccessResult();
    }

    /**
     * 查詢賣單列表
     *
     * @param param 查詢參數
     * @return 賣單列表
     */
    public Result getSellOrderList(C2CQuerySellOrderParam param) {
        // TODO: 業務邏輯實作
        return Result.genSuccessResult();
    }
}
