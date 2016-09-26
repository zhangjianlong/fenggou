package com.slash.youth.ui.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.slash.youth.R;
import com.slash.youth.databinding.ActivityPublishServiceModeBinding;
import com.slash.youth.ui.activity.MapActivity;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.ToastUtils;

/**
 * Created by zhouyifeng on 2016/9/20.
 */
public class PublishServiceModeModel extends BaseObservable {
    public static final int LOCATION_TYPE_MAP_LOCATION = 0;//地图定位
    public static final int LOCATION_TYPE_AREA_RANGE = 1;//区域范围
    public static final int OFFLINE_COST_TYPE_EMPLOYER = 2;//线下消费类型，雇主请客
    public static final int OFFLINE_COST_TYPE_AA = 3;//线下消费类型，AA制;

    ActivityPublishServiceModeBinding mActivityPublishServiceModeBinding;
    Activity mActivity;
    private final Bundle mPublishServiceDataBundle;
    boolean isOnlineService = true;//默认为线上服务
    int locationType = LOCATION_TYPE_MAP_LOCATION;//定位方式，默认为“地图定位”
    int offlineCostType = OFFLINE_COST_TYPE_EMPLOYER;//线下消费方式，默认为“雇主请客”
    boolean isTurnOnSafeBox = true;//是否开启保险箱，默认为开启
    boolean isTurnOn2YuanRewardPlan = true;//是否开启两元奖励计划，默认为true

    public PublishServiceModeModel(ActivityPublishServiceModeBinding activityPublishServiceModeBinding, Activity activity) {
        this.mActivity = activity;
        this.mActivityPublishServiceModeBinding = activityPublishServiceModeBinding;
        mPublishServiceDataBundle = mActivity.getIntent().getBundleExtra("publishServiceDataBundle");
        initView();
    }

    private void initView() {


    }

    public void goBack(View v) {
        mActivity.finish();
    }

    public void publishService(View v) {
        //这里应该调用服务端相关接口进行发布服务
        ToastUtils.shortToast("发布服务成功");
    }

    public void getDetailLocation(View v) {
        Intent intentMapActivity = new Intent(CommonUtils.getContext(), MapActivity.class);
        intentMapActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        CommonUtils.getContext().startActivity(intentMapActivity);
    }

    /**
     * 选择发布线上服务
     *
     * @param v
     */
    public void choosePublishOnlineService(View v) {
        mActivityPublishServiceModeBinding.tvPublishOnlineServiceText.setTextColor(0xff31C5E4);
        mActivityPublishServiceModeBinding.tvPublishOfflineServiceText.setTextColor(0xff666666);
        mActivityPublishServiceModeBinding.viewPublishOnlineServiceUnderline.setBackgroundColor(0xff31C5E4);
        mActivityPublishServiceModeBinding.viewPublishOfflineServiceUnderline.setBackgroundColor(0xffE5E5E5);
        RelativeLayout.LayoutParams paramsOnline = (RelativeLayout.LayoutParams) mActivityPublishServiceModeBinding.viewPublishOnlineServiceUnderline.getLayoutParams();
        paramsOnline.height = CommonUtils.dip2px(2);
        mActivityPublishServiceModeBinding.viewPublishOnlineServiceUnderline.setLayoutParams(paramsOnline);
        RelativeLayout.LayoutParams paramsOffline = (RelativeLayout.LayoutParams) mActivityPublishServiceModeBinding.viewPublishOfflineServiceUnderline.getLayoutParams();
        paramsOffline.height = CommonUtils.dip2px(1);
        mActivityPublishServiceModeBinding.viewPublishOfflineServiceUnderline.setLayoutParams(paramsOffline);

        isOnlineService = true;
    }

    /**
     * 选择发布线下服务
     *
     * @param v
     */
    public void choosePublishOfflineService(View v) {
        mActivityPublishServiceModeBinding.tvPublishOnlineServiceText.setTextColor(0xff666666);
        mActivityPublishServiceModeBinding.tvPublishOfflineServiceText.setTextColor(0xff31C5E4);
        mActivityPublishServiceModeBinding.viewPublishOnlineServiceUnderline.setBackgroundColor(0xffE5E5E5);
        mActivityPublishServiceModeBinding.viewPublishOfflineServiceUnderline.setBackgroundColor(0xff31C5E4);
        RelativeLayout.LayoutParams paramsOnline = (RelativeLayout.LayoutParams) mActivityPublishServiceModeBinding.viewPublishOnlineServiceUnderline.getLayoutParams();
        paramsOnline.height = CommonUtils.dip2px(1);
        mActivityPublishServiceModeBinding.viewPublishOnlineServiceUnderline.setLayoutParams(paramsOnline);
        RelativeLayout.LayoutParams paramsOffline = (RelativeLayout.LayoutParams) mActivityPublishServiceModeBinding.viewPublishOfflineServiceUnderline.getLayoutParams();
        paramsOffline.height = CommonUtils.dip2px(2);
        mActivityPublishServiceModeBinding.viewPublishOfflineServiceUnderline.setLayoutParams(paramsOffline);

        isOnlineService = false;
    }


