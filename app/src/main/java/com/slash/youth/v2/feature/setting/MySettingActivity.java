package com.slash.youth.v2.feature.setting;

import android.content.Context;
import android.content.Intent;

import com.slash.youth.R;
import com.slash.youth.databinding.ActMysettingBinding;
import com.slash.youth.v2.base.BaseActivity;
import com.slash.youth.databinding.ActMySettingBinding;
import com.slash.youth.v2.di.components.DaggerMySettingComponent;
import com.slash.youth.v2.di.components.MySettingComponent;
import com.slash.youth.v2.di.modules.MySettingModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_mysetting)
public final class MySettingActivity extends BaseActivity<MySettingViewModel, ActMysettingBinding> {

    MySettingComponent component;

    public final static void instance(Context context) {
        context.startActivity(new Intent(context, MySettingActivity.class));
    }

    @BeforeViews
    void beforViews() {
        component = DaggerMySettingComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .mysettingModule(new MySettingModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
