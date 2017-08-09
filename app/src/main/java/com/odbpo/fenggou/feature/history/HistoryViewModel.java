package com.odbpo.fenggou.feature.history;


import android.databinding.ObservableField;

import com.core.op.Static;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.list.ListViewModel;
import com.odbpo.fenggou.data.net.AbsAPICallback;
import com.odbpo.fenggou.databinding.ActHistoryBinding;
import com.odbpo.fenggou.domain.bean.HistoryListBean;
import com.odbpo.fenggou.domain.bean.base.CustomerInfo;
import com.odbpo.fenggou.domain.interactor.history.GetHistoryUserCase;
import com.odbpo.fenggou.feature.search.SearchItemViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@PerActivity
public class HistoryViewModel extends ListViewModel<HistoryItemViewModel, ActHistoryBinding> {
    public ObservableField<String> toolTitle = new ObservableField<>(Static.CONTEXT.getString(R.string.app_history_title));
    private GetHistoryUserCase getHistoryUserCase;
    private List<HistoryListBean.DataBean> dataBeanList = new ArrayList<>();
    private HistoryListBean historyListBean = new HistoryListBean();

    @Inject
    public HistoryViewModel(RxAppCompatActivity activity, GetHistoryUserCase getHistoryUserCase) {
        super(activity);
        this.getHistoryUserCase = getHistoryUserCase;
    }

    @Override
    public void afterViews() {
        super.afterViews();

    }

    @Override
    public void refresh() {
        isRefreshing.set(true);
        loadData(false);


    }

    @Override
    public void loadMoreData() {

    }

    @Override
    public boolean isComplete() {
        return totalSize() == dataBeanList.size() ? true : false;
    }

    @Override
    public int totalSize() {
        return historyListBean.getTotal();
    }

    @Override
    public void loadData(boolean loadMore) {
        params.put("region", "2962");
        getHistoryUserCase.setFormParams(params);
        getHistoryUserCase.execute().compose(activity.bindToLifecycle()).subscribe(new AbsAPICallback<HistoryListBean>() {


            @Override
            protected void onDone(HistoryListBean tempHistoryListBean) {
                if (!loadMore) {
                    dataBeanList.clear();
                }
                historyListBean = tempHistoryListBean;
                dataBeanList.addAll(historyListBean.getData());

            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                updateView();
            }
        });

    }

    private void updateView() {
        isRefreshing.set(false);
        itemViewModels.clear();
        Observable.from(dataBeanList).subscribe(dataBean -> {
            itemViewModels.add(new HistoryItemViewModel(dataBean));

        }, e -> {
        }, () -> {

            if (totalSize() > 10 && dataBeanList.size() > 0) {
                itemViewModels.add(new HistoryItemViewModel(activity, isComplete()));
            }

            if (null != binding.recyclerView.getAdapter()) {
                binding.recyclerView.getAdapter().notifyDataSetChanged();
            }
        });

    }

    @Override
    public int setItem(ItemView itemView, int position, HistoryItemViewModel item) {
        itemView.set(BR.viewModel, R.layout.item_history_layout);
        return 1;
    }
}