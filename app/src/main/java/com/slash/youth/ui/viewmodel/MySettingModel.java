package com.slash.youth.ui.viewmodel;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.slash.youth.R;
import com.slash.youth.databinding.ActivityMySettingBinding;
import com.slash.youth.domain.SetBean;
import com.slash.youth.domain.SetMsgBean;
import com.slash.youth.domain.SetTimeBean;
import com.slash.youth.global.GlobalConstants;
import com.slash.youth.http.protocol.BaseProtocol;
import com.slash.youth.http.protocol.LoginoutProtocol;
import com.slash.youth.http.protocol.MySettingProtocol;
import com.slash.youth.http.protocol.SetMsgProtocol;
import com.slash.youth.http.protocol.SetTimeProtocol;
import com.slash.youth.ui.activity.FindPassWordActivity;
import com.slash.youth.ui.activity.LoginActivity;
import com.slash.youth.ui.activity.MySettingActivity;
import com.slash.youth.ui.activity.RevisePasswordActivity;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.Constants;
import com.slash.youth.utils.LogKit;
import com.slash.youth.utils.SpUtils;

import java.util.HashMap;

/**
 * Created by zss on 2016/11/3.
 */
public class MySettingModel extends BaseObservable {
    private ActivityMySettingBinding activityMySettingBinding;
    private boolean isInstalment = false;//消息
    private boolean isInstalment1 = false;//时间
    private HashMap<String, String> paramsMap = new HashMap<>();
    private int status;
    private String endtime;
    private String starttime;
    private int status1;
    private int status2;
    private MySettingActivity mySettingActivity;
    private boolean isTimeOpen;//时间设置
    private boolean isMsgOpen;//消息设置
    private String title = "找回交易密码";

    public MySettingModel(ActivityMySettingBinding activityMySettingBinding,MySettingActivity mySettingActivity) {
        this.activityMySettingBinding = activityMySettingBinding;
        this.mySettingActivity  = mySettingActivity;
        initView();
        initData();

    }

    private void initView() {
        if(SpUtils.getBoolean("create_ok",false)){
            activityMySettingBinding.viewRevise.setVisibility(View.VISIBLE);
            activityMySettingBinding.rlRevise.setVisibility(View.VISIBLE);
            activityMySettingBinding.tvSetAndfindPassword.setText(title);
        }
    }

    private void initData() {
        //第一次网络获取状态，展示时间
        getTimeState();
        //信息展示
        getMsgState();
    }

    private void getMsgState() {
        SetMsgProtocol setMsgProtocol = new SetMsgProtocol();
        setMsgProtocol.getDataFromServer(new BaseProtocol.IResultExecutor<SetMsgBean>() {
            @Override
            public void execute(SetMsgBean dataBean) {
                int rescode = dataBean.getRescode();
                if(rescode == 0){
                    SetMsgBean.DataBean data = dataBean.getData();
                    SetMsgBean.DataBean.DataBean1 data1 = data.getData();
                    int dnd1 = data1.getDnd();//1表示已经设置 0表示未设置
                    switchMsgDnd2Boolean(dnd1);
                   // setMsgState(activityMySettingBinding.ivPublishDemandInstalmentHandle,activityMySettingBinding.ivPublishDemandInstalmentBg,dnd);
                }
            }
            @Override
            public void executeResultError(String result) {
            LogKit.d("result:"+result);
            }
        });
    }

    private void getTimeState() {
        SetTimeProtocol setTimeProtocol = new SetTimeProtocol();
        setTimeProtocol.getDataFromServer(new BaseProtocol.IResultExecutor<SetTimeBean>() {
            @Override
            public void execute(SetTimeBean dataBean) {
                int rescode = dataBean.getRescode();
                if(rescode == 0){
                    SetTimeBean.DataBean data = dataBean.getData();
                    SetTimeBean.DataBean.DataBean1 data1 = data.getData();
                    int dnd = data1.getDnd(); //1表示已经设置 0表示未设置
                    switchTimeDnd2Boolean(dnd);
                    // setTimeState(activityMySettingBinding.ivPublishDemandInstalmentHandle1,activityMySettingBinding.ivPublishDemandInstalmentBg1,dnd);
                    endtime = data1.getEndtime();
                    starttime = data1.getStarttime();
                    activityMySettingBinding.tvTime.setText(starttime +"-"+ endtime);
                }
            }
            @Override
            public void executeResultError(String result) {
                LogKit.d("result:"+result);
            }
        });
    }

