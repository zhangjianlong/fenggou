package com.slash.youth.ui.viewmodel;

import android.app.Activity;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;

import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.databinding.ItemChatOtherSendVoiceBinding;
import com.slash.youth.global.GlobalConstants;
import com.slash.youth.utils.BitmapKit;
import com.slash.youth.utils.LogKit;

import java.io.IOException;

/**
 * Created by zhouyifeng on 2016/11/18.
 */
public class ChatOtherSendVoiceModel extends BaseObservable {
    ItemChatOtherSendVoiceBinding mItemChatOtherSendVoiceBinding;
    Activity mActivity;
    Uri mVoiceUri;
    int mDuration;
    String mTargetAvatar;

    public ChatOtherSendVoiceModel(ItemChatOtherSendVoiceBinding itemChatOtherSendVoiceBinding, Activity activity, Uri voiceUri, int duration, String targetAvatar) {
        this.mItemChatOtherSendVoiceBinding = itemChatOtherSendVoiceBinding;
        this.mActivity = activity;
        this.mVoiceUri = voiceUri;
        this.mDuration = duration;
        this.mTargetAvatar = targetAvatar;

        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {
        setVoiceDuration(mDuration + " ̋   ");

        BitmapKit.bindImage(mItemChatOtherSendVoiceBinding.ivChatOtherAvatar, GlobalConstants.HttpUrl.IMG_DOWNLOAD + "?fileId=" + mTargetAvatar);
    }

    boolean isClickStartVoice = true;
    MediaPlayer mediaPlayer;

    public void playVoice(View v) {
        if (isClickStartVoice) {
            startVoice();
        } else {
            stopVoice();
        }
        isClickStartVoice = !isClickStartVoice;
    }

    //播放语音
    private void startVoice() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                LogKit.v("播放结束");
                isClickStartVoice = true;
                stopVoice();
            }
        });
        try {
            mediaPlayer.setDataSource(mActivity, mVoiceUri);
            mediaPlayer.prepare();
            mediaPlayer.start();
            startVoiceAnimation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //停止播放语音
    private void stopVoice() {
        if (mediaPlayer != null) {
//            mediaPlayer.reset();
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            stopVoiceAnimation();
        }
    }

    AnimationDrawable animVoicePlay;

    private void startVoiceAnimation() {
        mItemChatOtherSendVoiceBinding.ivChatVoiceAnim.setImageResource(R.drawable.anim_other_voice_play);
        animVoicePlay = (AnimationDrawable) mItemChatOtherSendVoiceBinding.ivChatVoiceAnim.getDrawable();
        animVoicePlay.start();
    }

    private void stopVoiceAnimation() {
        animVoicePlay.stop();
        animVoicePlay = null;
        mItemChatOtherSendVoiceBinding.ivChatVoiceAnim.setImageResource(R.mipmap.sound3_icon);
    }


    private String voiceDuration;

    @Bindable
    public String getVoiceDuration() {
        return voiceDuration;
    }

    public void setVoiceDuration(String voiceDuration) {
        this.voiceDuration = voiceDuration;
        notifyPropertyChanged(BR.voiceDuration);
    }
}