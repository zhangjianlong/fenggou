package com.slash.youth.v2.feature.profile;


import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;

import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.R;
import com.slash.youth.databinding.ActProfileBinding;
import com.slash.youth.utils.CommonUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.Objects;

import javax.inject.Inject;

@PerActivity
public class ProfileViewModel extends BAViewModel<ActProfileBinding> {
    public final ObservableField<String> title = new ObservableField<>(CommonUtils.getContext().getString(R.string.app_profile_title));
    public final ObservableField<String> templateInput = new ObservableField<>();
    public final ObservableField<String> templateSize = new ObservableField<>(CommonUtils.getContext().getString(R.string.app_profile_template_limit));


    public TextWatcher templateWatch = new TextWatcher() {
        private CharSequence temp;

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            temp = s;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            templateSize.set(temp.length() + "/300");
        }
    };

    @Inject
    public ProfileViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}