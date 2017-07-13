package com.odbpo.fenggou.feature.search;


import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;

import com.core.op.Static;
import com.core.op.bindingadapter.bottomnavigation.NavigationRes;
import com.core.op.bindingadapter.bottomnavigation.ViewBindingAdapter;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.weight.sortView.SortRes;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.ActSearchBinding;
import com.odbpo.fenggou.domain.bean.RecommendProductBean;
import com.odbpo.fenggou.feature.main.shopping.loginCart.ShoppingItemViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static com.odbpo.fenggou.R.id.tabLayout;

@PerActivity
public class SearchViewModel extends BAViewModel<ActSearchBinding> {

    @Inject
    public SearchViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
        initTab();
        initData();
        upadataView();
    }


    private void initTab() {
        SortRes defaultRes = new SortRes();
        defaultRes.setTextRes(R.string.app_search_default);
        defaultRes.setSort(SortRes.DEFAULT);

        SortRes priceRes = new SortRes();
        priceRes.setTextRes(R.string.app_search_price);
        priceRes.setSort(SortRes.AES);

        SortRes saleRes = new SortRes();
        saleRes.setTextRes(R.string.app_search_sale);
        saleRes.setSort(SortRes.AES);

        items.add(defaultRes);
        items.add(priceRes);
        items.add(saleRes);
        binding.tabLayout.upDate(items);

    }

    public List<SortRes> items = new ArrayList<>();
    public final ObservableField<Drawable> bg = new ObservableField<>(Static.CONTEXT.getResources().getDrawable(R.drawable.view1));
    public final List<ShoppingItemViewModel> itemViewModels = new ArrayList<>();

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_shopping);
    private List<RecommendProductBean> recommendProductBeanList = new ArrayList<>();



    public final ReplyCommand changeLayout = new ReplyCommand(() -> {

        if (bg.get().getConstantState().equals(Static.CONTEXT.getResources().getDrawable(R.drawable.view2).getConstantState())) {
            bg.set(Static.CONTEXT.getResources().getDrawable(R.drawable.view1));
        } else {
            bg.set(Static.CONTEXT.getResources().getDrawable(R.drawable.view2));
        }

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