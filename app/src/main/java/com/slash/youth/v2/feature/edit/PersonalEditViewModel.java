package com.slash.youth.v2.feature.edit;


import android.content.Intent;
import android.databinding.ObservableField;

import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.AppToast;
import com.core.op.lib.utils.JsonUtil;
import com.core.op.lib.utils.StrUtil;
import com.core.op.lib.weight.imgselector.MultiImageSelector;
import com.slash.youth.R;
import com.slash.youth.databinding.ActPersonaleditBinding;
import com.slash.youth.domain.bean.OtherInfo;
import com.slash.youth.domain.interactor.main.SaveCompanyUseCase;
import com.slash.youth.domain.interactor.main.MineInfoUseCase;
import com.slash.youth.domain.interactor.main.OtherInfoUseCase;
import com.slash.youth.domain.interactor.main.SaveInfoUseCase;
import com.slash.youth.domain.interactor.main.SaveNameUseCase;
import com.slash.youth.domain.interactor.main.SaveSexUseCase;
import com.slash.youth.domain.interactor.main.UserHeadUseCase;
import com.slash.youth.engine.LoginManager;
import com.slash.youth.global.GlobalConstants;
import com.slash.youth.ui.activity.ReplacePhoneActivity;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.Constants;
import com.slash.youth.utils.CustomEventAnalyticsUtils;
import com.slash.youth.v2.feature.local.LocalActivity;
import com.slash.youth.v2.feature.profile.ProfileActivity;
import com.slash.youth.v2.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

@PerActivity
public class PersonalEditViewModel extends BAViewModel<ActPersonaleditBinding> {
    public final ObservableField<String> title = new ObservableField<>(CommonUtils.getContext().getString(R.string.app_personal_title));
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> phoneNumber = new ObservableField<>();
    public final ObservableField<String> company = new ObservableField<>();
    public final ObservableField<String> companyPostion = new ObservableField<>();
    public final ObservableField<String> area = new ObservableField<>();
    public final ObservableField<String> profile = new ObservableField<>();
    public final ObservableField<String> headUrl = new ObservableField<>();
    public final ObservableField<Boolean> man = new ObservableField<>(true);
    public final ObservableField<Boolean> woman = new ObservableField<>(false);
    public final ObservableField<Boolean> job = new ObservableField<>(true);
    public final ObservableField<Boolean> self = new ObservableField<>(false);
    public final ObservableField<OtherInfo.UinfoBean> data = new ObservableField<>();

    private ArrayList<String> mSelectPath;
    private UserHeadUseCase userHeadUseCase;
    private MineInfoUseCase mineInfoUseCase;
    private OtherInfoUseCase infoUseCase;
    private SaveNameUseCase saveNameUseCase;
    private SaveSexUseCase saveSexUseCase;
    private SaveInfoUseCase saveInfoUseCase;
    private SaveCompanyUseCase saveCompanyUseCase;
    private String userName;
    private String userDesc;
    private String userPosition;
    private String userCompany;
    private int userSex;
    private int userCareertype;


    @Inject
    public PersonalEditViewModel(RxAppCompatActivity activity, UserHeadUseCase userHeadUseCase, MineInfoUseCase mineInfoUseCase, OtherInfoUseCase infoUseCase,
                                 SaveNameUseCase saveNameUseCase, SaveSexUseCase saveSexUseCase, SaveInfoUseCase saveInfoUseCase, SaveCompanyUseCase saveCompanyUseCase) {
        super(activity);
        this.userHeadUseCase = userHeadUseCase;
        this.mineInfoUseCase = mineInfoUseCase;
        this.infoUseCase = infoUseCase;
        this.saveNameUseCase = saveNameUseCase;
        this.saveSexUseCase = saveSexUseCase;
        this.saveInfoUseCase = saveInfoUseCase;
        this.saveCompanyUseCase = saveCompanyUseCase;
    }

