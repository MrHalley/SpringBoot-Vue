package org.sang.batch.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Mr.Du
 * @date 2020/10/17 8:37
 */
@Slf4j
@Component
public class MyFirstJob {
    public void sayHello(){
        log.warn("MyFirstJob:sayHello:"+new Date());
    }
}
