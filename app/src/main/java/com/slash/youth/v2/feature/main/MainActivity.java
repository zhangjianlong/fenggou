package com.slash.youth.v2.feature.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.core.op.lib.di.HasComponent;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;
import com.slash.youth.R;
import com.slash.youth.databinding.ActMainBinding;
import com.slash.youth.engine.MsgManager;
import com.slash.youth.ui.activity.ChatActivity;
import com.slash.youth.ui.activity.ChooseSkillActivity;
import com.slash.youth.ui.activity.GuidActivity;
import com.slash.youth.ui.activity.LoginActivity;
import com.slash.youth.ui.activity.MessageActivity;
import com.slash.youth.ui.activity.PerfectInfoActivity;
import com.slash.youth.ui.activity.SplashActivity;
import com.slash.youth.ui.dialog.offline.OfflineDialog;
import com.slash.youth.ui.dialog.offline.OfflineViewModel;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.LogKit;
import com.slash.youth.v2.base.BaseActivity;
import com.slash.youth.v2.di.components.DaggerMainComponent;
import com.slash.youth.v2.di.components.MainComponent;
import com.slash.youth.v2.di.modules.MainModule;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

import static com.slash.youth.engine.MsgManager.NEW_MESSAGE;
import static com.slash.youth.engine.MsgManager.OFF_LINE;

@RootView(R.layout.act_main)
public final class MainActivity extends BaseActivity<MainViewModel, ActMainBinding> implements HasComponent<MainComponent> {

    MainComponent component;

    //记录第一次点击的时间
    private long clickTime = 0;
    private OfflineDialog offlineDialog;
    View msgIconLayer;
    ImageView ivMsgIcon;
    boolean isAddMsgIconLayer = false;

    public final static void instance(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @BeforeViews
    void beforViews() {
        component = DaggerMainComponent.builder()
                .appComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .mainModule(new MainModule())
                .build();
        component.inject(this);
    }

    @AfterViews
    void afterViews() {
        Messenger.getDefault().register(this, OFF_LINE, () -> {
            offline();
        });

        Messenger.getDefault().register(this, NEW_MESSAGE, () -> {
            setIvMsgIconState();
            setMsgChangeListener();
        });
        //向没个Ativity都添加进入消息列表的icon
        msgIconLayer = View.inflate(CommonUtils.getContext(), R.layout.layer_every_msg_icon, null);
        ivMsgIcon = (ImageView) msgIconLayer.findViewById(R.id.iv_msg_icon);
        ivMsgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMessageActivity = new Intent(CommonUtils.getContext(), MessageActivity.class);
                startActivity(intentMessageActivity);
            }
        });
    }

    @Override
    public MainComponent getComponent() {
        return component;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (msgIconLayer != null) {
            if (!isAddMsgIconLayer) {
                this.addContentView(msgIconLayer, new ViewGroup.LayoutParams(-1, -1));
                isAddMsgIconLayer = true;
            }
        }

        if (ivMsgIcon != null) {
            setIvMsgIconState();
            setMsgChangeListener();
        }
    }


    /**
     * 刚进入消息页的时候，或者是回退到消息页的时候(这两种情况都会调用onStart方法)，通过融云的API获取总的未读消息数，消息Icon的颜色
     */
    private void setIvMsgIconState() {
        RongIMClient.getInstance().getUnreadCount(new RongIMClient.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                int totalUnreadCount = integer;
                LogKit.v("HomeActivity unReadCount:" + totalUnreadCount);
                if (totalUnreadCount <= 0) {//没有聊天消息，显示灰色的Icon
                    ivMsgIcon.setImageResource(R.mipmap.news_default);
                } else {//有聊天消息，显示红色的Icon
                    ivMsgIcon.setImageResource(R.mipmap.news_activation);
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        }, Conversation.ConversationType.PRIVATE);
    }

    /**
     * 注册未读消息的监听器，这样每次来新的聊天消息都能根据未读数量来更新icon颜色
     */
    private void setMsgChangeListener() {
        MsgManager.setTotalUnReadCountListener(new MsgManager.TotalUnReadCountListener() {

            @Override
            public void displayTotalUnReadCount(int count) {
                LogKit.v("HomeActivity unReadCount:" + count);
                if (count <= 0) {//没有聊天消息，显示灰色的Icon
                    ivMsgIcon.setImageResource(R.mipmap.news_default);
                } else {//有聊天消息，显示红色的Icon
                    ivMsgIcon.setImageResource(R.mipmap.news_activation);
                }
            }
        });
    }

    public void offline() {
        if (offlineDialog == null) {
            offlineDialog = new OfflineDialog(this, new OfflineViewModel(this));
        }
        if (!offlineDialog.isShowing()) {
            offlineDialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MsgManager.exit();
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }
}
