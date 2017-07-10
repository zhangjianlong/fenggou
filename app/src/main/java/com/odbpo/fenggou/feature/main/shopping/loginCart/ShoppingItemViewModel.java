package com.odbpo.fenggou.feature.main.shopping.loginCart;

import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.odbpo.fenggou.domain.bean.RecommendProductBean;

/**
 * @author: zjl
 * @Time: 2017/7/10 13:55
 * @Desc:
 */

public class ShoppingItemViewModel extends BViewModel {
    public final ObservableField<RecommendProductBean> shopping = new ObservableField<>();


    public ShoppingItemViewModel(RecommendProductBean recommendProductBean) {
        this.shopping.set(recommendProductBean);
    }
}
