package com.odbpo.fenggou.feature.username;


import android.databinding.ObservableField;

import com.core.op.Static;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.StrUtil;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.ActUsernameBinding;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class UsernameViewModel extends BAViewModel<ActUsernameBinding> {


    @Inject
    public UsernameViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }

    public ObservableField<String> toolTitle = new ObservableField<>(Static.CONTEXT.getString(R.string.app_user_name_title));
    public ObservableField<String> userName = new ObservableField<>();



    public ReplyCommand save = new ReplyCommand(() -> {
        if (StrUtil.isEmpty(userName.get())) {
            return;
        }
        Messenger.getDefault().send(userName.get(), MessageKey.SAVE_NAME);
        activity.finish();
    });

}