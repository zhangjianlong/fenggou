package com.slash.youth.v2.feature.main.mine;


import android.content.Intent;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.base.OnDialogLisetener;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.JsonUtil;
import com.core.op.lib.utils.PreferenceUtil;
import com.slash.youth.data.Global;
import com.slash.youth.databinding.FrgMineBinding;
import com.slash.youth.domain.bean.MineInfo;
import com.slash.youth.domain.interactor.main.MineInfoUseCase;
import com.slash.youth.domain.interactor.main.OtherInfoUseCase;
import com.slash.youth.engine.LoginManager;
import com.slash.youth.engine.UserInfoEngine;
import com.slash.youth.global.GlobalConstants;
import com.slash.youth.ui.activity.ApprovalActivity;
import com.slash.youth.ui.activity.MyAccountActivity;
import com.slash.youth.ui.activity.MyCollectionActivity;
import com.slash.youth.ui.activity.MyFriendActivtiy;
import com.slash.youth.ui.activity.MyHelpActivity;
import com.slash.youth.ui.activity.MySettingActivity;
import com.slash.youth.ui.activity.MySkillManageActivity;
import com.slash.youth.ui.activity.UserInfoActivity;
import com.slash.youth.ui.activity.UserinfoEditorActivity;
import com.slash.youth.ui.activity.VisitorsActivity;
import com.slash.youth.ui.activity.WebViewActivity;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.Constants;
import com.slash.youth.utils.CountUtils;
import com.slash.youth.utils.CustomEventAnalyticsUtils;
import com.slash.youth.utils.LogKit;
import com.slash.youth.v2.feature.dialog.mine.IdentificateDialog;
import com.slash.youth.v2.util.MessgeKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import static android.R.attr.id;
import static io.rong.imlib.statistics.UserData.phone;


@PerActivity
public class MineViewModel extends BFViewModel<FrgMineBinding> {

    public static final String START_ANIMATION = "START_ANIMATION";

    public ObservableField<MineInfo.DataBean> data = new ObservableField<>();
    private String[] grades = {"少侠", "大侠", "宗师", "至尊"};//4000  10000 请等待客服审核

    float expertⅠMaxMarks = 1000;
    float expertⅡMaxMarks = 4000;
    float expertⅢMaxMarks = 30000;
    float expertⅣMaxMarks = 99999;
    float expertMarks;
    float expertMarksProgress;//0到360

    boolean isLoadDataFinished = false;
    private RotateAnimation raExpertMarksMaker;

    IdentificateDialog dialog;

