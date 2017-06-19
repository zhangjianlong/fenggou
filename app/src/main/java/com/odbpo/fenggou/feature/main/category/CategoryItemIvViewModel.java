package com.odbpo.fenggou.feature.main.category;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BViewModel;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.domain.bean.CategoryResultBean;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import rx.Observable;

/**
 * @author: zjl
 * @Time: 2017/6/19 17:00
 * @Desc:
 */

public class CategoryItemIvViewModel extends BViewModel {
    public ObservableField<String> imageUri = new ObservableField<>();
    public ObservableField<String> thirdContent = new ObservableField<>();


    public CategoryItemIvViewModel(RxAppCompatActivity activity, CategoryResultBean.Tag3 tag3) {
        super(activity);
        this.thirdContent.set(tag3.getTagName());
        this.imageUri.set(tag3.getImageUrl());

    }


}
