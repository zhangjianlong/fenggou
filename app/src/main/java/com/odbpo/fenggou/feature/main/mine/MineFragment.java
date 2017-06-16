package com.odbpo.fenggou.feature.main.mine;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseFragment;
import com.odbpo.fenggou.databinding.FrgMineBinding;
import com.odbpo.fenggou.di.components.MainComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.frg_mine)
public final class MineFragment extends BaseFragment<MineViewModel, FrgMineBinding> {

    public static MineFragment instance() {
        return new MineFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(MainComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
