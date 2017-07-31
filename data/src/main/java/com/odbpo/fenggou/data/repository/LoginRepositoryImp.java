package com.odbpo.fenggou.data.repository;


import com.odbpo.fenggou.data.ApiClient;
import com.odbpo.fenggou.data.api.transformer.ErrorTransformer;
import com.odbpo.fenggou.data.util.RetrofitUtil;
import com.odbpo.fenggou.domain.bean.LoginResponse;
import com.odbpo.fenggou.domain.bean.PhoneLoginResultBean;
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


}
