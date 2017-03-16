package com.slash.youth.v2.feature.main.task.list;


import com.core.op.bindingadapter.common.BaseItemViewSelector;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.bindingadapter.common.ItemViewSelector;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.PreferenceUtil;
import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.domain.interactor.main.TaskListUseCase;
import com.slash.youth.engine.MsgManager;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.v2.base.list.BaseListViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

import static android.R.attr.data;
import static com.slash.youth.ui.activity.CityLocationActivity.map;
import static com.slash.youth.v2.feature.main.task.TaskViewModel.SHOW_NODATA;
import static com.slash.youth.v2.util.MessageKey.SHOW_NAVIGATION;
import static com.slash.youth.v2.util.MessageKey.TASK_CHANGE;
import static com.slash.youth.v2.util.MessageKey.TASK_REFRESH;

@PerActivity
public class TaskListViewModel extends BaseListViewModel<TaskListItemViewModel> {

    public static final String TASK_STUTUS = "TASK_STUTUS";
    public static final String TASK_ONWAY = "TASK_ONWAY";

    public static final String TASK_HISTORY = "TASK_HISTORY";

    TaskListUseCase useCase;

    private int type = 0;

    @Inject
    public TaskListViewModel(RxAppCompatActivity activity,
                             TaskListUseCase useCase) {
        super(activity);
        this.useCase = useCase;
    }

    @Override
    public void afterViews() {
        super.afterViews();
        loadData();
        Messenger.getDefault().register(this, TASK_STUTUS, String.class, status -> {
            if (status.equals(TASK_ONWAY)) {
                type = 0;
            } else {
                type = 3;
            }
            loadData();
        });

        Messenger.getDefault().register(this, TASK_REFRESH, () -> {
            loadData();
        });
    }

    @Override
    public void loadMore() {
    }

    @Override
    public void refresh() {
        loadData();
    }

    int count;

    public void loadData() {
        isRefreshing.set(true);
        useCase.setParams("{\"type\":\"" + type + "\"" +
                ",\"offset\":\"0\"" +
                ",\"limit\":\"20\"}");
        count = 0;
        useCase.execute().compose(activity.bindToLifecycle())
                .flatMap(data -> {
                    itemViewModels.clear();

                    if (data.getList() != null && data.getList().size() == 0) {
                        Messenger.getDefault().send(0, SHOW_NODATA);
                        binding.recyclerView.getAdapter().notifyDataSetChanged();
                        Messenger.getDefault().sendNoMsg(SHOW_NAVIGATION);
                        return null;
                    } else {
                        if (data.getList().size() <= 4) {
                            Messenger.getDefault().sendNoMsg(SHOW_NAVIGATION);
                        }
                        Messenger.getDefault().send(1, SHOW_NODATA);
                        itemViewModels.add(new TaskListItemViewModel(type));
                        return Observable.from(data.getList());
                    }
                })
                .subscribe(d -> {
                    count += PreferenceUtil.readLong(CommonUtils.getContext(), "TASK_" + d.tid);
                    itemViewModels.add(new TaskListItemViewModel(activity, d));
                }, error -> {
                    isRefreshing.set(false);
                }, () -> {
                    Messenger.getDefault().send(count, TASK_CHANGE);
                    binding.recyclerView.getAdapter().notifyDataSetChanged();
                    isRefreshing.set(false);
                });
    }

    @Override
    public ItemViewSelector<TaskListItemViewModel> itemView() {
        return new BaseItemViewSelector<TaskListItemViewModel>() {
            @Override
            public void select(ItemView itemView, int position, TaskListItemViewModel item) {
                if (position == 0) {
                    itemView.set(BR.viewModel, R.layout.item_main_task_title);
                } else {
                    itemView.set(BR.viewModel, R.layout.item_main_task);
                }
            }
        };
    }
}