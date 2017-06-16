package com.odbpo.fenggou.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.core.op.lib.di.HasComponent;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseActivity;
import com.odbpo.fenggou.databinding.ActMainBinding;
import com.odbpo.fenggou.di.components.DaggerMainComponent;
import com.odbpo.fenggou.di.components.MainComponent;
import com.odbpo.fenggou.di.modules.MainModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_main)
public final class MainActivity extends BaseActivity<MainViewModel, ActMainBinding> implements HasComponent<MainComponent> {

    MainComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerMainComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .mainModule(new MainModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
    }

    @Override
    public MainComponent getComponent() {
        return component;
    }
}
