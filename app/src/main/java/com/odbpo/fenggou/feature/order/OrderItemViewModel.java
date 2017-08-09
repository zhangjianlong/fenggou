package com.odbpo.fenggou.feature.order;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.core.op.bindingadapter.common.ItemView;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.list.BaseListItemViewModel;
import com.odbpo.fenggou.domain.bean.OrderListBean;
import com.odbpo.fenggou.domain.bean.PhoneLoginResultBean;
import com.odbpo.fenggou.domain.bean.SearchProductBean;
import com.odbpo.fenggou.feature.main.category.CategoryItemIvViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.Observable;

/**
 * @author: zjl
 * @Time: 2017/7/14 10:39
 * @Desc:
 */

public class OrderItemViewModel extends BaseListItemViewModel {
    public final ObservableList<OrderListItemViewModel> itemViewModels = new ObservableArrayList<>();
    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_order_goods_layout);


    public final ObservableField<OrderListBean.DataBean> orderData = new ObservableField<>();

    public OrderItemViewModel(OrderListBean.DataBean dataBean) {
        this.orderData.set(dataBean);
        itemViewModels.clear();
        Observable.from(orderData.get().getOrderGoodsList()).subscribe(data -> {
            itemViewModels.add(new OrderListItemViewModel(data));

        });


    }

    public OrderItemViewModel(RxAppCompatActivity activity, boolean isLoadComplete) {
        super(activity, isLoadComplete);
    }


}
