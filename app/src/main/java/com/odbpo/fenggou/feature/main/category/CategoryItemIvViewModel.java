package com.odbpo.fenggou.feature.main.category;

import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.messenger.Messenger;
import com.odbpo.fenggou.domain.bean.CategoryResultBean;
import com.odbpo.fenggou.domain.bean.ProductCategoryBean;
import com.core.op.lib.utils.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * @author: zjl
 * @Time: 2017/6/19 17:00
 * @Desc:
 */

public class CategoryItemIvViewModel extends BViewModel {
    public ObservableField<ProductCategoryBean.DataBean> thirdContent = new ObservableField<>();


    public CategoryItemIvViewModel(RxAppCompatActivity activity, CategoryResultBean.Tag3 tag3) {
        super(activity);
        thirdContent.set(tag3.getDataBean());

    }


    public final ReplyCommand search = new ReplyCommand(() -> {
        Messenger.getDefault().send(thirdContent.get().getCateId(), MessageKey.SEARCH);

    });


}
