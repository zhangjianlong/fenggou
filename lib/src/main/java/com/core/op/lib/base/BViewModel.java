package com.core.op.lib.base;

import android.support.v4.app.FragmentManager;

import com.core.op.lib.messenger.Messenger;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * @author: zjl
 * @Time:  2017/6/1 15:18
 * @Desc:
 */

public class BViewModel<T> {

    protected RxAppCompatActivity activity;

    public BViewModel() {
        activity = null;
    }

    public BViewModel(RxAppCompatActivity activity) {
        this.activity = activity;
    }

    public RxAppCompatActivity getActivity() {
        return activity;
    }

    protected T binding;

    public void setBinding(T binding) {
        this.binding = binding;
    }

    protected void onStart() {

    }

    protected void onResume() {

    }

    protected void onPause() {

    }

    protected void onStop() {

    }

    protected void onDestroy() {
        Messenger.getDefault().unregister(this);
    }
}