    @Override
    public void afterViews() {
        Messenger.getDefault().register(this, MessageKey.PUB_CITY_SELECTED, String.class, data -> {
            area.set(data);
        });

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


    public final ReplyCommand setProfile = new ReplyCommand(() -> {
        Intent intent = new Intent(activity, ProfileActivity.class);
        activity.startActivityForResult(intent, Constants.USERINFO_PROFILE);
    });


    public void uploadImage(String imgPath) {
        userHeadUseCase.setParams(imgPath);
        userHeadUseCase.execute().compose(activity.bindToLifecycle()).subscribe(data -> {
        }, error -> {
        });
    }


    public final ReplyCommand setPhoneNumber = new ReplyCommand(() -> {
        //埋点
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MINE_CLICK_TELEPHONE_NUMBER);
        Intent replacePhoneActivity = new Intent(activity, ReplacePhoneActivity.class);
        activity.startActivityForResult(replacePhoneActivity, Constants.USERINFO_PHONE);
    });


    public final ReplyCommand setArea = new ReplyCommand(() -> {
        Intent replacePhoneActivity = new Intent(activity, LocalActivity.class);
        activity.startActivityForResult(replacePhoneActivity, Constants.USERINFO_AREA);

    });

    private void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", LoginManager.currentLoginUserId + "");
        map.put("isvisitor", "0");
        infoUseCase.setParams(JsonUtil.mapToJson(map));
        infoUseCase.execute().compose(activity.bindToLifecycle()).subscribe(d -> {
            data.set(d.getUinfo());
            OtherInfo.UinfoBean info = d.getUinfo();
            name.set(info.getName());
            company.set(info.getCompany());
            companyPostion.set(info.getPosition());
            headUrl.set(GlobalConstants.HttpUrl.IMG_DOWNLOAD + "?fileId=" + info.getAvatar());
            switch (info.getSex()) {
                case 1:
                    man.set(true);
                    woman.set(false);
                    break;
                case 2:
                    man.set(false);
                    woman.set(true);
                    break;
            }
        }, error -> {
        });

    }


    public void saveName() {
        if (!getName()) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("name", userName);
        saveNameUseCase.setParams(JsonUtil.mapToJson(map));
        saveNameUseCase.execute().compose(activity.bindToLifecycle()).subscribe(data -> {

        }, err -> {

        });
    }


    public void saveSex() {
        getSex();
        Map<String, String> map = new HashMap<>();
        map.put("sex", userSex + "");
        saveSexUseCase.setParams(JsonUtil.mapToJson(map));
        saveSexUseCase.execute().compose(activity.bindToLifecycle()).subscribe(data -> {
        }, err -> {
        });
    }

    public void saveInfo() {
        if (!getName()) {
            return;
        }
        getCareertype();

        Map<String, String> map = new HashMap<>();
        map.put("name", userName);
        map.put("careertype", userCareertype + "");
        map.put("desc", "个人简介");
        saveInfoUseCase.setParams(JsonUtil.mapToJson(map));
        saveInfoUseCase.execute().compose(activity.bindToLifecycle()).subscribe(data -> {
        }, err -> {
        });
    }

    public void saveCompany() {
        if (!getCompany()) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("company", userCompany);
        map.put("position", userPosition);
        saveCompanyUseCase.setParams(JsonUtil.mapToJson(map));
        saveCompanyUseCase.execute().compose(activity.bindToLifecycle()).subscribe(data -> {
        }, err -> {
        });
    }


    private boolean getName() {
        userName = name.get();
        if (StrUtil.isEmpty(userName)) {
            AppToast.show(activity, "姓名不能为空");
            return false;
        }
        return true;
    }

    private boolean getCompany() {
        userCompany = company.get();
        userPosition = companyPostion.get();
        if (StrUtil.isEmpty(userCompany)) {
            AppToast.show(activity, "公司不能为空");
            return false;
        }

        if (StrUtil.isEmpty(userPosition)) {
            AppToast.show(activity, "职位不能为空");
            return false;
        }
        return true;
    }


    private void getSex() {
        if (man.get()) {
            userSex = 1;
        }
        if (woman.get()) {
            userSex = 2;
        }
    }

    private void getCareertype() {
        if (job.get()) {
            userCareertype = 1;
        }
        if (self.get()) {
            userCareertype = 2;
        }
    }


}