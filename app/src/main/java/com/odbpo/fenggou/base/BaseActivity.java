package com.odbpo.fenggou.base;

import android.databinding.ViewDataBinding;

import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.base.BActivity;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.di.components.AppComponent;
import com.odbpo.fenggou.di.modules.ActivityModule;
import com.odbpo.fenggou.global.SlashApplication;


/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/8/4
 */
public class BaseActivity<V extends BAViewModel, T extends ViewDataBinding> extends BActivity<V, T> {

    @Override
    protected void initBeforeView() {
        super.initBeforeView();
        getApplicationComponent().inject(this);
    }

    @Override
    protected void bindViewModel() {
        binding.setVariable(BR.viewModel, viewModel);
    }

    protected AppComponent getApplicationComponent() {
        return ((SlashApplication) getApplication()).getAppComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
