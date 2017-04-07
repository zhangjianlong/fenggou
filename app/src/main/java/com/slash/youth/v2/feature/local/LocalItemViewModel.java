package com.slash.youth.v2.feature.local;

import com.core.op.lib.base.BViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by acer on 2017/4/6.
 */

public class LocalItemViewModel extends BViewModel {

    public String name;

    public LocalItemViewModel(RxAppCompatActivity activity, String name) {
        super(activity);
        this.name = name;
    }
}
