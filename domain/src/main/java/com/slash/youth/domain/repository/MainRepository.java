package com.slash.youth.domain.repository;

import com.slash.youth.domain.bean.BannerConfigBean;
import com.slash.youth.domain.bean.CountBean;
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
import com.slash.youth.domain.bean.UploadBean;
import com.slash.youth.domain.bean.UserEvaluateBean;
import com.slash.youth.domain.bean.UserTaskBean;
import com.slash.youth.domain.bean.UserVisibleBean;
import com.slash.youth.domain.bean.base.BaseList;
import com.slash.youth.domain.interfaces.OnProgressListener;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
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

    Observable<BaseList<UserTaskBean>> getUserTasks(String def);

    Observable<CountBean> getUserTaskCount(String def);

    Observable<BaseList<UserEvaluateBean>> getUserEvaluates(String def);

    Observable<StatusBean> getFriendsStatus(String def);

    Observable<UploadBean> uploadUserHead(String path, OnProgressListener onProgressListener);

    Observable<CountBean> getUserEvaluateCount(String def);

    Observable<StatusBean> addFriend(String def);

    Observable<StatusBean> agreeFriend(String def);

    Observable<StatusBean> removeFriend(String def);

    Observable<UserVisibleBean> getUserVisible();

}
