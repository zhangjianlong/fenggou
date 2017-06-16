package com.odbpo.fenggou.feature.main.product;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseFragment;
import com.odbpo.fenggou.databinding.FrgProductBinding;
import com.odbpo.fenggou.di.components.MainComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.frg_product)
public final class ProductFragment extends BaseFragment<ProductViewModel, FrgProductBinding> {

    public static ProductFragment instance() {
        return new ProductFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(MainComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
