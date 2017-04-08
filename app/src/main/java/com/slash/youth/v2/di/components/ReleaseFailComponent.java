package com.slash.youth.v2.di.components;

import dagger.Component;


import com.slash.youth.v2.di.modules.ActivityModule;
import com.slash.youth.v2.di.modules.ReleaseFailModule;
import com.slash.youth.v2.feature.release.ReleaseFailActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, ReleaseFailModule.class})
public interface ReleaseFailComponent extends ActivityComponent {
    void inject(ReleaseFailActivity activity);
}