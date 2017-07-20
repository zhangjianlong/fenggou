package com.core.op.lib.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.core.op.lib.R;
import com.core.op.lib.utils.inject.InjectUtil;
import com.core.op.lib.weight.picker.view.BasePickerView;
import com.core.op.lib.weight.picker.view.DialogBuilder;
import com.core.op.lib.weight.popupWindow.BasePopupWindow;
import com.core.op.lib.weight.popupWindow.WindowBuilder;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author: zjl
 * @Time: 2017/7/19 18:46
 * @Desc:
 */
public abstract class BWindow<V extends BWindowViewModel, T extends ViewDataBinding> extends BasePopupWindow {


    public BWindow(WindowBuilder builder) {
        super(builder);
    }


    protected LayoutInflater inflater;

    protected V viewModel;

    protected T binding;

    public BWindow(WindowBuilder builder, V viewModel) {
        super(builder);
        this.viewModel = viewModel;
        binding = DataBindingUtil.
                inflate(LayoutInflater.from(context), InjectUtil.injectFrgRootView(this), (ViewGroup) ((RxAppCompatActivity) context).findViewById(android.R.id.content), false)
        ;
        viewModel.setBinding(binding);
        bindViewModel();
        InjectUtil.injectAfterView(this);
        viewModel.setWindow(this);
    }

    protected abstract void bindViewModel();

    public static WindowBuilder newWindow(Context context) {
        return new WindowBuilder(context);
    }
}
