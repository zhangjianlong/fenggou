package com.odbpo.fenggou.feature.main.shopping;


import android.support.v4.app.FragmentManager;

import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.FrgShoppingBinding;
import com.odbpo.fenggou.feature.Searchable.unloginCart.UnlonincartFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

@PerActivity
public class ShoppingViewModel extends BFViewModel<FrgShoppingBinding> {

    @Inject
    public ShoppingViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
        addFragment(R.id.fl_content, UnlonincartFragment.instance());

    }
}