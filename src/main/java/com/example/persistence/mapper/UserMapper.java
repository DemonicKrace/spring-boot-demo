package com.example.persistence.mapper;

import com.example.persistence.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);

    List<User> findAll();

    int insertUser(User User);

    int updateUser(User User);

    int deleteUser(Integer id);
}
