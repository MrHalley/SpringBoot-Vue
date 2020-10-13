package org.sang.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegService {
    public int register(String username,String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encode = encoder.encode(password);
        return saveToDb(username,encode);
    }
    public int saveToDb(String username,String encodePassword){
        return 0;
    }
}
