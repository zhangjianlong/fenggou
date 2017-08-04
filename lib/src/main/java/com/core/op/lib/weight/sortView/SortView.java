package com.core.op.lib.weight.sortView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.core.op.Static;
import com.core.op.lib.R;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.MessageKey;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

import static android.R.attr.breadCrumbShortTitle;
import static android.R.attr.data;
import static android.R.attr.textSize;

/**
 * @author: zjl
 * @Time: 2017/7/13 9:27
 * @Desc:
 */

public class SortView extends TabLayout implements TabLayout.OnTabSelectedListener {
    private Context context;
    private int index = 0;

    private List<Tab> tabs;
    private List<SortRes> sortres = new ArrayList<>();

    public SortView(Context context) {
        this(context, null);
    }

    public SortView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SortView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        addOnTabSelectedListener(this);

    }

    private void initTab() {
        tabs = new ArrayList<>();
        getSortItems().subscribe(data -> {
            UpdateView(data);
        }, error -> {
        }, () -> {
        });

        setTabs().subscribe(data -> {
            addTab(data);
        }, error -> {

        }, () -> {

        });


    }


    public void setRes(List<SortRes> sortres) {
        this.sortres.clear();
        this.sortres.addAll(sortres);

    }

    public void upDate(List<SortRes> sortres) {
        setRes(sortres);
        initTab();
    }


    private Observable<SortRes> getSortItems() {
        return Observable.from(sortres);
    }

    private Observable<Tab> setTabs() {
        return Observable.from(tabs);
    }


    @Override
    public void onTabSelected(Tab tab) {
        ((SortItemView) tab.getCustomView()).updateView();
        Tab temp = tab;
        Observable.from(tabs).filter(new Func1<Tab, Boolean>() {
            @Override
            public Boolean call(Tab tab) {

                return !temp.equals(tab);
            }
        }).subscribe(data -> {
            ((SortItemView) data.getCustomView()).updateNullDrawable();
        }, error -> {

        }, () -> {

        });
        sendMessage(sortres.get(tab.getPosition()));

    }

    @Override
    public void onTabUnselected(Tab tab) {


    }

    @Override
    public void onTabReselected(Tab tab) {
        ((SortItemView) tab.getCustomView()).updateView();
        sendMessage(sortres.get(tab.getPosition()));

    }

    /**
     * @author: zjl
     * @Time: 2017/7/13 12:56
     * @Desc: 更新点击后的view显示
     */
    private void UpdateView(SortRes data) {
        SortItemView sortItem = new SortItemView(context);
        sortItem.init(data);
        Tab tab = newTab();
        tab.setCustomView(sortItem);
        tabs.add(tab);
    }


    private void sendMessage(SortRes data) {
        Observable.timer(500, TimeUnit.MILLISECONDS).subscribe(d -> {
            Messenger.getDefault().send(data, MessageKey.SORT);
        });


    }
}
