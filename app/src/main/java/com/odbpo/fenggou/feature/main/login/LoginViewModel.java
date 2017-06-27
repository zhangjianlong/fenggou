package com.odbpo.fenggou.feature.main.login;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.core.op.Static;
import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.core.op.lib.messenger.Messenger;
import com.core.op.lib.utils.AppToast;
import com.core.op.lib.utils.StrUtil;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.FrgLoginBinding;
import com.odbpo.fenggou.feature.forget.ForgetActivity;
import com.odbpo.fenggou.util.MessageKey;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import javax.inject.Inject;

@PerActivity
public class LoginViewModel extends BFViewModel<FrgLoginBinding> {

    @Inject
    public LoginViewModel(RxAppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void afterViews() {

    }

    public final ObservableField<String> phone = new ObservableField<>();
    public final ObservableField<String> psd = new ObservableField<>();

    public final ObservableField<String> userName = new ObservableField<>();
    public final ObservableField<String> imageCode = new ObservableField<>();
    public final ObservableField<String> verifyCode = new ObservableField<>();
    public final ObservableField<String> psdAgain = new ObservableField<>();
    public final ObservableField<String> imageUri = new ObservableField<>("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1498020154&di=354e0947d9d994db9cd65e630cde85e7&src=http://pic7.nipic.com/20100519/4862714_212100033649_2.jpg");
    public final ObservableField<Boolean> agreeAgreement = new ObservableField<>(true);
    public final ObservableBoolean showRegisterLayout = new ObservableBoolean(false);


    public final ReplyCommand showLogin = new ReplyCommand(() -> {
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


    });


    public final ReplyCommand forgetPsd = new ReplyCommand(() -> {
        ForgetActivity.instance(activity);
    });


    public final ReplyCommand showRegister = new ReplyCommand(() -> {
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


    });

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

        Messenger.getDefault().sendNoMsg(MessageKey.LOGIN);


    });
}