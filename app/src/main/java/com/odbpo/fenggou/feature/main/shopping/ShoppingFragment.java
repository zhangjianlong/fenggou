package com.odbpo.fenggou.feature.main.shopping;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseFragment;
import com.odbpo.fenggou.databinding.FrgShoppingBinding;
import com.odbpo.fenggou.di.components.MainComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.frg_shopping)
public final class ShoppingFragment extends BaseFragment<ShoppingViewModel, FrgShoppingBinding> {

    public static ShoppingFragment instance() {
        return new ShoppingFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(MainComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
