package org.sang.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @Secured("ROLE_ADMIN")：表示访问方法需要ADMIN角色，角色前需要加“ROLE_"前缀
 * @PreAuthorize: 此注解在方法执行前执行
 * @PostAuthorize: 此注解在方法执行后执行
 */
@Service
public class MethodService {
    @Secured("ROLE_ADMIN")
    public String admin(){
        return "hello admin";
    }

    @PreAuthorize("hasRole('ADMIN') and hasRole('DBA')")
    public String dba(){
        return "hello dba";
    }

    @PreAuthorize("hasAnyRole('ADMIN','DBA','USER')")
    public String user(){
        return "user";
    }
}
