package com.zhixi;

import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.zhixi.mapper")
public class Shiro02SpringbootJspApplication {

    public static void main(String[] args) {
        SpringApplication.run(Shiro02SpringbootJspApplication.class, args);
    }
}
