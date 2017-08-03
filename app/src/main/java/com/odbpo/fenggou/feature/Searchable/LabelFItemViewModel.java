package com.odbpo.fenggou.feature.Searchable;

import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.messenger.Messenger;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import rx.subjects.ReplaySubject;


/**
 * @author: zjl
 * @Time: 2017/7/7 9:19
 * @Desc:
 */


public class LabelFItemViewModel extends BViewModel {

    public ObservableField<String> data = new ObservableField<>();


    public ReplyCommand searchHistory = new ReplyCommand(() -> {
        Messenger.getDefault().send(data.get(), MessageKey.SEARCH_KEY);
        activity.finish();
    });


    public int index;


    public LabelFItemViewModel(RxAppCompatActivity activity, String data, int index) {
        super(activity);
        this.data.set(data);
        this.index = index;
    }
}
