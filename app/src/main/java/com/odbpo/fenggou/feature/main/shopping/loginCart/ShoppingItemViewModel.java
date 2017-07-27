package com.odbpo.fenggou.feature.main.shopping.loginCart;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.odbpo.fenggou.databinding.ItemShoppingBinding;
import com.odbpo.fenggou.domain.bean.RecommendProductBean;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author: zjl
 * @Time: 2017/7/10 13:55
 * @Desc:
 */

public class ShoppingItemViewModel extends BViewModel {
    public final ObservableField<RecommendProductBean> shopping = new ObservableField<>();


    public ShoppingItemViewModel(RxAppCompatActivity activity, RecommendProductBean recommendProductBean) {
        super(activity);
        this.shopping.set(recommendProductBean);
    }


    public ObservableBoolean isChecked = new ObservableBoolean(false);
    public ObservableField<String> num = new ObservableField<String>("1");

    public final ReplyCommand checked = new ReplyCommand(() -> {
        num.set("2");
//        if (isChecked.get()) {
//            isChecked.set(false);
//        } else {
//            isChecked.set(true);
//        }
//        ((ItemShoppingBinding) binding).setViewModel(this);
//        ((ItemShoppingBinding) binding).executePendingBindings();

    });
}
