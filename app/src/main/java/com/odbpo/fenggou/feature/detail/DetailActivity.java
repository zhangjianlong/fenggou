package com.odbpo.fenggou.feature.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

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
import com.odbpo.fenggou.feature.register.RegisterActivity;

import java.lang.reflect.Method;

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
        binding.toolbar.toolbar.setTitle("");
        return binding.toolbar.toolbar;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_product_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more:
                RegisterActivity.instance(this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