    //设置消息
    public void setMsg(View view){
        if(isMsgOpen){
            activityMySettingBinding.ivToggleOpen1.setVisibility(View.VISIBLE);
            activityMySettingBinding.ivToggleClose1.setVisibility(View.GONE);
            status2 = 1;
        }else {
            activityMySettingBinding.ivToggleOpen1.setVisibility(View.GONE);
            activityMySettingBinding.ivToggleClose1.setVisibility(View.VISIBLE);
            status2 = 0;
        }
        isMsgOpen = !isMsgOpen;
        //点击状态，传回后端保存
        paramsMap.put("status",String.valueOf(status2));
        int state = setData(GlobalConstants.HttpUrl.SET_MSG_SET, paramsMap);
    }

    //点击设置时间
    public void setTime(View view){
        if(isTimeOpen){
            activityMySettingBinding.ivToggleOpen.setVisibility(View.VISIBLE);
            activityMySettingBinding.ivToggleClose.setVisibility(View.GONE);
            status1 = 1;
        }else {
            activityMySettingBinding.ivToggleOpen.setVisibility(View.GONE);
            activityMySettingBinding.ivToggleClose.setVisibility(View.VISIBLE);
            status1 = 0;
        }
        isTimeOpen = !isTimeOpen;
        //点击状态，传回后端保存
        paramsMap.put("status",String.valueOf(status1));
        int state = setData(GlobalConstants.HttpUrl.SET_MSG_SET, paramsMap);
    }

    //设置初始化状态
    public void setOriginalTimeState(boolean isOpen){
        if(isOpen){
            activityMySettingBinding.ivToggleOpen.setVisibility(View.VISIBLE);
            activityMySettingBinding.ivToggleClose.setVisibility(View.GONE);
        }else {
            activityMySettingBinding.ivToggleOpen.setVisibility(View.GONE);
            activityMySettingBinding.ivToggleClose.setVisibility(View.VISIBLE);
        }
    }

    //设置初始化状态
    public void setOriginalMsgState(boolean isOpen){
        if(isOpen){
            activityMySettingBinding.ivToggleOpen1.setVisibility(View.VISIBLE);
            activityMySettingBinding.ivToggleClose1.setVisibility(View.GONE);
        }else {
            activityMySettingBinding.ivToggleOpen1.setVisibility(View.GONE);
            activityMySettingBinding.ivToggleClose1.setVisibility(View.VISIBLE);
        }
    }

    //选择设置dnd到boolean
    private void switchTimeDnd2Boolean(int dnd) {
        switch (dnd){
            case 0://未设置
                setOriginalTimeState(false);
                isTimeOpen = true;
                break;
            case 1://已设置
                setOriginalTimeState(true);
                isTimeOpen = false;
                break;
        }
    }

    private void switchMsgDnd2Boolean(int dnd) {
        switch (dnd){
            case 0://未设置
                setOriginalMsgState(false);
                isMsgOpen= true;
                break;
            case 1://已设置
                setOriginalMsgState(true);
                isMsgOpen = false;
                break;
        }
    }


    private String SET_OK ="设置成功";
    private String SET_FAIL = "设置失败";

    //设置数据
    private int setData(String url,HashMap<String, String> paramsMap) {
        MySettingProtocol mySettingProtocol = new MySettingProtocol(url,paramsMap);
        mySettingProtocol.getDataFromServer(new BaseProtocol.IResultExecutor<SetBean>() {
            @Override
            public void execute(SetBean dataBean) {
                int rescode = dataBean.rescode;
                SetBean.DataBean data = dataBean.getData();
                //获取状态
                status = data.getStatus();
                if(status==1){
                    LogKit.d(SET_OK);
                }else {
                    LogKit.d(SET_FAIL);
                }
            }

            @Override
            public void executeResultError(String result) {
               LogKit.d("result:"+result);
            }
        });
        return status;
    }

    //修改密码
    public void revisePassWord(View view){
        Intent intentRevisePasswordActivity = new Intent(CommonUtils.getContext(), RevisePasswordActivity.class);
        mySettingActivity.startActivityForResult(intentRevisePasswordActivity,2);
    }

