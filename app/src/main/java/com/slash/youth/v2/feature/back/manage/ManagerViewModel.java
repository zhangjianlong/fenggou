package com.slash.youth.v2.feature.back.manage;


import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.databinding.FrgManagerBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class ManagerViewModel extends BFViewModel<FrgManagerBinding> {

    @Inject
    public ManagerViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}