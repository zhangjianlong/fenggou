package com.slash.youth.data.repository;


import com.slash.youth.data.ApiClient;
import com.slash.youth.data.api.transformer.ErrorTransformer;
import com.slash.youth.data.util.RetrofitUtil;
import com.slash.youth.domain.bean.BannerConfigBean;
import com.slash.youth.domain.bean.CountBean;
import com.slash.youth.domain.bean.FindDemand;
import com.slash.youth.domain.bean.FindServices;
import com.slash.youth.domain.bean.HomeTagInfoBean;
import com.slash.youth.domain.bean.MineInfo;
import com.slash.youth.domain.bean.MineManagerList;
import com.slash.youth.domain.bean.OtherInfo;
import com.slash.youth.domain.bean.PersonRelation;
import com.slash.youth.domain.bean.StatusBean;
import com.slash.youth.domain.bean.TaskList;
import com.slash.youth.domain.bean.UserEvaluateBean;
import com.slash.youth.domain.bean.UserTaskBean;
import com.slash.youth.domain.bean.base.BaseList;
import com.slash.youth.domain.repository.MainRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * @author op
 * @version 1.0
 * @description
 * @createDate 2016/10/11
 */
@Singleton
public class MainRepositoryImp implements MainRepository {

    ApiClient apiClient;


    @Inject
    public MainRepositoryImp(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public Observable<BannerConfigBean> getBanners(String def) {
        return apiClient.getBanners(RetrofitUtil.toRequestBody(def));
    }

    @Override
    public Observable<HomeTagInfoBean> getTags(String def) {
        return apiClient.getTags(RetrofitUtil.toRequestBody(def));
    }

    @Override
    public Observable<FindServices> getFindServices(String limit) {
        return apiClient.getFindServices(RetrofitUtil.toRequestBody(limit)).compose(new ErrorTransformer());
    }

    @Override
    public Observable<FindDemand> getFindDemand(String limit) {
        return apiClient.getFindDemand(RetrofitUtil.toRequestBody(limit)).compose(new ErrorTransformer());
    }

    @Override
    public Observable<BaseList<TaskList.TaskBean>> getTaskList(String def) {
        return apiClient.getTaskList(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer());
    }

    @Override
    public Observable<MineInfo> getMineInfo() {
        return apiClient.getMineInfo().compose(new ErrorTransformer<>());
    }

    @Override
    public Observable<OtherInfo> getOtherInfo(String def) {
        return apiClient.getOtherInfo(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }

    @Override
    public Observable<BaseList<MineManagerList.ListBean>> getMineManagerList(String def) {
        return apiClient.getMineManagerList(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }

    @Override
    public Observable<StatusBean> delManager(String def) {
        return apiClient.delManager(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }

    @Override
    public Observable<StatusBean> pubManager(String def) {
        return apiClient.pubManager(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }

    @Override
    public Observable<PersonRelation> getPersonRelation(String def) {
        return apiClient.getPersonRelation(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }

    @Override
    public Observable<BaseList<UserTaskBean>> getUserTasks(String def) {
        return apiClient.getUserTasks(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }

    @Override
    public Observable<CountBean> getUserTaskCount(String def) {
        return apiClient.getUserTaskCount(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }

    @Override
    public Observable<BaseList<UserEvaluateBean>> getUserEvaluates(String def) {
        return apiClient.getUserEvaluates(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }

    @Override
    public Observable<CountBean> getUserEvaluateCount(String def) {
        return apiClient.getUserEvaluateCount(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }

    @Override
    public Observable<StatusBean> getFriendsStatus(String def) {
        return apiClient.getFriendStatus(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }


    @Override
    public Observable<StatusBean> addFriend(String def) {
        return apiClient.addFriend(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }

    @Override
    public Observable<StatusBean> agreeFriend(String def) {
        return apiClient.agreeFriend(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }

    @Override
    public Observable<StatusBean> removeFriend(String def) {
        return apiClient.removeFriend(RetrofitUtil.toRequestBody(def)).compose(new ErrorTransformer<>());
    }
}
