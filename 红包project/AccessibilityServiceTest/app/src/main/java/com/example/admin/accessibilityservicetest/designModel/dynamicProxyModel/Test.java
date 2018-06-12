package com.example.admin.accessibilityservicetest.designModel.dynamicProxyModel;

/**
 * Created by liukan on 2018/3/29.
 */

public class Test {



    public static void main(String[] args){
        ProxyHandler handler = new ProxyHandler(new Tiger());
        IAnimal animal = (IAnimal) handler.getProxy();
        animal.findFood();
    }
}
