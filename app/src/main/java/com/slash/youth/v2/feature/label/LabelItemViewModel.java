package com.slash.youth.v2.feature.label;

import com.core.op.lib.base.BViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by acer on 2017/4/6.
 */

public class LabelItemViewModel extends BViewModel {

    public String name;

    public LabelItemViewModel(RxAppCompatActivity activity, String name) {
        super(activity);
    }
}
