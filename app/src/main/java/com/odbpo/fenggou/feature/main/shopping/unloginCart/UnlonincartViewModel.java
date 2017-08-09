package com.odbpo.fenggou.feature.main.shopping.unloginCart;


import android.databinding.ObservableBoolean;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.MyStateBarUtil;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.FrgUnlonincartBinding;
import com.odbpo.fenggou.domain.bean.RecommendProductBean;
import com.odbpo.fenggou.feature.main.info.InfoItemViewModel;
import com.core.op.lib.utils.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

@PerActivity
public class UnlonincartViewModel extends BFViewModel<FrgUnlonincartBinding> {


    @Inject
    public UnlonincartViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
        binding.statusBarFix.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MyStateBarUtil.getStateBarHeight()));
        initData();
        upadataView();
        Messenger.getDefault().register(activity, MessageKey.LOGIN, () -> {
            isLogin.set(true);
        });
    }

    public final List<InfoItemViewModel> itemViewModels = new ArrayList<>();

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_recommend_layout);
    private List<RecommendProductBean> recommendProductBeanList = new ArrayList<>();

    public ObservableBoolean isLogin = new ObservableBoolean(false);


    public final ReplyCommand goProductPage = new ReplyCommand(() -> {
        Messenger.getDefault().sendNoMsg(MessageKey.GO_PRODUCT);
    });


    public final ReplyCommand login = new ReplyCommand(() -> {

        Messenger.getDefault().sendNoMsg(MessageKey.GOLOGIN);

    });

    private void initData() {
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML)", "http://img3.imgtn.bdimg.com/it/u=231378,3680054829&fm=26&gp=0.jpg", "￥123.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img3.imgtn.bdimg.com/it/u=231378,3680054829&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img3.imgtn.bdimg.com/it/u=231378,3680054829&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img3.imgtn.bdimg.com/it/u=231378,3680054829&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img3.imgtn.bdimg.com/it/u=231378,3680054829&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img3.imgtn.bdimg.com/it/u=231378,3680054829&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img3.imgtn.bdimg.com/it/u=231378,3680054829&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img3.imgtn.bdimg.com/it/u=231378,3680054829&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img3.imgtn.bdimg.com/it/u=231378,3680054829&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img3.imgtn.bdimg.com/it/u=231378,3680054829&fm=26&gp=0.jpg", "￥1269.00"));

    }

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


    private Observable<RecommendProductBean> getproducts() {
        return Observable.from(recommendProductBeanList);
    }
}