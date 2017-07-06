package com.odbpo.fenggou.feature.search;


import android.databinding.ObservableField;
import android.support.design.widget.TabLayout;

import com.core.op.Static;
import com.core.op.bindingadapter.bottomnavigation.NavigationRes;
import com.core.op.bindingadapter.bottomnavigation.ViewBindingAdapter;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.ActSearchBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import static com.odbpo.fenggou.R.id.tabLayout;

@PerActivity
public class SearchViewModel extends BAViewModel<ActSearchBinding> {
    private final static String TAG_DEFAULT = "TAG_DEFAULT";
    private final static String TAG_PRICE = "TAG_PRICE";
    private final static String TAG_SALE = "TAG_SALE";

    @Inject
    public SearchViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
        initTab();
    }

    private void initTab() {
        TabLayout.Tab tab = binding.tabLayout.newTab();
        tab.setText(Static.CONTEXT.getText(R.string.app_search_default));
        tab.setTag(TAG_DEFAULT);
        binding.tabLayout.addTab(tab);
        tab = binding.tabLayout.newTab();
        tab.setText(Static.CONTEXT.getText(R.string.app_search_price));
        tab.setTag(TAG_PRICE);
        binding.tabLayout.addTab(tab);
        tab = binding.tabLayout.newTab();
        tab.setText(Static.CONTEXT.getText(R.string.app_search_sale));
        tab.setTag(TAG_SALE);
        binding.tabLayout.addTab(tab);
    }

}