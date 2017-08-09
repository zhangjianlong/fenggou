package com.odbpo.fenggou.feature.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BackActivity;
import com.odbpo.fenggou.databinding.ActOrderBinding;
import com.odbpo.fenggou.di.components.DaggerOrderComponent;
import com.odbpo.fenggou.di.components.OrderComponent;
import com.odbpo.fenggou.di.modules.OrderModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_order)
public final class OrderActivity extends BackActivity<OrderViewModel, ActOrderBinding> {

    OrderComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, OrderActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerOrderComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .orderModule(new OrderModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
    }

    @Override
    protected Toolbar setToolBar() {
        binding.toolbar.toolbar.setTitle(" ");
        return binding.toolbar.toolbar;
    }
}
