package com.slash.youth.v2.feature.userinfo.tab.task;


import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.databinding.FrgUsertaskBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class UserTaskViewModel extends BFViewModel<FrgUsertaskBinding> {

    @Inject
    public UserTaskViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}