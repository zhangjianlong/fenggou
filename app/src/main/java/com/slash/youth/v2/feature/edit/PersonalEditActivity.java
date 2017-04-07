package com.slash.youth.v2.feature.edit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;

import com.slash.youth.R;
import com.slash.youth.databinding.ActPersonaleditBinding;
import com.slash.youth.v2.base.BackActivity;
import com.slash.youth.v2.base.BaseActivity;
import com.slash.youth.v2.di.components.DaggerPersonalEditComponent;
import com.slash.youth.v2.di.components.PersonalEditComponent;
import com.slash.youth.v2.di.modules.PersonalEditModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_personaledit)
public final class PersonalEditActivity extends BackActivity<PersonalEditViewModel, ActPersonaleditBinding> {

    PersonalEditComponent component;

    public final static void instance(Context context) {
        context.startActivity(new Intent(context, PersonalEditActivity.class));
    }

    @BeforeViews
    void beforViews() {
        component = DaggerPersonalEditComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .personalEditModule(new PersonalEditModule())
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
