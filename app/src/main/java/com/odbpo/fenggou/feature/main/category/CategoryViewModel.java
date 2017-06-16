package com.odbpo.fenggou.feature.main.category;


import android.support.v4.app.FragmentManager;

import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.databinding.FrgCategoryBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

@PerActivity
public class CategoryViewModel extends BFViewModel<FrgCategoryBinding> {

    @Inject
    public CategoryViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}