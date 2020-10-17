package org.sang.batch.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Mr.Du
 * @date 2020/10/17 8:12
 * @Scheduled注解来标注一个定时任务，其中，
 * fixedDelay = 1000表示在当前任务执行结束1秒后开启另一个任务，
 * fixedRate = 2000 表示当前任务开始执行2秒后开启另一个定时任务，
 * initialDelay = 1000 表示首次执行的延迟时间
 * @Scheduled注解也可以使用cron表达式， cron="0****?"表示该定时任务每分钟制执行一次
 */
//@Component
@Slf4j
public class MySchedule {
    @Scheduled(fixedDelay = 1000)
    public void fixedDelay(){
        log.warn("fixedDelay:"+new Date());
    }
    @Scheduled(fixedRate = 2000)
    public void fixedRate(){
        log.warn("fixedRate:"+new Date());
    }
    @Scheduled(initialDelay = 1000,fixedRate = 2000)
    public void initialDelay(){
        log.warn("initialDelay:"+new Date());
    }
    @Scheduled(cron = "0 * * * * ?")
    public void cron(){
        log.warn("cron:"+new Date());
    }
}
