package com.slash.youth.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.slash.youth.R;
import com.slash.youth.databinding.ActivityRevisePasswordBinding;
import com.slash.youth.ui.viewmodel.MySkillManageModel;
import com.slash.youth.ui.viewmodel.RevisePassWordModel;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.LogKit;
import com.slash.youth.utils.ToastUtils;

/**
 * Created by zss on 2016/11/3.
 */
public class RevisePasswordActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private TextView save;
    private ActivityRevisePasswordBinding activityRevisePasswordBinding;
    private RevisePassWordModel revisePassWordModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRevisePasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_revise_password);
        revisePassWordModel = new RevisePassWordModel(activityRevisePasswordBinding);
        activityRevisePasswordBinding.setRevisePassWordModel(revisePassWordModel);
        listener();
    }

    private void listener() {
        findViewById(R.id.iv_userinfo_back).setOnClickListener(this);
        title = (TextView) findViewById(R.id.tv_userinfo_title);
        title.setText("修改交易密码");
        save = (TextView) findViewById(R.id.tv_userinfo_save);
        save.setOnClickListener(this);
        save.setText("确定");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_userinfo_back:
                finish();
                break;
            case R.id.tv_userinfo_save:
                String oldpass = revisePassWordModel.oldPassWordMap.get("oldpass");
                String newpass = revisePassWordModel.newPassWordMap.get("newpass");
                String surepass = revisePassWordModel.surePassWordMap.get("surepass");
                if(oldpass!=null &&newpass!=null && surepass!=null){
                    if(newpass.equals(surepass)){
                        revisePassWordModel.setPassWord(oldpass,newpass);
                    }else {
                        ToastUtils.shortToast("输入的新密码不一致,请重新输入");
                    }

                }else {
                    ToastUtils.shortToast("请填写完整");
                }
                finish();
                break;
        }
    }

}