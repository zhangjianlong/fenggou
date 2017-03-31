package com.slash.youth.v2.feature.bindingaccount;


import android.databinding.ObservableField;

import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.base.BViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.utils.AppToast;
import com.core.op.lib.utils.JsonUtil;
import com.core.op.lib.utils.PreferenceUtil;
import com.core.op.lib.utils.RxCountDown;
import com.core.op.lib.weight.progress.Progress;
import com.slash.youth.R;
import com.slash.youth.databinding.ActBindingBinding;
import com.slash.youth.domain.interactor.login.CheckBindingUseCase;
import com.slash.youth.domain.interactor.login.VerifyUseCase;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.LoginCheckUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscriber;

@PerActivity
public class BindingViewModel extends BAViewModel<ActBindingBinding> {
    public final ObservableField<String> phoneNum = new ObservableField<>();
    public final ObservableField<String> sendVerifyText = new ObservableField<>(CommonUtils.getContext().getString(R.string.app_binding_verify));
    public final ObservableField<Boolean> sendVerifyEnable = new ObservableField<>(true);
    public final ObservableField<String> verifyCode = new ObservableField<>();
    private String tempPhoneNum;
    private String tempVerifyCode;
    private VerifyUseCase verifyUseCase;
    private CheckBindingUseCase checkBindingUseCase;
    private Progress progress;

    public final ReplyCommand binding = new ReplyCommand(() -> {
        tempVerifyCode = verifyCode.get();
        tempPhoneNum = phoneNum.get();
        if (!LoginCheckUtil.checkBinding(tempPhoneNum, tempVerifyCode)) {
            return;
        }
        tempVerifyCode = tempVerifyCode.trim();
        tempPhoneNum = tempPhoneNum.trim();
        progress = Progress.create(activity).setStyle(Progress.Style.SPIN_INDETERMINATE);
        progress.show();
        Map<String, String> map = new HashMap<>();
        map.put("phone", tempPhoneNum);
        map.put("loginPlatform", "2");
        checkBindingUseCase.setParams(JsonUtil.mapToJson(map));
        checkBindingUseCase.execute().compose(activity.bindToLifecycle()).subscribe(data -> {
            switch (data.getRescode()) {
                //手机号已注册，未绑定
                case 1:
                    break;
                //手机号未注册
                case 10:
                    break;
                default:
                    break;
            }

        });


    });


    public final ReplyCommand sendVerify = new ReplyCommand(() -> {
        tempPhoneNum = phoneNum.get();
        if (!LoginCheckUtil.checkPhoeNumFormat(tempPhoneNum)) {
            return;
        }
        RxCountDown.countdown(60).compose(activity.bindToLifecycle()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                sendVerifyEnable.set(true);
                sendVerifyText.set(CommonUtils.getContext().getString(R.string.app_binding_verify));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                sendVerifyEnable.set(false);
                sendVerifyText.set(integer + "S");
            }
        });

        tempPhoneNum = tempPhoneNum.trim();
        Map<String, String> map = new HashMap<>();
        map.put("phone", tempPhoneNum);
        verifyUseCase.setParams(JsonUtil.mapToJson(map));
        verifyUseCase.execute().compose(activity.bindToLifecycle())
                .subscribe(data -> {
                    switch (data.getRescode()) {
                        case 0:
                            AppToast.show(CommonUtils.getContext(), R.string.app_login_verify_code_suc);
                            break;
                        default:
                            AppToast.show(CommonUtils.getContext(), R.string.app_login_verify_code_fail);
                    }
                });
    });
    

    @Inject
    public BindingViewModel(RxAppCompatActivity activity, CheckBindingUseCase checkBindingUseCase, VerifyUseCase verifyUseCase) {
        super(activity);
        this.verifyUseCase = verifyUseCase;
        this.checkBindingUseCase = checkBindingUseCase;
    }

    @Override
    public void afterViews() {

    }
}