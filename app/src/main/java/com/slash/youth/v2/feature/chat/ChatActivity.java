package com.slash.youth.v2.feature.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.core.op.lib.di.HasComponent;
import com.slash.youth.R;
import com.slash.youth.v2.base.BaseActivity;
import com.slash.youth.databinding.ActChatBinding;
import com.slash.youth.v2.di.components.DaggerChatComponent;
import com.slash.youth.v2.di.components.ChatComponent;
import com.slash.youth.v2.di.modules.ChatModule;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

@RootView(R.layout.act_chat)
public final class ChatActivity extends BaseActivity<ChatViewModel, ActChatBinding> implements HasComponent<ChatComponent> {

    ChatComponent component;

    public final static void instance(Context context) {
        instance(context, null);
    }

    public final static void instance(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ChatActivity.class);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        context.startActivity(intent);
    }

    @BeforeViews
    void beforViews() {
        component = DaggerChatComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .chatModule(new ChatModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
    }

    @Override
    public ChatComponent getComponent() {
        return component;
    }
}
