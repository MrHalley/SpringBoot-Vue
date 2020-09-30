package org.sang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 一般情况下，使用了页面模板后，用户需要用过控制器才能访问页面。
 * 有一些页面需要在控制器中加载数据，然后渲染，才能显示出来;还有
 * 一些页面在控制器中不需要加载数据，只是完成简单的跳转，对于这种
 * 页面，可以直接配置路径映射，提高访问速度。
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
    }
}
