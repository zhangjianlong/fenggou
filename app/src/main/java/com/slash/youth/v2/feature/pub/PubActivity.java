package com.slash.youth.v2.feature.pub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;
import com.slash.youth.R;
import com.slash.youth.databinding.ActPubBinding;
import com.slash.youth.v2.base.BackActivity;
import com.slash.youth.v2.base.BaseActivity;
import com.slash.youth.v2.di.components.DaggerPubComponent;
import com.slash.youth.v2.di.components.PubComponent;
import com.slash.youth.v2.di.modules.PubModule;

@RootView(R.layout.act_pub)
public final class PubActivity extends BackActivity<PubViewModel, ActPubBinding> {

    PubComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PubActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerPubComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .pubModule(new PubModule())
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
