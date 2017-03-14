package com.slash.youth.v2.feature.back.manage;


import com.core.op.bindingadapter.common.BaseItemViewSelector;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.bindingadapter.common.ItemViewSelector;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.JsonUtil;
import com.core.op.lib.utils.PreferenceUtil;
import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.data.Global;
import com.slash.youth.databinding.FrgManagerBinding;
import com.slash.youth.domain.interactor.main.DelManagerUseCase;
import com.slash.youth.domain.interactor.main.MineManagerListUseCase;
import com.slash.youth.domain.interactor.main.PubManagerUseCase;
import com.slash.youth.v2.base.list.BaseListViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

import static com.slash.youth.ui.activity.CityLocationActivity.map;
import static com.slash.youth.v2.util.MessageKey.MINE_MANAGER_DEL;
import static com.slash.youth.v2.util.MessageKey.MINE_MANAGER_REFRESH;

@PerActivity
public class ManagerViewModel extends BaseListViewModel<ManagerItemViewModel> {
    MineManagerListUseCase useCase;
    DelManagerUseCase delManagerUseCase;
    PubManagerUseCase pubManagerUseCase;
    int index = 0;

    @Inject
    public ManagerViewModel(RxAppCompatActivity activity,
                            MineManagerListUseCase useCase,
                            PubManagerUseCase pubManagerUseCase,
                            DelManagerUseCase delManagerUseCase) {
        super(activity);
        this.useCase = useCase;
        this.delManagerUseCase = delManagerUseCase;
        this.pubManagerUseCase = pubManagerUseCase;
    }

    @Override
    public void afterViews() {
        super.afterViews();
        refresh();

        Messenger.getDefault().register(activity, MINE_MANAGER_DEL, Integer.class, position -> {
            itemViewModels.remove(position);
            binding.recyclerView.getAdapter().notifyDataSetChanged();
        });

        Messenger.getDefault().register(activity, MINE_MANAGER_REFRESH, Integer.class, position -> {
            binding.recyclerView.getAdapter().notifyDataSetChanged();
        });
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void refresh() {
        loadData(false);
    }

    public void loadData(boolean isMore) {
        isRefreshing.set(true);
        index = 0;
        Map<String, String> map = new HashMap<>();
        map.put("offset", "0");
        map.put("limit", "20");
        useCase.setParams(JsonUtil.mapToJson(map));
        useCase.execute().compose(fragment.bindToLifecycle())
                .flatMap(data -> {
                    if (!isMore) {
                        index = 0;
                        itemViewModels.clear();
                    }
                    return Observable.from(data.getList());
                })
                .subscribe(d -> {
                    itemViewModels.add(new ManagerItemViewModel(activity, d, delManagerUseCase, pubManagerUseCase, index));
                    index++;
                }, error -> {
                    isRefreshing.set(false);
                }, () -> {
                    isRefreshing.set(false);
                    binding.recyclerView.getAdapter().notifyDataSetChanged();
                });
    }

    @Override
    public ItemViewSelector<ManagerItemViewModel> itemView() {
        return new BaseItemViewSelector<ManagerItemViewModel>() {
            @Override
            public void select(ItemView itemView, int position, ManagerItemViewModel item) {
                itemView.set(BR.viewModel, R.layout.item_manage);
            }
        };
    }
}