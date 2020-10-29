package com.example.commonutils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Du
 * @date 2020/10/28 18:40
 * 统一结果返回
 */
@Data
public class R {
    @ApiModelProperty("是否成功")
    private Boolean success;
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("返回消息")
    private String message;
    @ApiModelProperty("返回数据")
    private Map<String,Object> data = new HashMap<>();

    private R(){}
    private R(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public static R ok(){
        return new R(true,ResultCode.SUCCESS,"成功");
    }

    public static R error(){
        return new R(false,ResultCode.ERROR,"失败");
    }

    public R success(Boolean success){
        this.success = success;
        return this;
    }

    public R code(Integer code){
        this.code = code;
        return this;
    }

    public R message(String message){
        this.message = message;
        return this;
    }

    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public R data(Map<String,Object> data){
        this.data = data;
        return this;
    }
}
