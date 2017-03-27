package com.slash.youth.v2.feature.userinfo;

import android.content.Context;
import android.content.Intent;

import com.slash.youth.R;
import com.slash.youth.v2.base.BaseActivity;
import com.slash.youth.databinding.ActUserinfoBinding;
import com.slash.youth.v2.di.components.DaggerUserInfoComponent;
import com.slash.youth.v2.di.components.UserInfoComponent;
import com.slash.youth.v2.di.modules.UserInfoModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_userinfo)
public final class UserInfoActivity extends BaseActivity<UserInfoViewModel, ActUserinfoBinding> {

    UserInfoComponent component;

    public final static void instance(Context context) {
        context.startActivity(new Intent(context, UserInfoActivity.class));
    }

    @BeforeViews
    void beforViews() {
        component = DaggerUserInfoComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .userInfoModule(new UserInfoModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
