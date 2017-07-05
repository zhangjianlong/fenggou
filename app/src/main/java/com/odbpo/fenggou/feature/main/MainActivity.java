package com.odbpo.fenggou.feature.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.core.op.lib.di.HasComponent;
import com.core.op.lib.utils.MyStateBarUtil;
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
    //记录第一次点击的时间
    private long clickTime = 0;

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
        MyStateBarUtil.transparencyBar(this);
    }

    @Override
    public MainComponent getComponent() {
        return component;
    }


    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}
