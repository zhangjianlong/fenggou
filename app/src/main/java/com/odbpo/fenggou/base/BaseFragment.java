package com.odbpo.fenggou.base;

import android.databinding.ViewDataBinding;

import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.base.BFragment;
import com.core.op.lib.di.HasComponent;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.di.components.AppComponent;
import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.global.SlashApplication;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/8/11
 */
public class BaseFragment<V extends BFViewModel, T extends ViewDataBinding> extends BFragment<V, T> {

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    @Override
    protected void bindViewModel() {
        binding.setVariable(BR.viewModel, viewModel);
    }


    /**
     * 为独立fragment注入
     *
     * @return
     */
    protected AppComponent getApplicationComponent() {
        return ((SlashApplication) getActivity().getApplication()).getAppComponent();
    }

    /**
     * 为独立fragment注入
     *
     * @return
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule((RxAppCompatActivity) getActivity());
    }
}
