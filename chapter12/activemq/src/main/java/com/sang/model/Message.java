package com.sang.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.Du
 * @date 2020/10/16 10:33
 */
@Data
public class Message implements Serializable{
    private String content;
    private Date date;
}
