package com.example.admin.accessibilityservicetest.designModel;

/**
 * Created by liukan on 2018/3/29.
 */

public class SingletonMode {

    private static volatile SingletonMode singletonMode;

    private SingletonMode(){}

    public static SingletonMode getInstance(){
        if(singletonMode == null){
            synchronized (SingletonMode.class){
                if(singletonMode == null){
                    singletonMode = new SingletonMode();
                }
            }
        }
        return singletonMode;
    }
}
