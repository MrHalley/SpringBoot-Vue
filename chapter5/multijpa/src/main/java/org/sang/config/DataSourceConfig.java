package org.sang.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.one")
    //@Primary //当存在多个实例时，该实例将优先被使用
    DataSource dsOne(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties("spring.datasource.two")
    @Primary
    DataSource dsTwo(){
        return DruidDataSourceBuilder.create().build();
    }
}
