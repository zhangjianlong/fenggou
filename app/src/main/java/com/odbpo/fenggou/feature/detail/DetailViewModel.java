package com.odbpo.fenggou.feature.detail;


import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class DetailViewModel extends BAViewModel<ActDetailBinding> {


    @Inject
    public DetailViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}