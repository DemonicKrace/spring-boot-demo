package com.example.common.order;

import java.util.HashMap;
import java.util.Map;

public enum OrderTypeEnum {
    UNKNOWN(-1, "未定義"),
    LIMIT_TYPE(1, "限價單"),
    MARKET_TYPE(2, "市價單"),
    ;

    private final Integer type;
    private final String desc;

    OrderTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }


    private final static Map<Integer, OrderTypeEnum> map;

    static {
        map = new HashMap<>();
        for (OrderTypeEnum orderTypeEnum : OrderTypeEnum.values()) {
            map.put(orderTypeEnum.getType(), orderTypeEnum);
        }
    }

    public static OrderTypeEnum getEnumByType(Integer type) {
        OrderTypeEnum orderTypeEnum = map.get(type);
        return orderTypeEnum == null ? UNKNOWN : orderTypeEnum;
    }
}
