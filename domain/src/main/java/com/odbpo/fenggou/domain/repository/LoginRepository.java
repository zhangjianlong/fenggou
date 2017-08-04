package com.odbpo.fenggou.domain.repository;


import com.odbpo.fenggou.domain.bean.LoginResponse;
import com.odbpo.fenggou.domain.bean.OrderNumBean;
import com.odbpo.fenggou.domain.bean.PhoneLoginResultBean;
import com.odbpo.fenggou.domain.bean.ProductCategoryBean;
import com.odbpo.fenggou.domain.bean.SearchProductBean;
import com.odbpo.fenggou.domain.bean.base.CustomerInfo;

import java.util.Map;

import rx.Observable;

/**
 * Created by acer on 2017/3/4.
 */

public interface LoginRepository {

    Observable<LoginResponse> login(Map map);

    Observable<CustomerInfo> getCustomerInfo();


    Observable<ProductCategoryBean> getProductCategory(Map maps);

    Observable<CustomerInfo> getHistory(Map maps);

    Observable<SearchProductBean> searchGoods(String str);

    Observable<String> getOrderNum(String def);
}
