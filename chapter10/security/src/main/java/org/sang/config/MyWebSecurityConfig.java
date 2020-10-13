package org.sang.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置用户,url路径安全,登录页面，加密算法，注销等
 */
//@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     * @return 不对密码加载，必须指定一种加密方式
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 此处配置账户后，appliction.yml中配置的就无效。
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                //配置三个用户，及其对应角色
                .withUser("admin").password("123").roles("ADMIN","USER")
                .and()
                .withUser("sang").password("123").roles("USER")
                .and()
                .withUser("root").password("123").roles("ADMIN","DBA");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //配置访问对应路径时，需要的角色。注意路径大的应该配在后面
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/user/**")
                .access("hasAnyRole('ADMIN','USER')")
                .antMatchers("/db/**")
                .access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest()
                .authenticated()
                .and()
                //开启表单登录，并配置登录页面、登录接口,发起一个post请求进行登录。
                .formLogin()
                .loginPage("/login_page")
                .loginProcessingUrl("/login")
                .usernameParameter("name")
                .passwordParameter("passwd")
                //登录成功逻辑，可以跳转页面，可以返回Json。这里返回json
                .successHandler((request,response,authentication)->{
                    Object principal = authentication.getPrincipal();
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    response.setStatus(200);
                    Map<String,Object> map = new HashMap<>();
                    map.put("status",200);
                    map.put("msg",principal);
                    ObjectMapper om = new ObjectMapper();
                    out.write(om.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                //登录失败逻辑，e为AuthenticationException类型参数，可以用来获取登录失败原因，给用户一个明确的提示
                .failureHandler((request,response,e)->{
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    response.setStatus(401);
                    Map<String,Object> map = new HashMap<>();
                    map.put("status",401);
                    if(e instanceof LockedException){
                        map.put("msg","账户被锁定，登录失败");
                    }else if(e instanceof BadCredentialsException){
                        map.put("msg","账户名或密码输入错误，登录失败");
                    }else if(e instanceof AccountExpiredException){
                        map.put("msg","账户已过期，登录失败");
                    }else if(e instanceof CredentialsExpiredException){
                        map.put("msg","密码已过期,登录失败");
                    }else{
                        map.put("msg","登录失败");
                    }
                    ObjectMapper om = new ObjectMapper();
                    out.write(om.writeValueAsString(map));
                    out.flush();
                    out.close();
                })
                //表示和登录相关的接口(此处配的login)无需认证
                .permitAll()
                .and()
                //注销登录配置
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler((request,response,authentication)->{
                    //完成一些数据清除工作，例如清除Cookie，SpringSecurity提供了常见的实现
                })
                .logoutSuccessHandler((request,response,authentication)->{
                    response.sendRedirect("/login_page");
                })
                .and()
                //关闭csrf,否则注销有可能失效
                .csrf()
                .disable();

    }
}
