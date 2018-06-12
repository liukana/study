package com.example.admin.accessibilityservicetest.aspectj;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by liukan on 2018/3/20.
 */

@Aspect
public class HookMethodAspectJ {

//    @Pointcut("execution(* com.example.admin.accessibilityservicetest.MainActivity.jumpToSettingPage(..))")
//    public void executionHookMethod() {
//
//    }
//
//    @Around("executionHookMethod()")
//    public Object HookMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
//        Log.e("lk", "static 方法测试");
//        return joinPoint.proceed();
//    }

//    @Pointcut("call(* retrofit2.Retrofit.create(..))")
//    public void executionHookLogMethod() {
//
//    }
//    @Before("executionHookLogMethod()")
//    public void HookLogMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
//        Log.e("lk", "Retrofit.create..... ");
////        return joinPoint.proceed();
//    }

    @Pointcut("call(* com.example.admin.accessibilityservicetest.MainActivity.on**(..))")
    public void executionHookLogMethod() {

    }
    @Before("executionHookLogMethod()")
    public void HookLogMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("lk", "测试");
    }
}
