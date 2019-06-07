package com.nowcoder.aspect;

import com.sun.org.apache.xpath.internal.operations.String;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* com.nowcoder.controller.*.*(..))")
    public void beforeMethod(){
        logger.info("before method " + new Date());

    }
    @Before("execution(* com.nowcoder.controller.*.*(..))")
    public void beforeMethodJP(JoinPoint joinPoint){
        StringBuilder sb = new StringBuilder();
        for (Object arg : joinPoint.getArgs()){
            sb.append("arg: "+ arg.toString() + "|");
        }

        logger.info(sb.toString());
    }

    @After("execution(* com.nowcoder.controller.*.*(..))")
    public void afterMethod(){
        logger.info("after method " + new Date());
    }
}
