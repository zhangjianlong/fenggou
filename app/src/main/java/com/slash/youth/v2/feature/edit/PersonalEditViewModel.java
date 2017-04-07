package com.slash.youth.v2.feature.edit;


import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.databinding.ActPersonaleditBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class PersonalEditViewModel extends BAViewModel<ActPersonaleditBinding> {


    @Inject
    public PersonalEditViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}