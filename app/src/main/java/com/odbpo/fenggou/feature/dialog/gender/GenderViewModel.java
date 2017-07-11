package com.odbpo.fenggou.feature.dialog.gender;


import com.core.op.lib.base.BDViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.databinding.DlgGenderBinding;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class GenderViewModel extends BDViewModel<DlgGenderBinding> {

    @Inject
    public GenderViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    public ReplyCommand cancel = new ReplyCommand(() -> {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    });

}