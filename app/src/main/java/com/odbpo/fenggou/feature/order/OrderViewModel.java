package com.odbpo.fenggou.feature.order;


import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.databinding.ActOrderBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class OrderViewModel extends BAViewModel<ActOrderBinding> {


    @Inject
    public OrderViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}