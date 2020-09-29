package org.sang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by sang on 2018/7/6.
 */
@Slf4j
@Component
public class MyEnv implements EnvironmentAware {
    Environment environment;
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    public void printEnv() {
        log.info(environment.getProperty("JAVA_HOME"));
        log.info(environment.getProperty("CLASSPATH"));
    }
}
