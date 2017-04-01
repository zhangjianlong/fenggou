package com.slash.youth.v2.feature.chat.list;

import android.databinding.ObservableField;

import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;

/**
 * Created by acer on 2017/3/31.
 */

public class ChatListItemViewModel extends BViewModel {

    //发送和接收消息的时间间隔  11月7日 14:17
    public final ObservableField<String> infoText = new ObservableField<>();

    //交换手机号码
    public final ObservableField<String> otherContactInfo = new ObservableField<>();
    public final ReplyCommand callToOther = new ReplyCommand(() -> {

    });
    //接受到的图片
    public final ObservableField<String> imgUrl = new ObservableField<>();
    //接受


}
