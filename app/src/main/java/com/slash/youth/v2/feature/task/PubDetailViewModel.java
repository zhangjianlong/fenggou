package com.slash.youth.v2.feature.task;


import android.databinding.ObservableField;

import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.R;
import com.slash.youth.databinding.ActPubdetailBinding;
import com.slash.youth.utils.CommonUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class PubDetailViewModel extends BAViewModel<ActPubdetailBinding> {
    public ObservableField<String> title = new ObservableField<>(CommonUtils.getContext().getString(R.string.app_pub_detail_title));


    @Inject
    public PubDetailViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }


    public ReplyCommand share = new ReplyCommand(() -> {

    });
}