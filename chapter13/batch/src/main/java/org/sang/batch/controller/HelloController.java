package org.sang.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr.Du
 * @date 2020/10/17 11:42
 */
@RestController
public class HelloController {
    private JobLauncher jobLauncher;
    private Job job;

    public HelloController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    /**
     * 通过JobLauncher中的run方法启动一个批处理
     */
    @GetMapping("/hello")
    public void hello(){
        try {
            jobLauncher.run(job,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
