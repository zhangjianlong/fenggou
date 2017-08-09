package com.odbpo.fenggou.data.repository;


import com.odbpo.fenggou.data.ApiClient;
import com.odbpo.fenggou.data.api.transformer.ErrorTransformer;
import com.odbpo.fenggou.data.util.RetrofitUtil;
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
import com.odbpo.fenggou.domain.repository.LoginRepository;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author: zjl
 * @Time: 2017/6/1 15:15
 * @Desc:
 */

@Singleton
public class LoginRepositoryImp implements LoginRepository {

    ApiClient apiClient;

    @Inject
    public LoginRepositoryImp(ApiClient apiClient) {
        this.apiClient = apiClient;
    }


    @Override
    public Observable<LoginResponse> login(Map map) {
        return apiClient.login(RetrofitUtil.toFormDataRequestBody(map));
    }

    @Override
    public Observable<CustomerInfo> getCustomerInfo() {
        return apiClient.getCustomer();
    }


    @Override
    public Observable<ProductCategoryBean> getProductCategory(Map maps) {
        return apiClient.getProductCategory(maps);
    }

    @Override
    public Observable<CustomerInfo> getHistory(Map maps) {
        return apiClient.getHistory(maps);
    }

    @Override
    public Observable<SearchProductBean> searchGoods(String str) {
        return apiClient.searchGoods(RetrofitUtil.toRequestBody(str));
    }

    @Override
    public Observable<String> getOrderNum(String def) {
        return apiClient.getOrderNum(def);
    }


    @Override
    public Observable<LoginResponse> getNotification(Map map) {
        return apiClient.getNotification(map);
    }

    @Override
    public Observable<OrderListBean> getOrderList(String def, Map map) {
        return apiClient.getOrderList(def, map);
    }

    @Override
    public Observable<HistoryNumBean> getFollowNum() {
        return apiClient.getFollowNum();
    }

    @Override
    public Observable<HistoryNumBean> getHistoryNum() {
        return apiClient.getHistoryNum();
    }

    @Override
    public Observable<HistoryListBean> getHistoryList(Map map) {
        return apiClient.getHistoryList(map);
    }

    @Override
    public Observable<FollowListBean> getFollowList(Map map) {
        return apiClient.getFollowList(map);
    }


}
