package com.example.admin.accessibilityservicetest.designModel.dynamicProxyModel;

import android.util.Log;

/**
 * Created by liukan on 2018/3/29.
 */

public class Tiger implements IAnimal {
    @Override
    public void findFood() {
        System.out.println("老虎找到了食物！！");
    }
}
