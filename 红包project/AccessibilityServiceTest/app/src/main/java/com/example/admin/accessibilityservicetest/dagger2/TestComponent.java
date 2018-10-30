package com.example.admin.accessibilityservicetest.dagger2;


import dagger.Component;

/**
 * Created by liukan on 2018/7/24.
 */
@Component(modules = TestModule.class)
public interface TestComponent {

    void inject(DaggerTestActivity daggerTestActivity);
}
