package com.slash.youth.v2.feature.userinfo;


import android.support.design.widget.AppBarLayout;

import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.R;
import com.slash.youth.databinding.ActUserinfoBinding;
import com.slash.youth.v2.feature.userinfo.tab.UserInfoTabFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import static com.slash.youth.R.id.appBarLayout;

@PerActivity
public class UserInfoViewModel extends BAViewModel<ActUserinfoBinding> {

    public String title = "个人信息";

    @Inject
    public UserInfoViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
        addFragment(R.id.fl_container, UserInfoTabFragment.instance());

        binding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                binding.swipeRefreshLayout.setEnabled(verticalOffset == 0);
            }
        });
    }
}