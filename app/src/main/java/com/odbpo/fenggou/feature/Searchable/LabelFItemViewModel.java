package com.odbpo.fenggou.feature.Searchable;

import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * @author: zjl
 * @Time: 2017/7/7 9:19
 * @Desc:
 */


public class LabelFItemViewModel extends BViewModel {

    public ObservableField<String> data = new ObservableField<>();

    public int index;


    public LabelFItemViewModel(RxAppCompatActivity activity, String data, int index) {
        super(activity);
        this.data.set(data);
        this.index = index;
    }
}