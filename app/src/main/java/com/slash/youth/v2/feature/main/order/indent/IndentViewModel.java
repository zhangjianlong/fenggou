package com.slash.youth.v2.feature.main.order.indent;


import android.support.v4.app.FragmentManager;

import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.databinding.FrgIndentBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

@PerActivity
public class IndentViewModel extends BFViewModel<FrgIndentBinding> {

    @Inject
    public IndentViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}