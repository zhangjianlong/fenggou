package com.odbpo.fenggou.feature.detail;

import android.content.Intent;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.core.op.Static;
import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.utils.MyStateBarUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


public class FindBannerItemViewModel extends BViewModel {
    public ObservableField<String> url = new ObservableField<>();



    public final ReplyCommand click = new ReplyCommand(() -> {

    });


    public FindBannerItemViewModel(RxAppCompatActivity activity, String url) {
        super(activity);
        this.url.set(url);
    }
}
