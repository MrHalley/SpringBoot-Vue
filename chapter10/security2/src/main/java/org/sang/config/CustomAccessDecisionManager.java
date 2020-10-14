package org.sang.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 当一个请求走完 FilterInvocationSecurityMetadataSource中的
 * getAttributes方法后,接下来就会来到AccessDecisionManager类
 * 中进行角色信息的比对。
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    /**
     * @param authentication 包含当前登录用户的信息
     * @param object FilterInvocation对象，可以获取当前请求对象等
     * @param configAttributes FilterInvocationSecurity中的getAttributes方法的返回值，即当前请求url所需要的角色
     * @throws AccessDeniedException 当前登录用户不具备当前请求url所需要的的角色信息时，抛出此异常
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        /**
         * 进行角色信息对比，如果需要的角色是ROLE_LOGIN,说明当前请求的URL用户登录后即可访问，
         * 如果authentication是UsernamePasswordAuthenticationToken的实力，那么说明当前
         * 用户已登录，该方法到此结束，否则进入正常的判断流程，如果当前用户具备请求需要的角色，
         * 方法结束。
         */
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for(ConfigAttribute configAttribute : configAttributes){
            if("ROLE_LOGIN".equals(configAttribute.getAttribute()) && authentication instanceof UsernamePasswordAuthenticationToken)
                return;
            for(GrantedAuthority authority : authorities){
                if(configAttribute.getAttribute().equals(authority.getAuthority()))
                    return;
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
