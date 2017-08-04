package com.core.op.lib.weight.sortView;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.core.op.lib.R;

/**
 * @author: zjl
 * @Time: 2017/7/13 9:58
 * @Desc:
 */

public class SortRes {


    public String searchKey;


    public int getDesRes() {
        return desRes;
    }

    public void setDesRes(int desRes) {
        this.desRes = desRes;
    }

    public int getAesRes() {
        return aesRes;
    }

    public void setAesRes(int aesRes) {
        this.aesRes = aesRes;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;


    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }


    /**
     * 排序，order 0 表示升序；1表示降序
     */

    public final static int AES = 0;//升序
    public final static int DEFAULT = 2;//默认排序，无升降
    public final static int DES = 1;//降序

    @DrawableRes
    private int desRes = R.drawable.down;

    @DrawableRes
    private int aesRes = R.drawable.up;


    public int getTextRes() {
        return textRes;
    }

    public void setTextRes(int textRes) {
        this.textRes = textRes;
    }

    @StringRes
    private int textRes;


    private int sort = 1;


}
