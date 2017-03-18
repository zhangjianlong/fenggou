package com.slash.youth.v2.feature.main.task.list;


import com.core.op.bindingadapter.common.BaseItemViewSelector;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.bindingadapter.common.ItemViewSelector;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.JsonUtil;
import com.core.op.lib.utils.PreferenceUtil;
import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.domain.bean.TaskList;
import com.slash.youth.domain.bean.base.BaseList;
import com.slash.youth.domain.interactor.UseCase;
import com.slash.youth.domain.interactor.main.TaskListUseCase;
import com.slash.youth.engine.MsgManager;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.v2.base.list.BaseListItemViewModel;
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
import static com.slash.youth.v2.util.MessageKey.TASK_POINT_REFRESH;
import static com.slash.youth.v2.util.MessageKey.TASK_REFRESH;

@PerActivity
public class TaskListViewModel extends BaseListViewModel<TaskList.TaskBean, TaskListItemViewModel> {

    public static final String TASK_STUTUS = "TASK_STUTUS";
    public static final String TASK_ONWAY = "TASK_ONWAY";

    public static final String TASK_HISTORY = "TASK_HISTORY";

    TaskListUseCase useCase;

    private int type = 0;

    int count;

    @Inject
    public TaskListViewModel(RxAppCompatActivity activity,
                             TaskListUseCase useCase) {
        super(activity);
        this.useCase = useCase;
    }

    @Override
    public void afterViews() {
        super.afterViews();
        Messenger.getDefault().register(this, TASK_STUTUS, String.class, status -> {
            if (status.equals(TASK_ONWAY)) {
                type = 0;
            } else {
                type = 3;
            }
            refresh();
        });

        Messenger.getDefault().register(this, TASK_REFRESH, () -> {
            loadData(false);
        });

        Messenger.getDefault().register(this, TASK_POINT_REFRESH, () -> {
            if (itemViewModels.size() != 0) {
                count = 0;
                Observable.from(itemViewModels).subscribe(d -> {
                    if (d.taskBean != null)
                        count += PreferenceUtil.readLong(CommonUtils.getContext(), "TASK_" + d.taskBean.tid);
                });
                Messenger.getDefault().send(count, TASK_CHANGE);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData(false);
    }

    @Override
    public UseCase<BaseList<TaskList.TaskBean>> useCase() {
        return useCase;
    }

    @Override
    public Map<String, String> prams() {
        Map<String, String> map = super.prams();
        map.put("type", type + "");
        return map;
    }

    @Override
    public void addData(TaskList.TaskBean taskBean) {
        itemViewModels.add(new TaskListItemViewModel(activity, taskBean));
    }

    @Override
    public void doComplate() {
        itemViewModels.add(new TaskListItemViewModel(activity, isComplate));
        Messenger.getDefault().send(count, TASK_CHANGE);
    }

    @Override
    protected void doListData(BaseList<TaskList.TaskBean> data, boolean isLoadMore) {
        if (!isLoadMore) {
            count = 0;
            if (data.getList() != null && data.getList().size() == 0) {
                Messenger.getDefault().send(0, SHOW_NODATA);
                binding.recyclerView.getAdapter().notifyDataSetChanged();
                Messenger.getDefault().sendNoMsg(SHOW_NAVIGATION);
            } else {
                if (data.getList().size() <= 4) {
                    Messenger.getDefault().sendNoMsg(SHOW_NAVIGATION);
                }
                Messenger.getDefault().send(1, SHOW_NODATA);
                itemViewModels.add(new TaskListItemViewModel(type));
            }
        }
    }

    @Override
    protected void doData(TaskList.TaskBean data, boolean isLoadMore) {
        count += PreferenceUtil.readLong(CommonUtils.getContext(), "TASK_" + data.tid);
    }

//    public void loadData() {
//        isRefreshing.set(true);
//
//        Map<String, String> map = new HashMap<>();
//        map.put("type", type + "");
//        map.put("offset", (offset * 10) + "");
//        map.put("limit", limit + "");
//        useCase.setParams(JsonUtil.mapToJson(map));
//        count = 0;
//        useCase.execute().compose(activity.bindToLifecycle())
//                .flatMap(data -> {
//                    itemViewModels.clear();
//                    if (data.getList() != null && data.getList().size() == 0) {
//                        Messenger.getDefault().send(0, SHOW_NODATA);
//                        binding.recyclerView.getAdapter().notifyDataSetChanged();
//                        Messenger.getDefault().sendNoMsg(SHOW_NAVIGATION);
//                        return null;
//                    } else {
//                        if (data.getList().size() <= 4) {
//                            Messenger.getDefault().sendNoMsg(SHOW_NAVIGATION);
//                        }
//                        Messenger.getDefault().send(1, SHOW_NODATA);
//                        itemViewModels.add(new TaskListItemViewModel(type));
//                        return Observable.from(data.getList());
//                    }
//                })
//                .subscribe(d -> {
//                    count += PreferenceUtil.readLong(CommonUtils.getContext(), "TASK_" + d.tid);
//                    itemViewModels.add(new TaskListItemViewModel(activity, d));
//                }, error -> {
//                    isRefreshing.set(false);
//                }, () -> {
//                    if (itemViewModels != null && itemViewModels.size() < limit) {
//                        isComplate = true;
//                    }
//                    itemViewModels.add(new TaskListItemViewModel(activity, isComplate));
//                    Messenger.getDefault().send(count, TASK_CHANGE);
//                    binding.recyclerView.getAdapter().notifyDataSetChanged();
//                    isRefreshing.set(false);
//                });
//    }
//
//    private void loadMoreData() {
//        Map<String, String> map = new HashMap<>();
//        map.put("type", type + "");
//        map.put("offset", (offset * 10) + "");
//        map.put("limit", limit + "");
//        useCase.setParams(JsonUtil.mapToJson(map));
//        useCase.execute().compose(activity.bindToLifecycle())
//                .flatMap(data -> {
//                    if (data.getList() != null && data.getList().size() < limit) {
//                        isComplate = true;
//                    }
//                    itemViewModels.remove(itemViewModels.size() - 1);
//                    return Observable.from(data.getList());
//                })
//                .subscribe(d -> {
//                    count += PreferenceUtil.readLong(CommonUtils.getContext(), "TASK_" + d.tid);
//                    itemViewModels.add(new TaskListItemViewModel(activity, d));
//                }, error -> {
//                }, () -> {
//                    itemViewModels.add(new TaskListItemViewModel(activity, isComplate));
//                    Messenger.getDefault().send(count, TASK_CHANGE);
//                    binding.recyclerView.getAdapter().notifyDataSetChanged();
//                });
//    }

    @Override
    public int setItem(ItemView itemView, int position, TaskListItemViewModel item) {
        if (position == 0) {
            itemView.set(BR.viewModel, R.layout.item_main_task_title);
        } else {
            itemView.set(BR.viewModel, R.layout.item_main_task);
        }
        return 2;
    }
}