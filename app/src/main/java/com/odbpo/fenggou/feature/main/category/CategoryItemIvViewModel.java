package com.odbpo.fenggou.feature.main.category;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.messenger.Messenger;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.domain.bean.CategoryResultBean;
import com.odbpo.fenggou.domain.bean.ProductCategoryBean;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import rx.Observable;

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
