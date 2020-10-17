package org.sang.batch.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author Mr.Du
 * @date 2020/10/17 8:38
 * 若继承自QuartzJobBean，则需要实现executeInternal方法，
 * 该方法在任务被调用时使用。
 */
@Slf4j
public class MySecondJob extends QuartzJobBean {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.warn("hello:"+name+":"+new Date());
    }
}
