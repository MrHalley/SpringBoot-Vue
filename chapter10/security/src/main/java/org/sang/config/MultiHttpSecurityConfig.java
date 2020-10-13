package org.sang.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 多个HttpSecurity
 * 测试MyWebSecurityConfig时，需要将下面所有的@Configuration注解注释掉。
 */
@Configuration
public class MultiHttpSecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder(){
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder(10);
    }
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("admin").password("$2a$10$Kz9rYaHx29TyqoOQKkI2quzQMo3sA7I6XS5KJIqyI4OFjZbD4G//m").roles("ADMIN","USER")
        .and()
        .withUser("sang").password("$2a$10$beKv52zj0bqAS7aO8X4LLu0lgIx5Nt5TNS/lQzWBt8Kcw6Brnh4om").roles("USER");
    }
    @Configuration
    @Order(1)   //配置优先级，数字越小级优先级越大，未加@Order注解的配置优先级最小
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**").authorizeRequests()
                    .anyRequest().hasRole("ADMIN");
        }
    }
    @Configuration
    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/login")
                    .and()
                    .csrf()
                    .disable();
        }
    }
}
