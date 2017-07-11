package com.odbpo.fenggou.feature.nickname;


import android.databinding.ObservableField;

import com.core.op.Static;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.ActNicknameBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class NicknameViewModel extends BAViewModel<ActNicknameBinding> {


    @Inject
    public NicknameViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }

    public ReplyCommand save = new ReplyCommand(() -> {

    });


    public final ObservableField<String> toolTitle = new ObservableField<>(Static.CONTEXT.getString(R.string.app_nick_name_set_hint));
    public ObservableField<String> nickName = new ObservableField<>();

}