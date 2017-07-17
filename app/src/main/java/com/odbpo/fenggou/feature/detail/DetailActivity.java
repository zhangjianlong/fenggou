package com.odbpo.fenggou.feature.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BackActivity;
import com.odbpo.fenggou.base.BaseActivity;
import com.odbpo.fenggou.databinding.ActDetailBinding;
import com.odbpo.fenggou.di.components.DaggerDetailComponent;
import com.odbpo.fenggou.di.components.DetailComponent;
import com.odbpo.fenggou.di.modules.DetailModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_detail)
public final class DetailActivity extends BackActivity<DetailViewModel, ActDetailBinding> {

    DetailComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, DetailActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerDetailComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .detailModule(new DetailModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
    }

    @Override
    protected Toolbar setToolBar() {
        return binding.toolbar.toolbar;
    }


}
