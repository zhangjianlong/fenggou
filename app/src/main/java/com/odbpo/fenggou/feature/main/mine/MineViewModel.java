package com.odbpo.fenggou.feature.main.mine;


import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.FrgMineBinding;
import com.odbpo.fenggou.feature.main.info.InfoFragment;
import com.odbpo.fenggou.feature.main.login.LoginFragment;
import com.core.op.lib.utils.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class MineViewModel extends BFViewModel<FrgMineBinding> {

    @Inject
    public MineViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
        addFragment(R.id.fl_content, LoginFragment.instance());
        Messenger.getDefault().register(activity, MessageKey.LOGIN, () -> {
            replaceFragment(R.id.fl_content, InfoFragment.instance());
        });
    }


}