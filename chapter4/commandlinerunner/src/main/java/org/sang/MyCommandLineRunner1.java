package org.sang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Order(1) //对实现CommandLineRunner的类的调用顺序进行排序
@Slf4j
public class MyCommandLineRunner1 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.warn("Runner1 >>> "+ Arrays.toString(args));
    }
}
