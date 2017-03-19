package com.slash.youth.domain.repository;

import com.slash.youth.domain.bean.BannerConfigBean;
import com.slash.youth.domain.bean.CustomerService;
import com.slash.youth.domain.bean.FindDemand;
import com.slash.youth.domain.bean.FindServices;
import com.slash.youth.domain.bean.HomeTagInfoBean;
import com.slash.youth.domain.bean.MineInfo;
import com.slash.youth.domain.bean.MineManagerList;
import com.slash.youth.domain.bean.OtherInfo;
import com.slash.youth.domain.bean.PersonRelation;
import com.slash.youth.domain.bean.StatusBean;
import com.slash.youth.domain.bean.TaskList;
import com.slash.youth.domain.bean.base.BaseList;

import rx.Observable;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/10/11
 */
public interface MainRepository {

    Observable<BannerConfigBean> getBanners(String def);

    Observable<HomeTagInfoBean> getTags(String def);

    Observable<FindServices> getFindServices(String def);

    Observable<FindDemand> getFindDemand(String def);

    Observable<BaseList<TaskList.TaskBean>> getTaskList(String def);

    Observable<OtherInfo> getOtherInfo(String def);

    Observable<MineInfo> getMineInfo();

    Observable<BaseList<MineManagerList.ListBean>> getMineManagerList(String def);

    Observable<StatusBean> delManager(String def);

    Observable<StatusBean> pubManager(String def);

    Observable<PersonRelation> getPersonRelation(String def);

}
