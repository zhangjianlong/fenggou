package com.odbpo.fenggou.feature.history;

import android.databinding.ObservableField;

import com.odbpo.fenggou.base.list.BaseListItemViewModel;
import com.odbpo.fenggou.domain.bean.HistoryListBean;
import com.odbpo.fenggou.domain.bean.SearchProductBean;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author: zjl
 * @Time: 2017/8/9 8:36
 * @Desc:
 */
public class HistoryItemViewModel extends BaseListItemViewModel {

    public final ObservableField<HistoryListBean.DataBean> data = new ObservableField<>();

    public HistoryItemViewModel(HistoryListBean.DataBean dataBean) {
        this.data.set(dataBean);
    }

    public HistoryItemViewModel(RxAppCompatActivity activity, boolean isLoadComplete) {
        super(activity, isLoadComplete);
    }

}
