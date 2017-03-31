package com.slash.youth.v2.feature.userinfo.tab.evaluate;

import com.slash.youth.R;
import com.slash.youth.v2.base.BaseFragment;
import com.slash.youth.databinding.FrgEvaluateBinding;
import com.slash.youth.v2.di.components.UserInfoComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.frg_evaluate)
public final class EvaluateFragment extends BaseFragment<EvaluateViewModel, FrgEvaluateBinding> {

    public static EvaluateFragment instance() {
        return new EvaluateFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(UserInfoComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
