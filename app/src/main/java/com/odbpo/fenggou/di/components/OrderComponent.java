package com.odbpo.fenggou.di.components;

import dagger.Component;


import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.di.modules.OrderModule;
import com.odbpo.fenggou.feature.order.OrderActivity;
import com.core.op.lib.di.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class, OrderModule.class})
public interface OrderComponent extends ActivityComponent {
    void inject(OrderActivity activity);
}