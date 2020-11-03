package com.example.servicebase.handler;

import com.example.commonutils.MyException;
import com.example.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * @author Mr.Du
 * @date 2020/10/30 14:06
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exception(Exception e){
        e.printStackTrace();
        return R.error().message(e.getMessage());
    }

    /**
     * 自定义异常处理
     * @param myException 自定义的异常类
     * @return
     */
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public R myException(MyException myException){
        return R.error().message(myException.getMessage()).code(myException.getCode());
    }
}