    public final ReplyCommand personInfoClick = new ReplyCommand(() -> {
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MINE_CLICK_PERSON_MESSAGE);
        Intent intentUserInfoActivity = new Intent(CommonUtils.getContext(), UserInfoActivity.class);
        intentUserInfoActivity.putExtra("phone", data.get().getPhone());
        intentUserInfoActivity.putExtra("skillTag", data.get().getTag());
        intentUserInfoActivity.putExtra("Uid", LoginManager.currentLoginUserId);
        activity.startActivity(intentUserInfoActivity);
    });

    public final ReplyCommand identificateClick = new ReplyCommand(() -> {
        Intent intentApprovalActivity = new Intent(CommonUtils.getContext(), ApprovalActivity.class);
        intentApprovalActivity.putExtra("careertype", data.get().getCareertype());
        intentApprovalActivity.putExtra("Uid", LoginManager.currentLoginUserId);
        activity.startActivityForResult(intentApprovalActivity, UserInfoEngine.MY_USER_EDITOR);
    });

    public final ReplyCommand approvalClick = new ReplyCommand(() -> {
        //埋点
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MINE_CLICK_APPROVE);

        switch (data.get().getCareertype()) {
            case 1:
                if (TextUtils.isEmpty(data.get().getCompany()) || TextUtils.isEmpty(data.get().getName()) || TextUtils.isEmpty(data.get().getPosition())) {
                    if (!dialog.isShowing())
                        dialog.show();
                } else {
                    jumpApprovalActivity();
                }
                break;
            case 2:
                if (TextUtils.isEmpty(data.get().getName())) {
                    if (!dialog.isShowing())
                        dialog.show();
                } else {
                    jumpApprovalActivity();
                }
                break;
            case 0:
                if (!dialog.isShowing())
                    dialog.show();
                break;
        }
    });

    private void jumpApprovalActivity() {
        Intent intentApprovalActivity = new Intent(CommonUtils.getContext(), ApprovalActivity.class);
        intentApprovalActivity.putExtra("careertype", data.get().getCareertype());
        intentApprovalActivity.putExtra("Uid", LoginManager.currentLoginUserId);
        activity.startActivityForResult(intentApprovalActivity, Constants.APPROVAL);
    }

    public final ReplyCommand influenceClick = new ReplyCommand(() -> {
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MINE_CLICK_INFLUENCE);
        Intent intentCommonQuestionActivity = new Intent(CommonUtils.getContext(), WebViewActivity.class);
        intentCommonQuestionActivity.putExtra("influence", "influence");
        activity.startActivity(intentCommonQuestionActivity);
    });

    public final ReplyCommand accountClick = new ReplyCommand(() -> {
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MINE_CLICK_MY_ACCOUNT);
        Intent intentMyAccountActivity = new Intent(CommonUtils.getContext(), MyAccountActivity.class);
        intentMyAccountActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intentMyAccountActivity);
    });

    public final ReplyCommand friendClick = new ReplyCommand(() -> {
        Intent intentChooseFriendActivtiy = new Intent(CommonUtils.getContext(), MyFriendActivtiy.class);
        activity.startActivity(intentChooseFriendActivtiy);
    });

    public final ReplyCommand visitorClick = new ReplyCommand(() -> {
        Intent intentChooseFriendActivtiy = new Intent(CommonUtils.getContext(), VisitorsActivity.class);
        activity.startActivity(intentChooseFriendActivtiy);
    });

    public final ReplyCommand managerClick = new ReplyCommand(() -> {
        Intent intentMySkillManageActivity = new Intent(CommonUtils.getContext(), MySkillManageActivity.class);
        intentMySkillManageActivity.putExtra("Title", Constants.MY_TITLE_MANAGER_MY_PUBLISH);
        intentMySkillManageActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intentMySkillManageActivity);
    });

    public final ReplyCommand collectionClick = new ReplyCommand(() -> {
        Intent intentMyCollectionActivity = new Intent(CommonUtils.getContext(), MyCollectionActivity.class);
        intentMyCollectionActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intentMyCollectionActivity);
        //埋点
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MINE_CLICK_MY_COLLECT);
    });

    public final ReplyCommand helpClick = new ReplyCommand(() -> {
        Intent intentMyHelpActivity = new Intent(CommonUtils.getContext(), MyHelpActivity.class);
        intentMyHelpActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intentMyHelpActivity);
        //帮助的埋点
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MINE_CLICK_HELP);
    });

    public final ReplyCommand editorClick = new ReplyCommand(() -> {
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MINE_CLICK_EDIT_PROFILE);

        Intent intentUserinfoEditorActivity = new Intent(CommonUtils.getContext(), UserinfoEditorActivity.class);
        intentUserinfoEditorActivity.putExtra("phone", data.get().getPhone());
//        intentUserinfoEditorActivity.putExtra("myUinfo", myinfo);
        intentUserinfoEditorActivity.putExtra("myId", data.get().getId());
        activity.startActivityForResult(intentUserinfoEditorActivity, UserInfoEngine.MY_USER_EDITOR);
    });

    public final ReplyCommand settingClick = new ReplyCommand(() -> {
        Intent intentMySettingActivity = new Intent(CommonUtils.getContext(), MySettingActivity.class);
        intentMySettingActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intentMySettingActivity);
        //设置的埋点
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MINE_CLICK_SET);
    });

    MineInfoUseCase mineInfoUseCase;
    OtherInfoUseCase otherInfoUseCase;

    public String totalsMoney = "0.0元";

    public ObservableField<String> uri = new ObservableField<>();
    public String over;

    public String grade;

    public String mark = "0";
    public ObservableField<String> connection = new ObservableField<>("0");

    @Inject
    public MineViewModel(RxAppCompatActivity activity,
                         MineInfoUseCase mineInfoUseCase,
                         OtherInfoUseCase otherInfoUseCase,
                         IdentificateDialog dialog) {
        super(activity);
        this.mineInfoUseCase = mineInfoUseCase;
        this.otherInfoUseCase = otherInfoUseCase;
        this.dialog = dialog;
        dialog.setOnDialogLisetener(new OnDialogLisetener() {
            @Override
            public void onConfirm() {
                Intent intentUserinfoEditorActivity = new Intent(CommonUtils.getContext(), UserinfoEditorActivity.class);
                intentUserinfoEditorActivity.putExtra("myId", data.get().getId());
                activity.startActivityForResult(intentUserinfoEditorActivity, UserInfoEngine.MY_USER_EDITOR);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    @Override
    public void afterViews() {
        binding.svPagerHomeMy.setHightView(binding.rlHead, binding.rlHeadUp, binding.ivMine);
        Messenger.getDefault().register(this, START_ANIMATION, () -> {
            doMarksAnimation();
        });
        Messenger.getDefault().register(this, MessgeKey.UPDATE_FRIEND_NUM, () -> {
            loadData();
        });

        loadData();


    }


    private void loadData() {
        mineInfoUseCase.execute().compose(activity.bindToLifecycle())
                .subscribe(d -> {
                    MineInfo.DataBean data = d.getMyinfo();
                    this.data.set(data);

                    totalsMoney = CountUtils.DecimalFormat(data.getAmount()) + "元";
                    uri.set(GlobalConstants.HttpUrl.IMG_DOWNLOAD + "?fileId=" + data.getAvatar());

                    int v = (int) (data.getExpertratio() * 100);
                    over = v + "%";

                    List<Integer> expertlevels = data.getExpertlevels();//每个等级对应的分数
                    if (expertlevels.size() != 0) {
                        expertMarks = data.getExpertscore();
                        int expertlevel = data.getExpertlevel();//当前对应的等级
                        if (expertlevel > 0 && expertlevel <= 4) {
                            grade = grades[expertlevel];
                            int expertscore = expertlevels.get(expertlevel - 1);
                            mark = (int) (expertscore - expertMarks) + "";
                        }
                    }
                    setExpertMarks();
                    initAnimation();
                    isLoadDataFinished = true;
                });

        Map<String, String> map = new HashMap<>();
        map.put("uid", PreferenceUtil.readLong(activity, "slash_sp.config", Global.UID, 0l) + "");
        map.put("anonymity", "1");
        otherInfoUseCase.setParams(JsonUtil.mapToJson(map));
        otherInfoUseCase.execute().compose(activity.bindToLifecycle())
                .subscribe(d -> {
                    connection.set(d.getUinfo().getRelationshipscount() + "");
                });
    }

    private void setExpertMarks() {
//        expertMarks = 2000;//这个数据实际应该从服务端获取
//             expertMarksProgress = expertMarks / totalExpertMarks * 360;
        if (expertMarks >= 0 && expertMarks <= expertⅠMaxMarks) {
            expertMarksProgress = expertMarks / expertⅠMaxMarks * 90;
        } else if (expertMarks <= expertⅡMaxMarks) {
            expertMarksProgress = 90 + (expertMarks - expertⅠMaxMarks) / (expertⅡMaxMarks - expertⅠMaxMarks) * 90;
        } else if (expertMarks <= expertⅢMaxMarks) {
            expertMarksProgress = 180 + (expertMarks - expertⅡMaxMarks) / (expertⅢMaxMarks - expertⅡMaxMarks) * 90;
        } else if (expertMarks <= expertⅣMaxMarks) {
            expertMarksProgress = 270 + (expertMarks - expertⅢMaxMarks) / (expertⅣMaxMarks - expertⅢMaxMarks) * 90;
        }
    }


    private void initAnimation() {
        raExpertMarksMaker = new RotateAnimation(0, expertMarksProgress, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        raExpertMarksMaker.setDuration(120 * 16);
        raExpertMarksMaker.setInterpolator(new LinearInterpolator());
        raExpertMarksMaker.setFillAfter(true);
    }

    private void initScoreView() {
        binding.flHomeMyExpertMarksMaker.startAnimation(raExpertMarksMaker);
        binding.rsvHomeMyExpertMarksProgress.setStartProgressAngle(0);
        binding.rsvHomeMyExpertMarksProgress.setTotalProgressAngle(expertMarksProgress);
        binding.rsvHomeMyExpertMarksProgress.post(new Runnable() {
            @Override
            public void run() {
                binding.rsvHomeMyExpertMarksProgress.initRingProgressDraw();
            }
        });
    }

    public void doMarksAnimation() {
        if (isLoadDataFinished) {
            initScoreView();
            initExpertMarksProgress();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        LogKit.v("HomeMyPager waiting load data...");
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (isLoadDataFinished) {
                            CommonUtils.getHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    initScoreView();
                                    initExpertMarksProgress();
                                }
                            });
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    private void initExpertMarksProgress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 120; i++) {
                    try {
                        long startMill = System.currentTimeMillis();
                        final float displayMarks = expertMarks * (i + 1) / 120;
                        CommonUtils.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                binding.tvHomeMyExpertMarks.setText((int) displayMarks + "");
                            }
                        });
                        long endMill = System.currentTimeMillis();
                        if (16 - (endMill - startMill) > 0) {
                            Thread.sleep(16 - (endMill - startMill));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
}
