package com.odbpo.fenggou.data.util;

import java.util.Iterator;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by acer on 2017/3/6.
 */

public class RetrofitUtil {

    public static RequestBody toRequestBody(String body) {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);
    }


    public static RequestBody toFormDataRequestBody(Map map) {
        return getStringRequestBody(map);
    }


    /**
     * 添加文本请求体参数
     */
    @SuppressWarnings("rawtypes")
    public static RequestBody getStringRequestBody(Map<?, ?> stringBodyMap) {
        FormBody.Builder multipartBodyBuilder = new FormBody.Builder();
        if ((stringBodyMap == null) || (stringBodyMap.size() <= 0)) {
            return null;
        }
        if ((stringBodyMap == null) || (stringBodyMap.size() <= 0)) {
            return null;
        }
        /*解析文本请求体*/
        if ((stringBodyMap != null) && (stringBodyMap.size() > 0)) {
            Iterator iterator = stringBodyMap.entrySet().iterator();
            while (iterator.hasNext()) {
                java.util.Map.Entry entry = (java.util.Map.Entry) iterator.next();
                multipartBodyBuilder.add(entry.getKey() + "", entry.getValue() + "");
            }
        }
        return multipartBodyBuilder.build();
    }
}


