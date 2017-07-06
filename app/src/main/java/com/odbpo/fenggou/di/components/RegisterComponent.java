package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.RegisterModule;
import com.odbpo.fenggou.feature.register.RegisterActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, RegisterModule.class})
public interface RegisterComponent extends ActivityComponent {
    void inject(RegisterActivity activity);
}