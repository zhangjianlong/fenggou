package com.slash.youth.data;

import com.slash.youth.data.api.BaseResponse;
import com.slash.youth.domain.bean.BannerConfigBean;
import com.slash.youth.domain.bean.ConversationBean;
import com.slash.youth.domain.bean.CustomerService;
import com.slash.youth.domain.bean.FindDemand;
import com.slash.youth.domain.bean.FindServices;
import com.slash.youth.domain.bean.HomeTagInfoBean;
import com.slash.youth.domain.bean.LoginResult;
import com.slash.youth.domain.bean.MineInfo;
import com.slash.youth.domain.bean.MineManagerList;
import com.slash.youth.domain.bean.OtherInfo;
import com.slash.youth.domain.bean.PersonRelation;
import com.slash.youth.domain.bean.StatusBean;
import com.slash.youth.domain.bean.TaskList;
import com.slash.youth.domain.bean.base.BaseList;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/8/8
 */

public interface ApiClient {

    @POST(UriMethod.GET_CUSTOMER_SERVICE)
    Observable<BaseResponse<CustomerService>> getCustomService(@Body RequestBody requestBody);


    @POST(UriMethod.TOKEN_LOGIN)
    Observable<BaseResponse<LoginResult>> login(@Body RequestBody requestBody);

    @POST(UriMethod.GET_BANNER_CONFIG)
    Observable<BannerConfigBean> getBanners(@Body RequestBody requestBody);

    @POST(UriMethod.HOME_TAG_CONFIG)
    Observable<HomeTagInfoBean> getTags(@Body RequestBody requestBody);

    @POST(UriMethod.GET_RECOMMEND_SERVICE2)
    Observable<BaseResponse<FindServices>> getFindServices(@Body RequestBody requestBody);

    @POST(UriMethod.GET_RECOMMEND_DEMAND2)
    Observable<BaseResponse<FindDemand>> getFindDemand(@Body RequestBody requestBody);

    @POST(UriMethod.GET_MY_TASK_LIST)
    Observable<BaseResponse<BaseList<TaskList.TaskBean>>> getTaskList(@Body RequestBody requestBody);


    @POST(UriMethod.MY_INFO)
    Observable<BaseResponse<MineInfo>> getMineInfo();


    @POST(UriMethod.MY_USERINFO)
    Observable<BaseResponse<OtherInfo>> getOtherInfo(@Body RequestBody requestBody);


    @POST(UriMethod.MANAGE_PUBLISH_LIST)
    Observable<BaseResponse<BaseList<MineManagerList.ListBean>>> getMineManagerList(@Body RequestBody requestBody);

    @POST(UriMethod.SKILL_MANAGE_DELETE)
    Observable<BaseResponse<StatusBean>> delManager(@Body RequestBody requestBody);


    @POST(UriMethod.UP_AND_DOWN_TASK)
    Observable<BaseResponse<StatusBean>> pubManager(@Body RequestBody requestBody);

    @POST(UriMethod.PERSON_RELATION_FIRST_PAGER)
    Observable<BaseResponse<PersonRelation>> getPersonRelation(@Body RequestBody requestBody);

    @POST(UriMethod.GET_CONVERSATION_LIST)
    Observable<BaseResponse<BaseList<ConversationBean>>> getConversationList(@Body RequestBody requestBody);


    @POST(UriMethod.DEL_CONVERSATION_LIST)
    Observable<BaseResponse<StatusBean>> delConversation(@Body RequestBody requestBody);
}

