package com.example.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "c2c_order")
public class C2COrder implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 訂單編號
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 用戶ID
     */
    @Column(name = "user_id")
    private Integer userid;

    /**
     * 區塊鏈的協議
     */
    @Column(name = "protocol")
    private String protocol;

    /**
     * 代幣代號
     */
    @Column(name = "coin")
    private String coin;

    /**
     * 代幣金額
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * 訂單狀態
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 創建時間
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 更新時間
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;
}