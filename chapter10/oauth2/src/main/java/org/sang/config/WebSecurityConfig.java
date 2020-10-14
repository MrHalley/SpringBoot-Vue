package org.sang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 将authenticationManagerBean 和 userDetailsService
     * 注入授权服务器配置类中使用
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$10$beKv52zj0bqAS7aO8X4LLu0lgIx5Nt5TNS/lQzWBt8Kcw6Brnh4om")
                .roles("admin")
                .and()
                .withUser("sang")
                .password("$2a$10$beKv52zj0bqAS7aO8X4LLu0lgIx5Nt5TNS/lQzWBt8Kcw6Brnh4om")
                .roles("user");
    }

    /**
     * 配置"/oauth/**"模式的url直接放行，
     * SpringSecurity和资源服务器配置中，一共涉及两个HttpSecurity
     * SpringSecurity配置的优先级高于资源服务器的配置,及请求地址先经过
     * SpringSecurity的HttpSecurity再经过资源服务器的HttpSecurity
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/oauth/**").authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .and().csrf().disable();
    }
}
