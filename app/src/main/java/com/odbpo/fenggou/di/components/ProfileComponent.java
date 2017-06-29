package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.ProfileModule;
import com.odbpo.fenggou.feature.profile.ProfileActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, ProfileModule.class})
public interface ProfileComponent extends ActivityComponent {
    void inject(ProfileActivity activity);
}