package org.sang.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@Slf4j
@Aspect
public class LogAspect {
    //*返回任意值
    @Pointcut("execution(* org.sang.service.*.*(..))")
    public void pcl(){}
    //目标方法执行之前执行
    @Before(value = "pcl()")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.warn(name+"方法开始执行...");
    }
    //目标方法执行之后执行
    @After(value = "pcl()")
    public void after(JoinPoint jp){
        String name = jp.getSignature().getName();
        log.warn(name+"方法执行结束");
    }
    //获取目标方法的返回值
    @AfterReturning(value = "pcl()",returning = "result")
    public void afterReturning(JoinPoint jp,String result){
        String name = jp.getSignature().getName();
        log.warn(name+"方法的返回值为："+result);
    }
    //目标方法发生异常时，该方法被调用
    @AfterThrowing(value = "pcl()",throwing = "e")
    public void afterThrowing(JoinPoint jp,Exception e){
        String name = jp.getSignature().getName();
        log.warn(name+"方法抛异常了，异常是："+e.getMessage());
    }
    @Around("pcl()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        return pjp.proceed();
    }
}
