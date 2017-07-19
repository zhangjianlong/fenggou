package com.odbpo.fenggou.feature.message;


import android.databinding.ObservableField;

import com.core.op.Static;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.ActMessageBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class MessageViewModel extends BAViewModel<ActMessageBinding> {


    @Inject
    public MessageViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }

    public ObservableField<String> toolTitle = new ObservableField<>(Static.CONTEXT.getString(R.string.app_message_title));
}