package com.slash.youth.v2.feature.local;


import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.utils.FileUtil;
import com.core.op.lib.utils.JsonUtil;
import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.databinding.ActLabelBinding;
import com.slash.youth.databinding.ActLocalBinding;
import com.slash.youth.domain.bean.ProvinceBean;
import com.slash.youth.engine.Scheduler;
import com.slash.youth.v2.feature.main.find.FindItemViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.R.attr.data;
import static u.aly.av.P;
import static u.aly.av.ac;

@PerActivity
public class LocalViewModel extends BAViewModel<ActLocalBinding> {

    public String title;

    public final ItemView hotItemView = ItemView.of(BR.viewModel, R.layout.item_local_hot);
    public final ObservableList<LocalItemViewModel> hotItemViewModels = new ObservableArrayList<>();

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_local);
    public final ObservableList<LocalItemViewModel> itemViewModels = new ObservableArrayList<>();

    @Inject
    public LocalViewModel(RxAppCompatActivity activity) {
        super(activity);
        title = activity.getString(R.string.app_label_title);
    }

    @Override
    public void afterViews() {
        loadData();
    }

    private void loadData() {
        Observable.from(JsonUtil.GsonToList(FileUtil.readFileFromAssets(activity, "citys.json"), ProvinceBean.class))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    itemViewModels.add(new LocalItemViewModel(activity, data.getName()));
                }, error -> {

                });

        Observable.from(activity.getResources().getStringArray(R.array.hot_citys))
                .subscribe(data -> {
                    hotItemViewModels.add(new LocalItemViewModel(activity, data));
                }, error -> {

                });
    }
}