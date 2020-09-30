package org.sang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;


@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
    }

}
