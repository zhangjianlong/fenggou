package com.odbpo.fenggou.feature.main.login;

import android.databinding.ObservableField;

import com.core.op.lib.command.ReplyCommand;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseFragment;
import com.odbpo.fenggou.databinding.FrgLoginBinding;
import com.odbpo.fenggou.di.components.MainComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;

import javax.inject.Inject;

/**
 * @author: zjl
 * @Time: 2017/6/16 10:06
 * @Desc: 登录 页面
 */

@RootView(R.layout.frg_login)
public final class LoginFragment extends BaseFragment<LoginViewModel, FrgLoginBinding> {

    public static LoginFragment instance() {
        return new LoginFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(MainComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
    }

}
