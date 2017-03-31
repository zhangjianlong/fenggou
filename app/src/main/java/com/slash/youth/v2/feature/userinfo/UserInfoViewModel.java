package com.slash.youth.v2.feature.userinfo;


import android.databinding.ObservableField;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.core.op.lib.base.BAViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.utils.DoubleUtil;
import com.core.op.lib.utils.JsonUtil;
import com.core.op.lib.weight.swipe.SwipeRefreshLayout;
import com.slash.youth.R;
import com.slash.youth.databinding.ActUserinfoBinding;
import com.slash.youth.domain.bean.OtherInfo;
import com.slash.youth.domain.interactor.main.OtherInfoUseCase;
import com.slash.youth.global.GlobalConstants;
import com.slash.youth.v2.feature.userinfo.tab.UserInfoTabFragment;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

@PerActivity
public class UserInfoViewModel extends BAViewModel<ActUserinfoBinding> {

    public final ObservableField<Integer> titleVisible = new ObservableField<>(View.VISIBLE);
    public final ObservableField<Integer> authVisible = new ObservableField<>(View.GONE);

    public final ObservableField<Boolean> isRefreshing = new ObservableField<>(false);

    public final ReplyCommand onRefreshCommand = new ReplyCommand<>(() -> {
        refresh();
    });

    OtherInfoUseCase useCase;
    long uid;

    public ObservableField<String> uri = new ObservableField<>();
    public ObservableField<String> relationCount = new ObservableField<>();
    public ObservableField<String> relationProgress = new ObservableField<>();

    public ObservableField<String> taskCount = new ObservableField<>();
    public ObservableField<String> taskProgress = new ObservableField<>();


    public ObservableField<String> serviceCount = new ObservableField<>();
    public ObservableField<String> serviceProgress = new ObservableField<>();
    public ObservableField<OtherInfo.DataBean> data = new ObservableField<>();

    @Inject
    public UserInfoViewModel(RxAppCompatActivity activity,
                             OtherInfoUseCase useCase) {
        super(activity);
        this.useCase = useCase;
    }

    @Override
    public void afterViews() {

        //设置样式刷新显示的位置
        uid = activity.getIntent().getBundleExtra("data").getLong("Uid");
        binding.swipeRefreshLayout.setProgressViewOffset(true, -20, 100);
        binding.swipeRefreshLayout.setColorSchemeResources(R.color.app_theme_colorPrimary);
        addFragment(R.id.fl_container, UserInfoTabFragment.instance());
        binding.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (!binding.swipeRefreshLayout.isEnabled() && verticalOffset == 0) {
                    binding.swipeRefreshLayout.setEnabled(true);
                } else if (binding.swipeRefreshLayout.isEnabled() && verticalOffset != 0) {
                    binding.swipeRefreshLayout.setEnabled(false);
                }
            }
        });

        binding.swipeRefreshLayout.setOnDragListener(new SwipeRefreshLayout.OnDragListener() {
            @Override
            public void onStartDrag() {
                startOutAnim();
            }

            @Override
            public void onStopDragNoRefresh() {
                startInAnim();
            }
        });

        refresh();
    }

    private void refresh() {
        isRefreshing.set(true);
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid + "");
        map.put("anonymity", "1");
        useCase.setParams(JsonUtil.mapToJson(map));
        useCase.execute().compose(activity.bindToLifecycle())
                .map(d -> d.getUinfo())
                .subscribe(data -> {
                    UserInfoViewModel.this.data.set(data);
                    uri.set(GlobalConstants.HttpUrl.IMG_DOWNLOAD + "?fileId=" + data.getAvatar());

                    relationCount.set(String.format(activity.getString(R.string.app_userinfo_relation), data.getRelationshipscount()));
                    relationProgress.set(String.format(activity.getString(R.string.app_userinfo_relationprogress), (int) (data.getFansratio() * 100)));


                    taskCount.set(String.format(activity.getString(R.string.app_userinfo_task), (int) data.getAchievetaskcount()));
                    taskProgress.set(String.format(activity.getString(R.string.app_userinfo_taskprogress), (int) (data.getTotoltaskcount())));


                    serviceCount.set(String.format(activity.getString(R.string.app_userinfo_service), DoubleUtil.changeDecimal(data.getUserservicepoint(), 1) + ""));
                    serviceProgress.set(String.format(activity.getString(R.string.app_userinfo_serviceprogress), DoubleUtil.changeDecimal(data.getAverageservicepoint(), 1) + ""));
                    authVisible.set(data.getIsauth() == 1 ? View.VISIBLE : View.GONE);


                }, error -> {
                    isRefreshing.set(false);
                    startInAnim();
                }, () -> {
                    isRefreshing.set(false);
                    startInAnim();
                });
    }

    protected void startOutAnim() {
        Animation animation = AnimationUtils.loadAnimation(activity, R.anim.anim_alpha_out);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                titleVisible.set(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        binding.toolbar.startAnimation(animation);
    }

    protected void startInAnim() {
        titleVisible.set(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(activity, R.anim.anim_alpha_in);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        binding.toolbar.startAnimation(animation);
    }
}