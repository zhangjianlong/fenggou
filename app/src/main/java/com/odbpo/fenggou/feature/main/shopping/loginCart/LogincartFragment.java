package com.odbpo.fenggou.feature.main.shopping.loginCart;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseFragment;
import com.odbpo.fenggou.databinding.FrgLogincartBinding;
import com.odbpo.fenggou.di.components.MainComponent;
import com.odbpo.fenggou.feature.register.RegisterActivity;
import com.odbpo.fenggou.util.MessageKey;

@RootView(R.layout.frg_logincart)
public final class LogincartFragment extends BaseFragment<LogincartViewModel, FrgLogincartBinding> {
    private boolean isEdit = false;

    public static LogincartFragment instance() {
        return new LogincartFragment();
    }

    @BeforeViews
    void beforViews() {
        getComponent(MainComponent.class).inject(this);
    }

    @AfterViews
    void afterViews() {
        setHasOptionsMenu(true);
        ((AppCompatActivity) activity).setSupportActionBar(binding.toolbar.toolbar);
        binding.toolbar.toolbar.setTitle("");
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.toolbar_edit, menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                isEdit = true;
                activity.invalidateOptionsMenu();
                Messenger.getDefault().send(isEdit, MessageKey.EDIT);
                break;
            case R.id.sure:
                isEdit = false;
                activity.invalidateOptionsMenu();
                Messenger.getDefault().send(isEdit, MessageKey.EDIT);
                break;
        }

        return true;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (isEdit) {
            menu.findItem(R.id.edit).setVisible(false);
            menu.findItem(R.id.sure).setVisible(true);
        } else {
            menu.findItem(R.id.edit).setVisible(true);
            menu.findItem(R.id.sure).setVisible(false);
        }


    }
}
