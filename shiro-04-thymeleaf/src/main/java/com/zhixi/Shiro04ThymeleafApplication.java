package com.zhixi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhixi.mapper")
public class Shiro04ThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(Shiro04ThymeleafApplication.class, args);
    }

}
