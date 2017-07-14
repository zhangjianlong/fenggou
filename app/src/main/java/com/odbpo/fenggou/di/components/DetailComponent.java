package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.DetailModule;
import com.odbpo.fenggou.feature.detail.DetailActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, DetailModule.class})
public interface DetailComponent extends ActivityComponent {
    void inject(DetailActivity activity);
}