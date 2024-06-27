package com.example.controller.param.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseOrderParam implements Serializable {
    private Integer userId;
    private String orderId;
    private Date createDate;
}
