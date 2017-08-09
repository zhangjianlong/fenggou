package com.odbpo.fenggou.feature.follow;

import android.databinding.ObservableField;

import com.odbpo.fenggou.base.list.BaseListItemViewModel;
import com.odbpo.fenggou.domain.bean.FollowListBean;
import com.odbpo.fenggou.domain.bean.HistoryListBean;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author: zjl
 * @Time: 2017/8/9 8:36
 * @Desc:
 */
public class FollowItemViewModel extends BaseListItemViewModel {

    public final ObservableField<FollowListBean.DataBean> data = new ObservableField<>();

    public FollowItemViewModel(FollowListBean.DataBean dataBean) {
        this.data.set(dataBean);
    }

    public FollowItemViewModel(RxAppCompatActivity activity, boolean isLoadComplete) {
        super(activity, isLoadComplete);
    }

}