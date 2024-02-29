package com.julius.controller;

import com.julius.entity.SignInDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody SignInDTO signInDTO) {

        UsernamePasswordAuthenticationToken authenticationToken =
                // TODO: 前端公钥加密 后端私钥处理
                new UsernamePasswordAuthenticationToken(signInDTO.getUsername(),signInDTO.getPassword());

        authenticationManager.authenticate(authenticationToken);
        return "success";
    }
}