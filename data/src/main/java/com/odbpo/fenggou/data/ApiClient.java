package com.odbpo.fenggou.data;


import com.odbpo.fenggou.data.api.BaseResponse;
import com.odbpo.fenggou.domain.bean.PhoneLoginResultBean;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * @author: zjl
 * @Time:  2017/6/1 15:12
 * @Desc: 
 */


public interface ApiClient {

    @POST(UriMethod.TOKEN_LOGIN)
    Observable<BaseResponse<PhoneLoginResultBean>> login(@Body RequestBody requestBody);


}

