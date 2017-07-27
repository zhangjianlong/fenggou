package com.odbpo.fenggou.feature.main.info;


import android.databinding.ObservableField;

import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.FrgInfoBinding;
import com.odbpo.fenggou.domain.bean.RecommendProductBean;
import com.odbpo.fenggou.feature.message.MessageActivity;
import com.odbpo.fenggou.feature.profile.ProfileActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;


@PerActivity
public class InfoViewModel extends BFViewModel<FrgInfoBinding> {

    @Inject
    public InfoViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
//        initData();
//        upadataView();

    }

    public final ObservableField<String> imageUri = new ObservableField<>("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1498020154&di=354e0947d9d994db9cd65e630cde85e7&src=http://pic7.nipic.com/20100519/4862714_212100033649_2.jpg");
    public final ObservableField<String> userName = new ObservableField<>("18625254516");
    public final ObservableField<String> userLevel = new ObservableField<>("普通会员");

    public final List<InfoItemViewModel> itemViewModels = new ArrayList<>();

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_recommend_layout);
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

    public ReplyCommand profileAct = new ReplyCommand(() -> {
        ProfileActivity.instance(activity);

    });

    public ReplyCommand message = new ReplyCommand(() -> {
        MessageActivity.instance(activity);

    });


}