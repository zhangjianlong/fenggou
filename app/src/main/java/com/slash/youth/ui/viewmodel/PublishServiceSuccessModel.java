package com.slash.youth.ui.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.slash.youth.BR;
import com.slash.youth.databinding.ActivityPublishServiceSuccessBinding;
import com.slash.youth.domain.CommonResultBean;
import com.slash.youth.domain.RecommendDemandUserBean;
import com.slash.youth.engine.LoginManager;
import com.slash.youth.engine.ServiceEngine;
import com.slash.youth.engine.UserInfoEngine;
import com.slash.youth.http.protocol.BaseProtocol;
import com.slash.youth.ui.activity.ApprovalActivity;
import com.slash.youth.ui.activity.ServiceDetailActivity;
import com.slash.youth.ui.adapter.RecommendDemandAdapter;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.ToastUtils;

import java.text.SimpleDateFormat;

/**
 * Created by zhouyifeng on 2016/11/9.
 */
public class PublishServiceSuccessModel extends BaseObservable {

    ActivityPublishServiceSuccessBinding mActivityPublishServiceSuccessBinding;
    Activity mActivity;
    long serviceId;
    boolean isUpdate;

    public PublishServiceSuccessModel(ActivityPublishServiceSuccessBinding activityPublishServiceSuccessBinding, Activity activity) {
        this.mActivity = activity;
        this.mActivityPublishServiceSuccessBinding = activityPublishServiceSuccessBinding;
        initData();
        initView();
    }

    private void initData() {
        serviceId = mActivity.getIntent().getLongExtra("serviceId", -1);
        isUpdate = mActivity.getIntent().getBooleanExtra("isUpdate", false);
        getDataFromServer();
    }

    private void getDataFromServer() {
        UserInfoEngine.getUserAuthStatus(new BaseProtocol.IResultExecutor<CommonResultBean>() {
            @Override
            public void execute(CommonResultBean dataBean) {
                if (dataBean.data.status == 2) {
                    //已认证
                    setAuthLayerVisibility(View.VISIBLE);
                    setNoAuthLayerVisibility(View.GONE);
                    ServiceEngine.getRecommendDemandUser(new BaseProtocol.IResultExecutor<RecommendDemandUserBean>() {
                        @Override
                        public void execute(RecommendDemandUserBean dataBean) {
                            mActivityPublishServiceSuccessBinding.lvRecommendDemand.setAdapter(new RecommendDemandAdapter(dataBean.data.list));
                        }

                        @Override
                        public void executeResultError(String result) {
                            ToastUtils.shortToast("获取推荐的需求者失败");
                        }
                    }, serviceId + "", "5");
                } else {
                    //未认证
                    setAuthLayerVisibility(View.GONE);
                    setNoAuthLayerVisibility(View.VISIBLE);
                }
            }

            @Override
            public void executeResultError(String result) {
                //这里不会执行
            }
        });

//        ServiceEngine.getRecommendDemandUser();
    }

