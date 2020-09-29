package org.sang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ImportResource("classpath:beans.xml")
public class HelloController {
    @Autowired
    Hello hello;
    @GetMapping("/hello")
    public String hello(){
        return hello.sayHello("江南一点雨");
    }
}
