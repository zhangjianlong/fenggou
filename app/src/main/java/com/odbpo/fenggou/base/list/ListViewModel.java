package com.odbpo.fenggou.base.list;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.core.op.Static;
import com.core.op.bindingadapter.common.BaseItemViewSelector;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.bindingadapter.common.ItemViewSelector;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.weight.EmptyLayout;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.feature.search.SearchItemViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;

public abstract class ListViewModel<V extends BaseListItemViewModel, T> extends BAViewModel<T> {

    private int typeCount = 0;

    private int limit = 10;

    public final List<V> itemViewModels = new ArrayList<>();
    public final ItemViewSelector<V> itemView = itemView();

    public final ObservableBoolean isRefreshing = new ObservableBoolean(false);


    public final ObservableField<Integer> errorVisible = new ObservableField(View.GONE);

    public final ObservableField<Integer> errorType = new ObservableField(EmptyLayout.HIDE_LAYOUT);

    public final ReplyCommand errorClick = new ReplyCommand<>(() -> {
    });

    public final ReplyCommand<Integer> onLoadMoreCommand = new ReplyCommand<>((p) -> {
        loadMore();
    });


    public Map<String, String> params = new HashMap<>();
    public final static String PAGE_NUM = "pageNum";

    public ListViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
        params.put(PAGE_NUM, "0");
        loadData(true);
    }

    public void loadMore() {
        if (!isComplete()) {
            loadMoreData();
            params.put(PAGE_NUM, (Integer.valueOf(params.get(PAGE_NUM) + 1)) + "");
            Observable.timer(500, TimeUnit.MILLISECONDS).subscribe(d -> {
                loadData(true);
            });

        }
    }


    public final ReplyCommand onRefreshCommand = new ReplyCommand<>(() -> {
        params.put(PAGE_NUM, "0");
        refresh();
    });


    public abstract void refresh();

    public abstract void loadMoreData();


    public abstract boolean isComplete();

    public abstract int totalSize();

    public abstract void loadData(final boolean loadMore);


    public abstract int setItem(ItemView itemView, int position, V item);


    public ItemViewSelector<V> itemView() {
        return new BaseItemViewSelector<V>() {
            @Override
            public void select(ItemView itemView, int position, V item) {
                if (itemViewModels.size() > limit && position == itemViewModels.size() - 1) {
                    itemView.set(BR.viewModel, R.layout.item_loadmore);
                } else {
                    typeCount = setItem(itemView, position, item);
                }
            }

            @Override
            public int viewTypeCount() {
                return typeCount + (isComplete() ? 1 : 0);
            }
        };
    }


}

