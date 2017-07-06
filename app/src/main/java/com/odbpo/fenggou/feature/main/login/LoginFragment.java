package com.odbpo.fenggou.feature.main.login;

import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.core.op.lib.command.ReplyCommand;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseFragment;
import com.odbpo.fenggou.databinding.FrgLoginBinding;
import com.odbpo.fenggou.di.components.MainComponent;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;
import com.odbpo.fenggou.feature.register.RegisterActivity;

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
        setHasOptionsMenu(true);
        ((AppCompatActivity) activity).setSupportActionBar(binding.toolbar.toolbar);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_register, menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.register:
                RegisterActivity.instance(activity);
                break;
        }

        return true;
    }
}
