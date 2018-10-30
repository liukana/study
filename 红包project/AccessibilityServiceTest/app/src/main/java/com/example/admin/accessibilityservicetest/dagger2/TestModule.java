package com.example.admin.accessibilityservicetest.dagger2;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liukan on 2018/7/24.
 */
@Module
public class TestModule {

    @Provides
    Host provideHost(){
        return new Host("liukan");
    }

    @Provides
    @Named("new_name")
    Host provideNewHost(){
        return new Host("liukan_new");
    }

}
