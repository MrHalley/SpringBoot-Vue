package org.sang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class Chapter013Application {
    @GetMapping("/home")
    public String home(){
        return "hello spring boot";
    }
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Chapter013Application.class);
        builder.application().setAdditionalProfiles("dev");
        builder.run(args);
    }

}
