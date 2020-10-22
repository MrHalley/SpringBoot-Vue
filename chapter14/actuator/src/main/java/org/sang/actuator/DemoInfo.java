package org.sang.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Du
 * @date 2020/10/22 12:13
 */
@Component
public class DemoInfo implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String,String> info = new HashMap<>(){{
           put("name2","fd");
           put("email2","fd3");
        }};
        builder.withDetail("author2",info);
    }
}
