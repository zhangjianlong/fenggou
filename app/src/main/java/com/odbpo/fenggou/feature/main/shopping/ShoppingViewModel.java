package com.odbpo.fenggou.feature.main.shopping;


import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.FrgShoppingBinding;
import com.odbpo.fenggou.feature.main.shopping.loginCart.LogincartFragment;
import com.odbpo.fenggou.feature.main.shopping.unloginCart.UnlonincartFragment;
import com.core.op.lib.utils.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

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

        Messenger.getDefault().register(activity, MessageKey.LOGIN, () -> {
            replaceFragment(R.id.fl_content, LogincartFragment.instance());
        });

    }
}