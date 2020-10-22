package org.sang.actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author Mr.Du
 * @date 2020/10/22 14:04
 * 自定义HealthIndicator
 */
@Component
public class MyAppHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        //自定义的检查方法
        //Health.up.build() 代表健康
        //Health.down().withDetail("mas","服务异常").build();
        return Health.status("FATAL").withDetail("msg","网络断开").build();
    }
}
