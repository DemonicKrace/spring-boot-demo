package com.example.persistence.mapper;

import com.example.persistence.model.C2COrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface C2COrderMapper {
    @Select("select * from c2c_order where id = #{id}")
    C2COrder findById(@Param("id") Long id);

    @Select("select * from c2c_order where order_id = #{orderId}")
    C2COrder findByOrderId(@Param("orderId") String orderId);

    List<C2COrder> findAll();

    int insertOrder(C2COrder c2COrder);

    int updateOrder(C2COrder c2COrder);
}
