package org.sang.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserSevice {
    public String getUserById(Integer id){
        log.warn("get..."+id);
        return "user";
    }
    public void deleteUserById(Integer id){
        log.warn("delete..."+id);
        if(id == 0){
            int i = 5/0;
        }
    }
}
