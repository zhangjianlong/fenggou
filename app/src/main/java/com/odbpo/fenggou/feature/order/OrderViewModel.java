package com.odbpo.fenggou.feature.order;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.core.op.Static;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.bindingadapter.common.ItemViewSelector;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.MessageKey;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.list.ListViewModel;
import com.odbpo.fenggou.data.net.AbsAPICallback;
import com.odbpo.fenggou.databinding.ActOrderBinding;
import com.odbpo.fenggou.domain.bean.OrderListBean;
import com.odbpo.fenggou.domain.interactor.order.OrderUseCase;
import com.odbpo.fenggou.util.OrderStatus;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

@PerActivity
public class OrderViewModel extends ListViewModel<OrderItemViewModel, ActOrderBinding> {

    public ObservableField<String> toolTitle = new ObservableField<>();
    public ObservableBoolean emptyLayoutShow = new ObservableBoolean(true);
    public final ItemViewSelector<OrderItemViewModel> itemView = itemView();
    private OrderUseCase orderUseCase;
    private Map<String, String> map = new HashMap<>();
    private List<OrderListBean.DataBean> orderList = new ArrayList<>();
    private OrderListBean orderListBean = new OrderListBean();
    private String orderStatus;

    @Inject
    public OrderViewModel(RxAppCompatActivity activity, OrderUseCase orderUseCase) {
        super(activity);
        this.orderUseCase = orderUseCase;
    }

    @Override
    public void afterViews() {
        initData();
        super.afterViews();


    }

    @Override
    public void refresh() {
        isRefreshing.set(true);
        map.put("pageNum", "0");
        loadData(false);

    }

    @Override
    public void loadMoreData() {
        map.put("pageNum", (Integer.valueOf(map.get("pageNum")) + 1) + "");
    }

    @Override
    public boolean isComplete() {
        return totalSize() == orderList.size() ? true : false;
    }

    @Override
    public int totalSize() {
        return orderListBean.getTotal();
    }

    @Override
    public void loadData(boolean loadMore) {

        orderUseCase.setParams(orderStatus);
        orderUseCase.setFormParams(map);
        orderUseCase.execute().compose(activity.bindToLifecycle()).subscribe(new AbsAPICallback<OrderListBean>() {
            @Override
            protected void onDone(OrderListBean tempOrderListBean) {
                if (tempOrderListBean == null) {
                    return;
                }
                orderListBean = tempOrderListBean;
                if (!loadMore) {
                    orderList.clear();
                }
                orderList.addAll(orderListBean.getData());

            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                isRefreshing.set(false);
                upadataView();
            }
        });

    }


    @Override
    public int setItem(ItemView itemView, int position, OrderItemViewModel item) {
        itemView.set(BR.viewModel, R.layout.item_order_layout);
        return 1;
    }


    public final ReplyCommand goProductPage = new ReplyCommand(() -> {
        activity.finish();
        Messenger.getDefault().sendNoMsg(MessageKey.GO_PRODUCT);
    });


    private void initData() {
        map.put("pageNum", "0");
        Bundle data = activity.getIntent().getBundleExtra("data");
        switch (data.getInt("status")) {
            case 0:
                orderStatus = "";
                toolTitle.set(Static.CONTEXT.getString(R.string.app_order_title_all));
                break;
            case 1:
                orderStatus = OrderStatus.NOPAY + "";
                toolTitle.set(Static.CONTEXT.getString(R.string.app_order_title_pay));
                break;
            case 2:
                orderStatus = OrderStatus.YESSEND + "";
                toolTitle.set(Static.CONTEXT.getString(R.string.app_order_title_receive));
                break;
            case 3:
                orderStatus = "unappraised";
                toolTitle.set(Static.CONTEXT.getString(R.string.app_order_title_evaluate));
                break;
            case 4:
                orderStatus = OrderStatus.BACKORDER + "";
                toolTitle.set(Static.CONTEXT.getString(R.string.app_order_title_return));
                break;
        }
    }


    private void upadataView() {
        itemViewModels.clear();
        getOrderList()
                .subscribe(data -> {
                    itemViewModels.add(new OrderItemViewModel(data));
                }, error -> {
                }, () -> {
                    if (totalSize() > 10 && orderList.size() > 0) {
                        itemViewModels.add(new OrderItemViewModel(activity, isComplete()));
                    }

                    if (binding.recyclerView.getAdapter() != null) {
                        binding.recyclerView.getAdapter().notifyDataSetChanged();
                    }

                    if (itemViewModels.size() > 0) {
                        emptyLayoutShow.set(false);
                    } else {
                        emptyLayoutShow.set(true);
                    }
                });
    }

    private Observable<OrderListBean.DataBean> getOrderList() {
        return Observable.from(orderList);
    }
}