package com.odbpo.fenggou.feature.register;


import android.databinding.ObservableField;

import com.core.op.Static;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.ActRegisterBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.Observable;

import javax.inject.Inject;

@PerActivity
public class RegisterViewModel extends BAViewModel<ActRegisterBinding> {


    @Inject
    public RegisterViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }

    public ObservableField<String> title = new ObservableField<>(Static.CONTEXT.getString(R.string.app_main_register));
    public ObservableField<Boolean> agreeAgreement = new ObservableField<>(false);

    public final ReplyCommand agreeProtocol = new ReplyCommand(() -> {
        if (agreeAgreement.get()) {
            agreeAgreement.set(false);
        } else {
            agreeAgreement.set(true);
        }

    });

}