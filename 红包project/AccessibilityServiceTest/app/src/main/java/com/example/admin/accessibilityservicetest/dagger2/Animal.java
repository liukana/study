package com.example.admin.accessibilityservicetest.dagger2;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by liukan on 2018/7/24.
 */
public class Animal {

    private int age;

    private Host host;

    @Inject
    public Animal(@Named("new_name") Host host) {
        this.host = host;
        this.age = 10;
    }

    public int getAge() {
        return age;
    }

    public Host getHost() {
        return host;
    }
}
