package com.example.homediet;

import com.example.homediet.service.IDietService;
import com.example.homediet.service.impl.DietServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.homediet.mapper")
public class HomeDietApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeDietApplication.class, args);
    }

}