package com.odbpo.fenggou.feature.main.info;


import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.widget.Switch;

import com.core.op.Static;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.utils.StrUtil;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.data.net.AbsAPICallback;
import com.odbpo.fenggou.data.util.ShareKey;
import com.odbpo.fenggou.data.util.SpUtil;
import com.odbpo.fenggou.databinding.FrgInfoBinding;
import com.odbpo.fenggou.domain.bean.HistoryNumBean;
import com.odbpo.fenggou.domain.bean.OrderNumBean;
import com.odbpo.fenggou.domain.bean.RecommendProductBean;
import com.odbpo.fenggou.domain.bean.base.CustomerInfo;
import com.odbpo.fenggou.domain.interactor.customer.CustomerInfoUserCase;
import com.odbpo.fenggou.domain.interactor.customer.FollowNumUserCase;
import com.odbpo.fenggou.domain.interactor.customer.HistoryNumUserCase;
import com.odbpo.fenggou.domain.interactor.customer.OrderNumUserCase;
import com.odbpo.fenggou.feature.follow.FollowActivity;
import com.odbpo.fenggou.feature.history.HistoryActivity;
import com.odbpo.fenggou.feature.message.MessageActivity;
import com.odbpo.fenggou.feature.order.OrderActivity;
import com.odbpo.fenggou.feature.profile.ProfileActivity;
import com.odbpo.fenggou.util.OrderStatus;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;


@PerActivity
public class InfoViewModel extends BFViewModel<FrgInfoBinding> {

    private CustomerInfoUserCase customerInfoUserCase;
    private FollowNumUserCase followNumUserCase;
    private HistoryNumUserCase historyNumUserCase;
    private OrderNumUserCase orderNumUserCase;
    public ObservableField<String> historyNum = new ObservableField("0");
    public ObservableField<String> followNum = new ObservableField("0");


    @Inject
    public InfoViewModel(RxAppCompatActivity activity, HistoryNumUserCase historyNumUserCase, FollowNumUserCase followNumUserCase, CustomerInfoUserCase customerInfoUserCase, OrderNumUserCase orderNumUserCase) {
        super(activity);
        this.customerInfoUserCase = customerInfoUserCase;
        this.orderNumUserCase = orderNumUserCase;
        this.followNumUserCase = followNumUserCase;
        this.historyNumUserCase = historyNumUserCase;
    }

    @Override
    public void afterViews() {
//        initData();
//        upadataView();
        getCustomerInfo();
        getOrderNum();
        getFollowNum();
        getHistoryNum();

    }


    public final ObservableField<String> imageUri = new ObservableField<>("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1498020154&di=354e0947d9d994db9cd65e630cde85e7&src=http://pic7.nipic.com/20100519/4862714_212100033649_2.jpg");
    public final ObservableField<String> userName = new ObservableField<>("18625254516");
    public final ObservableField<String> userLevel = new ObservableField<>("普通会员");
    public final ObservableField<String> orderPayNum = new ObservableField<>("0");
    public final ObservableField<String> orderReceiveNum = new ObservableField<>("0");
    public final ObservableField<String> orderEvaluateNum = new ObservableField<>("0");
    public final ObservableField<String> orderReturnNum = new ObservableField<>("0");

