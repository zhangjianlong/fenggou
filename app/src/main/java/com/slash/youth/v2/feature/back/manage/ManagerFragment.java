package com.slash.youth.v2.feature.back.manage;

import com.slash.youth.R;
import com.slash.youth.v2.base.BaseFragment;
import com.slash.youth.databinding.FrgManagerBinding;
import com.slash.youth.v2.di.components.SimpleBackComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.frg_manager)
public final class ManagerFragment extends BaseFragment<ManagerViewModel, FrgManagerBinding> {

    public static ManagerFragment instance() {
        return new ManagerFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(SimpleBackComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
