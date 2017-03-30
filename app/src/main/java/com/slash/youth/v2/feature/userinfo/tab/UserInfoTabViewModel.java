package com.slash.youth.v2.feature.userinfo.tab;


import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.R;
import com.slash.youth.v2.base.tab.TabViewModel;
import com.slash.youth.v2.feature.userinfo.tab.about.AboutFragment;
import com.slash.youth.v2.feature.userinfo.tab.evaluate.EvaluateFragment;
import com.slash.youth.v2.feature.userinfo.tab.task.UserTaskFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.Arrays;

import javax.inject.Inject;

@PerActivity
public class UserInfoTabViewModel extends TabViewModel {

    @Inject
    public UserInfoTabViewModel(RxAppCompatActivity activity) {
        super(activity);
        pageLimit.set(3);
        titles.addAll(Arrays.asList(activity.getResources().getStringArray(R.array.userinfo_tabs)));
        fragments.add(AboutFragment.instance());
        fragments.add(EvaluateFragment.instance());
        fragments.add(UserTaskFragment.instance());
    }

    @Override
    public void afterViews() {

    }
}