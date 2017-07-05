package com.odbpo.fenggou.feature.Searchable;


import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.databinding.ActSearchableBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class SearchableViewModel extends BAViewModel<ActSearchableBinding> {


    @Inject
    public SearchableViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}