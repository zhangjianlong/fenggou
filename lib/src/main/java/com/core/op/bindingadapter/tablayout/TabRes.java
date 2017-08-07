package com.core.op.bindingadapter.tablayout;

import android.support.annotation.StringRes;

/**
 * @author: zjl
 * @Time: 2017/8/7 10:02
 * @Desc:
 */
public class TabRes {
    @StringRes
    private int textRes;

    private String tag;

    public int getTextRes() {
        return textRes;
    }

    public void setTextRes(int textRes) {
        this.textRes = textRes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public TabRes(int textRes, String tag) {
        this.textRes = textRes;
        this.tag = tag;

    }
}
