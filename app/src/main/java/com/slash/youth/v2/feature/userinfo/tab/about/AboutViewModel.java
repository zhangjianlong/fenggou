package com.slash.youth.v2.feature.userinfo.tab.about;


import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.databinding.FrgAboutBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class AboutViewModel extends BFViewModel<FrgAboutBinding> {

    @Inject
    public AboutViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }

}
