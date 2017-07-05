package com.odbpo.fenggou.feature.Searchable.unloginCart;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseFragment;
import com.odbpo.fenggou.databinding.FrgUnlonincartBinding;
import com.odbpo.fenggou.di.components.MainComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.frg_unlonincart)
public final class UnlonincartFragment extends BaseFragment<UnlonincartViewModel, FrgUnlonincartBinding> {

    public static UnlonincartFragment instance() {
        return new UnlonincartFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(MainComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
