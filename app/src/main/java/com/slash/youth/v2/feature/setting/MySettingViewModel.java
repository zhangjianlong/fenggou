package com.slash.youth.v2.feature.setting;


import android.view.View;

import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.di.PerActivity;
import com.slash.youth.databinding.ActivityMySettingBinding;
import com.slash.youth.domain.CommonResultBean;
import com.slash.youth.engine.AccountManager;
import com.slash.youth.http.protocol.BaseProtocol;
import com.slash.youth.utils.LogKit;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

@PerActivity
public class MySettingViewModel extends BAViewModel<ActivityMySettingBinding> {


    @Inject
    public MySettingViewModel(RxAppCompatActivity activity) {
        super(activity);
    }


    @Override
    public void afterViews() {
        testPassWord();
    }


    //判断是否有交易密码
    private void testPassWord() {
        AccountManager.getTradePasswordStatus(new BaseProtocol.IResultExecutor<CommonResultBean>() {
            @Override
            public void execute(CommonResultBean dataBean) {
                int rescode = dataBean.rescode;
                if (rescode == 0) {
                    int status = dataBean.data.status;
                    switch (status) {
                        case 1://1表示当前有交易密码

                            activityMySettingBinding.viewRevise.setVisibility(View.VISIBLE);
                            activityMySettingBinding.rlRevise.setVisibility(View.VISIBLE);
                            activityMySettingBinding.tvSetAndfindPassword.setText(findPassWord);
                            type = 1;
                            break;
                        case 2:// 2表示当前没有交易密码
                            activityMySettingBinding.viewRevise.setVisibility(View.GONE);
                            activityMySettingBinding.rlRevise.setVisibility(View.GONE);
                            activityMySettingBinding.tvSetAndfindPassword.setText(setPassWord);
                            type = 2;
                            break;
                        case 3://3有密码处于审核中
                            activityMySettingBinding.viewRevise.setVisibility(View.GONE);
                            activityMySettingBinding.rlRevise.setVisibility(View.GONE);
                            activityMySettingBinding.tvSetAndfindPassword.setText(setPassWord);
                            type = 3;
                            break;
                    }
                }
            }

            @Override
            public void executeResultError(String result) {
                LogKit.d("result：" + result);
            }
        });
    }
}