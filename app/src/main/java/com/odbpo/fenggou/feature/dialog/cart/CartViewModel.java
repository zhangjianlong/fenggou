package com.odbpo.fenggou.feature.dialog.cart;


import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.core.op.lib.base.BDViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.utils.MyStateBarUtil;
import com.odbpo.fenggou.databinding.DlgCartBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import static android.R.attr.id;

@PerActivity
public class CartViewModel extends BDViewModel<DlgCartBinding> {

    @Inject
    public CartViewModel(RxAppCompatActivity activity) {
        super(activity);
    }


    public ObservableInt height = new ObservableInt((MyStateBarUtil.getScreenHeight() * 4 / 5));
    public ObservableField uri = new ObservableField("http://img2.imgtn.bdimg.com/it/u=2790627569,4263420720&fm=214&gp=0.jpg");

    public final ReplyCommand cancel = new ReplyCommand(() -> {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    });

}