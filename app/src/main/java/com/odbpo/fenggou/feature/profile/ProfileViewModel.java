package com.odbpo.fenggou.feature.profile;


import android.databinding.ObservableField;

import com.core.op.Static;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.data.util.ShareKey;
import com.odbpo.fenggou.data.util.SpUtil;
import com.odbpo.fenggou.databinding.ActProfileBinding;
import com.odbpo.fenggou.domain.bean.base.CustomerInfo;
import com.odbpo.fenggou.feature.dialog.gender.GenderDialog;
import com.odbpo.fenggou.feature.nickname.NicknameActivity;
import com.odbpo.fenggou.feature.username.UsernameActivity;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import rx.Observable;

@PerActivity
public class ProfileViewModel extends BAViewModel<ActProfileBinding> {
    private GenderDialog genderDialog;


    @Inject
    public ProfileViewModel(RxAppCompatActivity activity, GenderDialog genderDialog) {
        super(activity);
        this.genderDialog = genderDialog;
    }

    @Override
    public void afterViews() {
        Messenger.getDefault().register(activity, MessageKey.SAVE_NAME, String.class, data -> {
            userName.set(data);

        });


    }


    public final ObservableField<CustomerInfo> customerInfo = new ObservableField<>((CustomerInfo) SpUtil.readObject(ShareKey.CUSTOMER_INFO));

    public final ObservableField<String> toolTitle = new ObservableField<>(Static.CONTEXT.getString(R.string.app_profile_title));
    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> show1 = new ObservableField<>();
    public ObservableField<String> userLevel = new ObservableField<>("普通会员");


    public ReplyCommand exit = new ReplyCommand(() -> {

    });
    public ReplyCommand setUserName = new ReplyCommand(() -> {
        UsernameActivity.instance(activity);

    });

    public ReplyCommand setNickName = new ReplyCommand(() -> {
        NicknameActivity.instance(activity);

    });

    public ReplyCommand setGender = new ReplyCommand(() -> {
        if (!genderDialog.isShowing()) {
            genderDialog.show();
        }

    });

}