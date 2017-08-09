package com.odbpo.fenggou.feature.follow;


import android.databinding.ObservableField;

import com.core.op.Static;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.list.ListViewModel;
import com.odbpo.fenggou.data.net.AbsAPICallback;
import com.odbpo.fenggou.databinding.ActFollowBinding;
import com.odbpo.fenggou.domain.bean.FollowListBean;
import com.odbpo.fenggou.domain.bean.HistoryListBean;
import com.odbpo.fenggou.domain.bean.base.CustomerInfo;
import com.odbpo.fenggou.domain.interactor.follow.GetFollowUserCase;
import com.odbpo.fenggou.feature.history.HistoryItemViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@PerActivity
public class FollowViewModel extends ListViewModel<FollowItemViewModel, ActFollowBinding> {
    public ObservableField<String> toolTitle = new ObservableField<>(Static.CONTEXT.getString(R.string.app_follow_title));
    private GetFollowUserCase followUserCase;
    private List<FollowListBean.DataBean> dataBeanList = new ArrayList<>();
    private FollowListBean followListBean = new FollowListBean();

    @Inject
    public FollowViewModel(RxAppCompatActivity activity, GetFollowUserCase getFollowUserCase) {
        super(activity);
        this.followUserCase = getFollowUserCase;
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
        return followListBean.getTotal();
    }

    @Override
    public void loadData(boolean loadMore) {
        params.put("region", "2962");
        followUserCase.setFormParams(params);
        followUserCase.execute().compose(activity.bindToLifecycle()).subscribe(new AbsAPICallback<FollowListBean>() {
            @Override
            protected void onDone(FollowListBean tempFollowListBean) {
                if (!loadMore) {
                    dataBeanList.clear();
                }
                followListBean = tempFollowListBean;
                dataBeanList.addAll(followListBean.getData());

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
            itemViewModels.add(new FollowItemViewModel(dataBean));

        }, e -> {
        }, () -> {

            if (totalSize() > 10 && dataBeanList.size() > 0) {
                itemViewModels.add(new FollowItemViewModel(activity, isComplete()));
            }

            if (null != binding.recyclerView.getAdapter()) {
                binding.recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
    }


    @Override
    public int setItem(ItemView itemView, int position, FollowItemViewModel item) {
        itemView.set(BR.viewModel, R.layout.item_follow_layout);
        return 0;
    }
}