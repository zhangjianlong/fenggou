package com.slash.youth.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.slash.youth.R;
import com.slash.youth.databinding.ActivityTransactionRecordBinding;
import com.slash.youth.ui.viewmodel.MyAccountModel;
import com.slash.youth.ui.viewmodel.TransactionRecoreModel;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.LogKit;

/**
 * Created by zss on 2016/11/6.
 */
public class TransactionRecordActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ActivityTransactionRecordBinding activityTransactionRecordBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTransactionRecordBinding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_record);
        TransactionRecoreModel transactionRecoreModel = new TransactionRecoreModel(activityTransactionRecordBinding);
        activityTransactionRecordBinding.setTransactionRecoreModel(transactionRecoreModel);

        listener();
    }
    private void listener() {
        findViewById(R.id.iv_userinfo_back).setOnClickListener(this);
        title = (TextView) findViewById(R.id.tv_userinfo_title);
        title.setText("交易记录");
        findViewById(R.id.fl_title_right).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_userinfo_back:
                finish();
                break;
        }
    }
}