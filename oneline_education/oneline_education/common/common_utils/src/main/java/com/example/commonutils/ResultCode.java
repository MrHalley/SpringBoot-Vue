package com.example.commonutils;

import lombok.Data;

/**
 * 统一返回值的状态码
 */
public enum ResultCode {
    SUCCESS(20000),ERROR(20001);
    private final Integer code;
    ResultCode(Integer code){
        this.code = code;
    }
    public Integer getCode(){
        return code;
    }
}
