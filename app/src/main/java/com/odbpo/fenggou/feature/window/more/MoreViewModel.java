package com.odbpo.fenggou.feature.window.more;


import com.core.op.lib.base.BDViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.databinding.WindowMoreBinding;
import com.core.op.lib.base.BWindowViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class MoreViewModel extends BWindowViewModel<WindowMoreBinding> {

    @Inject
    public MoreViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

}