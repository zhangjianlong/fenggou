package com.odbpo.fenggou.feature.forget;


import android.databinding.ObservableField;

import com.core.op.Static;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.ActForgetBinding;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class ForgetViewModel extends BAViewModel<ActForgetBinding> {


    @Inject
    public ForgetViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {
    }

    public final ObservableField<String> title = new ObservableField<>(Static.CONTEXT.getString(R.string.app_forget_title));
    public final ObservableField<String> verifyText = new ObservableField<>(Static.CONTEXT.getString(R.string.app_forget_verify_text));

    public ReplyCommand submit = new ReplyCommand(() -> {
    });
    public ReplyCommand getVerifyCode = new ReplyCommand(() -> {
    });
}