package com.yc.sevnenstore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yc.sevnenstore.mapper")
public class SevnenStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevnenStoreApplication.class, args);
    }

}
