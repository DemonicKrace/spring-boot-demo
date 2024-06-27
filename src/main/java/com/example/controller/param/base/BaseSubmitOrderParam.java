package com.example.controller.param.base;

import lombok.Data;

import java.io.Serializable;

/**
 * 提交訂單通用參數
 * <p>
 * userId 可從HTTP Header獲取 (可選)
 */
@Data
public class BaseSubmitOrderParam implements Serializable {
    // 用戶ID
    private Integer userId;
    // 交易安全碼
    private String code;
}
