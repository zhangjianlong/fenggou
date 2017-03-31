package com.slash.youth.v2.feature.chat;


import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.databinding.ActChatBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class ChatViewModel extends BAViewModel<ActChatBinding> {


    @Inject
    public ChatViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}