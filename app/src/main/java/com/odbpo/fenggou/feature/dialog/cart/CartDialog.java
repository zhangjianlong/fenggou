package com.odbpo.fenggou.feature.dialog.cart;

import android.view.Gravity;

import com.core.op.lib.base.BDialog;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.DlgCartBinding;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.RootView;
import com.odbpo.fenggou.base.BaseDialog;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@RootView(R.layout.dlg_cart)
public final class CartDialog extends BaseDialog<CartViewModel, DlgCartBinding> {

    @Inject
    public CartDialog(RxAppCompatActivity activity, CartViewModel viewModel) {
        super(BDialog.newDialog(activity)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(false), viewModel);
    }

    @AfterViews
    void afterViews() {
    }
}
