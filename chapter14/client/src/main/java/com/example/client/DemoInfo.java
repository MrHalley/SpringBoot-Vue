package com.example.client;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Mr.Du
 * @date 2020/10/22 19:11
 */
@Component
public class DemoInfo implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("author",new HashMap<>(){{
            put("name","江南");
            put("email","hello@gmail.com");
        }});
    }
}
