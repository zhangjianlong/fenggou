package com.odbpo.fenggou.feature.search;


import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
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
import com.odbpo.fenggou.base.list.ListViewModel;
import com.odbpo.fenggou.data.net.AbsAPICallback;
import com.odbpo.fenggou.databinding.ActSearchBinding;
import com.odbpo.fenggou.domain.bean.EsSearchRequest;
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

import static com.odbpo.fenggou.R.id.action0;
import static com.odbpo.fenggou.R.id.tabLayout;

@PerActivity
public class SearchViewModel extends ListViewModel<SearchItemViewModel, ActSearchBinding> {
    private SearchGoodsUserCase searchGoodsUserCase;
    private EsSearchRequest esSearchRequest;

    @Inject
    public SearchViewModel(RxAppCompatActivity activity, SearchGoodsUserCase searchGoodsUserCase) {
        super(activity);
        this.searchGoodsUserCase = searchGoodsUserCase;
    }

    @Override
    public void afterViews() {
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.app_theme_colorPrimary);
        binding.swipeRefreshLayout.setProgressViewOffset(true, -20, 100);
        Bundle bundle = activity.getIntent().getBundleExtra("data");
        int cateId = bundle.getInt("cateId");
        List<Long> cateIdList = new ArrayList<>();
        cateIdList.add((long) 1);
        esSearchRequest = new EsSearchRequest();
        esSearchRequest.setTypeIds(cateIdList);

        initTab();
        initData(esSearchRequest);
        Messenger.getDefault().register(activity, MessageKey.PRODUCT_DETAIL, () -> {
            DetailActivity.instance(activity);
        });

        Messenger.getDefault().register(activity, MessageKey.SEARCH_KEY, String.class, data -> {
            EsSearchRequest esSearchRequest1 = new EsSearchRequest();
            esSearchRequest = esSearchRequest1;
            esSearchRequest1.setQueryString(data);
            initData(esSearchRequest1);
        });
    }

    @Override
    public void loadMore() {
        loadMore = true;
        EsSearchRequest esSearchRequest = new EsSearchRequest();
        esSearchRequest = this.esSearchRequest;
        esSearchRequest.setPageNum(esSearchRequest.getPageNum() + 1);
        initData(esSearchRequest);
    }

    @Override
    public void refresh() {
        isRefreshing.set(true);
        EsSearchRequest esSearchRequest = new EsSearchRequest();
        esSearchRequest = this.esSearchRequest;
        esSearchRequest.setPageNum(1);
        initData(esSearchRequest);

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
    public final ObservableInt emptyLayoutShow = new ObservableInt(View.GONE);
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


    /**
     * @author: zjl
     * @Time: 2017/8/3 10:42
     * @Desc: 网络请求（搜索商品数据）
     */

    private void initData(EsSearchRequest esSearchRequest) {
        searchGoodsUserCase.setParams(JsonUtil.BeanToJson(esSearchRequest));
        searchGoodsUserCase.execute().compose(activity.bindToLifecycle()).subscribe(new AbsAPICallback<SearchProductBean>() {
            @Override
            protected void onDone(SearchProductBean searchProductBean) {
                isRefreshing.set(false);
                if (loadMore) {
                } else {
                    recommendProductBeanList.clear();
                }
                recommendProductBeanList.addAll(searchProductBean.getData());
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                loadMore = false;
                isRefreshing.set(false);
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
                    if (itemViewModels.size() > 0) {
                        binding.emptyLayout.setVisibility(View.GONE);
                    } else {
                        binding.emptyLayout.setVisibility(View.VISIBLE);
                    }
                    if (binding.recyclerView.getAdapter() != null) {
                        binding.recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });
    }


    private Observable<SearchProductBean.DataBean> getproducts() {
        return Observable.from(recommendProductBeanList);
    }

    public ItemViewSelector<SearchItemViewModel> itemView() {
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