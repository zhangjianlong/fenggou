package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.UsernameModule;
import com.odbpo.fenggou.feature.username.UsernameActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, UsernameModule.class})
public interface UsernameComponent extends ActivityComponent {
    void inject(UsernameActivity activity);
}