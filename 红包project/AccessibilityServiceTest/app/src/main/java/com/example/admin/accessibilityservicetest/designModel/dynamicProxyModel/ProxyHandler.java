package com.example.admin.accessibilityservicetest.designModel.dynamicProxyModel;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by liukan on 2018/3/29.
 */

public class ProxyHandler implements InvocationHandler {

    private Object mTarget;

    public ProxyHandler(Object target) {
        this.mTarget = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("可以拦截方法了！！");
        method.invoke(mTarget, args);
        System.out.println("方法执行结束！！");
        return null;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(mTarget.getClass().getClassLoader(), mTarget.getClass().getInterfaces(), this);
    }
}
