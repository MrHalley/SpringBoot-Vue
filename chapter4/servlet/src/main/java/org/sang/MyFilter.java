package org.sang;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter可认为是Servlet的一种“加强版”，它主要用于对用户请求进行预处理，
 * 也可以对HttpServletResponse进行后处理，是个典型的处理链。
 * 《轻量级JavaEE企业级应用,2.9Filter介绍》
 */
@Slf4j
@WebFilter("/*")
public class MyFilter implements Filter {
    //FilterConfig可用于访问Filter的配置信息
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        log.warn("初始化Filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //--------下面代码用于对用户请求进行预处理
        ServletContext context = this.filterConfig.getServletContext();
        long before = System.currentTimeMillis();
        log.warn("MyFilter >>> doFilter 开始过滤");
        HttpServletRequest hrequest = (HttpServletRequest) request;
        log.warn("Filter已经截获到用户的请求的地址: "+hrequest.getServletPath());
        //Filter只是链式处理，请求依然放行到目的地址
        chain.doFilter(request,response);
        //--------下面代码用于对服务器响应执行后处理
        long after = System.currentTimeMillis();
        log.warn("过滤结束");
        log.warn("请求被定位到: "+hrequest.getRequestURI()+"  所花的时间为："+(after - before)+"ms");
    }

    @Override
    public void destroy() {
        log.warn("MyFilter >>> destroy");
    }
}
