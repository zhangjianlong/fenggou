package com.slash.youth.ui.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.slash.youth.R;
import com.slash.youth.databinding.ActivityPublishServiceBinding;
import com.slash.youth.ui.viewmodel.PublishServiceModel;

/**
 * Created by zhouyifeng on 2016/9/19.
 */
public class PublishServiceActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPublishServiceBinding activityPublishServiceBinding = DataBindingUtil.setContentView(this, R.layout.activity_publish_service);
        PublishServiceModel publishServiceModel = new PublishServiceModel(activityPublishServiceBinding);
        activityPublishServiceBinding.setPublishServiceModel(publishServiceModel);
    }
}
