package com.odbpo.fenggou.feature.dialog.gender;

import android.view.Gravity;

import com.core.op.lib.base.BDialog;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.DlgGenderBinding;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.RootView;
import com.odbpo.fenggou.base.BaseDialog;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@RootView(R.layout.dlg_gender)
public final class GenderDialog extends BaseDialog<GenderViewModel, DlgGenderBinding> {

    @Inject
    public GenderDialog(RxAppCompatActivity activity, GenderViewModel viewModel) {
        super(BDialog.newDialog(activity)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(false), viewModel);
    }

    @AfterViews
    void afterViews() {
    }
}
