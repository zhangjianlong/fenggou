package com.odbpo.fenggou.domain.repository;


import com.odbpo.fenggou.domain.bean.PhoneLoginResultBean;

import rx.Observable;

/**
 * Created by acer on 2017/3/4.
 */

public interface LoginRepository {
    Observable<PhoneLoginResultBean> login(String def);


}
