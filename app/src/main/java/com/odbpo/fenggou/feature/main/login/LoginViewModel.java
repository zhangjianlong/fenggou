package com.odbpo.fenggou.feature.main.login;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.core.op.Static;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.AppToast;
import com.core.op.lib.utils.JsonUtil;
import com.core.op.lib.utils.StrUtil;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.data.net.AbsAPICallback;
import com.odbpo.fenggou.data.util.ShareKey;
import com.odbpo.fenggou.data.util.SpUtil;
import com.odbpo.fenggou.databinding.FrgLoginBinding;
import com.odbpo.fenggou.domain.bean.LoginResponse;
import com.odbpo.fenggou.domain.bean.PhoneLoginResultBean;
import com.odbpo.fenggou.domain.interactor.login.LoginResultUseCase;
import com.odbpo.fenggou.feature.forget.ForgetActivity;
import com.odbpo.fenggou.feature.main.category.CategoryFragment;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

@PerActivity
public class LoginViewModel extends BFViewModel<FrgLoginBinding> {
    private final static String TAG_LOGIN = "TAG_LOGIN";
    private final static String TAG_REGISTER = "TAG_REGISTER";
    private LoginResultUseCase loginResultUseCase;

    @Inject
    public LoginViewModel(RxAppCompatActivity activity, LoginResultUseCase loginResultUseCase) {
        super(activity);
        this.loginResultUseCase = loginResultUseCase;

    }

    @Override
    public void afterViews() {
        initTab();
    }

    public final ObservableField<String> title = new ObservableField<>(Static.CONTEXT.getString(R.string.app_main_login));
    public final ObservableField<String> phone = new ObservableField<>();
    public final ObservableField<String> psd = new ObservableField<>("wenzi1994");

    public final ObservableField<String> userName = new ObservableField<>("18550001915");
    public final ObservableField<Drawable> eye = new ObservableField<>(Static.CONTEXT.getResources().getDrawable(R.drawable.eye_unclick));
    public final ObservableField<String> verifyCode = new ObservableField<>();
    public final ObservableBoolean showRegisterLayout = new ObservableBoolean(false);


    public void showLogin() {
        if (!showRegisterLayout.get()) {
            return;
        }
        Animation slideToRight = AnimationUtils.loadAnimation(
                activity, R.anim.slide_to_right);
        binding.leftLl.startAnimation(slideToRight);
        slideToRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showRegisterLayout.set(false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        binding.rightLl.startAnimation(AnimationUtils.loadAnimation(
                activity, R.anim.slide_from_left
        ));


    }

    ;


    public final ReplyCommand forgetPsd = new ReplyCommand(() -> {
        ForgetActivity.instance(activity);
    });


    public void showRegister() {
        if (showRegisterLayout.get()) {
            return;
        }
        Animation slideToRight = AnimationUtils.loadAnimation(
                activity, R.anim.slide_from_right);
        binding.leftLl.startAnimation(slideToRight);
        slideToRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showRegisterLayout.set(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        binding.rightLl.startAnimation(AnimationUtils.loadAnimation(
                activity, R.anim.slide_to_left
        ));


    }

    ;

    public final ReplyCommand agreeProtocol = new ReplyCommand(() -> {

    });

    public final ReplyCommand protocol = new ReplyCommand(() -> {

    });
    public final ReplyCommand register = new ReplyCommand(() -> {
        if (StrUtil.isEmpty(phone.get())) {
            AppToast.show(Static.CONTEXT.getString(R.string.app_main_login_phone_empty));
            return;
        }

        if (StrUtil.isEmpty(psd.get())) {
            AppToast.show(Static.CONTEXT.getString(R.string.app_main_login_psd_empty));
            return;
        }

    });
    public final ReplyCommand login = new ReplyCommand(() -> {
        if (StrUtil.isEmpty(userName.get())) {
            AppToast.show(Static.CONTEXT.getString(R.string.app_main_login_phone_empty));
            return;
        }

        if (StrUtil.isEmpty(psd.get())) {
            AppToast.show(Static.CONTEXT.getString(R.string.app_main_login_psd_empty));
            return;
        }


        Map<String, String> map = new HashMap<>();
        map.put("user", userName.get());
        map.put("password", psd.get());
        loginResultUseCase.setFormParams(map);
        loginResultUseCase.execute().compose(activity.bindToLifecycle())
                .subscribe(new AbsAPICallback<LoginResponse>() {
                    @Override
                    protected void onDone(LoginResponse loginResponse) {
                        LoginResponse loginResult = loginResponse;
                        SpUtil.write(ShareKey.TOKEN, loginResult.getToken());
                        Messenger.getDefault().sendNoMsg(MessageKey.LOGIN);

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                });


    });

    public final ReplyCommand clickEye = new ReplyCommand(() -> {
        if (eye.get().getConstantState().equals(Static.CONTEXT.getResources().getDrawable(R.drawable.eye_unclick).getConstantState())) {
            eye.set(Static.CONTEXT.getResources().getDrawable(R.drawable.eye_click));
        } else {
            eye.set(Static.CONTEXT.getResources().getDrawable(R.drawable.eye_unclick));
        }

    });


    private void initTab() {
        TabLayout.Tab tab = binding.tabLayout.newTab();
        tab.setText(Static.CONTEXT.getText(R.string.app_main_account_login));
        tab.setTag(TAG_LOGIN);
        binding.tabLayout.addTab(tab);
        tab = binding.tabLayout.newTab();
        tab.setText(Static.CONTEXT.getText(R.string.app_main_phone_login));
        tab.setTag(TAG_REGISTER);
        binding.tabLayout.addTab(tab);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getTag().equals(TAG_LOGIN)) {
                    showLogin();

                } else if (tab.getTag().equals(TAG_REGISTER)) {
                    showRegister();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}