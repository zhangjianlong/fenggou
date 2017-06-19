package com.odbpo.fenggou.feature.main.login;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.app.FragmentManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.core.op.lib.base.BFViewModel;
import com.core.op.lib.command.ReplyCommand;
import com.core.op.lib.di.PerActivity;
import com.odbpo.fenggou.R;
import com.odbpo.fenggou.databinding.FrgLoginBinding;
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
    public final ObservableField<String> imageCode = new ObservableField<>();
    public final ObservableField<String> verifyCode = new ObservableField<>();
    public final ObservableField<String> psd = new ObservableField<>();
    public final ObservableField<String> psdAgain = new ObservableField<>();
    public final ObservableField<Boolean> agreeAgreement = new ObservableField<>(true);
    public final ObservableBoolean showRegisterLayout = new ObservableBoolean(true);


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

    });
}