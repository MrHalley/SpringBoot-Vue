package org.sang.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
public class Book {
    private String name;
    private String author;
    //@JsonIgnore
    //@JSONField(serialize = false) //针对在使用fastjson做序列化时使用@ JsonIgnore不生效的bug
    private Float price;
    //@JsonFormat(pattern = "yyyy-MM-dd")
    //@JSONField(format = "yyyy/MM/dd")//alibaba的注解
    private Date publicationDate;
}
