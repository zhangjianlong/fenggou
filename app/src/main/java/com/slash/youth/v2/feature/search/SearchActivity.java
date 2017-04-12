package com.slash.youth.v2.feature.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.slash.youth.R;
import com.slash.youth.v2.base.BaseActivity;
import com.slash.youth.databinding.ActSearchBinding;
import com.slash.youth.v2.di.components.DaggerSearchComponent;
import com.slash.youth.v2.di.components.SearchComponent;
import com.slash.youth.v2.di.modules.SearchModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_search)
public final class SearchActivity extends BaseActivity<SearchViewModel, ActSearchBinding> {

    SearchComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SearchActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerSearchComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .searchModule(new SearchModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
    }
}
