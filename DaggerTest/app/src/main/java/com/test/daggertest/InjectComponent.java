package com.test.daggertest;

import dagger.Component;

@Component
public interface InjectComponent {

    void inject(MainActivity activity);
}
