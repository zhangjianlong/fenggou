package com.slash.youth.v2.feature.pub;


import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.databinding.ActPubBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class PubViewModel extends BAViewModel<ActPubBinding> {


    @Inject
    public PubViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}