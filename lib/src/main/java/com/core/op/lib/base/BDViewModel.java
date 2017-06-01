package com.core.op.lib.base;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author: zjl
 * @Time:  2017/6/1 15:18
 * @Desc:
 */

public abstract class BDViewModel<T> extends BViewModel<T> {

    protected BDialog dialog;

    protected OnDialogLisetener onDialogLisetener;


    public BDViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    public void setDialog(BDialog dialog) {
        this.dialog = dialog;
    }

    public void setOnDialogLisetener(OnDialogLisetener onDialogLisetener) {
        this.onDialogLisetener = onDialogLisetener;
    }
}
