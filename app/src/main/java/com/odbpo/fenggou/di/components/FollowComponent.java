package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.FollowModule;
import com.odbpo.fenggou.feature.follow.FollowActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, FollowModule.class})
public interface FollowComponent extends ActivityComponent {
    void inject(FollowActivity activity);
}