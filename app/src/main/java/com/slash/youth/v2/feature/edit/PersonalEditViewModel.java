package com.slash.youth.v2.feature.edit;


import android.databinding.ObservableField;

import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.R;
import com.slash.youth.databinding.ActPersonaleditBinding;
import com.slash.youth.utils.CommonUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class PersonalEditViewModel extends BAViewModel<ActPersonaleditBinding> {
    public final ObservableField<String> title = new ObservableField<>(CommonUtils.getContext().getString(R.string.app_personal_title));

    @Inject
    public PersonalEditViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}