//package com.slash.youth.v2.feature.message.list;
//
//
//import com.core.op.bindingadapter.common.ItemView;
//import com.core.op.bindingadapter.common.ItemViewSelector;
//import com.core.op.lib.base.BFViewModel;
//import com.core.op.lib.di.PerActivity;
//import com.slash.youth.domain.bean.base.BaseList;
//import com.slash.youth.domain.interactor.UseCase;
//import com.slash.youth.v2.base.list.BaseListItemViewModel;
//import com.slash.youth.v2.base.list.BaseListViewModel;
//import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
//
//import javax.inject.Inject;
//
//@PerActivity
//public class MListViewModel extends BaseListViewModel<MListItemViewModel> {
//
//    @Inject
//    public MListViewModel(RxAppCompatActivity activity) {
//        super(activity);
//    }
//
//    @Override
//    public UseCase<BaseList<MListItemViewModel>> useCase() {
//        return null;
//    }
//
//    @Override
//    public void addData(Object o) {
//
//    }
//
//    @Override
//    public void doComplate() {
//
//    }
//
//    @Override
//    public int setItem(ItemView itemView, int position, BaseListItemViewModel item) {
//        return 0;
//    }
//}