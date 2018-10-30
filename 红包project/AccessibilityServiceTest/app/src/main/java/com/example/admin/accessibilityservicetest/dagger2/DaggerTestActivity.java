package com.example.admin.accessibilityservicetest.dagger2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.admin.accessibilityservicetest.R;

import javax.inject.Inject;

/**
 *    dagger接入：
 *
 *    添加依赖：
 *          implementation 'com.google.dagger:dagger:2.16'
 *          annotationProcessor 'com.google.dagger:dagger-compiler:2.16'
 *
 *    android.defaultConfig下添加：
 *
 *              javaCompileOptions {
 *                  annotationProcessorOptions {
 *                  includeCompileClasspath true
 *              }
 *
 *
 *
 * ----------------------------------------------------------------------------------------------------------------------------------------
 *
 *
 *
 * 1. 简单的dagger使用用例：
 *      创建Animal类，Host类。  Animal类持有Host类实例引用。
 *
 *      DaggerTestActivity 中输出Animal某个实例的相关属性，Animal animal添加注解@Inject，表明该变量可以被依赖注入，Animal、Host对应构造函数添加注解@Inject，
 *      创建TestComponent接口，提供inject()，在需要注入的类中调用。
 *
 *      编译项目，在build/generated/source/apt/文件夹下生成相关文件。
 *
 * 2. 使用module提供实例：
 *
 *      创建TestModule。添加Host provideHost()，使用@Provides注解，表示提供host实例，此时去掉Host构造方法的注解@Inject，
 *      TestComponent接口对应注解修改为 @Component(modules = TestModule.class)，此时host实例由TestModule提供。
 *
 *      在提供依赖对象这一层面上，@Provides级别高于@Inject。
 *
 */
public class DaggerTestActivity extends AppCompatActivity {


    @Inject
    public Animal animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test);

        DaggerTestComponent.create().inject(this);

        Toast.makeText(this, animal.getAge() + "," + animal.getHost().getmName(), Toast.LENGTH_SHORT).show();

    }
}
