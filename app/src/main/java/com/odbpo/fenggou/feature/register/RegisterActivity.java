package com.odbpo.fenggou.feature.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BackActivity;
import com.odbpo.fenggou.base.BaseActivity;
import com.odbpo.fenggou.databinding.ActRegisterBinding;
import com.odbpo.fenggou.di.components.DaggerRegisterComponent;
import com.odbpo.fenggou.di.components.RegisterComponent;
import com.odbpo.fenggou.di.modules.RegisterModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_register)
public final class RegisterActivity extends BackActivity<RegisterViewModel, ActRegisterBinding> {

    RegisterComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RegisterActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerRegisterComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .registerModule(new RegisterModule())
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
