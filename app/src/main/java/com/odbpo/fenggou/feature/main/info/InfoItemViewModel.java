package com.odbpo.fenggou.feature.main.info;

import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.odbpo.fenggou.domain.bean.RecommendProductBean;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author: zjl
 * @Time: 2017/6/27 10:46
 * @Desc:
 */

public class InfoItemViewModel extends BViewModel {

    public final ObservableField<String> productName = new ObservableField<>();
    public final ObservableField<String> productPrice = new ObservableField<>();
    public final  ObservableField<String> productImage = new ObservableField<>();
    public final  ObservableField<RecommendProductBean> recommendProduct = new ObservableField<>();


    public InfoItemViewModel(RecommendProductBean recommendProductBean) {
        recommendProduct.set(recommendProductBean);
//        productName.set(recommendProductBean.getProductName());
//        productPrice.set(recommendProductBean.getProductPrice());
//        productImage.set(recommendProductBean.getProductUrl());
    }
}
