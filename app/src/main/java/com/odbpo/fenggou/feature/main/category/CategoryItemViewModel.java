package com.odbpo.fenggou.feature.main.category;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import com.core.op.bindingadapter.common.ItemView;
import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.messenger.Messenger;
import com.odbpo.fenggou.BR;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.domain.bean.CategoryResultBean;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import rx.Observable;

import static android.R.attr.data;

/**
 * @author: zjl
 * @Time: 2017/6/19 12:22
 * @Desc:
 */

public class CategoryItemViewModel extends BViewModel {

    public int index;
    public ObservableField<Boolean> checked = new ObservableField<>();
    public ObservableField<String> content = new ObservableField<>();
    public ObservableField<String> senondContent = new ObservableField<>();

    public ObservableField<ColorDrawable> bgDrawable = new ObservableField<>();
    public ObservableField<Integer> textDrawable = new ObservableField<>();
    public final ObservableList<CategoryItemIvViewModel> thirdItemViewModels = new ObservableArrayList<>();
    public final ItemView thirdItemView = ItemView.of(BR.viewModel, R.layout.item_category_iv_layout);


    public CategoryItemViewModel(RxAppCompatActivity activity, String data, int index, boolean selected) {
        super(activity);
        this.content.set(data);
        this.index = index;

        if (selected) {
            bgDrawable.set(new ColorDrawable(activity.getResources().getColor(R.color.white)));
            textDrawable.set(activity.getResources().getColor(R.color.app_theme_colorPrimary));
        } else {
            bgDrawable.set(new ColorDrawable(activity.getResources().getColor(R.color.app_theme_background_f3f3f3)));
            textDrawable.set(activity.getResources().getColor(R.color.app_text_black999));
        }
    }


    public CategoryItemViewModel(RxAppCompatActivity activity, CategoryResultBean.Tag2 data) {
        super(activity);
        this.senondContent.set(data.getTagName());
        thirdItemViewModels.clear();
        getThirdLableBeens(data.getTag3()).subscribe(data1 -> {
            thirdItemViewModels.add(new CategoryItemIvViewModel(activity, data1));
        }, error -> {
        }, () -> {

        });


    }


    public CategoryItemViewModel() {


    }

    public ReplyCommand click = new ReplyCommand(() -> {
        Messenger.getDefault().send(index, MessageKey.LABEL_SELECT_STAIR);
    });


    private Observable<CategoryResultBean.Tag3> getThirdLableBeens(List<CategoryResultBean.Tag3> tag3) {
        return Observable.from(tag3);
    }
}
