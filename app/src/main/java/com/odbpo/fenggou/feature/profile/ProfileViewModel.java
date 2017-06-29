package com.odbpo.fenggou.feature.profile;


import android.databinding.ObservableField;

import com.core.op.Static;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.ActProfileBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class ProfileViewModel extends BAViewModel<ActProfileBinding> {


    @Inject
    public ProfileViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }

    public final ObservableField<String> title = new ObservableField<>("dsaads");
    public final ObservableField<String> toolTitle = new ObservableField<>(Static.CONTEXT.getString(R.string.app_profile_title));
    public final ObservableField<Boolean> show = new ObservableField<>(false);
    public final ObservableField<Boolean> show1 = new ObservableField<>(true);
}