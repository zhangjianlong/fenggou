package com.odbpo.fenggou.feature.message;


import android.databinding.ObservableField;
import android.support.design.widget.TabLayout;

import com.core.op.Static;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.bindingadapter.common.ItemViewSelector;
import com.core.op.bindingadapter.tablayout.TabRes;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.list.ListViewModel;
import com.odbpo.fenggou.data.net.AbsAPICallback;
import com.odbpo.fenggou.databinding.ActMessageBinding;
import com.odbpo.fenggou.domain.bean.LoginResponse;
import com.odbpo.fenggou.domain.bean.SearchProductBean;
import com.odbpo.fenggou.domain.interactor.notification.GetNotificationUseCase;
import com.odbpo.fenggou.feature.search.SearchItemViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.util.HasExecutionScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

@PerActivity
public class MessageViewModel extends ListViewModel<MessageItemViewModel, ActMessageBinding> {
    private final static String MANAGER_TAG = "MANAGER_TAG";
    private final static String SYSTEM_TAG = "SYSTEM_TAG";
    public ObservableField<String> toolTitle = new ObservableField<>(Static.CONTEXT.getString(R.string.app_message_title));
    public List<TabRes> tabRes = new ArrayList<>();
    public final ItemViewSelector<MessageItemViewModel> itemView = itemView();
    private List<SearchProductBean.DataBean> recommendProductBeanList = new ArrayList<>();
    private GetNotificationUseCase getNotificationUseCase;
    private Map<String, String> map = new HashMap<>();


    @Inject
    public MessageViewModel(RxAppCompatActivity activity, GetNotificationUseCase getNotificationUseCase) {
        super(activity);
        this.getNotificationUseCase = getNotificationUseCase;

    }

    @Override
    public void afterViews() {
        map.put("pageSize", "1");
        initTab();
        super.afterViews();

    }

    @Override
    public void refresh() {
        isRefreshing.set(true);
        loadData(false);

    }

    @Override
    public void loadMoreData() {
        map.put("pageSize", (Integer.valueOf(map.get("pageSize")) + 1) + "");

    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public int totalSize() {
        return 0;
    }

    @Override
    public void loadData(boolean loadMore) {
        getNotificationUseCase.setFormParams(map);
        getNotificationUseCase.execute().compose(activity.bindToLifecycle()).subscribe(new AbsAPICallback<LoginResponse>() {
            @Override
            protected void onDone(LoginResponse loginResponse) {

            }

            @Override
            public void onCompleted() {
                super.onCompleted();
            }


        });

    }

    @Override
    public int setItem(ItemView itemView, int position, MessageItemViewModel item) {
        itemView.set(BR.viewModel, R.layout.item_message);
        return 1;
    }


    private void initTab() {
        tabRes.add(new TabRes(R.string.app_message_manager_notification, MANAGER_TAG));
        tabRes.add(new TabRes(R.string.app_message_system_notification, SYSTEM_TAG));
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}