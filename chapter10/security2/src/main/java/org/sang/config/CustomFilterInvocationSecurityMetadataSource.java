package org.sang.config;

import org.sang.mapper.MenuMapper;
import org.sang.model.Menu;
import org.sang.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 要实现动态配置权限，首先要自定义FilterInvocationSecurityMetadataSource,
 * Spring Security 通过FilterInvocationSecurityMetadataSource
 * 接口的getAttributes方法来确定一个请求需要哪些角色，FilterInvocationSecurityMetaDataSource
 * 接口的默认实现类是DefaultFilterInvocationSecurityMetadataSource，参考
 * DefaultFilterInvocationSecurityMetadataSource的实现，可以定义自己的
 * FilterInvocationSecurityMetadataSource。
 *
 * 开发者自定义 FilterInvocationSecurityMetadataSource主要实现该接口中的
 * getAttributes方法,该方法的参数是一个 FilterInvocation，开发者可以从
 * FilterInvocation中提取出当前请求的URL,返回值是 Collection<ConfigAttribute>，
 * 表示当前请求 URL 所需的角色。
 *
 * AntPathMatcher，主要用来实现 ant 风格的 URL 匹配。
 *
 * 从数据库中获取所有的资源信息，即本案例中的 menu表以及menu所对应的role,
 * 在项目环境中，可以将资源信息缓存在 Redis 或者其他缓存数据库中．
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final MenuMapper menuMapper;

    public CustomFilterInvocationSecurityMetadataSource(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> menuList = menuMapper.getAllMenus();
        //遍历过程中获取当前请求的URL所需要的角色信息并返回。如果当前请求的URL在资源表中不存在相应的模式，
        //就假设该请求登录后即可访问，即直接返回ROLE_LOGIN
        for(Menu menu : menuList){
            if(antPathMatcher.match(menu.getPattern(),requestUrl)){
                List<Role> roleList = menu.getRoles();
                String[] roleArr = new String[roleList.size()];
                for(int i = 0; i < roleArr.length; i ++){
                    roleArr[i] = roleList.get(i).getName();
                }
                return SecurityConfig.createList(roleArr);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * support方法返回类对象是否支持校验
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
