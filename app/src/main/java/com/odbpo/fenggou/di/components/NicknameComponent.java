package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.NicknameModule;
import com.odbpo.fenggou.feature.nickname.NicknameActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, NicknameModule.class})
public interface NicknameComponent extends ActivityComponent {
    void inject(NicknameActivity activity);
}