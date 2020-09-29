package org.sang.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.lang.reflect.Modifier;

//@Configuration
public class GsonConfig {
    @Bean
    GsonHttpMessageConverter gsonHttpMessageConverter(){
        //提供一个GsonHttpMessageConverter实例
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        GsonBuilder builder = new GsonBuilder();
        //设置Gson解析时日期的格式
        builder.setDateFormat("yyyy-MM-dd");
        //设置Gson解析时修饰符为protected的字段被过滤掉
        builder.excludeFieldsWithModifiers(Modifier.PROTECTED);
        Gson gson = builder.create();
        converter.setGson(gson);
        return converter;
    }
}
