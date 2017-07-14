package com.odbpo.fenggou.feature.search;

import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.odbpo.fenggou.domain.bean.RecommendProductBean;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author: zjl
 * @Time: 2017/7/14 10:39
 * @Desc:
 */

public class SearchItemViewModel extends BViewModel {

    public final ObservableField<RecommendProductBean> shopping = new ObservableField<>();

    public SearchItemViewModel(RecommendProductBean recommendProductBean) {
        this.shopping.set(recommendProductBean);
    }


}
