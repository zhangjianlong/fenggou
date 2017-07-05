package com.odbpo.fenggou.feature.Searchable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BackActivity;
import com.odbpo.fenggou.base.BaseActivity;
import com.odbpo.fenggou.databinding.ActSearchableBinding;
import com.odbpo.fenggou.di.components.DaggerSearchableComponent;
import com.odbpo.fenggou.di.components.SearchableComponent;
import com.odbpo.fenggou.di.modules.SearchableModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_searchable)
public final class SearchableActivity extends BackActivity<SearchableViewModel, ActSearchableBinding> {

    SearchableComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SearchableActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerSearchableComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .searchableModule(new SearchableModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
    }

    @Override
    protected Toolbar setToolBar() {
        return binding.toolbar;
    }
}
