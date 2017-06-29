package com.odbpo.fenggou.feature.main.info;

import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.odbpo.fenggou.domain.bean.RecommendProductBean;

/**
 * @author: zjl
 * @Time: 2017/6/27 10:46
 * @Desc:
 */

public class InfoItemViewModel extends BViewModel {

    public final ObservableField<RecommendProductBean> recommendProduct = new ObservableField<>();


    public InfoItemViewModel(RecommendProductBean recommendProductBean) {
        recommendProduct.set(recommendProductBean);
    }
}
