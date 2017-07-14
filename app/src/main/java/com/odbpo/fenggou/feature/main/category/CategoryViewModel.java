package com.odbpo.fenggou.feature.main.category;


import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.nfc.Tag;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.core.op.Static;
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
import com.odbpo.fenggou.databinding.FrgCategoryBinding;
import com.odbpo.fenggou.domain.bean.CategoryResultBean;
import com.odbpo.fenggou.feature.Searchable.SearchableActivity;
import com.odbpo.fenggou.feature.search.SearchActivity;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static android.R.attr.data;
import static android.R.attr.tag;

@PerActivity
public class CategoryViewModel extends BFViewModel<FrgCategoryBinding> {

    @Inject
    public CategoryViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
        binding.statusBarFix.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MyStateBarUtil.getStateBarHeight(activity)));
        Messenger.getDefault().register(this, MessageKey.LABEL_SELECT_STAIR, Integer.class, a -> {
            upadataView((int) a);
        });
        Messenger.getDefault().register(this, MessageKey.SEARCH, () -> {
            SearchActivity.instance(activity);
        });
        initData();
        upadataView(0);
    }


    private CategoryItemViewModel categoryItemViewModel;
    public final List<CategoryItemViewModel> itemViewModels = new ArrayList<>();
    public final ItemViewSelector<CategoryItemViewModel> itemView = itemView();
    public final List<CategoryItemViewModel> secondItemViewModels = new ArrayList<>();
    public final ItemView secondItemView = ItemView.of(BR.viewModel, R.layout.item_category_layout);


    private CategoryResultBean categoryResultBean;
    private int index = 0;


    private ItemViewSelector<CategoryItemViewModel> itemView() {
        return new BaseItemViewSelector<CategoryItemViewModel>() {
            @Override
            public void select(ItemView itemView, int position, CategoryItemViewModel item) {
                itemView.set(BR.viewModel, R.layout.item_common_tv);
            }
        };
    }

    private void initData() {
        List<CategoryResultBean.Tag3> tag3s = new ArrayList<>();
        List<CategoryResultBean.Tag3> tag3s1 = new ArrayList<>();
        tag3s.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));


        tag3s1.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s1.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s1.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s1.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s1.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s1.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s1.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s1.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s1.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));
        tag3s1.add(new CategoryResultBean.Tag3("tag3-" + 1, "http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg"));

        List<CategoryResultBean.Tag2> tag2s = new ArrayList<>();
        List<CategoryResultBean.Tag2> tag2s1 = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            tag2s.add(new CategoryResultBean.Tag2("tag2" + i, tag3s));
        }

        tag2s1.add(new CategoryResultBean.Tag2("tag2-0", tag3s1));


        List<CategoryResultBean.Tag1> tag1s = new ArrayList<>();
        tag1s.add(new CategoryResultBean.Tag1("精品酒类", tag2s));
        tag1s.add(new CategoryResultBean.Tag1("个护化妆", tag2s1));

        categoryResultBean = new CategoryResultBean();
        categoryResultBean.setTag1(tag1s);


    }

    private void upadataView(int selected) {
        List<CategoryResultBean.Tag2> tag2 = categoryResultBean.getTag1().get(selected).getTag2();
        itemViewModels.clear();
        index = 0;
        getLableBeens()
                .subscribe(data -> {
                    if (index == selected) {
                        categoryItemViewModel = new CategoryItemViewModel(activity, data.getTagName(), index, true);
                        itemViewModels.add(categoryItemViewModel);
                    } else {
                        itemViewModels.add(new CategoryItemViewModel(activity, data.getTagName(), index, false));
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