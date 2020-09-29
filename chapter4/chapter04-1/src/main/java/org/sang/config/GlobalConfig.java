package org.sang.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置全局数据
 * 通过以下配置，在任意请求的Controller中，通过方法参数中的
 * Model都可以获取info的数据
 */
@ControllerAdvice
public class GlobalConfig {
    @ModelAttribute("info")
    public Map<String,String> userInfo(){
        Map<String,String> map = new HashMap<>();
        map.put("username","罗贯中");
        map.put("gender","男");
        return map;
    }
}
