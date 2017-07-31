package com.odbpo.fenggou.domain.bean;

/**
 * @author: zjl
 * @Time: 2017/7/31 12:41
 * @Desc:
 */
public class LoginResponse {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
}
