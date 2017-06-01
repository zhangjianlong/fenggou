package com.odbpo.fenggou.base.list;

import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.utils.inject.RootView;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseFragment;
import com.odbpo.fenggou.databinding.FrgBaselistBinding;

@RootView(R.layout.frg_baselist)
public abstract class BaseListFragment<V extends BFViewModel> extends BaseFragment<V, FrgBaselistBinding> {

}
