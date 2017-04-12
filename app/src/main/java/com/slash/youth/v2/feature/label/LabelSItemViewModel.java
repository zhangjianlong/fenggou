package com.slash.youth.v2.feature.label;

import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.messenger.Messenger;
import com.slash.youth.domain.bean.LabelBean;
import com.slash.youth.v2.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by acer on 2017/4/6.
 */

public class LabelSItemViewModel extends BViewModel {

    public LabelBean data;

    public int index;

    public ObservableField<Boolean> selected = new ObservableField<>();

    public ReplyCommand click = new ReplyCommand(()->{
        Messenger.getDefault().send(data.getId(),MessageKey.LABEL_SELECT_STAIR);
    });

    public LabelSItemViewModel(RxAppCompatActivity activity, LabelBean data, int index, boolean selected) {
        super(activity);
        this.data = data;
        this.selected.set(selected);
        this.index = index;
    }

    public void setSelected(boolean selected){
        this.selected.set(selected);
    }
}
