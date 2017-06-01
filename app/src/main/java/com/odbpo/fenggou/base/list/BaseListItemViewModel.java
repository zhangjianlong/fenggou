package com.odbpo.fenggou.base.list;


import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public class BaseListItemViewModel<T> extends BViewModel<T> {

    public final ObservableField<Boolean> isLoadComplete = new ObservableField<>();

    public BaseListItemViewModel(RxAppCompatActivity activity, boolean isLoadComplete) {
        super(activity);
        this.isLoadComplete.set(isLoadComplete);
    }

    public BaseListItemViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    public BaseListItemViewModel() {
    }
}