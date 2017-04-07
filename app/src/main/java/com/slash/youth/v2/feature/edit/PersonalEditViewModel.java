package com.slash.youth.v2.feature.edit;


import android.databinding.ObservableField;

import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.utils.AppToast;
import com.core.op.lib.utils.JsonUtil;
import com.core.op.lib.utils.RxCountDown;
import com.slash.youth.R;
import com.slash.youth.databinding.ActPersonaleditBinding;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.CustomEventAnalyticsUtils;
import com.slash.youth.utils.LoginCheckUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscriber;

@PerActivity
public class PersonalEditViewModel extends BAViewModel<ActPersonaleditBinding> {
    public final ObservableField<String> title = new ObservableField<>(CommonUtils.getContext().getString(R.string.app_personal_title));
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> phoneNumber = new ObservableField<>();
    public final ObservableField<String> company = new ObservableField<>();
    public final ObservableField<String> companyPostion = new ObservableField<>();
    public final ObservableField<String> need = new ObservableField<>();
    public final ObservableField<String> provide = new ObservableField<>();
    public final ObservableField<String> area = new ObservableField<>();
    public final ObservableField<String> profile = new ObservableField<>();


    @Inject
    public PersonalEditViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }


    public final ReplyCommand setHead = new ReplyCommand(() -> {

    });


}