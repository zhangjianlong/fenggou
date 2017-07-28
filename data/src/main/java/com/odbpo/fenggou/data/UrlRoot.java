package com.odbpo.fenggou.data;

import com.odbpo.fenggou.data.util.StrUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: zjl
 * @Time: 2017/6/1 15:13
 * @Desc:
 */

public class UrlRoot {
    public static String filename = "assets/url.properties";

    public static String API_PATH = "http://10.33.180.12:8080/";

    public static void setApiPath() {
        Properties prop = new Properties();
        try {
            InputStream in = UrlRoot.class.getClassLoader().getResourceAsStream(filename);
            if (in != null) {
                prop.load(in);
                String url = prop.getProperty("API_PATH");
                if (!StrUtil.isEmpty(url)) {
                    API_PATH = url;
                }
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
