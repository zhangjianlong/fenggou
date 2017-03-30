package com.slash.youth.v2.feature.login;


import android.content.Intent;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.utils.AppToast;
import com.core.op.lib.utils.JsonUtil;
import com.core.op.lib.utils.PreferenceUtil;
import com.core.op.lib.utils.RxCountDown;
import com.core.op.lib.weight.progress.Progress;
import com.slash.youth.R;
import com.slash.youth.databinding.ActLoginBinding;
import com.slash.youth.domain.UserInfoBean;
import com.slash.youth.domain.bean.PhoneLoginResultBean;
import com.slash.youth.domain.interactor.login.LoginResultUseCase;
import com.slash.youth.domain.interactor.login.PhoneLoginUseCase;
import com.slash.youth.domain.interactor.login.VerifyUseCase;
import com.slash.youth.engine.LoginManager;
import com.slash.youth.engine.MsgManager;
import com.slash.youth.engine.UserInfoEngine;
import com.slash.youth.http.protocol.BaseProtocol;
import com.slash.youth.ui.activity.*;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.CustomEventAnalyticsUtils;
import com.slash.youth.utils.LogKit;
import com.slash.youth.utils.LoginCheckUtil;
import com.slash.youth.utils.SpUtils;
import com.slash.youth.utils.ToastUtils;
import com.slash.youth.v2.feature.main.MainActivity;
import com.slash.youth.v2.util.ShareKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscriber;


@PerActivity
public class LoginViewModel extends BAViewModel<ActLoginBinding> {
    public final ObservableField<String> phoneNum = new ObservableField<>();
    public final ObservableField<String> verifyNum = new ObservableField<>();
    public final ObservableField<String> sendVerifyText = new ObservableField<>(CommonUtils.getContext().getString(R.string.app_login_verify));
    public final ObservableField<Boolean> agreeAgreement = new ObservableField<>(true);
    public final ObservableField<Boolean> sendVerifyEnable = new ObservableField<>(true);
    private String tempPhoneNum;
    private String tempVerifyNum;
    private Progress progress;
    VerifyUseCase verifyUseCase;
    PhoneLoginUseCase  phoneLoginCase;


    public final ReplyCommand help = new ReplyCommand(() -> {

    });

    public final ReplyCommand qqLogin = new ReplyCommand(() -> {

    });

    public final ReplyCommand weixinLogin = new ReplyCommand(() -> {

    });

    public final ReplyCommand protocol = new ReplyCommand(() -> {

    });

    public final ReplyCommand agreeProtocol = new ReplyCommand(() -> {

    });

