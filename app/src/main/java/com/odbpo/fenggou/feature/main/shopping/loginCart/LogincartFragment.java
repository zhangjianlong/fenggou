package com.odbpo.fenggou.feature.main.shopping.loginCart;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.core.op.lib.utils.inject.AfterViews;
import com.core.op.lib.utils.inject.BeforeViews;
import com.core.op.lib.utils.inject.RootView;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.base.BaseFragment;
import com.odbpo.fenggou.databinding.FrgLogincartBinding;
import com.odbpo.fenggou.di.components.MainComponent;
import com.odbpo.fenggou.feature.register.RegisterActivity;

@RootView(R.layout.frg_logincart)
public final class LogincartFragment extends BaseFragment<LogincartViewModel, FrgLogincartBinding> {

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
                break;
        }

        return true;
    }
}
