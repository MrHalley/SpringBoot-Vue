package org.sang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
@Order(1)
public class MyApplicationRunner2 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<String> nonOptionArgs = args.getNonOptionArgs();
        log.warn("2-nonOptionArgs >>>"+nonOptionArgs);
        Set<String> optionNames = args.getOptionNames();
        for(String optionName : optionNames){
            System.out.println("2-key:"+optionName+";value:"+args.getOptionValues(optionName));
        }
    }
}
