package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.ForgetModule;
import com.odbpo.fenggou.feature.forget.ForgetActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, ForgetModule.class})
public interface ForgetComponent extends ActivityComponent {
    void inject(ForgetActivity activity);
}