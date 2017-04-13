package com.slash.youth.v2.feature.main.order;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.rxjava.Transformers;
import com.core.op.lib.utils.DeviceUtil;
import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.v2.base.tab.TabViewModel;
import com.slash.youth.v2.feature.main.order.history.HistoryFragment;
import com.slash.youth.v2.feature.main.order.indent.IndentFragment;
import com.slash.youth.v2.feature.main.order.mission.MissionFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;

import static android.R.attr.data;
import static com.slash.youth.R.id.tabLayout;

@PerActivity
public class OrderViewModel extends TabViewModel<OrderTabViewModel> {

    @Inject
    public OrderViewModel(RxAppCompatActivity activity) {
        super(activity);
        pageLimit.set(3);
        titles.addAll(Arrays.asList(activity.getResources().getStringArray(R.array.order_tabs)));
        fragments.add(MissionFragment.instance());
        fragments.add(IndentFragment.instance());
        fragments.add(HistoryFragment.instance());

        items.add(new OrderTabViewModel(activity,"任务"));
        items.add(new OrderTabViewModel(activity,"订单"));
        items.add(new OrderTabViewModel(activity,"历史"));
    }

    @Override
    public void afterViews() {
        super.afterViews();
        Log.i("ytp " , "afterViews");
        scrollEnable.set(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("ytp " , "onstart");
        binding.tabLayout.setPadding(0,DeviceUtil.dip2px(activity,25),0,0);

//        Observable.from(items)
//                .compose(Transformers.mapWithIndex())
//                .subscribe(data->{
//                    ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(activity),itemView.layoutRes(),binding.tabLayout,false);
//                    viewDataBinding.setVariable(itemView.bindingVariable(),data.value());
//                    binding.tabLayout.getTabAt((int)data.index()).setCustomView(viewDataBinding.getRoot());
//                });

//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(DeviceUtil.dip2px(activity,50),0,DeviceUtil.dip2px(activity,50),0);
//        binding.tabLayout.setLayoutParams(layoutParams);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("ytp " , "onResume");
    }

    @Override
    protected ItemView tabItemView() {
        return ItemView.of(BR.viewModel, R.layout.item_order_tab);
    }
}