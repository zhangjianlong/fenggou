package com.odbpo.fenggou.base;

import android.databinding.ViewDataBinding;

import com.core.op.lib.base.BDViewModel;
import com.core.op.lib.base.BDialog;
import com.core.op.lib.weight.picker.view.DialogBuilder;
import com.odbpo.fenggou.BR;

/**
 * @author: zjl
 * @Time:  2017/6/1 15:12
 * @Desc:
 */

public class BaseDialog<V extends BDViewModel, T extends ViewDataBinding> extends BDialog<V, T> {

    public BaseDialog(DialogBuilder builder, V viewModel) {
        super(builder, viewModel);
    }

    @Override
    protected void bindViewModel() {
        binding.setVariable(BR.viewModel, viewModel);
    }
}
