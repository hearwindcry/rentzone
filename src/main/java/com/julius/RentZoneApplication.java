package com.julius;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@MapperScan("com.julius.mapper")
@SpringBootApplication
@EnableWebSecurity
public class RentZoneApplication
{
    public static void main( String[] args ){
        SpringApplication.run(RentZoneApplication.class, args);
    }
}
