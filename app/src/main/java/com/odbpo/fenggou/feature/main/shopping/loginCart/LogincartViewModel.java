package com.odbpo.fenggou.feature.main.shopping.loginCart;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.core.op.Static;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.FrgLogincartBinding;
import com.odbpo.fenggou.domain.bean.RecommendProductBean;
import com.odbpo.fenggou.feature.main.info.InfoItemViewModel;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static android.R.attr.data;

@PerActivity
public class LogincartViewModel extends BFViewModel<FrgLogincartBinding> {

    @Inject
    public LogincartViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
        initData();
        upadataView();
        Messenger.getDefault().register(this, MessageKey.EDIT, Boolean.class, edit -> {
            isEdit.set(edit);
        });

    }


    public final ObservableField<String> title = new ObservableField<>(Static.CONTEXT.getString(R.string.app_shopping_my));
    public final ObservableField<String> salesPromotion = new ObservableField<>("共有一件促销");
    public final ObservableBoolean isEdit = new ObservableBoolean();
    public final List<ShoppingItemViewModel> itemViewModels = new ArrayList<>();

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_shopping);
    private List<RecommendProductBean> recommendProductBeanList = new ArrayList<>();


    private void initData() {
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML)", "http://img1.imgtn.bdimg.com/it/u=3936672698,3321331245&fm=26&gp=0.jpg", "￥123.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img1.imgtn.bdimg.com/it/u=3936672698,3321331245&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img1.imgtn.bdimg.com/it/u=3936672698,3321331245&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img1.imgtn.bdimg.com/it/u=3936672698,3321331245&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img1.imgtn.bdimg.com/it/u=3936672698,3321331245&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img1.imgtn.bdimg.com/it/u=3936672698,3321331245&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img1.imgtn.bdimg.com/it/u=3936672698,3321331245&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img1.imgtn.bdimg.com/it/u=3936672698,3321331245&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img1.imgtn.bdimg.com/it/u=3936672698,3321331245&fm=26&gp=0.jpg", "￥1269.00"));
        recommendProductBeanList.add(new RecommendProductBean("圣特庄园干红葡萄酒(750ML 优惠价)", "http://img1.imgtn.bdimg.com/it/u=3936672698,3321331245&fm=26&gp=0.jpg", "￥1269.00"));

    }

    private void upadataView() {
        itemViewModels.clear();
        getproducts()
                .subscribe(data -> {
                    itemViewModels.add(new ShoppingItemViewModel(data));
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