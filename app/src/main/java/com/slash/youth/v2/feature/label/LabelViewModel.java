package com.slash.youth.v2.feature.label;


import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.databinding.ActLabelBinding;
import com.slash.youth.v2.feature.main.find.FindItemViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerActivity
public class LabelViewModel extends BAViewModel<ActLabelBinding> {

    public String title;

    public final ItemView secondItemView = ItemView.of(BR.viewModel, R.layout.item_label_hot);
    public final List<FindItemViewModel> secondItemViewModels = new ArrayList<>();

    public final ItemView itemView = ItemView.of(BR.viewModel, R.layout.item_label);
    public final List<FindItemViewModel> itemViewModels = new ArrayList<>();

    @Inject
    public LabelViewModel(RxAppCompatActivity activity) {
        super(activity);
        title = activity.getString(R.string.app_label_title);
    }

    @Override
    public void afterViews() {

    }
}