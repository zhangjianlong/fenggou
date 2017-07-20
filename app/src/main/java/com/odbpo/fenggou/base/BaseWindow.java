package com.odbpo.fenggou.base;

import android.databinding.ViewDataBinding;

import com.core.op.lib.base.BDViewModel;
import com.core.op.lib.base.BDialog;
import com.core.op.lib.base.BWindow;
import com.core.op.lib.base.BWindowViewModel;
import com.core.op.lib.weight.picker.view.DialogBuilder;
import com.core.op.lib.weight.popupWindow.WindowBuilder;
import com.odbpo.fenggou.BR;

/**
 * @author: zjl
 * @Time: 2017/7/19 18:52
 * @Desc:
 */
public class BaseWindow<V extends BWindowViewModel, T extends ViewDataBinding> extends BWindow<V, T> {

    public BaseWindow(WindowBuilder builder, V viewModel) {
        super(builder, viewModel);
    }

    @Override
    protected void bindViewModel() {
        binding.setVariable(BR.viewModel, viewModel);
    }

}
