package com.core.op.bindingadapter.sortView;

import android.app.Activity;
import android.databinding.BindingAdapter;

import com.core.op.bindingadapter.bottomnavigation.NavigationRes;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.weight.navigation.AHBottomNavigation;
import com.core.op.lib.weight.navigation.AHBottomNavigationAdapter;
import com.core.op.lib.weight.sortView.SortRes;
import com.core.op.lib.weight.sortView.SortView;

import java.util.List;


public final class ViewBindingAdapter {


    /**
     * 底部导航菜单控件初始化
     */
    @BindingAdapter(value = {"items"}, requireAll = false)
    public static void sortViewItems(final SortView sortTextView, final List<SortRes> sortRes) {

        if (sortRes == null || sortRes.size() == 0) {
            return;
        }

        sortTextView.setRes(sortRes);

    }

}