    private void initView() {
        if (isUpdate) {
            setUpdateSuccessHintVisibility(View.VISIBLE);
            setPublishSuccessHintVisibility(View.GONE);
            mActivityPublishServiceSuccessBinding.lvRecommendDemand.setVisibility(View.GONE);
            mActivityPublishServiceSuccessBinding.tvPublishServiceText.setText("修改服务");
            mActivityPublishServiceSuccessBinding.tvPublishSuccessText.setText("修改成功");

            setUpdateSuccessVisibility(View.VISIBLE);
            setPublishSuccessVisibility(View.GONE);
        } else {
            setUpdateSuccessHintVisibility(View.GONE);
            setPublishSuccessHintVisibility(View.VISIBLE);
            mActivityPublishServiceSuccessBinding.lvRecommendDemand.setVisibility(View.VISIBLE);

            setUpdateSuccessVisibility(View.GONE);
            setPublishSuccessVisibility(View.VISIBLE);
        }
        mActivityPublishServiceSuccessBinding.lvRecommendDemand.setVerticalScrollBarEnabled(false);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("展示有效期至yyyy年MM月dd日HH:mm");
        String displayValidityDatetime = simpleDateFormat.format(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
        mActivityPublishServiceSuccessBinding.tvDisplayValidityDatetime.setText(displayValidityDatetime);
        setAfterUpdateValidDate(displayValidityDatetime);
    }

    public void closeSuccessActivity(View v) {
        mActivity.finish();
    }

    //跳转到服务详情页
    public void gotoServiceDetail(View v) {
        Intent intentServiceDetailActivity = new Intent(CommonUtils.getContext(), ServiceDetailActivity.class);
        intentServiceDetailActivity.putExtra("serviceId", serviceId);
        mActivity.startActivity(intentServiceDetailActivity);
    }

    /**
     * 如果发服务者未认证，点击去认证
     *
     * @param v
     */
    public void gotoAuth(View v) {
        Intent intentApprovalActivity = new Intent(CommonUtils.getContext(), ApprovalActivity.class);
        intentApprovalActivity.putExtra("Uid", LoginManager.currentLoginUserId);
        mActivity.startActivity(intentApprovalActivity);
    }

    /**
     * 服务发布成功页面，点击“马上联系他们”，将服务分享给推荐的需求者
     *
     * @param v
     */
    public void shareTask(View v) {

    }

    private int publishSuccessHintVisibility;
    private int updateSuccessHintVisibility;
    private int authLayerVisibility;
    private int noAuthLayerVisibility;

    private int updateSuccessVisibility = View.GONE;
    private int publishSuccessVisibility;
    private String afterUpdateValidDate;

    @Bindable
    public int getPublishSuccessVisibility() {
        return publishSuccessVisibility;
    }

    public void setPublishSuccessVisibility(int publishSuccessVisibility) {
        this.publishSuccessVisibility = publishSuccessVisibility;
        notifyPropertyChanged(BR.publishSuccessVisibility);
    }

    @Bindable
    public String getAfterUpdateValidDate() {
        return afterUpdateValidDate;
    }

    public void setAfterUpdateValidDate(String afterUpdateValidDate) {
        this.afterUpdateValidDate = afterUpdateValidDate;
        notifyPropertyChanged(BR.afterUpdateValidDate);
    }

    @Bindable
    public int getUpdateSuccessVisibility() {
        return updateSuccessVisibility;
    }

    public void setUpdateSuccessVisibility(int updateSuccessVisibility) {
        this.updateSuccessVisibility = updateSuccessVisibility;
        notifyPropertyChanged(BR.updateSuccessVisibility);
    }

    @Bindable
    public int getNoAuthLayerVisibility() {
        return noAuthLayerVisibility;
    }

    public void setNoAuthLayerVisibility(int noAuthLayerVisibility) {
        this.noAuthLayerVisibility = noAuthLayerVisibility;
        notifyPropertyChanged(BR.noAuthLayerVisibility);
    }

    @Bindable
    public int getAuthLayerVisibility() {
        return authLayerVisibility;
    }

    public void setAuthLayerVisibility(int authLayerVisibility) {
        this.authLayerVisibility = authLayerVisibility;
        notifyPropertyChanged(BR.authLayerVisibility);
    }

    @Bindable
    public int getUpdateSuccessHintVisibility() {
        return updateSuccessHintVisibility;
    }

    public void setUpdateSuccessHintVisibility(int updateSuccessHintVisibility) {
        this.updateSuccessHintVisibility = updateSuccessHintVisibility;
        notifyPropertyChanged(BR.updateSuccessHintVisibility);
    }

    @Bindable
    public int getPublishSuccessHintVisibility() {
        return publishSuccessHintVisibility;
    }

    public void setPublishSuccessHintVisibility(int publishSuccessHintVisibility) {
        this.publishSuccessHintVisibility = publishSuccessHintVisibility;
        notifyPropertyChanged(BR.publishSuccessHintVisibility);
    }
}
