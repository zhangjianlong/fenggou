package com.core.op.lib.base;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/7/21
 */
public abstract class BDViewModel<T> extends BViewModel<T> {

    protected BDialog dialog;

    public BDViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    public void setDialog(BDialog dialog) {
        this.dialog = dialog;
    }


}
