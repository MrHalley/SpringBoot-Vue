package com.example.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Mr.Du
 * @date 2020/10/28 13:53
 */
@SpringBootApplication
//扫描swagger2的配置文件所在的包
@ComponentScan(basePackages = {"com.example"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
