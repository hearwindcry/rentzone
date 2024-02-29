package com.julius.controller;

import com.julius.entity.User;
import com.julius.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public User getUser(){
        int x = 0;
        User user = userMapper.queryOne();
        return user;
    }

    @GetMapping("/t")
    public String getHello(){
        return "hello guys";
    }
}
