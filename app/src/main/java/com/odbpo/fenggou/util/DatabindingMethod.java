package com.odbpo.fenggou.util;

import android.widget.Switch;

import com.core.op.Static;
import com.odbpo.fenggou.R;

/**
 * @author: zjl
 * @Time: 2017/7/31 15:18
 * @Desc:
 */
public class DatabindingMethod {

    public static String getGender(String tempGender) {
        String gender = "";
        switch (tempGender) {
            case "1":
                gender = Static.CONTEXT.getString(R.string.app_profile_boy);
                break;
            case "2":
                gender = Static.CONTEXT.getString(R.string.app_profile_girl);
                break;
            case "0":
                gender = Static.CONTEXT.getString(R.string.app_profile_secrecy);
                break;
            default:
                gender = Static.CONTEXT.getString(R.string.app_profile_secrecy);
                break;
        }

        return gender;

    }


    public static String getPrice(double price) {
        return "￥" + price;
    }
}
