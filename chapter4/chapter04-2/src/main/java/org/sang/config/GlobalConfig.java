package org.sang.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalConfig {
    @ModelAttribute("info")
    public Map<String,String> userInfo(){
        Map<String,String> map = new HashMap<>();
        map.put("username","罗贯中");
        map.put("gender","男");
        return map;
    }
    /*处理@ModelAttribute("a")对应的参数,将a.前缀的属性绑定到a的model*/
    @InitBinder("a")
    public void init(WebDataBinder binder){
        binder.setFieldDefaultPrefix("a.");
    }
    @InitBinder("b")
    public void init2(WebDataBinder binder){
        binder.setFieldDefaultPrefix("b.");
    }


}