    //设置密码
    public void findPassWord(View view){
        Intent intentFindPassWordActivity = new Intent(CommonUtils.getContext(), FindPassWordActivity.class);
        mySettingActivity.startActivityForResult(intentFindPassWordActivity, Constants.MYSETTING_SETPASSWORD);
    }

    //退出程序
    public void finishApp(View view){
        logout();
        Intent intentLoginActivity = new Intent(CommonUtils.getContext(), LoginActivity.class);
        mySettingActivity.startActivity(intentLoginActivity);
        mySettingActivity.finish();

        /*Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        mySettingActivity.startActivity(intent);*/
    }

    //登录退出的接口
    private void logout() {
        final LoginoutProtocol loginoutProtocol = new LoginoutProtocol();
        loginoutProtocol.getDataFromServer(new BaseProtocol.IResultExecutor() {
            @Override
            public void execute(Object dataBean) {
                LogKit.d("dataBean :"+dataBean.toString());
            }

            @Override
            public void executeResultError(String result) {
            LogKit.d("result:"+result);
            }
        });
    }


    //以前的代码
    //时间设置
    public void timeSetting(View view){
        RelativeLayout.LayoutParams layoutParams
                = (RelativeLayout.LayoutParams) activityMySettingBinding.ivPublishDemandInstalmentHandle1.getLayoutParams();
        if (isInstalment1) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            activityMySettingBinding.ivPublishDemandInstalmentBg1.setImageResource(R.mipmap.background_safebox_toggle_weijihuo);
            status1 = 0;
        } else {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            activityMySettingBinding.ivPublishDemandInstalmentBg1.setImageResource(R.mipmap.background_safebox_toggle);
            status1 = 1;
        }
        activityMySettingBinding.ivPublishDemandInstalmentHandle1.setLayoutParams(layoutParams);
        isInstalment1 = !isInstalment1;
        //点击状态，传回后端保存
        paramsMap.put("starttime",starttime);
        paramsMap.put("endtime",endtime);
        paramsMap.put("status",String.valueOf(status1));
        int state = setData(GlobalConstants.HttpUrl.SET_TIME_SET, paramsMap);
    }

    //得到初始化的状态
    private void setTimeState(ImageView v1,ImageView v2,int dnd) {
        switch (dnd){
            case 1://1表示已经设置
                clicktToggle(v1,v2,false);
                isInstalment1 = !isInstalment1;
                break;
            case 0://0表示未设置
                clicktToggle(v1,v2,true);
                break;
        }
    }

    //消息设置
    public void newsSetting(View view){
        RelativeLayout.LayoutParams layoutParams
                = (RelativeLayout.LayoutParams) activityMySettingBinding.ivPublishDemandInstalmentHandle.getLayoutParams();
        if (isInstalment) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            activityMySettingBinding.ivPublishDemandInstalmentBg.setImageResource(R.mipmap.background_safebox_toggle_weijihuo);
            status2 = 0;
        } else {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            activityMySettingBinding.ivPublishDemandInstalmentBg.setImageResource(R.mipmap.background_safebox_toggle);
            status2 = 1;
        }
        activityMySettingBinding.ivPublishDemandInstalmentHandle.setLayoutParams(layoutParams);
        isInstalment = !isInstalment;
        //点击状态，传回后端保存
        paramsMap.put("status",String.valueOf(status2));
        int state = setData(GlobalConstants.HttpUrl.SET_MSG_SET, paramsMap);
    }

    //得到初始化按钮的点击状态
    private void clicktToggle(ImageView v1, ImageView v2 , boolean isCheck) {
        RelativeLayout.LayoutParams layoutParams
                = (RelativeLayout.LayoutParams) v1.getLayoutParams();
        if (isCheck) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            v2.setImageResource(R.mipmap.background_safebox_toggle_weijihuo);
        } else {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            v2.setImageResource(R.mipmap.background_safebox_toggle);
        }
        v1.setLayoutParams(layoutParams);
    }

    private void setMsgState(ImageView v1,ImageView v2,int dnd) {
        switch (dnd){
            case 1://1表示已经设置
                clicktToggle(v1,v2,false);
                isInstalment = !isInstalment;
                break;
            case 0://0表示未设置
                clicktToggle(v1,v2,true);
                break;
        }
    }

}