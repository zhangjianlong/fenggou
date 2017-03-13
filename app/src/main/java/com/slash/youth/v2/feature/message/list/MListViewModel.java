package com.slash.youth.v2.feature.message.list;


import com.core.op.bindingadapter.common.ItemViewSelector;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.v2.base.list.BaseListViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class MListViewModel extends BaseListViewModel<MListItemViewModel> {

    @Inject
    public MListViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
        loadData();
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void refresh() {

    }

    private void loadData() {

    }

    @Override
    public ItemViewSelector<MListItemViewModel> itemView() {
        return null;
    }

}