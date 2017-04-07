package com.slash.youth.v2.feature.edit;


import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;

import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.utils.AppToast;
import com.core.op.lib.utils.JsonUtil;
import com.core.op.lib.utils.PreferenceUtil;
import com.core.op.lib.utils.RxCountDown;
import com.core.op.lib.weight.imgselector.MultiImageSelector;
import com.slash.youth.R;
import com.slash.youth.data.Global;
import com.slash.youth.databinding.ActPersonaleditBinding;
import com.slash.youth.domain.bean.MineInfo;
import com.slash.youth.domain.interactor.main.MineInfoUseCase;
import com.slash.youth.domain.interactor.main.UserHeadUseCase;
import com.slash.youth.engine.DemandEngine;
import com.slash.youth.engine.LoginManager;
import com.slash.youth.global.GlobalConstants;
import com.slash.youth.ui.activity.ReplacePhoneActivity;
import com.slash.youth.ui.viewmodel.ActivityUserInfoEditorModel;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.Constants;
import com.slash.youth.utils.CountUtils;
import com.slash.youth.utils.CustomEventAnalyticsUtils;
import com.slash.youth.utils.LoginCheckUtil;
import com.slash.youth.utils.SpUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import org.xutils.image.ImageOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public final ObservableField<String> headUrl = new ObservableField<>();
    public final ObservableField<Boolean> man = new ObservableField<>(true);
    public final ObservableField<Boolean> woman = new ObservableField<>(false);
    public final ObservableField<Boolean> job = new ObservableField<>(true);
    public final ObservableField<Boolean> self = new ObservableField<>(false);

    private ArrayList<String> mSelectPath;
    private UserHeadUseCase userHeadUseCase;
    private MineInfoUseCase mineInfoUseCase;


    @Inject
    public PersonalEditViewModel(RxAppCompatActivity activity, UserHeadUseCase userHeadUseCase, MineInfoUseCase mineInfoUseCase) {
        super(activity);
        this.userHeadUseCase = userHeadUseCase;
        this.mineInfoUseCase = mineInfoUseCase;
    }

    @Override
    public void afterViews() {

    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    public final ReplyCommand setHead = new ReplyCommand(() -> {
        MultiImageSelector selector = MultiImageSelector.create(activity);
        selector.showCamera(true);
        selector.count(9);
        selector.single();
        selector.origin(mSelectPath);
        selector.setCrop(true);
        selector.start(activity, MultiImageSelector.REQUEST_IMAGE);
        //编辑头像的埋点
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MINE_EDIT_AVATAR);
    });


    public void uploadImage(String imgPath) {
        final File tempFile = new File(imgPath);
        userHeadUseCase.setParams(imgPath);
        userHeadUseCase.execute().compose(activity.bindToLifecycle()).subscribe(data -> {
        });
    }


    public final ReplyCommand setPhoneNumber = new ReplyCommand(() -> {
        //埋点
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MINE_CLICK_TELEPHONE_NUMBER);
        Intent replacePhoneActivity = new Intent(activity, ReplacePhoneActivity.class);
        activity.startActivityForResult(replacePhoneActivity, Constants.USERINFO_PHONE);
    });


    private void loadData() {
        mineInfoUseCase.execute().compose(activity.bindToLifecycle())
                .subscribe(d -> {
                    if (null == d) {
                        return;
                    }
                    MineInfo.DataBean data = d.getMyinfo();
                    phoneNumber.set(data.getPhone());
                    name.set(data.getName());
                    company.set(data.getCompany());
                    companyPostion.set(data.getPosition());
                    headUrl.set(data.getAvatar());
                }, error -> {

                });

    }

}