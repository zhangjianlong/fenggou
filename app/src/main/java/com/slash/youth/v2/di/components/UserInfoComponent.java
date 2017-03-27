package com.slash.youth.v2.di.components;

import dagger.Component;


import com.slash.youth.v2.di.modules.ActivityModule;
import com.slash.youth.v2.di.modules.UserInfoModule;
import com.slash.youth.v2.feature.userinfo.UserInfoActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, UserInfoModule.class})
public interface UserInfoComponent extends ActivityComponent {
    void inject(UserInfoActivity activity);
}