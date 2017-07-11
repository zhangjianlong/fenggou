package com.odbpo.fenggou.feature.nickname;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BackActivity;
import com.odbpo.fenggou.base.BaseActivity;
import com.odbpo.fenggou.databinding.ActNicknameBinding;
import com.odbpo.fenggou.di.components.DaggerNicknameComponent;
import com.odbpo.fenggou.di.components.NicknameComponent;
import com.odbpo.fenggou.di.modules.NicknameModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_nickname)
public final class NicknameActivity extends BackActivity<NicknameViewModel, ActNicknameBinding> {

    NicknameComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, NicknameActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerNicknameComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .nicknameModule(new NicknameModule())
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
