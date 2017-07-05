package com.odbpo.fenggou.feature.Searchable.loginCart;


import android.support.v4.app.FragmentManager;

import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.databinding.FrgLogincartBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

@PerActivity
public class LogincartViewModel extends BFViewModel<FrgLogincartBinding> {

    @Inject
    public LogincartViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}