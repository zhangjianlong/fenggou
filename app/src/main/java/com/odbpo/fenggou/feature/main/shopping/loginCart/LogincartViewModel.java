package com.odbpo.fenggou.feature.main.shopping.loginCart;


import android.databinding.ObservableField;

import com.core.op.Static;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.FrgLogincartBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class LogincartViewModel extends BFViewModel<FrgLogincartBinding> {

    @Inject
    public LogincartViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }


    public final ObservableField<String> title = new ObservableField<>(Static.CONTEXT.getString(R.string.app_shopping_my));
    public final ObservableField<String> salesPromotion = new ObservableField<>("共有一件促销");
}