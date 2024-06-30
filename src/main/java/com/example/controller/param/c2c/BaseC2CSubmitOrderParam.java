package com.example.controller.param.c2c;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * C2C的訂單提交共通的參數
 */
@Data
public class BaseC2CSubmitOrderParam implements Serializable {
    /**
     * 對應提交的用戶ID
     */
    private Integer userId;

    /**
     * 區塊鏈的協議, ERC20, TRC20
     */
    private String protocol;

    /**
     * 代幣代號, USDT
     */
    private String coin;

    /**
     * 對應的代幣金額
     */
    private BigDecimal amount;
}
