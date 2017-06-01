package com.odbpo.fenggou.data.api;

import com.odbpo.fenggou.data.api.BaseResponse;

import okhttp3.ResponseBody;

/**
 * @author: zjl
 * @Time:  2017/6/1 15:14
 * @Desc: 
 */

public class Response<T> {

    private okhttp3.Response response;
    private BaseResponse<T> body;
    private ResponseBody errorBody;

    public okhttp3.Response getResponse() {
        return response;
    }

    public void setResponse(okhttp3.Response response) {
        this.response = response;
    }

    public BaseResponse<T> getBody() {
        return body;
    }

    public void setBody(BaseResponse<T> body) {
        this.body = body;
    }

    public ResponseBody getErrorBody() {
        return errorBody;
    }

    public void setErrorBody(ResponseBody errorBody) {
        this.errorBody = errorBody;
    }
}
