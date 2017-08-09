package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.HistoryModule;
import com.odbpo.fenggou.feature.history.HistoryActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, HistoryModule.class})
public interface HistoryComponent extends ActivityComponent {
    void inject(HistoryActivity activity);
}