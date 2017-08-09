package com.odbpo.fenggou.data;


import com.odbpo.fenggou.data.api.BaseResponse;
import com.odbpo.fenggou.domain.bean.FollowListBean;
import com.odbpo.fenggou.domain.bean.HistoryListBean;
import com.odbpo.fenggou.domain.bean.HistoryNumBean;
import com.odbpo.fenggou.domain.bean.LoginResponse;
import com.odbpo.fenggou.domain.bean.OrderListBean;
import com.odbpo.fenggou.domain.bean.OrderNumBean;
import com.odbpo.fenggou.domain.bean.PhoneLoginResultBean;
import com.odbpo.fenggou.domain.bean.ProductCategoryBean;
import com.odbpo.fenggou.domain.bean.SearchProductBean;
import com.odbpo.fenggou.domain.bean.base.CustomerInfo;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author: zjl
 * @Time: 2017/6/1 15:12
 * @Desc:
 */


public interface ApiClient {


    @POST(UriMethod.LOGIN)
    Observable<LoginResponse> login(@Body RequestBody requestBody);

    @GET(UriMethod.CUSTOMERS)
    Observable<CustomerInfo> getCustomer();

    @GET(UriMethod.PRODUCT_CATEGORY)
    Observable<ProductCategoryBean> getProductCategory(@QueryMap Map<String, String> maps);

    @GET(UriMethod.BROWSERECORD)
    Observable<CustomerInfo> getHistory(@QueryMap Map<String, String> maps);

    @POST(UriMethod.SEARCH_GOODS)
    Observable<SearchProductBean> searchGoods(@Body RequestBody requestBody);

    @GET(UriMethod.ORDER_NUM)
    Observable<String> getOrderNum(@Path("status") String status);

    @GET(UriMethod.SYSTEM_NOTIFICATION)
    Observable<LoginResponse> getNotification(@QueryMap Map<String, String> maps);

    @GET(UriMethod.ORDER_GOODSLIST)
    Observable<OrderListBean> getOrderList(@Path("status") String status, @QueryMap Map<String, String> maps);

    @GET(UriMethod.FOLLOW_NUM)
    Observable<HistoryNumBean> getFollowNum();

    @GET(UriMethod.HISTORY_NUM)
    Observable<HistoryNumBean> getHistoryNum();


    @GET(UriMethod.HISTORY_LIST)
    Observable<HistoryListBean> getHistoryList(@QueryMap Map<String, String> maps);

    @GET(UriMethod.FOLLOW_LIST)
    Observable<FollowListBean> getFollowList(@QueryMap Map<String, String> maps);
}

