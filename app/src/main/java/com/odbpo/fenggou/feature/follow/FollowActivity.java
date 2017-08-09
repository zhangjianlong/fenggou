package com.odbpo.fenggou.feature.follow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BackActivity;
import com.odbpo.fenggou.base.BaseActivity;
import com.odbpo.fenggou.databinding.ActFollowBinding;
import com.odbpo.fenggou.di.components.DaggerFollowComponent;
import com.odbpo.fenggou.di.components.FollowComponent;
import com.odbpo.fenggou.di.modules.FollowModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_follow)
public final class FollowActivity extends BackActivity<FollowViewModel, ActFollowBinding> {

    FollowComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, FollowActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerFollowComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .followModule(new FollowModule())
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
