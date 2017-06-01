package com.core.op.bindingadapter.recyclerview;

import android.support.annotation.NonNull;

import com.core.op.bindingadapter.common.BindingRecyclerViewAdapter;
import com.core.op.bindingadapter.common.ItemViewArg;


/**
 * @author: zjl
 * @Time:  2017/6/1 15:17
 * @Desc:
 */

public class MultiRecyclerViewAdapter<T> extends BindingRecyclerViewAdapter {

    public MultiRecyclerViewAdapter(@NonNull ItemViewArg arg) {
        super(arg);
    }
}
