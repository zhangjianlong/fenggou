package com.odbpo.fenggou.base.tab;

import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.utils.inject.RootView;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseFragment;
import com.odbpo.fenggou.databinding.FrgTabBinding;

@RootView(R.layout.frg_tab)
public abstract class TabFragment<V extends BFViewModel> extends BaseFragment<V, FrgTabBinding> {

}
