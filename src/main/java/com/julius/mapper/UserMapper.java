package com.julius.mapper;

import com.julius.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("select * from t_user")
    User queryOne();

    @Select("select * from t_user where user_name = ${username}}")
    User queryByUsername(String username);
}
