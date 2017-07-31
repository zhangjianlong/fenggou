package com.odbpo.fenggou.feature.username;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BackActivity;
import com.odbpo.fenggou.base.BaseActivity;
import com.odbpo.fenggou.databinding.ActUsernameBinding;
import com.odbpo.fenggou.di.components.DaggerUsernameComponent;
import com.odbpo.fenggou.di.components.UsernameComponent;
import com.odbpo.fenggou.di.modules.UsernameModule;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

@RootView(R.layout.act_username)
public final class UsernameActivity extends BackActivity<UsernameViewModel, ActUsernameBinding> {

    UsernameComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, UsernameActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerUsernameComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .usernameModule(new UsernameModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
    }

    @Override
    protected Toolbar setToolBar() {
        binding.toolbar.toolbar.setTitle("");
        return binding.toolbar.toolbar;
    }
}
