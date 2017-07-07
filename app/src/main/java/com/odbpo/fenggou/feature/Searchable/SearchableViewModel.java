package com.odbpo.fenggou.feature.Searchable;


import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.ActSearchableBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@PerActivity
public class SearchableViewModel extends BAViewModel<ActSearchableBinding> {

    List<String> hotDatas = new ArrayList<>();
    List<String> historyData = new ArrayList<>();
    int index = 0;

    public ItemView flowItemView = ItemView.of(BR.viewModel, R.layout.item_flow);
    public List<LabelFItemViewModel> flowItemViewModels = new ArrayList<>();

    public ItemView hostoryFlowItemView = ItemView.of(BR.viewModel, R.layout.item_flow);
    public List<LabelFItemViewModel> hostoryFlowItemViewModels = new ArrayList<>();


    @Inject
    public SearchableViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
        hotDatas.add("鼠标垫子");
        hotDatas.add("桂花乌龙");
        hotDatas.add("鼠标垫");
        hotDatas.add("电脑");
        historyData.add("zhangxia");
        historyData.add("桂花乌龙");
        historyData.add("zhangxia");
        historyData.add("zhangxia");
        historyData.add("zhangxia");
        historyData.add("桂花乌龙");
        historyData.add("桂花乌龙");
        updataFlow();
        updataHistoryFlow();


    }


    private void updataFlow() {
        flowItemViewModels.clear();
        index = 0;
        Observable.from(hotDatas)
                .subscribe(data -> {
                    flowItemViewModels.add(new LabelFItemViewModel(activity, data, index));
                }, error -> {
                }, () -> {
                    if (binding.flowlayout.getAdapter() != null) {
                        binding.flowlayout.notifyChange();
                    }
                });
    }

    private void updataHistoryFlow() {
        hostoryFlowItemViewModels.clear();
        index = 1;
        Observable.from(historyData)
                .subscribe(data -> {
                    hostoryFlowItemViewModels.add(new LabelFItemViewModel(activity, data, index));
                }, error -> {
                }, () -> {
                    if (binding.hostoryflowlayout.getAdapter() != null) {
                        binding.hostoryflowlayout.notifyChange();
                    }
                });
    }
}