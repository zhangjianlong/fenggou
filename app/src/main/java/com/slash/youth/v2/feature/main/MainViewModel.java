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
import com.slash.youth.engine.MsgManager;
import com.slash.youth.v2.feature.main.find.FindFragment;
import com.slash.youth.v2.feature.main.mine.MineFragment;
import com.slash.youth.v2.feature.main.task.TaskFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


import static com.slash.youth.v2.feature.main.mine.MineViewModel.START_ANIMATION;
import static com.slash.youth.v2.util.MessageKey.TASK_CHANGE;

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

        Messenger.getDefault().register(this, TASK_CHANGE, () -> {
            binding.bottomNavigation.setNotification("" + (getMessageCount() == 0 ? "" : getMessageCount()),
                    1);
        });
//        binding.bottomNavigation.setNotification("" + (getMessageCount() == 0 ? "" : getMessageCount()),
//                1);
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