package com.core.op.lib.utils;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by acer on 2017/3/10.
 */

public class JsonUtil {

    public static String mapToJson(Map<String, String> map) {
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }
}
