package com.odbpo.fenggou.feature.window.more;

import com.core.op.lib.base.BWindow;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.WindowMoreBinding;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.RootView;
import com.odbpo.fenggou.base.BaseWindow;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@RootView(R.layout.window_more)
public final class MoreWindow extends BaseWindow<MoreViewModel, WindowMoreBinding> {

    @Inject
    public MoreWindow(RxAppCompatActivity activity, MoreViewModel viewModel) {
        super(BWindow.newWindow(activity).setContentView(R.layout.window_more), viewModel);
    }

    @AfterViews
    void afterViews() {
    }
}
