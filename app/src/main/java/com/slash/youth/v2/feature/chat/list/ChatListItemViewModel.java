package com.slash.youth.v2.feature.chat.list;

import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by acer on 2017/3/31.
 */

public class ChatListItemViewModel extends BViewModel {

    public enum ChatType {
        SEND_TEXT,
        SEND_IMG,
        SEND_SHARE,
        RECEIVE_TEXT,
        RECEIVE_IMG,
        RECEIVE_SHARE,
        RECEIVE_PHONE,
        INFO,
        PHONE;
    }

    /*text显示内容*/
    public ObservableField<String> text = new ObservableField<>();

    public ChatListItemViewModel(RxAppCompatActivity activity) {
        super(activity);
    }
}
