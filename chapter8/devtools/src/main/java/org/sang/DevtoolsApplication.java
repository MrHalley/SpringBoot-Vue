package org.sang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevtoolsApplication {

    public static void main(String[] args) {
        //关闭Devtool的java配置
        System.setProperty("spring.devtools.restart.enabled","false");
        SpringApplication.run(DevtoolsApplication.class, args);
    }

}