    /**
     * 选择定位方式为地图定位
     *
     * @param v
     */
    public void checkMapLocation(View v) {
        mActivityPublishServiceModeBinding.ivServiceMaplocationChecked.setImageResource(R.mipmap.btn_employer_treat);
        mActivityPublishServiceModeBinding.ivServiceArearangeChecked.setImageResource(R.mipmap.ellipse_4);
        locationType = LOCATION_TYPE_MAP_LOCATION;
    }


    /**
     * 选择定位方式为区域范围
     *
     * @param v
     */
    public void checkAreaRange(View v) {
        mActivityPublishServiceModeBinding.ivServiceMaplocationChecked.setImageResource(R.mipmap.ellipse_4);
        mActivityPublishServiceModeBinding.ivServiceArearangeChecked.setImageResource(R.mipmap.btn_employer_treat);
        locationType = LOCATION_TYPE_AREA_RANGE;
    }

    /**
     * 选择线下消费方式为 雇主请客
     *
     * @param v
     */
    public void checkedOfflineCostTypeEmployer(View v) {
        mActivityPublishServiceModeBinding.ivOfflinecostEmployerChecked.setImageResource(R.mipmap.btn_employer_treat);
        mActivityPublishServiceModeBinding.ivOfflinecostAaChecked.setImageResource(R.mipmap.ellipse_4);

        offlineCostType = OFFLINE_COST_TYPE_EMPLOYER;
    }

    /**
     * 选择线下消费方式为 AA制
     *
     * @param v
     */
    public void checkedOfflineCostTypeAA(View v) {
        mActivityPublishServiceModeBinding.ivOfflinecostEmployerChecked.setImageResource(R.mipmap.ellipse_4);
        mActivityPublishServiceModeBinding.ivOfflinecostAaChecked.setImageResource(R.mipmap.btn_employer_treat);

        offlineCostType = OFFLINE_COST_TYPE_AA;
    }


    /**
     * 开关保险箱选项
     *
     * @param v
     */
    public void toggleSafeBox(View v) {
        if (isTurnOnSafeBox) {
            //关闭
            mActivityPublishServiceModeBinding.ivServiceSafeboxToggleBg.setImageResource(R.mipmap.safebox_toggle_off);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mActivityPublishServiceModeBinding.ivServiceSafeboxToggleHandle.getLayoutParams();
            params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mActivityPublishServiceModeBinding.ivServiceSafeboxToggleHandle.setLayoutParams(params);
        } else {
            //开启
            mActivityPublishServiceModeBinding.ivServiceSafeboxToggleBg.setImageResource(R.mipmap.background_safebox_toggle);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mActivityPublishServiceModeBinding.ivServiceSafeboxToggleHandle.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mActivityPublishServiceModeBinding.ivServiceSafeboxToggleHandle.setLayoutParams(params);
        }

        isTurnOnSafeBox = !isTurnOnSafeBox;

    }


    /**
     * 开关两元奖励计划
     *
     * @param v
     */
    public void toggle2yuanRewardPlan(View v) {
        if (isTurnOn2YuanRewardPlan) {
            //关闭
            mActivityPublishServiceModeBinding.iv2yuanRewardplanToggleBg.setImageResource(R.mipmap.safebox_toggle_off);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mActivityPublishServiceModeBinding.iv2yuanRewardplanToggleHandle.getLayoutParams();
            params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mActivityPublishServiceModeBinding.iv2yuanRewardplanToggleHandle.setLayoutParams(params);
        } else {
            //开启
            mActivityPublishServiceModeBinding.iv2yuanRewardplanToggleBg.setImageResource(R.mipmap.background_safebox_toggle);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mActivityPublishServiceModeBinding.iv2yuanRewardplanToggleHandle.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            mActivityPublishServiceModeBinding.iv2yuanRewardplanToggleHandle.setLayoutParams(params);
        }
        isTurnOn2YuanRewardPlan = !isTurnOn2YuanRewardPlan;
    }
}
