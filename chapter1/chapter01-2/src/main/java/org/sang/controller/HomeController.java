package org.sang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    private final MyEnv env;

    public HomeController(MyEnv env) {
        this.env = env;
    }

    @GetMapping("/home")
    public String home(){
        env.printEnv();
        return "hello spring boot!";
    }
}
