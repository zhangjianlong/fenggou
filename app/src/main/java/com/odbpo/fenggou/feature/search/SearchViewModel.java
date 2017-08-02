package com.odbpo.fenggou.feature.search;


import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.core.op.Static;
import com.core.op.bindingadapter.bottomnavigation.NavigationRes;
import com.core.op.bindingadapter.bottomnavigation.ViewBindingAdapter;
import com.core.op.bindingadapter.common.BaseItemViewSelector;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.bindingadapter.common.ItemViewSelector;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.JsonUtil;
import com.core.op.lib.utils.MyStateBarUtil;
import com.core.op.lib.weight.sortView.SortRes;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.data.net.AbsAPICallback;
import com.odbpo.fenggou.databinding.ActSearchBinding;
import com.odbpo.fenggou.domain.bean.RecommendProductBean;
import com.odbpo.fenggou.domain.bean.SearchProductBean;
import com.odbpo.fenggou.domain.interactor.search.SearchGoodsUserCase;
import com.odbpo.fenggou.feature.Searchable.SearchableActivity;
import com.odbpo.fenggou.feature.detail.DetailActivity;
import com.odbpo.fenggou.feature.main.shopping.loginCart.ShoppingItemViewModel;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;

import static com.odbpo.fenggou.R.id.tabLayout;

@PerActivity
public class SearchViewModel extends BAViewModel<ActSearchBinding> {
    private SearchGoodsUserCase searchGoodsUserCase;

    @Inject
    public SearchViewModel(RxAppCompatActivity activity, SearchGoodsUserCase searchGoodsUserCase) {
        super(activity);
        this.searchGoodsUserCase = searchGoodsUserCase;
    }

    @Override
    public void afterViews() {
        initTab();
        initData();
        Messenger.getDefault().register(activity, MessageKey.PRODUCT_DETAIL, () -> {
            DetailActivity.instance(activity);
        });
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
    public final List<SearchItemViewModel> itemViewModels = new ArrayList<>();

    //控制recyleview 布局的标志 true为线性布局  false为网格布局
    private boolean isLiearlayout = true;
    public final ItemViewSelector<SearchItemViewModel> itemView = itemView();

    private List<SearchProductBean.DataBean> recommendProductBeanList = new ArrayList<>();


    public final ReplyCommand changeLayout = new ReplyCommand(() -> {
        if (bg.get().getConstantState().equals(Static.CONTEXT.getResources().getDrawable(R.drawable.view2).getConstantState())) {
            bg.set(Static.CONTEXT.getResources().getDrawable(R.drawable.view1));
            isLiearlayout = true;
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            binding.recyclerView.getAdapter().notifyDataSetChanged();

        } else {
            isLiearlayout = false;
            bg.set(Static.CONTEXT.getResources().getDrawable(R.drawable.view2));
            binding.recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
            binding.recyclerView.getAdapter().notifyDataSetChanged();
        }

    });


    public final ReplyCommand clickSearchView = new ReplyCommand(() -> {
        SearchableActivity.instance(activity);
    });


    private void initData() {
        Map<String, String> maps1 = new HashMap<>();
        maps1.put("queryString", "意大利");
        searchGoodsUserCase.setParams(JsonUtil.mapToJson(maps1));
        searchGoodsUserCase.execute().compose(activity.bindToLifecycle()).subscribe(new AbsAPICallback<SearchProductBean>() {
            @Override
            protected void onDone(SearchProductBean searchProductBean) {
                recommendProductBeanList.addAll(searchProductBean.getData());

            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                upadataView();
            }


        });


    }

    private void upadataView() {
        itemViewModels.clear();
        getproducts()
                .subscribe(data -> {
                    itemViewModels.add(new SearchItemViewModel(data));
                }, error -> {
                }, () -> {
                    if (binding.recyclerView.getAdapter() != null) {
                        binding.recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });
    }


    private Observable<SearchProductBean.DataBean> getproducts() {
        return Observable.from(recommendProductBeanList);
    }

    private ItemViewSelector<SearchItemViewModel> itemView() {
        return new BaseItemViewSelector<SearchItemViewModel>() {
            @Override
            public void select(ItemView itemView, int position, SearchItemViewModel item) {
                if (isLiearlayout) {
                    itemView.set(BR.viewModel, R.layout.item_search_linear_layout);
                } else {
                    itemView.set(BR.viewModel, R.layout.item_search_gird_layout);
                }

            }
        };
    }

}