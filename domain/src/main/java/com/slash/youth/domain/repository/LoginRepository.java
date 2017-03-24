package com.slash.youth.domain.repository;

import com.slash.youth.domain.bean.CustomerService;
import com.slash.youth.domain.bean.LoginResult;
import com.slash.youth.domain.bean.ResCodeBean;
import com.slash.youth.domain.bean.StatusBean;

import rx.Observable;
import rx.observers.Observers;

/**
 * Created by acer on 2017/3/4.
 */

public interface LoginRepository {

    Observable<CustomerService> getCustomService(String def);

    Observable<LoginResult> login(String def);

    Observable<ResCodeBean> getVerifyCode(String def);
}
