package com.odbpo.fenggou.feature.main;


import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.core.op.bindingadapter.bottomnavigation.NavigationRes;
import com.core.op.bindingadapter.bottomnavigation.ViewBindingAdapter;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.ActMainBinding;
import com.odbpo.fenggou.feature.main.category.CategoryFragment;
import com.odbpo.fenggou.feature.main.mine.MineFragment;
import com.odbpo.fenggou.feature.main.product.ProductFragment;
import com.odbpo.fenggou.feature.main.shopping.ShoppingFragment;
import com.core.op.lib.utils.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class MainViewModel extends BAViewModel<ActMainBinding> {
    public FragmentManager fragmentManager;
    public final NavigationRes navigation = NavigationRes.of(R.array.tab_colors, R.menu.bottom_navigation_main).setAccent(R.color.app_theme_colorPrimary)
            .setDefaultBackground(R.color.app_theme_background);

    public final ObservableField<Integer> selectPosition = new ObservableField<>(0);
    public final List<Fragment> fragments = new ArrayList<>();
    public final ObservableField<Integer> pageLimit = new ObservableField<>(4);

    @Inject
    public MainViewModel(RxAppCompatActivity activity) {
        super(activity);
        fragmentManager = activity.getSupportFragmentManager();
        fragments.add(new ProductFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new MineFragment());
    }

    @Override
    public void afterViews() {
        Messenger.getDefault().register(this, MessageKey.GOLOGIN, () -> {
            binding.bottomNavigation.clickView(3);

        });

        Messenger.getDefault().register(this, MessageKey.GO_PRODUCT, () -> {
            binding.bottomNavigation.clickView(0);

        });

    }


    public final ReplyCommand<ViewBindingAdapter.NavigationDataWrapper> selectedCommand = new ReplyCommand<>(p -> {
        selectPosition.set(p.position);

    });


}