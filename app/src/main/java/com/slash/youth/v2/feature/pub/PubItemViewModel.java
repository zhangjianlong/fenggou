package com.slash.youth.v2.feature.pub;

import com.core.op.lib.base.BViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by acer on 2017/4/7.
 */

public class PubItemViewModel extends BViewModel {

    public String name;

    public PubItemViewModel(RxAppCompatActivity activity, String name) {
        super(activity);
    }
}
