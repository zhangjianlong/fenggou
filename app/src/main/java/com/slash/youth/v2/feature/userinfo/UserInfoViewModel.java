package com.slash.youth.v2.feature.userinfo;


import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.databinding.ActUserinfoBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class UserInfoViewModel extends BAViewModel<ActUserinfoBinding> {


    @Inject
    public UserInfoViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}