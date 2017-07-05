package com.odbpo.fenggou.feature.Searchable.unloginCart;


import android.support.v4.app.FragmentManager;
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
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

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
        binding.statusBarFix.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MyStateBarUtil.getStateBarHeight(activity)));
        initData();
        upadataView();
    }

    public final List<InfoItemViewModel> itemViewModels = new ArrayList<>();

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_recommend_layout);
    private List<RecommendProductBean> recommendProductBeanList = new ArrayList<>();


    public final ReplyCommand go = new ReplyCommand(() -> {

    });


    public final ReplyCommand login = new ReplyCommand(() -> {
        Messenger.getDefault().sendNoMsg(MessageKey.GOLOGIN);

    });

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
}