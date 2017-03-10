package com.slash.youth.v2.feature.main;


import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.core.op.bindingadapter.bottomnavigation.NavigationRes;
import com.core.op.bindingadapter.bottomnavigation.ViewBindingAdapter;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.slash.youth.R;
import com.slash.youth.databinding.ActMainBinding;
import com.slash.youth.utils.LogKit;
import com.slash.youth.v2.feature.main.find.FindFragment;
import com.slash.youth.v2.feature.main.mine.MineFragment;
import com.slash.youth.v2.feature.main.task.TaskFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

import static com.slash.youth.engine.MsgManager.NEW_MESSAGE;
import static com.slash.youth.v2.feature.main.mine.MineViewModel.START_ANIMATION;
import static com.umeng.socialize.Config.dialog;

@PerActivity
public class MainViewModel extends BAViewModel<ActMainBinding> {
    public static final String CHANG_POSITION = "CHANG_POSITION";

    public FragmentManager fragmentManager;

    public final ObservableField<Integer> selectPosition = new ObservableField<>(0);

    public final ObservableField<Integer> pageLimit = new ObservableField<>(3);

    public final List<Fragment> fragments = new ArrayList<>();

    public final NavigationRes navigation = NavigationRes.of(R.array.tab_colors, R.menu.bottom_navigation_main).setAccent(R.color.app_theme_colorPrimary)
            .setDefaultBackground(R.color.app_theme_background);

    public final ReplyCommand<ViewBindingAdapter.NavigationDataWrapper> selectedCommand = new ReplyCommand<>(p -> {
        selectPosition.set(p.position);
        if (p.position == 2) {
            Messenger.getDefault().sendNoMsg(START_ANIMATION);
        }
    });

    @Inject
    public MainViewModel(RxAppCompatActivity activity) {
        super(activity);
        fragmentManager = activity.getSupportFragmentManager();
        fragments.add(new FindFragment());
        fragments.add(new TaskFragment());
        fragments.add(new MineFragment());

    }

    @Override
    public void afterViews() {
        Messenger.getDefault().register(this, CHANG_POSITION, () -> {
            selectPosition.set(0);
        });

        Messenger.getDefault().register(this, NEW_MESSAGE, () -> {
            setIvMsgIconState();
        });

        setIvMsgIconState();
    }

    /**
     * 刚进入消息页的时候，或者是回退到消息页的时候(这两种情况都会调用onStart方法)，通过融云的API获取总的未读消息数，消息Icon的颜色
     */
    private void setIvMsgIconState() {
        RongIMClient.getInstance().getUnreadCount(new RongIMClient.ResultCallback<Integer>() {
            @Override
            public void onSuccess(Integer integer) {
                int totalUnreadCount = integer;
                if (totalUnreadCount <= 0) {//没有聊天消息，显示灰色的Icon
                    binding.bottomNavigation.setNotification("", 1);
                } else {//有聊天消息，显示红色的Icon
                    binding.bottomNavigation.setNotification("" + totalUnreadCount, 1);
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        }, Conversation.ConversationType.PRIVATE);
    }
}