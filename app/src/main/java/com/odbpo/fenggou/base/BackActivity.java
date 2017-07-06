package com.odbpo.fenggou.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.core.op.lib.base.BAViewModel;

/**
 * @author: zjl
 * @Time: 2017/6/1 15:12
 * @Desc:
 */

public abstract class BackActivity<V extends BAViewModel, T extends ViewDataBinding> extends BaseActivity<V, T> {

    @Override
    protected void initAfterView() {

        setToolBar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        super.initAfterView();
    }

    protected abstract Toolbar setToolBar();
}
