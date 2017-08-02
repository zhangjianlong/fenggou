package com.odbpo.fenggou.feature.search;

import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.messenger.Messenger;
import com.odbpo.fenggou.domain.bean.RecommendProductBean;
import com.odbpo.fenggou.domain.bean.SearchProductBean;
import com.odbpo.fenggou.feature.detail.DetailActivity;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author: zjl
 * @Time: 2017/7/14 10:39
 * @Desc:
 */

public class SearchItemViewModel extends BViewModel {

    public final ObservableField<SearchProductBean.DataBean> productData = new ObservableField<>();

    public SearchItemViewModel(SearchProductBean.DataBean dataBean) {
        this.productData.set(dataBean);
    }

    public final ReplyCommand productDetail = new ReplyCommand(() -> {
        Messenger.getDefault().sendNoMsg(MessageKey.PRODUCT_DETAIL);

    });

    public final ReplyCommand girdProductDeTail = new ReplyCommand(() -> {
        Messenger.getDefault().sendNoMsg(MessageKey.PRODUCT_DETAIL);

    });


}
