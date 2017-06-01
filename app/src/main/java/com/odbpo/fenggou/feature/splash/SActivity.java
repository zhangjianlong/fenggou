package com.odbpo.fenggou.feature.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseActivity;
import com.odbpo.fenggou.databinding.ActSBinding;
import com.odbpo.fenggou.di.components.DaggerSComponent;
import com.odbpo.fenggou.di.components.SComponent;
import com.odbpo.fenggou.di.modules.SModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_s)
public final class SActivity extends BaseActivity<SViewModel, ActSBinding> {

    SComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerSComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .sModule(new SModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
