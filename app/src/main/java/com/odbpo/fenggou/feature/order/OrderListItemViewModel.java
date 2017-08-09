package com.odbpo.fenggou.feature.order;

import android.databinding.ObservableField;

import com.odbpo.fenggou.base.list.BaseListItemViewModel;
import com.odbpo.fenggou.domain.bean.OrderListBean;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author: zjl
 * @Time: 2017/7/14 10:39
 * @Desc:
 */

public class OrderListItemViewModel extends BaseListItemViewModel {

    public final ObservableField<OrderListBean.DataBean.OrderGoodsListBean> orderGoodsData = new ObservableField<>();


    public OrderListItemViewModel(OrderListBean.DataBean.OrderGoodsListBean data) {
        this.orderGoodsData.set(data);
    }

}
