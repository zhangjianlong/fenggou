package com.odbpo.fenggou.feature.forget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BackActivity;
import com.odbpo.fenggou.base.BaseActivity;
import com.odbpo.fenggou.databinding.ActForgetBinding;
import com.odbpo.fenggou.di.components.DaggerForgetComponent;
import com.odbpo.fenggou.di.components.ForgetComponent;
import com.odbpo.fenggou.di.modules.ForgetModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_forget)
public final class ForgetActivity extends BackActivity<ForgetViewModel, ActForgetBinding> {

    ForgetComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ForgetActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerForgetComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .forgetModule(new ForgetModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
    }

    @Override
    protected Toolbar setToolBar() {
        return binding.toolbar.toolbar;
    }
}