    public final ReplyCommand login = new ReplyCommand(() -> {
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.REGISTER_CLICK_ENTER);
        tempVerifyNum = verifyNum.get();
        tempPhoneNum = phoneNum.get();
        if (!LoginCheckUtil.checkLogin(tempPhoneNum,tempVerifyNum,agreeAgreement.get())){
            return;
        }
        tempVerifyNum = tempVerifyNum.trim();
        tempPhoneNum = tempPhoneNum.trim();
        progress = Progress.create(activity).setStyle(Progress.Style.SPIN_INDETERMINATE);
        progress.show();
        Map<String,String> map = new HashMap<>();
        map.put("phone",tempPhoneNum);
        map.put("pin",tempVerifyNum);
        map.put("3pToken","");
        map.put("userInfo","");
        phoneLoginCase.setParams(JsonUtil.mapToJson(map));
             phoneLoginCase.execute().compose(activity.bindToLifecycle()).subscribe(data->{
                 if (data!=null&&data.getData()==null){
                     ToastUtils.shortToast(CommonUtils.getContext().getString(R.string.app_login_fail));
                     return;
                 }
                 PreferenceUtil.write(CommonUtils.getContext(), ShareKey.USER_PHONE, tempPhoneNum);
                 LoginManager.currentLoginUserPhone = tempPhoneNum;
                 int rescode = data.getRescode();
                 PhoneLoginResultBean.Data  loginRes = data.getData();
                 String rongToken = loginRes.getRongToken();//融云token
                 String token = loginRes.getToken();
                 long uid = loginRes.getUid();
                 switch (rescode){
                     case 0:
                         //登陆成功，老用户
                         savaLoginState(uid, token, rongToken);
                         //链接融云
                         MsgManager.connectRongCloud(rongToken);
                         SpUtils.setBoolean("showMoreDemandDialog", false);
                         oldUserInfoCheck();
                         break;
                     case 7:
                         ToastUtils.shortToast(CommonUtils.getContext().getString(R.string.app_login_verify_code_error));
                         break;
                     case 11:
                         //登陆成功，新用户
                         savaLoginState(uid, token, rongToken);
                         //链接融云
                         MsgManager.connectRongCloud(rongToken);
                         SpUtils.setBoolean("showMoreDemandDialog", true);
                         Intent intentPerfectInfoActivity = new Intent(activity, PerfectInfoActivity.class);
                         activity.startActivity(intentPerfectInfoActivity);
                         break;
                     default:
                         ToastUtils.shortToast(CommonUtils.getContext().getString(R.string.app_login_fail) );
                         break;
                 }
        },error->{
            progress.dismiss();
        },()->{
            progress.dismiss();
        });
    });


    public final ReplyCommand sendVerify = new ReplyCommand(() -> {
        tempPhoneNum= phoneNum.get();
        if (!LoginCheckUtil.checkPhoeNumFormat(tempPhoneNum)){
            return;
        }
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.REGISTER_CLICK_VERTIFYCODE);
        RxCountDown.countdown(60).compose(activity.bindToLifecycle()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                sendVerifyEnable.set(true);
                sendVerifyText.set(CommonUtils.getContext().getString(R.string.app_login_verify));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                sendVerifyEnable.set(false);
                sendVerifyText.set(integer+"S");
            }
        });

        tempPhoneNum = tempPhoneNum.trim();
        Map<String,String> map = new HashMap<>();
        map.put("phone",tempPhoneNum);
        verifyUseCase.setParams(JsonUtil.mapToJson(map));
        verifyUseCase.execute().compose(activity.bindToLifecycle())
                .subscribe(data->{
                   switch (data.getRescode()){
                       case 0:
                           AppToast.show(CommonUtils.getContext(), R.string.app_login_verify_code_suc);
                           break;
                       default:
                           AppToast.show(CommonUtils.getContext(), R.string.app_login_verify_code_fail);
                    }
                });
    });


    @Inject
    public LoginViewModel(RxAppCompatActivity activity,
                          VerifyUseCase verifyUseCase,PhoneLoginUseCase phoneLoginCase) {
        super(activity);
        this.verifyUseCase =verifyUseCase;
        this.phoneLoginCase = phoneLoginCase;
    }


    @Override
    public void afterViews() {

    }


    private void savaLoginState(long uid, String token, String rongToken) {
        LoginManager.currentLoginUserId = uid;
        LoginManager.token = token;
        LoginManager.rongToken = rongToken;
        SpUtils.setLong("uid", uid);
        SpUtils.setString("token", token);
        SpUtils.setString("rongToken", rongToken);
    }

    /**
     * 根据是否设置了个人信息（真实姓名）和技能标签来跳转到不同的页面
     */
    private void oldUserInfoCheck() {
        //这里需要真实姓名、用户的一级、二级和三级技能标签来做判断
        UserInfoEngine.getMyUserInfo(new BaseProtocol.IResultExecutor<UserInfoBean>() {
            @Override
            public void execute(UserInfoBean dataBean) {
                UserInfoBean.UInfo uinfo = dataBean.data.uinfo;
                String realName = uinfo.name;
                if (TextUtils.isEmpty(realName)) {
                    Intent intentPerfectInfoActivity = new Intent(activity, PerfectInfoActivity.class);
                    activity.startActivity(intentPerfectInfoActivity);
                } else {
                    String industry = uinfo.industry;
                    String direction = uinfo.direction;
                    String tag = uinfo.tag;
                    if (!TextUtils.isEmpty(industry) && !TextUtils.isEmpty(direction) && !TextUtils.isEmpty(tag)) {
                        Intent intentHomeActivity2 = new Intent(activity, MainActivity.class);
                        activity.startActivity(intentHomeActivity2);
                        if (com.slash.youth.ui.activity.LoginActivity.activity != null) {
                            com.slash.youth.ui.activity.LoginActivity.activity.finish();
                            com.slash.youth.ui.activity.LoginActivity.activity = null;
                        }
                    } else {
                        Intent intentChooseSkillActivity = new Intent(activity, ChooseSkillActivity.class);
                        activity.startActivity(intentChooseSkillActivity);
                    }
                }
            }

            @Override
            public void executeResultError(String result) {
                LogKit.v("获取个人信息失败:" + result);
            }
        });
    }


}