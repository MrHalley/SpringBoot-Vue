package org.sang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 通过注解来灵活的配置方法安全。
 *
 * @EnableGlobalMethodSecurity: 开启注解的安全配置
 * prePostEnabled=true 解锁@PreAuthorize和PostAuthorize两个注解。
 * securedEnabled=true 解锁@Secured注解
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig {

}
