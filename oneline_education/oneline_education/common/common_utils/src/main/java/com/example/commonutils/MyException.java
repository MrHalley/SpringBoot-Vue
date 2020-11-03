package com.example.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mr.Du
 * @date 2020/10/30 14:20
 */
@Data
@AllArgsConstructor
public class MyException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String message;
}
