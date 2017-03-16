package com.slash.youth.v2.feature.main;


import android.content.Intent;
import android.databinding.ObservableField;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.core.op.bindingadapter.bottomnavigation.NavigationRes;
import com.core.op.bindingadapter.bottomnavigation.ViewBindingAdapter;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.slash.youth.R;
import com.slash.youth.databinding.ActMainBinding;
import com.slash.youth.engine.MsgManager;
import com.slash.youth.ui.activity.PublishDemandBaseInfoActivity;
import com.slash.youth.ui.activity.PublishServiceBaseInfoActivity;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.v2.feature.main.find.FindFragment;
import com.slash.youth.v2.feature.main.mine.MineFragment;
import com.slash.youth.v2.feature.main.task.TaskFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;


import rx.Observable;

import static android.R.attr.data;
import static com.slash.youth.v2.feature.main.mine.MineViewModel.START_ANIMATION;
import static com.slash.youth.v2.util.MessageKey.SHOW_MAIN_PUG;
import static com.slash.youth.v2.util.MessageKey.SHOW_NAVIGATION;
import static com.slash.youth.v2.util.MessageKey.TASK_CHANGE;
import static com.umeng.socialize.Config.dialog;

@PerActivity
public class MainViewModel extends BAViewModel<ActMainBinding> {
    public static final String CHANG_POSITION = "CHANG_POSITION";

    public FragmentManager fragmentManager;

    public final ObservableField<Integer> selectPosition = new ObservableField<>(0);

    public final ObservableField<Integer> pageLimit = new ObservableField<>(3);

    public final ObservableField<Integer> pubVisible = new ObservableField<>(View.GONE);

    public final List<Fragment> fragments = new ArrayList<>();

    Handler handler = new Handler();
    public final NavigationRes navigation = NavigationRes.of(R.array.tab_colors, R.menu.bottom_navigation_main).setAccent(R.color.app_theme_colorPrimary)
            .setDefaultBackground(R.color.app_theme_background);

    public ReplyCommand cancel = new ReplyCommand(() -> {

        pubVisible.set(View.GONE);
//        binding.slDemand.startBounceAnim(-600, 0, 1000);
////        Observable.timer(300, TimeUnit.MILLISECONDS).subscribe(data -> {
////        });
////        Observable.timer(1300, TimeUnit.MILLISECONDS).subscribe(data -> {
////
////        });
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                binding.slServer.startBounceAnim(-600, 0, 1000);
//            }
//        }, 100);
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            }
//        }, 1100);
    });

    public ReplyCommand pubDemandClick = new ReplyCommand(() -> {
        Intent intentPublishDemandBaseInfoActivity = new Intent(CommonUtils.getContext(), PublishDemandBaseInfoActivity.class);
        activity.startActivity(intentPublishDemandBaseInfoActivity);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                pubVisible.set(View.GONE);
            }
        }, 500);
    });

    public ReplyCommand pubServerClick = new ReplyCommand(() -> {
        Intent intentPublishServiceBaseInfoActivity = new Intent(CommonUtils.getContext(), PublishServiceBaseInfoActivity.class);
        activity.startActivity(intentPublishServiceBaseInfoActivity);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pubVisible.set(View.GONE);
            }
        }, 500);
    });

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
        Messenger.getDefault().register(this, SHOW_NAVIGATION, () -> {
            if (binding.bottomNavigation.isHidden())
                binding.bottomNavigation.restoreBottomNavigation(true);
        });

        Messenger.getDefault().register(this, TASK_CHANGE, Integer.class, d -> {
            binding.bottomNavigation.setNotification("" + (d == 0 ? "" : d),
                    1);
        });

        Messenger.getDefault().register(this, SHOW_MAIN_PUG, () -> {
            pubVisible.set(View.VISIBLE);
            binding.slDemand.startBounceAnim(0, -600, 500);

            binding.slServer.startBounceAnim(0, -600, 500);
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    binding.slServer.startBounceAnim(0, -600, 500);
//                }
//            }, 100);
//            Observable.timer(300, TimeUnit.MILLISECONDS).subscribe(data -> {
//
//            });
        });

    }


    private int getMessageCount() {
        int count = 0;
        if (MsgManager.everyTaskMessageCount != null && MsgManager.everyTaskMessageCount.keySet() != null)
            for (Long key : MsgManager.everyTaskMessageCount.keySet()) {
                count += MsgManager.everyTaskMessageCount.get(key);
            }
        return count;
    }
}