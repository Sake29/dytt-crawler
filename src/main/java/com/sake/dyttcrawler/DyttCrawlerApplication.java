package com.sake.dyttcrawler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.sake.dyttcrawler.mapper")
@SpringBootApplication
public class DyttCrawlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DyttCrawlerApplication.class, args);
    }

}