    public final List<InfoItemViewModel> itemViewModels = new ArrayList<>();

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_recommend_layout);
    private List<RecommendProductBean> recommendProductBeanList = new ArrayList<>();

    private Observable<RecommendProductBean> getproducts() {
        return Observable.from(recommendProductBeanList);
    }

    public ReplyCommand profileAct = new ReplyCommand(() -> {
        ProfileActivity.instance(activity);

    });
    public ReplyCommand goHistory = new ReplyCommand(() -> {
        HistoryActivity.instance(activity);

    });
    public ReplyCommand goFollow = new ReplyCommand(() -> {
        FollowActivity.instance(activity);

    });

    public ReplyCommand message = new ReplyCommand(() -> {
        MessageActivity.instance(activity);

    });

    public ReplyCommand orderAll = new ReplyCommand(() -> {
        Bundle data = new Bundle();
        data.putInt("status", 0);
        OrderActivity.instance(activity, data);

    });
    public ReplyCommand orderPay = new ReplyCommand(() -> {
        Bundle data = new Bundle();
        data.putInt("status", 1);
        OrderActivity.instance(activity, data);

    });
    public ReplyCommand orderReceive = new ReplyCommand(() -> {
        Bundle data = new Bundle();
        data.putInt("status", 2);
        OrderActivity.instance(activity, data);

    });

    public ReplyCommand orderEvaluate = new ReplyCommand(() -> {
        Bundle data = new Bundle();
        data.putInt("status", 3);
        OrderActivity.instance(activity, data);

    });
    public ReplyCommand orderReturn = new ReplyCommand(() -> {
        Bundle data = new Bundle();
        data.putInt("status", 4);
        OrderActivity.instance(activity, data);

    });


    private void upadataView() {
        itemViewModels.clear();
        getproducts()
                .subscribe(data -> {
                    itemViewModels.add(new InfoItemViewModel(data));
                }, error -> {
                }, () -> {
                    if (binding.recyclerView.getAdapter() != null) {
                        binding.recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });
    }


    private void getCustomerInfo() {
        customerInfoUserCase.execute().compose(activity.bindToLifecycle()).subscribe(new AbsAPICallback<CustomerInfo>() {
            @Override
            protected void onDone(CustomerInfo customerInfo) {
                CustomerInfo customerInfo1 = customerInfo;
                SpUtil.write(ShareKey.CUSTOMER_INFO, customerInfo1);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    /**
     * @author: zjl
     * @Time: 2017/8/8 15:23
     * @Desc: 请求订单数量
     */

    public void getOrderNum() {
        List<Integer> orderStatus = new ArrayList<>();
        orderStatus.add(OrderStatus.NOPAY);
        orderStatus.add(OrderStatus.YESSEND);
        orderStatus.add(OrderStatus.YESGET);
        orderStatus.add(OrderStatus.SUCESSDRAWBACK);
        Observable.from(orderStatus).subscribe(data -> {
            orderNumUserCase.setParams(data + "");
            orderNumUserCase.execute().compose(activity.bindToLifecycle()).subscribe(num -> {
                if (StrUtil.isEmpty(num)) {
                    return;
                }
                int tempValue = Integer.valueOf(data);
                switch (tempValue) {
                    case OrderStatus.NOPAY:
                        orderPayNum.set(num);
                        break;
                    case OrderStatus.YESSEND:
                        orderReceiveNum.set(num);
                        break;
                    case OrderStatus.YESGET:
                        orderEvaluateNum.set(num);
                        break;
                    case OrderStatus.SUCESSDRAWBACK:
                        orderReturnNum.set(num);
                        break;
                }

            }, error -> {

            }, () -> {

            });

        });
    }


    /**
     * @author: zjl
     * @Time: 2017/8/8 15:23
     * @Desc: 请求历史记录数量
     */

    private void getHistoryNum() {
        historyNumUserCase.execute().compose(activity.bindToLifecycle()).subscribe(new AbsAPICallback<HistoryNumBean>() {
            @Override
            protected void onDone(HistoryNumBean data) {
                historyNum.set(data.getTotal() + "");

            }
        });

    }


    /**
     * @author: zjl
     * @Time: 2017/8/8 15:23
     * @Desc: 请求我的关注数量
     */
    private void getFollowNum() {
        followNumUserCase.execute().compose(activity.bindToLifecycle()).subscribe(new AbsAPICallback<HistoryNumBean>() {
            @Override
            protected void onDone(HistoryNumBean data) {
                followNum.set(data.getTotal() + "");

            }
        });
    }


}