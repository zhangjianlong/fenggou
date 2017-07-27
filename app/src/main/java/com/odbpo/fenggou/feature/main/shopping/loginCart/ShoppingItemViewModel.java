package com.odbpo.fenggou.feature.main.shopping.loginCart;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.RelativeLayout;

import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.utils.StrUtil;
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
    public ObservableInt productNum = new ObservableInt(1);

    public final ReplyCommand checked = new ReplyCommand(() -> {
        if (isChecked.get()) {
            isChecked.set(false);
        } else {
            isChecked.set(true);
        }

    });

    public final ReplyCommand sub = new ReplyCommand(() -> {
        if (productNum.get() <= 1) {
            return;
        } else {
            productNum.set(productNum.get() - 1);
        }

    });
    public final ReplyCommand plus = new ReplyCommand(() -> {

        productNum.set(productNum.get() + 1);

    });


    public final ReplyCommand<String> afterTextChangedCommand = new ReplyCommand(s -> {
        if (StrUtil.isEmpty(s.toString())) {
            productNum.set(1);
        }
    });


}
