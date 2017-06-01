package com.odbpo.fenggou.base;

import android.databinding.ViewDataBinding;

import com.core.op.lib.base.BDViewModel;
import com.core.op.lib.base.BDialog;
import com.core.op.lib.weight.picker.view.DialogBuilder;
import com.odbpo.fenggou.BR;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/12/21
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
