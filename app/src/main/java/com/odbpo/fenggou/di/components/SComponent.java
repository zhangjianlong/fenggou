package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.SModule;
import com.odbpo.fenggou.feature.splash.SActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, SModule.class})
public interface SComponent extends ActivityComponent {
    void inject(SActivity activity);
}