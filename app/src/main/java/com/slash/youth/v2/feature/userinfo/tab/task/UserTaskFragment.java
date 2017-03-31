package com.slash.youth.v2.feature.userinfo.tab.task;

import com.slash.youth.R;
import com.slash.youth.databinding.FrgUsertaskBinding;
import com.slash.youth.v2.base.BaseFragment;
import com.slash.youth.v2.di.components.UserInfoComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.frg_usertask)
public final class UserTaskFragment extends BaseFragment<UserTaskViewModel, FrgUsertaskBinding> {

    public static UserTaskFragment instance() {
        return new UserTaskFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(UserInfoComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
