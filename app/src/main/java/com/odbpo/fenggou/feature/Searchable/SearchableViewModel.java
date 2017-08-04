package com.odbpo.fenggou.feature.Searchable;


import android.databinding.ObservableInt;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.StrUtil;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.data.util.ShareKey;
import com.odbpo.fenggou.data.util.SpUtil;
import com.odbpo.fenggou.databinding.ActSearchableBinding;
import com.core.op.lib.utils.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
    public ObservableInt showHis = new ObservableInt(View.GONE);


    @Inject
    public SearchableViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
        initData();
        hotDatas.add("鼠标垫子");
        hotDatas.add("桂花乌龙");
        hotDatas.add("鼠标垫");
        hotDatas.add("电脑");
        updataFlow();
        binding.serachview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (StrUtil.isEmpty(query.trim())) {
                    return true;
                }
                Messenger.getDefault().send(query.trim(), MessageKey.SEARCH_KEY);
                saveData(query.trim());
                activity.finish();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    private void initData() {
        historyData.clear();
        if (null != (Collection<? extends String>) SpUtil.readObject(ShareKey.HOSTORYDATA)) {
            historyData.addAll((Collection<? extends String>) SpUtil.readObject(ShareKey.HOSTORYDATA));
        }

        if (historyData != null && historyData.size() == 0) {
            showHis.set(View.GONE);
        } else {
            showHis.set(View.VISIBLE);
        }

        updataHistoryFlow();
    }


    private void saveData(String searchKey) {
        if (historyData.size() >= 10) {
            historyData.remove(0);
        }
        if (!historyData.contains(searchKey)) {
            historyData.add(searchKey);
            SpUtil.saveObject((Serializable) historyData, ShareKey.HOSTORYDATA);
        }
    }

    private void cleanHistory() {
        historyData.clear();
        SpUtil.saveObject((Serializable) historyData, ShareKey.HOSTORYDATA);
        initData();
    }


    public final ReplyCommand clickDelHis = new ReplyCommand(() -> {
        cleanHistory();
    });


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