package com.example.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
//import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mr.Du
 * @date 2020/10/28 13:55
 */
@Configuration
@MapperScan(basePackages = "com.example.eduservice.mapper")
public class EduConfig {

}
