package com.slash.youth.v2.feature.chat.list;


import android.support.v4.app.FragmentManager;

import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.databinding.FrgChatlistBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

@PerActivity
public class ChatListViewModel extends BFViewModel<FrgChatlistBinding> {

    @Inject
    public ChatListViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }
}