package com.slash.youth.ui.holder;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slash.youth.ui.view.fly.RandomLayout;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.LogKit;
import com.slash.youth.utils.SpUtils;

/**
 * Created by zhouyifeng on 2016/9/7.
 */
public class LocationCityFirstLetterHolder extends BaseHolder<Character> {

    private TextView mTvLocationCityFirstLetter;
    public static int height;

    @Override
    public View initView() {
        mTvLocationCityFirstLetter = new TextView(CommonUtils.getContext());
        mTvLocationCityFirstLetter.setTextSize(12);
        mTvLocationCityFirstLetter.setPadding(CommonUtils.dip2px(3),CommonUtils.dip2px(3),CommonUtils.dip2px(3),CommonUtils.dip2px(3));
        mTvLocationCityFirstLetter.setTextColor(Color.parseColor("#999999"));
        mTvLocationCityFirstLetter.setGravity(Gravity.CENTER);
        return mTvLocationCityFirstLetter;
    }

    @Override
    public void refreshView(Character data) {
        mTvLocationCityFirstLetter.setText(data + "");
        getTextHight(mTvLocationCityFirstLetter);
    }

    private int getTextHight(TextView mTvLocationCityFirstLetter) {
        mTvLocationCityFirstLetter.measure(0,0);
        height = mTvLocationCityFirstLetter.getMeasuredHeight();
        return height;
    }
}
