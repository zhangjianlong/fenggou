package com.slash.youth.v2.feature.dialog.common;


import com.core.op.lib.base.BDViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.databinding.DlgCommonBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class CommonViewModel extends BDViewModel<DlgCommonBinding> {

    @Inject
    public CommonViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    public void initValue(String title, String content) {
    }
}