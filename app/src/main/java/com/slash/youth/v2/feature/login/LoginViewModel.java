package com.slash.youth.v2.feature.login;


import android.databinding.ObservableField;
import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.utils.AppToast;
import com.core.op.lib.utils.JsonUtil;
import com.core.op.lib.weight.progress.Progress;
import com.slash.youth.R;
import com.slash.youth.databinding.ActLoginBinding;
import com.slash.youth.domain.interactor.login.LoginResultUseCase;
import com.slash.youth.domain.interactor.login.VerifyUseCase;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.CustomEventAnalyticsUtils;
import com.slash.youth.utils.LoginCheckUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;



@PerActivity
public class LoginViewModel extends BAViewModel<ActLoginBinding> {
    public final ObservableField<String> phoneNum = new ObservableField<>();
    public final ObservableField<String> verifyNum = new ObservableField<>();
    public final ObservableField<Boolean> agreeAgreement = new ObservableField<>(true);
    private String tempPhoneNum;
    private String tempVerifyNum;
    private Progress progress;
    VerifyUseCase verifyUseCase;
    LoginResultUseCase  phoneLoginCase;


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
        map.put("phone",tempPhoneNum);
        verifyUseCase.setParams(JsonUtil.mapToJson(map));
        phoneLoginCase.


    });


    public final ReplyCommand sendVerify = new ReplyCommand(() -> {
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.REGISTER_CLICK_VERTIFYCODE);
        tempPhoneNum= phoneNum.get();
        if (!LoginCheckUtil.checkPhoeNumFormat(tempPhoneNum)){
            return;
        }
        tempPhoneNum = tempPhoneNum.trim();
        Map<String,String> map = new HashMap<>();
        map.put("phone",tempPhoneNum);
        verifyUseCase.setParams(JsonUtil.mapToJson(map));
        verifyUseCase.execute().compose(activity.bindToLifecycle())
                .subscribe(data->{
                    if (null == data){
                        return;
                    }
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
                          VerifyUseCase verifyUseCase,LoginResultUseCase phoneLoginCase) {
        super(activity);
        this.verifyUseCase =verifyUseCase;
        this.phoneLoginCase = phoneLoginCase;
    }


    @Override
    public void afterViews() {

    }


}