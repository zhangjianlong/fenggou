package com.core.op.bindingadapter.tablayout;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.design.widget.TabLayout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.EditText;

import com.core.op.bindingadapter.common.ItemViewArg;
import com.core.op.lib.rxjava.Transformers;

import java.util.List;

import rx.Observable;


/**
 * Created by kelin on 16-3-24.
 */
public final class ViewBindingAdapter {
    // TabLayout
    @BindingAdapter(value = {"itemView", "items"}, requireAll = false)
    public static <T> void setAdapter(TabLayout tabLayout, ItemViewArg<T> arg, List<T> items) {
        if (arg == null || tabLayout.getTabCount() == 0) {
            return;
//            throw new IllegalArgumentException("itemView must not be null");
        }

        Log.i("ytp ", "tabLayout");
//        Observable.from(items)
//                .compose(Transformers.mapWithIndex())
//                .subscribe(data->{
//                    ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(tabLayout.getContext()),arg.layoutRes(),tabLayout,false);
//                    viewDataBinding.setVariable(arg.bindingVariable(),data.value());
//                    tabLayout.getTabAt((int)data.index()).setCustomView(viewDataBinding.getRoot());
//                });
    }

    @BindingAdapter(value = {"tabRes"}, requireAll = false)
    public static void setTabRes(TabLayout tabLayout, List<TabRes> tabRes) {
        if (!(tabRes != null && tabRes.size() > 0)) {
            return;
        }
        Observable.from(tabRes).subscribe(data -> {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(data.getTextRes());
            tab.setTag(data.getTag());
            tabLayout.addTab(tab);
        });

    }


}

