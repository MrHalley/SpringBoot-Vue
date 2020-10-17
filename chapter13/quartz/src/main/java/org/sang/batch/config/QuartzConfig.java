package org.sang.batch.config;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.SimpleTrigger;
import org.sang.batch.schedule.MySecondJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

/**
 * @author Mr.Du
 * @date 2020/10/17 8:41
 *
 * JobDetail的配置有两种方式:
 * 第一种方式通过MethodInvokingJobDetailFactoryBean类配置
 * JobDetail,只需要指定Job的实例名和要调用的方法即可，注册这种方
 * 式无法在创建JobDetail时传递参数。
 * 第二种方式是通过JobDetailFactoryBean来实现的，这种方式只需要
 * 指定JobClass即可，然后可以通过JobDataMap传递参数到Job中，Job
 * 只需要提供属性名，并且提供一个相应的set方法即可接收参数
 *
 * Trigger有多种不同的实现，这里展示了两种常用的Trigger:
 * SimpleTrigger和CronTrigger,这两种Trigger分别使用SimpleTriigerFactoryBean
 * 和CronTriggerFactoryBean进行创建。在SimpleTriggerFactoryBean对象中，
 * 首先设置JobDetail,然后设置setRepeatCount配置任务循环次数，
 * setStartDelay配置任务启动延迟时间，setRepeatInternal配置任务
 * 的时间间隔。CronTriggerFactoryBean对象中，则主要配置JobDeatil和Cron表达式
 *
 * 最后通过SchedulerFactoryBean创建SchedulerFactory,然后配置Trigger即可。
 */
@Configuration
public class QuartzConfig {
    @Bean
    MethodInvokingJobDetailFactoryBean jobDetailFactoryBean(){
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("myFirstJob");
        bean.setTargetMethod("sayHello");
        return bean;
    }
    @Bean
    JobDetailFactoryBean jobDetail2(){
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MySecondJob.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name","sang");
        bean.setJobDataMap(jobDataMap);
        bean.setDurability(true);
        return bean;
    }
    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        bean.setRepeatCount(3);
        bean.setStartDelay(1000);
        bean.setRepeatInterval(2000);
        return bean;
    }
    @Bean
    CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetail2().getObject());
        bean.setCronExpression("* * * * * ?");
        return bean;
    }
    @Bean
    SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        SimpleTrigger simpleTrigger = simpleTriggerFactoryBean().getObject();
        CronTrigger cronTrigger = cronTriggerFactoryBean().getObject();
        schedulerFactoryBean.setTriggers(simpleTrigger,cronTrigger);
        return schedulerFactoryBean;
    }
}
