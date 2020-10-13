package org.sang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.sang.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootTest
class SecurityApplicationTests {

    @Autowired
    private MethodService methodService;

    /**
     * 测试BCryptPasswordEncoder加密
     */
    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

        String encode = encoder.encode("123");
        //$2a$10$beKv52zj0bqAS7aO8X4LLu0lgIx5Nt5TNS/lQzWBt8Kcw6Brnh4om
        log.warn(encode);
        log.warn(String.valueOf(encoder.matches("123","$2a$10$beKv52zj0bqAS7aO8X4LLu0lgIx5Nt5TNS/lQzWBt8Kcw6Brnh4om")));
        log.warn(String.valueOf(encoder.matches("123",encode)));
    }

    /**
     * 测试methodService 关于方法安全的配置
     */
    public void methodServiceTest(){
        methodService.admin();
    }
}
