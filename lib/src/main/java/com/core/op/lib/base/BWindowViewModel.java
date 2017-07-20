package com.core.op.lib.base;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author: zjl
 * @Time: 2017/7/19 18:46
 * @Desc:
 */
public class BWindowViewModel<T> extends BViewModel<T> {
    protected BWindow bWindow;

    public BWindowViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    public void setWindow(BWindow bWindow) {
        this.bWindow = bWindow;
    }

}
