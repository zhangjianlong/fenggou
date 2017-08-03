package com.odbpo.fenggou.feature.main.category;


import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.core.op.bindingadapter.common.BaseItemViewSelector;
import com.core.op.bindingadapter.common.ItemView;
import com.core.op.bindingadapter.common.ItemViewSelector;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.MyStateBarUtil;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.data.net.AbsAPICallback;
import com.odbpo.fenggou.databinding.FrgCategoryBinding;
import com.odbpo.fenggou.domain.bean.CategoryResultBean;
import com.odbpo.fenggou.domain.bean.ProductCategoryBean;
import com.odbpo.fenggou.domain.interactor.category.GetProductCategoryUseCase;
import com.odbpo.fenggou.feature.Searchable.SearchableActivity;
import com.odbpo.fenggou.feature.forget.ForgetActivity;
import com.odbpo.fenggou.feature.search.SearchActivity;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;


@PerActivity
public class CategoryViewModel extends BFViewModel<FrgCategoryBinding> {
    private GetProductCategoryUseCase getProductCategoryUseCase;


    @Inject
    public CategoryViewModel(RxAppCompatActivity activity, GetProductCategoryUseCase getProductCategoryUseCase) {
        super(activity);
        this.getProductCategoryUseCase = getProductCategoryUseCase;
    }

    @Override
    public void afterViews() {
        binding.statusBarFix.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MyStateBarUtil.getStateBarHeight()));
        Messenger.getDefault().register(this, MessageKey.LABEL_SELECT_STAIR, Integer.class, a -> {
            upadataView((int) a);
        });
        Messenger.getDefault().register(this, MessageKey.SEARCH, Integer.class, data -> {
            Bundle bundle = new Bundle();
            bundle.putInt("cateId", data);
            SearchActivity.instance(activity,bundle);
        });

        Map<String, String> maps = new HashMap<>();
        maps.put("recursion ", "true");
        getProductCategoryUseCase.setFormParams(maps);
        getProductCategoryUseCase.execute().compose(activity.bindToLifecycle()).subscribe(new AbsAPICallback<ProductCategoryBean>() {
            @Override
            protected void onDone(ProductCategoryBean productCategoryBean) {
                initData(productCategoryBean);
                upadataView(0);

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }


    private CategoryItemViewModel categoryItemViewModel;
    public final List<CategoryItemViewModel> itemViewModels = new ArrayList<>();
    public final ItemViewSelector<CategoryItemViewModel> itemView = itemView();
    public final List<CategoryItemViewModel> secondItemViewModels = new ArrayList<>();
    public final ItemView secondItemView = ItemView.of(BR.viewModel, R.layout.item_category_layout);


    private CategoryResultBean categoryResultBean;
    private int index = 0;
    private ProductCategoryBean productCategoryBean;


    private ItemViewSelector<CategoryItemViewModel> itemView() {
        return new BaseItemViewSelector<CategoryItemViewModel>() {
            @Override
            public void select(ItemView itemView, int position, CategoryItemViewModel item) {
                itemView.set(BR.viewModel, R.layout.item_common_tv);
            }
        };
    }

    private void initData(ProductCategoryBean productCategoryBean) {
        List<ProductCategoryBean.DataBean> tag1 = new ArrayList<>();
        List<ProductCategoryBean.DataBean> tag2 = new ArrayList<>();
        List<ProductCategoryBean.DataBean> tag3 = new ArrayList<>();
        Observable.from(productCategoryBean.getData()).subscribe(dataBean -> {
            switch (dataBean.getGrade()) {
                case 1:
                    tag1.add(dataBean);
                    break;
                case 2:
                    tag2.add(dataBean);
                    break;
                case 3:
                    tag3.add(dataBean);
                    break;
            }

        }, error -> {

        }, () -> {

        });

        categoryResultBean = new CategoryResultBean();

        List<CategoryResultBean.Tag1> tempTag1List = new ArrayList<>();

        Observable.from(tag1).subscribe(tag1Data -> {
            CategoryResultBean.Tag1 tempTag1 = new CategoryResultBean.Tag1();
            List<CategoryResultBean.Tag2> tag2List = new ArrayList<CategoryResultBean.Tag2>();

            tempTag1.setDataBean(tag1Data);

            Observable.from(tag2).subscribe(tag2Data -> {
                List<CategoryResultBean.Tag3> tag3List = new ArrayList<CategoryResultBean.Tag3>();

                if (tag1Data.getId() == tag2Data.getParentId()) {
                    tag3List.clear();
                    Observable.from(tag3).subscribe(tag3Data -> {
                        if (tag3Data.getParentId() == tag2Data.getId()) {
                            tag3List.add(new CategoryResultBean.Tag3(tag3Data));
                        }
                    });
                    tag2List.add(new CategoryResultBean.Tag2(tag2Data, tag3List));


                }

            });
            tempTag1.setTag2(tag2List);
            tempTag1List.add(tempTag1);

        }, error -> {

        }, () -> {
            categoryResultBean.setTag1(tempTag1List);


        });
    }

    private void upadataView(int selected) {
        List<CategoryResultBean.Tag2> tag2 = categoryResultBean.getTag1().get(selected).getTag2();
        itemViewModels.clear();
        index = 0;
        getLableBeens()
                .subscribe(data -> {
                    if (index == selected) {
                        categoryItemViewModel = new CategoryItemViewModel(activity, data.getDataBean(), index, true);
                        itemViewModels.add(categoryItemViewModel);
                    } else {
                        itemViewModels.add(new CategoryItemViewModel(activity, data.getDataBean(), index, false));
                    }
                    index++;
                }, error -> {
                }, () -> {
                    if (binding.recyclerView.getAdapter() != null) {
                        binding.recyclerView.getAdapter().notifyDataSetChanged();
                    }
                    updataSecond(tag2);
                });
    }

    private void updataSecond(List<CategoryResultBean.Tag2> tag2) {
        secondItemViewModels.clear();
        index = 0;
        getSecondLableBeens(tag2)
                .subscribe(data -> {
                    secondItemViewModels.add(new CategoryItemViewModel(activity, data));
                    index++;
                }, error -> {
                }, () -> {
                    if (binding.secondRecyclerView.getAdapter() != null) {
                        binding.secondRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                });


    }


    private Observable<CategoryResultBean.Tag1> getLableBeens() {
        return Observable.from(categoryResultBean.getTag1());
    }


    private Observable<CategoryResultBean.Tag2> getSecondLableBeens(List<CategoryResultBean.Tag2> tag2) {
        return Observable.from(tag2);
    }


    public final ReplyCommand clickSearchView = new ReplyCommand(() -> {
        SearchableActivity.instance(activity);
    });

}