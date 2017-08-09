package com.odbpo.fenggou.feature.history;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BackActivity;
import com.odbpo.fenggou.base.BaseActivity;
import com.odbpo.fenggou.databinding.ActHistoryBinding;
import com.odbpo.fenggou.di.components.DaggerHistoryComponent;
import com.odbpo.fenggou.di.components.HistoryComponent;
import com.odbpo.fenggou.di.modules.HistoryModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_history)
public final class HistoryActivity extends BackActivity<HistoryViewModel, ActHistoryBinding> {

    HistoryComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, HistoryActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerHistoryComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .historyModule(new HistoryModule())
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
}
