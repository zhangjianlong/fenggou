package com.slash.youth.v2.feature.refund;


import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.databinding.ActRefundBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class RefundViewModel extends BAViewModel<ActRefundBinding> {


    @Inject
    public RefundViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}