package org.sang.config;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class MyErrorViewResolver/* extends DefaultErrorViewResolver*/ implements ErrorViewResolver {
    /**
     * Create a new {@link DefaultErrorViewResolver} instance.
     *
     */
    /*public MyErrorViewResolver(ApplicationContext applicationContext, ResourceProperties resourceProperties) {
        super(applicationContext, resourceProperties);
    }*/

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("errorPage");
        mv.addAllObjects(model);
        mv.addObject("msg","出错啦msg");
        mv.addObject(model);
        return mv;
    }
}
