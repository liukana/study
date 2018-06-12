package com.example.admin.accessibilityservicetest.aspectj;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by liukan on 2018/3/20.
 */

@Aspect
public class AddLogAspectJ {

    @Pointcut("execution(@com.example.admin.accessibilityservicetest.aspectj.AddLog * *(..))")
    public void executionAddLog(){

    }

    @Around("executionAddLog()")
    public Object AddLogExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("lk","方法开始");
        Object object = joinPoint.proceed();
        Log.e("lk","方法结束");
        return object;
    }
}
