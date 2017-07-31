package com.odbpo.fenggou.data.net;

/**
 * Created by hedong on 2016/4/19.
 */

/**
 * 用于捕获服务器约定的错误类型
 */
public class ResultException extends RuntimeException {
    private String code;
    private String message;

    public ResultException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
