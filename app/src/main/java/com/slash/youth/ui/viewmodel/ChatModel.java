package com.slash.youth.ui.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.slash.youth.BR;
import com.slash.youth.R;
import com.slash.youth.databinding.ActivityChatBinding;
import com.slash.youth.databinding.ItemChatChangeContactWayInfoBinding;
import com.slash.youth.databinding.ItemChatDatetimeBinding;
import com.slash.youth.databinding.ItemChatFriendPicBinding;
import com.slash.youth.databinding.ItemChatFriendTextBinding;
import com.slash.youth.databinding.ItemChatInfoBinding;
import com.slash.youth.databinding.ItemChatMyPicBinding;
import com.slash.youth.databinding.ItemChatMySendBusinessCardBinding;
import com.slash.youth.databinding.ItemChatMySendVoiceBinding;
import com.slash.youth.databinding.ItemChatMyShareTaskBinding;
import com.slash.youth.databinding.ItemChatMyTextBinding;
import com.slash.youth.databinding.ItemChatOtherChangeContactWayBinding;
import com.slash.youth.databinding.ItemChatOtherSendAddFriendBinding;
import com.slash.youth.databinding.ItemChatOtherSendBusinessCardBinding;
import com.slash.youth.databinding.ItemChatOtherSendVoiceBinding;
import com.slash.youth.databinding.ItemChatOtherShareTaskBinding;
import com.slash.youth.domain.ChatCmdAddFriendBean;
import com.slash.youth.domain.ChatCmdBusinesssCardBean;
import com.slash.youth.domain.ChatCmdChangeContactBean;
import com.slash.youth.domain.ChatCmdShareTaskBean;
import com.slash.youth.domain.ChatTaskInfoBean;
import com.slash.youth.domain.CommonResultBean;
import com.slash.youth.domain.IsChangeContactBean;
import com.slash.youth.domain.SendMessageBean;
import com.slash.youth.domain.UserInfoBean;
import com.slash.youth.engine.LoginManager;
import com.slash.youth.engine.MsgManager;
import com.slash.youth.engine.UserInfoEngine;
import com.slash.youth.http.protocol.BaseProtocol;
import com.slash.youth.ui.activity.DemandDetailActivity;
import com.slash.youth.ui.activity.ServiceDetailActivity;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.CustomEventAnalyticsUtils;
import com.slash.youth.utils.IOUtils;
import com.slash.youth.utils.LogKit;
import com.slash.youth.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.CommandMessage;
import io.rong.message.ImageMessage;
import io.rong.message.ReadReceiptMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

/**
 * Created by zhouyifeng on 2016/11/16.
 */
public class ChatModel extends BaseObservable {

    ActivityChatBinding mActivityChatBinding;
    Activity mActivity;
    private TextView mTvChatFriendName;
    private LinearLayout mLlChatContent;//聊天内容容器
    private ScrollView mSvChatContent;

    private String targetId;
    private String targetName;
    private String targetAvatar;
    private int targetAvatarResource;

    ArrayList<SendMessageBean> listSendMsg = new ArrayList<SendMessageBean>();

    private int friendRelationShipStatus = 0;//0表示陌生人 1表示我主动加了他，他还未回复 2表示他主动加了我，我还未同意  3表示是好友关系
    private int changeContactStatus = 0;//1标识已经交换过  -1标识尚未交换过

    private int getBaseDataFinishedCount = 0;
    private int getBaseDataTotalCount = 0;
    ChatCmdShareTaskBean chatCmdShareTaskBean;

    public ChatModel(ActivityChatBinding activityChatBinding, Activity activity) {
        this.mActivityChatBinding = activityChatBinding;
        this.mActivity = activity;

        hideSoftInputMethod();//隐藏软件盘的方法要尽早调用，一开始就让输入框失去焦点，这样，软键盘一开始就不会弹出来
        targetId = mActivity.getIntent().getStringExtra("targetId");
        MsgManager.targetId = targetId;//设置聊天界面只显示当前聊天UserId发来的消息
        //判断聊天目标是否是斜杠小助手
        if (!"1000".equals(targetId)) {
            displayLoadLayer();
            getTargetUserInfo();
            getFriendRelationShipStatus();
            getChangeContactStatus();
            //为了正确显示对方的头像等信息，把初始化方法放在加载基本信息完毕之后执行
//            initData();
//            initView();
//            initListener();
        } else {
            initData();
            initView();
            initListener();
        }
    }

    private void initData() {

        MsgManager.setHistoryListener(new ChatHistoryListener());
        MsgManager.loadHistoryChatRecord();

        if ("1000".equals(targetId)) {//如果是斜杠小助手
            targetName = "斜杠小助手";
            MsgManager.targetName = targetName;
            setOtherUsername(targetName);
            targetAvatarResource = R.mipmap.slash_helper_square;
            MsgManager.targetAvatarResource = targetAvatarResource;
        } else {
            String chatCmdName = mActivity.getIntent().getStringExtra("chatCmdName");
            if (!TextUtils.isEmpty(chatCmdName)) {
                if (chatCmdName.equals("sendBusinessCard")) {
                    sendBusinessCard();
                } else if (chatCmdName.equals("sendShareTask")) {
                    chatCmdShareTaskBean = (ChatCmdShareTaskBean) mActivity.getIntent().getSerializableExtra("chatCmdShareTaskBean");
                    sendShareTask();
                }
            }
            Bundle taskInfoBundle = mActivity.getIntent().getBundleExtra("taskInfo");
            if (taskInfoBundle != null) {//如果通过“聊一聊”进入聊天界面，会带上任务，并发送给对方
                ChatTaskInfoBean chatTaskInfoBean = new ChatTaskInfoBean();
                chatTaskInfoBean.tid = taskInfoBundle.getLong("tid");
                chatTaskInfoBean.type = taskInfoBundle.getInt("type");
                chatTaskInfoBean.title = taskInfoBundle.getString("title");
                sendRelatedTaskInfo(chatTaskInfoBean);

                mActivityChatBinding.flRelatedTaskLine.setVisibility(View.VISIBLE);
                LogKit.v("chatTaskInfoBean.title:" + chatTaskInfoBean.title);
                setRelatedTaskTitle("相关任务:" + chatTaskInfoBean.title);
            } else {//如果进入界面时没有带上任务，就检测本地是否有对方发送过来的相关任务
                displayRelatedTask();
            }
        }
    }

    private void initView() {
        mLlChatContent = mActivityChatBinding.llChatContent;

        //测试添加各种消息条目
//        mLlChatContent.addView(createDateTimeView());
//        mLlChatContent.addView(createFriendTextView());
//        mLlChatContent.addView(createMyTextView());
//        mLlChatContent.addView(createDateTimeView());
//        mLlChatContent.addView(createFriendPicView());
//        mLlChatContent.addView(createMyPicView());
//        mLlChatContent.addView(createOtherSendAddFriendView());
//        mLlChatContent.addView(createInfoView("您已接受对方的加好友请求"));
//        mLlChatContent.addView(createInfoView("您向对方分享了一个服务"));
//        mLlChatContent.addView(createOtherShareTaskView());
//        mLlChatContent.addView(createMyShareTaskView());
//        mLlChatContent.addView(createOtherSendBusinessCardView());
//        mLlChatContent.addView(createMySendBusinessCardView());
//        mLlChatContent.addView(createOtherChangeContactWayView());
//        mLlChatContent.addView(createChangeContactWayInfoView());//因为背景切图还没有，所以暂未实现
//        mLlChatContent.addView(createOtherSendVoiceView());
//        mLlChatContent.addView(createMySendVoiceView());

        if ("1000".equals(targetId)) {
            mActivityChatBinding.tvOtherCompanyPosition.setVisibility(View.GONE);
            mActivityChatBinding.tvChatFriendName.setTextSize(16);
            mActivityChatBinding.flTextVoiceSwitchBtn.setVisibility(View.GONE);
            mActivityChatBinding.llChatInfoCmd.setVisibility(View.GONE);
        }

        //自动滚动到底部
        mSvChatContent = mActivityChatBinding.svChatContent;
        mSvChatContent.post(new Runnable() {
            @Override
            public void run() {
                mSvChatContent.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    /**
     * 刚进入页面时，显示加载层
     */
    private void displayLoadLayer() {
        setLoadLayerVisibility(View.VISIBLE);
    }

    /**
     * 数据加载完毕后,隐藏加载层
     */
    private void hideLoadLayer() {
        setLoadLayerVisibility(View.GONE);
    }

    private void getTargetUserInfo() {
        getBaseDataTotalCount++;
        UserInfoEngine.getOtherUserInfo(new BaseProtocol.IResultExecutor<UserInfoBean>() {
            @Override
            public void execute(UserInfoBean dataBean) {
                targetName = dataBean.data.uinfo.name;
                targetAvatar = dataBean.data.uinfo.avatar;
                MsgManager.targetName = targetName;
                MsgManager.targetAvatar = targetAvatar;
                setOtherUsername(targetName);
                setOtherCompanyAndPosition(dataBean.data.uinfo.company + " " + dataBean.data.uinfo.position);

                getBaseDataFinishedCount++;
                if (getBaseDataFinishedCount >= getBaseDataTotalCount) {
                    initData();
                    initView();
                    initListener();
                    hideLoadLayer();
                }
            }

            @Override
            public void executeResultError(String result) {
                ToastUtils.shortToast("获取聊天目标用户信息失败:" + result);

                getBaseDataFinishedCount++;
                if (getBaseDataFinishedCount >= getBaseDataTotalCount) {
                    initData();
                    initView();
                    initListener();
                    hideLoadLayer();
                }
            }
        }, targetId, "0");
    }

    private void getFriendRelationShipStatus() {
        getBaseDataTotalCount++;
        MsgManager.getAddFriendStatus(new BaseProtocol.IResultExecutor<CommonResultBean>() {
            @Override
            public void execute(CommonResultBean dataBean) {
                int status = dataBean.data.status;
//                if (status == 0) {
//                    //0表示陌生人
//                } else if (status == 1) {
//                    //1表示我主动加了他，他还未回复
//                } else if (status == 2) {
//                    //2表示他主动加了我，我还未同意
//                } else {
//                    //3表示是好友关系
//                }
                friendRelationShipStatus = status;

                getBaseDataFinishedCount++;
                if (getBaseDataFinishedCount >= getBaseDataTotalCount) {
                    initData();
                    initView();
                    initListener();
                    hideLoadLayer();
                }
            }

            @Override
            public void executeResultError(String result) {
                //这里不会执行
            }
        }, targetId);
    }

    private void getChangeContactStatus() {
        getBaseDataTotalCount++;
        MsgManager.getIsChangeContact(new BaseProtocol.IResultExecutor<IsChangeContactBean>() {
            @Override
            public void execute(IsChangeContactBean dataBean) {
                IsChangeContactBean.Data2 data = dataBean.data.data;
//                if (data.status == 1) {
//                    //已经交换过手机号
//                } else {
//                    //没有交换过手机号,发送交换手机号的请求
//                }
                changeContactStatus = data.status;

                getBaseDataFinishedCount++;
                if (getBaseDataFinishedCount >= getBaseDataTotalCount) {
                    initData();
                    initView();
                    initListener();
                    hideLoadLayer();
                }
            }

            @Override
            public void executeResultError(String result) {
                ToastUtils.shortToast("获取是否交换过手机号标识失败:" + result);

                getBaseDataFinishedCount++;
                if (getBaseDataFinishedCount >= getBaseDataTotalCount) {
                    initData();
                    initView();
                    initListener();
                    hideLoadLayer();
                }
            }
        }, targetId);
    }

    long startRecorderTime = 0;
    long endRecorderTime = 0;

    private void initListener() {
        setMessageListener();

        mActivityChatBinding.etChatInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() <= 0) {
                    setUploadPicBtnVisibility(View.VISIBLE);
                    setSendTextBtnVisibility(View.GONE);
                } else {
                    setUploadPicBtnVisibility(View.GONE);
                    setSendTextBtnVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mActivityChatBinding.etChatInput.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                LogKit.v("etChatInput----addOnLayoutChangeListener");
                mSvChatContent.fullScroll(View.FOCUS_DOWN);
            }
        });

        mLlChatContent.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                //                LogKit.v("mLlChatContent----addOnLayoutChangeListener");
                mSvChatContent.fullScroll(View.FOCUS_DOWN);
            }
        });

        mActivityChatBinding.tvChatInputVoice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int width = v.getWidth();
                int[] locationOnScreen = new int[2];
                v.getLocationOnScreen(locationOnScreen);
                //确定是指滑动的区域，超过这个区域就会取消发送语音
                int left = locationOnScreen[0];
                int top = locationOnScreen[1] - CommonUtils.dip2px(75);
                int right = left + width;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.setBackgroundResource(R.drawable.shape_chat_input_voice_bg);
                        setSendVoiceCmdLayerVisibility(View.VISIBLE);
                        setUpCancelSendVoiceVisibility(View.VISIBLE);
                        setRelaseCancelSendVoiceVisibility(View.GONE);
                        startRecorderTime = System.currentTimeMillis();
                        startSoundRecording();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float rawX = event.getRawX();
                        float rawY = event.getRawY();
                        if (rawX >= left && rawX <= right && rawY >= top) {
                            setUpCancelSendVoiceVisibility(View.VISIBLE);
                            setRelaseCancelSendVoiceVisibility(View.GONE);
                            isCancelRecord = false;
                        } else {
                            setUpCancelSendVoiceVisibility(View.GONE);
                            setRelaseCancelSendVoiceVisibility(View.VISIBLE);
                            isCancelRecord = true;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        v.setBackgroundResource(R.drawable.shape_chat_input_voice_untouch_bg);
                        setSendVoiceCmdLayerVisibility(View.GONE);
                        endRecorderTime = System.currentTimeMillis();
                        long timeSpan = endRecorderTime - startRecorderTime;
                        if (timeSpan > 500) {
                            stopSoundRecording();
                        } else {//这个判断只是为了防止不崩溃
                            CommonUtils.getHandler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    stopSoundRecording();
                                }
                            }, (500 - timeSpan));
                        }
                        break;
                }
                return true;
            }
        });
    }

    //接受消息用的监听
    private void setMessageListener() {
        MsgManager.setChatTextListener(new MsgManager.ChatTextListener() {
            @Override
            public void displayText(Message message, int left) {
                long sentTime = message.getSentTime();
                displayMsgTimeView(sentTime);

                displayReceiveTextMsg(message, false);

                //发送已经阅读的回执
                sendReadReceipt(sentTime);
            }
        });

        MsgManager.setChatPicListener(new MsgManager.ChatPicListener() {

            @Override
            public void dispayPic(Message message, int left) {
                long sentTime = message.getSentTime();
                displayMsgTimeView(sentTime);

                displayReceiveImageMsg(message, false);

                //发送已经阅读的回执
                sendReadReceipt(sentTime);
            }
        });
        MsgManager.setChatVoiceListener(new MsgManager.ChatVoiceListener() {
            @Override
            public void loadVoice(Message message, int left) {
                long sentTime = message.getSentTime();
                displayMsgTimeView(sentTime);

                displayReceiveVoiceMsg(message, false);

                //发送已经阅读的回执
                sendReadReceipt(sentTime);
            }
        });
        MsgManager.setChatOtherCmdListener(new MsgManager.ChatOtherCmdListener() {
            @Override
            public void doOtherCmd(Message message, int left) {
                long sentTime = message.getSentTime();
                displayMsgTimeView(sentTime);

                displayReceiveOtherCmdMsg(message, false);

                //发送已经阅读的回执
                sendReadReceipt(sentTime);
            }
        });
        MsgManager.setRelatedTaskListener(new MsgManager.RelatedTaskListener() {

            @Override
            public void displayRelatedTask() {
                ChatModel.this.displayRelatedTask();
            }
        });
        //接受消息回执的监听
        //在 onReadReceiptReceived() 回调里，请先判断 message.getConversationType() 和
        // message.getTargetId() 和当前会话一致，
        // 然后在UI里把该会话中发送时间戳之前的所有已发送消息状态置为已读（底层数据库消息状态已经改为已读）。
        RongIMClient.setReadReceiptListener(new RongIMClient.ReadReceiptListener() {
            @Override
            public void onReadReceiptReceived(Message message) {
                LogKit.v("setReadReceiptListener---------------------------");
                ReadReceiptMessage content = (ReadReceiptMessage) message.getContent();
                long lastMessageSendTime = content.getLastMessageSendTime();
                LogKit.v("lastMessageSendTime:" + lastMessageSendTime);
                //自行进行UI处理，把会话中发送时间戳之前的所有已发送消息状态置为已读
                ArrayList<SendMessageBean> listNeedRemove = new ArrayList<SendMessageBean>();
                LogKit.v("listSendMsg.size():" + listSendMsg.size());
                for (SendMessageBean sendMessageBean : listSendMsg) {
                    LogKit.v("sendMessageBean.sendTime:" + sendMessageBean.sendTime);
                    if (sendMessageBean.sendTime < lastMessageSendTime) {
                        final TextView tvReadStatus = (TextView) sendMessageBean.vReadStatus;
                        CommonUtils.getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                LogKit.v("标记已读");
                                tvReadStatus.setText("已读");
                            }
                        });
                        listNeedRemove.add(sendMessageBean);
                    }
                }
                listSendMsg.removeAll(listNeedRemove);
            }

            @Override
            public void onMessageReceiptRequest(Conversation.ConversationType conversationType, String s, String s1) {

            }

            @Override
            public void onMessageReceiptResponse(Conversation.ConversationType conversationType, String s, String s1, HashMap<String, Long> hashMap) {

            }
        });
    }

    /**
     * 发送阅读huizhi
     *
     * @param sentTime
     */
    private void sendReadReceipt(long sentTime) {
        RongIMClient.getInstance().sendReadReceiptMessage(Conversation.ConversationType.PRIVATE, targetId, sentTime);
    }

    boolean isCancelRecord = false;
    MediaRecorder mediaRecorder;
    File tmpVoiceFile;

    /**
     * 开始录音
     */
    public void startSoundRecording() {

        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(CommonUtils.getContext(), Manifest.permission.RECORD_AUDIO);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.RECORD_AUDIO}, 100);
                return;
            }
        }
        soundRecord();
    }

    public void soundRecord() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    tmpVoiceFile = new File(CommonUtils.getContext().getCacheDir(), "tmpVoice" + System.currentTimeMillis());
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
                    mediaRecorder.setOutputFile(tmpVoiceFile.getAbsolutePath());
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    try {
                        mediaRecorder.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaRecorder.start();
                } catch (Exception ex) {
                    LogKit.v("-----------聊天中的录音异常，比较诡异的未知异常，多半和权限有关-------------");
                    ex.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaRecorder != null) {
                    int maxAmplitude = 0;
                    try {
                        maxAmplitude = mediaRecorder.getMaxAmplitude();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    LogKit.v("maxAmplitude:" + maxAmplitude);
                    int volumeLevel;
                    if (maxAmplitude >= 0 && maxAmplitude < 2000) {
                        volumeLevel = 1;
                    } else if (maxAmplitude >= 1000 && maxAmplitude < 4000) {
                        volumeLevel = 2;
                    } else if (maxAmplitude >= 2000 && maxAmplitude < 6000) {
                        volumeLevel = 3;
                    } else if (maxAmplitude >= 3000 && maxAmplitude < 8000) {
                        volumeLevel = 4;
                    } else {
                        volumeLevel = 5;
                    }

                    setRecorderVolumeLevelIcon(volumeLevel);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void setRecorderVolumeLevelIcon(final int volumeLevel) {
        CommonUtils.getHandler().post(new Runnable() {
            @Override
            public void run() {
                if (volumeLevel == 1) {
                    mActivityChatBinding.ivChatRecorderVolume.setImageResource(R.mipmap.column1);
                } else if (volumeLevel == 2) {
                    mActivityChatBinding.ivChatRecorderVolume.setImageResource(R.mipmap.column2);
                } else if (volumeLevel == 3) {
                    mActivityChatBinding.ivChatRecorderVolume.setImageResource(R.mipmap.column3);
                } else if (volumeLevel == 4) {
                    mActivityChatBinding.ivChatRecorderVolume.setImageResource(R.mipmap.column4);
                } else if (volumeLevel == 5) {
                    mActivityChatBinding.ivChatRecorderVolume.setImageResource(R.mipmap.column5);
                }
            }
        });
    }


    /**
     * 停止录音
     */
    public void stopSoundRecording() {
        try {
            if (mediaRecorder == null) {
                return;
            }
//            mediaRecorder.reset();
            try {
                mediaRecorder.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            mediaRecorder.release();
            mediaRecorder = null;

            int duration = 0;
            MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(tmpVoiceFile.getAbsolutePath());
                mediaPlayer.prepare();
                duration = mediaPlayer.getDuration() / 1000;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (duration < 1) {
                ToastUtils.shortToast("录音时间过短");
                deleteTmpRecordingFile();
                return;
            }
            if (!isCancelRecord) {
                sendVoice(tmpVoiceFile.getAbsolutePath(), duration);
            } else {
                deleteTmpRecordingFile();
            }
        } catch (Exception ex) {
            LogKit.v("-----------聊天中的停止录音异常，比较诡异的未知异常，多半和权限有关-------------");
            ex.printStackTrace();
        }
    }

    /**
     * 删除录音保存的临时文件
     */
    public void deleteTmpRecordingFile() {
        if (tmpVoiceFile != null && tmpVoiceFile.exists()) {
            tmpVoiceFile.delete();
        }
    }

    /**
     * 发送语音
     */
    public void sendVoice(String voiceFilePath, final int duration) {
        final File voiceFile = new File(voiceFilePath);
        final VoiceMessage vocMsg = VoiceMessage.obtain(Uri.fromFile(voiceFile), duration);
        final long sendTime = System.currentTimeMillis();

        final View mySendVoiceView = null;
        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, vocMsg, null, null, new RongIMClient.SendMessageCallback() {
            @Override
            public void onError(Integer messageId, RongIMClient.ErrorCode e) {
                deleteTmpRecordingFile();
                if (mySendVoiceView != null) {
                    View view = mySendVoiceView.findViewById(R.id.iv_chat_send_msg_again);
                    view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSuccess(Integer integer) {
//                mLlChatContent.addView(createMySendVoiceView(Uri.fromFile(voiceFile), duration));
//                mLlChatContent.addView(createMySendVoiceView(vocMsg.getUri(), duration));
                deleteTmpRecordingFile();
                MsgManager.updateConversationList(targetId);//更新会话列表
            }
        }, new RongIMClient.ResultCallback<Message>() {
            @Override
            public void onSuccess(Message message) {
                long sentTime = System.currentTimeMillis();
                displayMsgTimeView(sentTime);

                VoiceMessage savedVoiceMessage = (VoiceMessage) message.getContent();
                View mySendVoiceView = createMySendVoiceView(savedVoiceMessage.getUri(), duration, false, savedVoiceMessage, targetId);

                View vReadStatus = mySendVoiceView.findViewById(R.id.tv_chat_msg_read_status);
                SendMessageBean sendMessageBean = new SendMessageBean(sendTime - 60 * 1000, vReadStatus);
                listSendMsg.add(sendMessageBean);

                mLlChatContent.addView(mySendVoiceView);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });

    }

    /**
     * 发送文本
     */
    public void sendText() {
        final String inputText = mActivityChatBinding.etChatInput.getText().toString();
        TextMessage textMessage = TextMessage.obtain(inputText);
        final long sendTime = System.currentTimeMillis();
        final View myTextView = createMyTextView(inputText, false, textMessage, targetId);
        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, textMessage, null, null, new RongIMClient.SendMessageCallback() {
            //发送消息的回调
            @Override
            public void onSuccess(Integer integer) {
                MsgManager.updateConversationList(targetId);//更新会话列表
            }

            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
                View view = myTextView.findViewById(R.id.iv_chat_send_msg_again);
                view.setVisibility(View.VISIBLE);
            }
        }, new RongIMClient.ResultCallback<Message>() {
            //消息存库的回调，可用于获取消息实体
            @Override
            public void onSuccess(Message message) {

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
        long sentTime = System.currentTimeMillis();
        displayMsgTimeView(sentTime);

        View vReadStatus = myTextView.findViewById(R.id.tv_chat_msg_read_status);
        SendMessageBean sendMessageBean = new SendMessageBean(sendTime - 60 * 1000, vReadStatus);
        listSendMsg.add(sendMessageBean);

        mLlChatContent.addView(myTextView);

    }

    /**
     * 发送图片
     */
    public void sendPic(String imgPath) {
        File imageSource = new File(imgPath);
        File imageThumb = new File(CommonUtils.getContext().getCacheDir(), "thumb" + System.currentTimeMillis());

        try {
            Bitmap bmpSource = BitmapFactory.decodeFile(imgPath);

            // 创建缩略图变换矩阵。
            Matrix m = new Matrix();
            m.setRectToRect(new RectF(0, 0, bmpSource.getWidth(), bmpSource.getHeight()), new RectF(0, 0, 160, 160), Matrix.ScaleToFit.CENTER);

            // 生成缩略图。
            Bitmap bmpThumb = Bitmap.createBitmap(bmpSource, 0, 0, bmpSource.getWidth(), bmpSource.getHeight(), m, true);

            imageThumb.createNewFile();

            FileOutputStream fosThumb = new FileOutputStream(imageThumb);

            // 保存缩略图。
            bmpThumb.compress(Bitmap.CompressFormat.JPEG, 60, fosThumb);

        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageMessage imageMessage = ImageMessage.obtain(Uri.fromFile(imageThumb), Uri.fromFile(imageSource));
        long sendTime = System.currentTimeMillis();

        final View myPicView = createMyPicView(Uri.fromFile(imageThumb), false, imageMessage, targetId);
        RongIMClient.getInstance().sendImageMessage(Conversation.ConversationType.PRIVATE, targetId, imageMessage, null, null, new RongIMClient.SendImageMessageCallback() {

            @Override
            public void onAttached(Message message) {
                //保存数据库成功
                LogKit.v("保存数据库成功");
            }

            @Override
            public void onError(Message message, RongIMClient.ErrorCode code) {
                //发送失败
                LogKit.v("发送失败");
                View view = myPicView.findViewById(R.id.iv_chat_send_msg_again);
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onSuccess(Message message) {
                //发送成功
                LogKit.v("发送成功");
                ImageMessage imageMessage1 = (ImageMessage) message.getContent();
                LogKit.v(imageMessage1.getRemoteUri().toString());
                MsgManager.updateConversationList(targetId);//更新会话列表
            }

            @Override
            public void onProgress(Message message, int progress) {
                //发送进度
                LogKit.v("发送进度:" + progress);
            }
        });

        long sentTime = System.currentTimeMillis();
        displayMsgTimeView(sentTime);

        View vReadStatus = myPicView.findViewById(R.id.tv_chat_msg_read_status);
        SendMessageBean sendMessageBean = new SendMessageBean(sendTime - 60 * 1000, vReadStatus);
        listSendMsg.add(sendMessageBean);

        mLlChatContent.addView(myPicView);

        setUploadPicLayerVisibility(View.GONE);
    }


    /**
     * 添加好友
     */
    public void sendAddFriend() {
        MsgManager.getAddFriendStatus(new BaseProtocol.IResultExecutor<CommonResultBean>() {
            @Override
            public void execute(CommonResultBean dataBean) {
                int status = dataBean.data.status;
                if (status == 0) {
                    //0表示陌生人,可以发送添加好友请求
                    //调用发送添加好友申请的接口
                    MsgManager.addFriend(new BaseProtocol.IResultExecutor<CommonResultBean>() {
                        @Override
                        public void execute(CommonResultBean dataBean) {
                            ChatCmdAddFriendBean chatCmdAddFriendBean = new ChatCmdAddFriendBean();
                            chatCmdAddFriendBean.uid = LoginManager.currentLoginUserId;
                            Gson gson = new Gson();
                            String jsonData = gson.toJson(chatCmdAddFriendBean);
                            //{"content":"addFriend","extra":"{\"QQ_uid\":\"10003\"}"}
                            TextMessage textMessage = TextMessage.obtain(MsgManager.CHAT_CMD_ADD_FRIEND);
                            textMessage.setExtra(jsonData);
                            //CommandMessage commandMessage = CommandMessage.obtain(MsgManager.CHAT_CMD_ADD_FRIEND, jsonData);
                            RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, textMessage, null, null, new RongIMClient.SendMessageCallback() {
                                @Override
                                public void onSuccess(Integer integer) {
                                    long sentTime = System.currentTimeMillis();
                                    displayMsgTimeView(sentTime);
                                    //ToastUtils.shortToast("send add friend success");
                                    View infoView = createInfoView("您已发送添加好友请求");
                                    mLlChatContent.addView(infoView);
                                    MsgManager.updateConversationList(targetId);//更新会话列表
                                }

                                @Override
                                public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
                                    long sentTime = System.currentTimeMillis();
                                    displayMsgTimeView(sentTime);

                                    View infoView = createInfoView("发送添加好友请求失败！！！");
                                    mLlChatContent.addView(infoView);
                                }
                            });
                        }

                        @Override
                        public void executeResultError(String result) {
                            View infoView = createInfoView("发送添加好友请求失败");
                            mLlChatContent.addView(infoView);
                        }
                    }, targetId, "对方请求添加您为好友");
                } else if (status == 1) {
                    //1表示我主动加了他，他还未回复
                    View infoView = createInfoView("您已向对方发送添加好友请求");
                    mLlChatContent.addView(infoView);
                } else if (status == 2) {
                    //2表示他主动加了我，我还未同意
                    View infoView = createInfoView("对方已经向您发送添加好友请求");
                    mLlChatContent.addView(infoView);
                } else {
                    //3表示是好友关系
                    View infoView = createInfoView("您与对方已经是好友关系");
                    mLlChatContent.addView(infoView);
                }
            }

            @Override
            public void executeResultError(String result) {
                //这里不会执行
            }
        }, targetId);
    }


    boolean isSendChangeContack = false;//表示是否已经发送了交换手机号的请求，因为发送交换手机号请求，完全走的是融云，服务端没有相关接口，所以服务端没提供查找是否已经发送交换手机号请求的接口，这里只能用一个变量来判断

    /**
     * 交换联系方式
     */
    public void sendChangeContact() {

        MsgManager.getIsChangeContact(new BaseProtocol.IResultExecutor<IsChangeContactBean>() {
            @Override
            public void execute(IsChangeContactBean dataBean) {
                IsChangeContactBean.Data2 data = dataBean.data.data;
                if (data.status == 1) {
                    //已经交换过手机号
                    String otherPhone;
                    if (targetId.equals(data.uid1 + "")) {
                        otherPhone = data.uid1phone;
                    } else {
                        otherPhone = data.uid2phone;
                    }
                    View changeContactWayInfoView = createChangeContactWayInfoView(targetName, otherPhone);
                    mLlChatContent.addView(changeContactWayInfoView);
                } else {
                    //没有交换过手机号,发送交换手机号的请求
                    if (!isSendChangeContack) {
                        ChatCmdChangeContactBean chatCmdChangeContactBean = new ChatCmdChangeContactBean();
                        chatCmdChangeContactBean.uid = LoginManager.currentLoginUserId;
                        chatCmdChangeContactBean.phone = LoginManager.currentLoginUserPhone;
                        Gson gson = new Gson();
                        String jsonData = gson.toJson(chatCmdChangeContactBean);
                        TextMessage textMessage = TextMessage.obtain(MsgManager.CHAT_CMD_CHANGE_CONTACT);
                        textMessage.setExtra(jsonData);

                        //CommandMessage commandMessage = CommandMessage.obtain(MsgManager.CHAT_CMD_SHARE_TASK, jsonData);
                        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, textMessage, null, null, new RongIMClient.SendMessageCallback() {
                            @Override
                            public void onSuccess(Integer integer) {
                                long sentTime = System.currentTimeMillis();
                                displayMsgTimeView(sentTime);

                                View infoView = createInfoView("您已发送交换手机号请求");
                                mLlChatContent.addView(infoView);
                                isSendChangeContack = true;
                                MsgManager.updateConversationList(targetId);//更新会话列表
                            }

                            @Override
                            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
                                long sentTime = System.currentTimeMillis();
                                displayMsgTimeView(sentTime);

                                View infoView = createInfoView("发送交换联系方式请求失败");
                                mLlChatContent.addView(infoView);
                            }
                        });
                    } else {
                        View infoView = createInfoView("您已发送交货手机号的请求");
                        mLlChatContent.addView(infoView);
                    }
                }
            }

            @Override
            public void executeResultError(String result) {
                ToastUtils.shortToast("获取是否交换过手机号标识失败:" + result);
            }
        }, targetId);
    }

    /**
     * 发送任务分享
     */
    public void sendShareTask() {
        Gson gson = new Gson();
        String jsonData = gson.toJson(chatCmdShareTaskBean);
        TextMessage textMessage = TextMessage.obtain(MsgManager.CHAT_CMD_SHARE_TASK);
        textMessage.setExtra(jsonData);

//        CommandMessage commandMessage = CommandMessage.obtain(MsgManager.CHAT_CMD_SHARE_TASK, jsonData);
        final long sendTime = System.currentTimeMillis();
        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, textMessage, null, null, new RongIMClient.SendMessageCallback() {
            @Override
            public void onSuccess(Integer integer) {
                long sentTime = System.currentTimeMillis();
                displayMsgTimeView(sentTime);

                View myShareTaskView = createMyShareTaskView(false, chatCmdShareTaskBean);


                View vReadStatus = myShareTaskView.findViewById(R.id.tv_chat_msg_read_status);
                SendMessageBean sendMessageBean = new SendMessageBean(sendTime - 60 * 1000, vReadStatus);
                listSendMsg.add(sendMessageBean);

                mLlChatContent.addView(myShareTaskView);
                MsgManager.updateConversationList(targetId);//更新会话列表
            }

            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    /**
     * 发送个人名片
     */
    public void sendBusinessCard() {
        ChatCmdBusinesssCardBean chatCmdBusinesssCardBean = new ChatCmdBusinesssCardBean();
        chatCmdBusinesssCardBean.uid = LoginManager.currentLoginUserId;
        chatCmdBusinesssCardBean.avatar = "";
        chatCmdBusinesssCardBean.name = "tom";
        chatCmdBusinesssCardBean.industry = "";
        chatCmdBusinesssCardBean.profession = "";
        Gson gson = new Gson();
        String jsonData = gson.toJson(chatCmdBusinesssCardBean);
        TextMessage textMessage = TextMessage.obtain(MsgManager.CHAT_CMD_BUSINESS_CARD);
        textMessage.setExtra(jsonData);


//        CommandMessage commandMessage = CommandMessage.obtain(MsgManager.CHAT_CMD_BUSINESS_CARD, jsonData);
        final long sendTime = System.currentTimeMillis();
        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, textMessage, null, null, new RongIMClient.SendMessageCallback() {
            @Override
            public void onSuccess(Integer integer) {
                long sentTime = System.currentTimeMillis();
                displayMsgTimeView(sentTime);

                View mySendBusinessCardView = createMySendBusinessCardView(false);

                View vReadStatus = mySendBusinessCardView.findViewById(R.id.tv_chat_msg_read_status);
                SendMessageBean sendMessageBean = new SendMessageBean(sendTime - 60 * 1000, vReadStatus);
                listSendMsg.add(sendMessageBean);

                mLlChatContent.addView(mySendBusinessCardView);
                MsgManager.updateConversationList(targetId);//更新会话列表
            }

            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    //同意交换联系方式
    public void agreeChangeContact(final String otherPhone, final TextView tvDeny, final TextView tvAgree) {
        //需要调用服务端保存已交换过联系方式状态的接口
        TextMessage textMessage = TextMessage.obtain(MsgManager.CHAT_CMD_AGREE_CHANGE_CONTACT);
        String myPhone = LoginManager.currentLoginUserPhone;
        textMessage.setExtra("{\"content\":\"" + myPhone + "\",\"otherPhone\":\"" + otherPhone + "\"}");//这里的otherPhone好像没有用到

        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, textMessage, null, null, new RongIMClient.SendMessageCallback() {
            @Override
            public void onSuccess(Integer integer) {
                tvDeny.setBackgroundResource(R.drawable.shape_chat_deny_change_contact_way_bg);
                tvAgree.setBackgroundResource(R.drawable.shape_chat_agree_change_contact_way_bg);
                tvDeny.setEnabled(false);
                tvAgree.setEnabled(false);

                long sentTime = System.currentTimeMillis();
                displayMsgTimeView(sentTime);

                View changeContactWayInfoView = createChangeContactWayInfoView(targetName, otherPhone);
                mLlChatContent.addView(changeContactWayInfoView);
                MsgManager.updateConversationList(targetId);//更新会话列表
                //设置手机号交换标识
                MsgManager.setChangeContact(new BaseProtocol.IResultExecutor<CommonResultBean>() {
                    @Override
                    public void execute(CommonResultBean dataBean) {

                    }

                    @Override
                    public void executeResultError(String result) {
                        ToastUtils.shortToast("设置手机号交换标识失败:" + result);
                    }
                }, targetId);
            }

            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
                View infoView = createInfoView("同意交换联系方式失败");
                mLlChatContent.addView(infoView);
            }
        });
    }

    //拒绝交换联系方式
    public void refuseChangeContact(final TextView tvDeny, final TextView tvAgree) {
        TextMessage textMessage = TextMessage.obtain(MsgManager.CHAT_CMD_REFUSE_CHANGE_CONTACT);
        textMessage.setExtra("{\"content\":\"对方拒绝交换联系方式\"}");

        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, textMessage, null, null, new RongIMClient.SendMessageCallback() {
            @Override
            public void onSuccess(Integer integer) {
                tvDeny.setBackgroundResource(R.drawable.shape_chat_deny_change_contact_way_bg);
                tvAgree.setBackgroundResource(R.drawable.shape_chat_agree_change_contact_way_bg);
                tvDeny.setEnabled(false);
                tvAgree.setEnabled(false);

                long sentTime = System.currentTimeMillis();
                displayMsgTimeView(sentTime);

                View infoView = createInfoView("您已拒绝和对方交换联系方式");
                mLlChatContent.addView(infoView);
                MsgManager.updateConversationList(targetId);//更新会话列表
            }

            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
                View infoView = createInfoView("拒绝交换联系方式失败");
                mLlChatContent.addView(infoView);
            }
        });
    }

    //同意添加对方为好友
    public void agreeAddFriend(final TextView tvDeny, final TextView tvAgree) {
        //0表示陌生人 1表示我主动加了他，他还未回复 2表示他主动加了我，我还未同意  3表示是好友关系
        if (friendRelationShipStatus != 2) {
            //调用同意添加好友的接口
            MsgManager.agreeAddFriend(new BaseProtocol.IResultExecutor<CommonResultBean>() {
                @Override
                public void execute(CommonResultBean dataBean) {
                    //同意添加好友成功，此时双方已经是好友关系了
                    tvDeny.setBackgroundResource(R.drawable.shape_chat_deny_change_contact_way_bg);
                    tvAgree.setBackgroundResource(R.drawable.shape_chat_agree_change_contact_way_bg);
                    tvDeny.setEnabled(false);
                    tvAgree.setEnabled(false);

                    TextMessage textMessage = TextMessage.obtain(MsgManager.CHAT_CMD_AGREE_ADD_FRIEND);
                    textMessage.setExtra("{\"content\":\"对方同意加我为好友\"}");

                    RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, textMessage, null, null, new RongIMClient.SendMessageCallback() {
                        @Override
                        public void onSuccess(Integer integer) {
                            long sentTime = System.currentTimeMillis();
                            displayMsgTimeView(sentTime);

                            View infoView = createInfoView("您已同意添加对方为好友");
                            mLlChatContent.addView(infoView);
                            MsgManager.updateConversationList(targetId);//更新会话列表
                        }

                        @Override
                        public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {

                        }
                    });
                }

                @Override
                public void executeResultError(String result) {
                    View infoView = createInfoView("同意添加好友申请失败");
                    mLlChatContent.addView(infoView);
                }
            }, targetId, "对方已同意添加您为好友");
        } else if (friendRelationShipStatus == 0) {//陌生人
            View infoView = createInfoView("状态错误，对方未向您发送添加好友请求");
            mLlChatContent.addView(infoView);
        } else if (friendRelationShipStatus == 1) {//表示我主动加了他，他还未回复
            View infoView = createInfoView("状态错误，您已经向对方发送添加好友请求");
            mLlChatContent.addView(infoView);
        } else if (friendRelationShipStatus == 3) {//表示我主动加了他，他还未回复
            View infoView = createInfoView("状态错误，你们已经是好友关系");
            mLlChatContent.addView(infoView);
        }
    }

    //拒绝添加对方为好友
    public void refuseAddFriend(final TextView tvDeny, final TextView tvAgree) {
        //0表示陌生人 1表示我主动加了他，他还未回复 2表示他主动加了我，我还未同意  3表示是好友关系
        if (friendRelationShipStatus != 2) {
            //调用拒绝添加好友的接口
            MsgManager.rejectAddFriend(new BaseProtocol.IResultExecutor<CommonResultBean>() {
                @Override
                public void execute(CommonResultBean dataBean) {
                    tvDeny.setBackgroundResource(R.drawable.shape_chat_deny_change_contact_way_bg);
                    tvAgree.setBackgroundResource(R.drawable.shape_chat_agree_change_contact_way_bg);
                    tvDeny.setEnabled(false);
                    tvAgree.setEnabled(false);

                    TextMessage textMessage = TextMessage.obtain(MsgManager.CHAT_CMD_REFUSE_ADD_FRIEND);
                    textMessage.setExtra("{\"content\":\"对方拒绝加我为好友\"}");

                    RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, textMessage, null, null, new RongIMClient.SendMessageCallback() {
                        @Override
                        public void onSuccess(Integer integer) {
                            long sentTime = System.currentTimeMillis();
                            displayMsgTimeView(sentTime);

                            View infoView = createInfoView("您已拒绝添加对方为好友");
                            mLlChatContent.addView(infoView);
                            MsgManager.updateConversationList(targetId);//更新会话列表
                        }

                        @Override
                        public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {

                        }
                    });
                }

                @Override
                public void executeResultError(String result) {
                    View infoView = createInfoView("拒绝添加好友申请失败");
                    mLlChatContent.addView(infoView);
                }
            }, targetId, "对方拒绝添加您为好友");
        } else if (friendRelationShipStatus == 0) {//陌生人
            View infoView = createInfoView("状态错误，对方未向您发送添加好友请求");
            mLlChatContent.addView(infoView);
        } else if (friendRelationShipStatus == 1) {//表示我主动加了他，他还未回复
            View infoView = createInfoView("状态错误，您已经向对方发送添加好友请求");
            mLlChatContent.addView(infoView);
        } else if (friendRelationShipStatus == 3) {//表示我主动加了他，他还未回复
            View infoView = createInfoView("状态错误，你们已经是好友关系");
            mLlChatContent.addView(infoView);
        }
    }


    /**
     * 发送相关任务信息，一般是从“聊一聊”按钮进来，就需要发送相关任务信息
     */
    public void sendRelatedTaskInfo(ChatTaskInfoBean chatTaskInfoBean) {
        //{"name":"taskInfo","data":"{\"tid\":\"123\",\"type\":\"1\",\"title\":\"APP开发\"}"}

        Gson gson = new Gson();
        String jsonData = gson.toJson(chatTaskInfoBean);
        CommandMessage commandMessage = CommandMessage.obtain(MsgManager.CHAT_TASK_INFO, jsonData);

        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, targetId, commandMessage, null, null, new RongIMClient.SendMessageCallback() {
            @Override
            public void onSuccess(Integer integer) {

            }

            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    public void goBack(View v) {
        mActivity.finish();
    }


    long firstMsgDisplayTime = -1;

    /**
     * 显示发送和接收消息的时间间隔
     *
     * @param sendTime
     */
    private void displayMsgTimeView(long sendTime) {
        long timeSpan = sendTime - firstMsgDisplayTime;
        if (timeSpan >= 5 * 60 * 1000) {
            View dateTimeView = createDateTimeView(sendTime);
            mLlChatContent.addView(dateTimeView);
            firstMsgDisplayTime = sendTime;
        }
    }

    //创建我发的文本消息View
    private View createMyTextView(String inputText, boolean isRead, TextMessage textMessage, String targetId) {
        ItemChatMyTextBinding itemChatMyTextBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_my_text, null, false);
        ChatMyTextModel chatMyTextModel = new ChatMyTextModel(itemChatMyTextBinding, mActivity, inputText, isRead, textMessage, targetId);
        itemChatMyTextBinding.setChatMyTextModel(chatMyTextModel);
        return itemChatMyTextBinding.getRoot();
    }

    //创建好友发的文本消息View
    private View createFriendTextView(String content) {
        ItemChatFriendTextBinding itemChatFriendTextBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_friend_text, null, false);
        ChatFriendTextModel chatFriendTextModel = new ChatFriendTextModel(itemChatFriendTextBinding, mActivity, targetAvatar);
        itemChatFriendTextBinding.setChatFriendTextModel(chatFriendTextModel);
        chatFriendTextModel.setTextContent(content);
        return itemChatFriendTextBinding.getRoot();
    }

    //创建聊天记录显示时间 View
    private View createDateTimeView(long datetime) {
        ItemChatDatetimeBinding itemChatDatetimeBinding =
                DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_datetime, null, false);
        ChatDatetimeModel chatDatetimeModel = new ChatDatetimeModel(itemChatDatetimeBinding, mActivity, datetime);
        itemChatDatetimeBinding.setChatDatetimeModel(chatDatetimeModel);
        return itemChatDatetimeBinding.getRoot();
    }

    //创建我发的图片View
    private View createMyPicView(Uri thumUri, boolean isRead, ImageMessage imageMessage, String targetId) {
        ItemChatMyPicBinding itemChatMyPicBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_my_pic, null, false);
        ChatMyPicModel chatMyPicModel = new ChatMyPicModel(itemChatMyPicBinding, mActivity, thumUri, isRead, imageMessage, targetId);
        itemChatMyPicBinding.setChatMyPicModel(chatMyPicModel);
        return itemChatMyPicBinding.getRoot();
    }

    //创建好友发的图片View
    private View createFriendPicView(Uri thumUri) {
        ItemChatFriendPicBinding itemChatFriendPicBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_friend_pic, null, false);
        ChatFriendPicModel chatFriendPicModel = new ChatFriendPicModel(itemChatFriendPicBinding, mActivity, thumUri, targetAvatar);
        itemChatFriendPicBinding.setChatFriendPicModel(chatFriendPicModel);
        return itemChatFriendPicBinding.getRoot();
    }

    private View createOtherSendAddFriendView(ChatCmdAddFriendBean chatCmdAddFriendBean, boolean isCanAddFriend) {
        ItemChatOtherSendAddFriendBinding itemChatOtherSendAddFriendBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_other_send_add_friend, null, false);
        ChatOtherSendAddFriendModel chatOtherSendAddFriendModel = new ChatOtherSendAddFriendModel(itemChatOtherSendAddFriendBinding, mActivity, this, targetAvatar, isCanAddFriend);
        itemChatOtherSendAddFriendBinding.setChatOtherSendAddFriendModel(chatOtherSendAddFriendModel);
        return itemChatOtherSendAddFriendBinding.getRoot();
    }

    //这种情况应该不存在，如果自己点击添加好友，应该是只提示一条消息
    private void createMySendAddFriendView() {

    }

    //参数可能只是为了方便测试
    private View createInfoView(String info) {
        ItemChatInfoBinding itemChatInfoBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_info, null, false);
        ChatInfoModel chatInfoModel = new ChatInfoModel(itemChatInfoBinding, mActivity);
        itemChatInfoBinding.setChatInfoModel(chatInfoModel);
        chatInfoModel.setInfo(info);
        return itemChatInfoBinding.getRoot();
    }

    private View createOtherShareTaskView(ChatCmdShareTaskBean chatCmdShareTaskBean) {
        ItemChatOtherShareTaskBinding itemChatOtherShareTaskBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_other_share_task, null, false);
        ChatOtherShareTaskModel chatOtherShareTaskModel = new ChatOtherShareTaskModel(itemChatOtherShareTaskBinding, mActivity, targetAvatar, chatCmdShareTaskBean);
        itemChatOtherShareTaskBinding.setChatOtherShareTaskModel(chatOtherShareTaskModel);
        View shareTaskView = itemChatOtherShareTaskBinding.getRoot();
        shareTaskView.setOnClickListener(new ShareTaskClickListener(chatCmdShareTaskBean));
        return shareTaskView;
    }

    private View createMyShareTaskView(boolean isRead, ChatCmdShareTaskBean chatCmdShareTaskBean) {
        ItemChatMyShareTaskBinding itemChatMyShareTaskBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_my_share_task, null, false);
        ChatMyShareTaskModel chatMyShareTaskModel = new ChatMyShareTaskModel(itemChatMyShareTaskBinding, mActivity, isRead, chatCmdShareTaskBean);
        itemChatMyShareTaskBinding.setChatMyShareTaskModel(chatMyShareTaskModel);
        View shareView = itemChatMyShareTaskBinding.getRoot();
        shareView.setOnClickListener(new ShareTaskClickListener(chatCmdShareTaskBean));
        return shareView;
    }

    private View createOtherSendBusinessCardView(ChatCmdBusinesssCardBean chatCmdBusinesssCardBean) {
        ItemChatOtherSendBusinessCardBinding itemChatOtherSendBusinessCardBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_other_send_business_card, null, false);
        ChatOtherSendBusinessCardModel chatOtherSendBusinessCardModel = new ChatOtherSendBusinessCardModel(itemChatOtherSendBusinessCardBinding, mActivity, targetAvatar);
        itemChatOtherSendBusinessCardBinding.setChatOtherSendBusinessCardModel(chatOtherSendBusinessCardModel);
        return itemChatOtherSendBusinessCardBinding.getRoot();
    }

    private View createMySendBusinessCardView(boolean isRead) {
        ItemChatMySendBusinessCardBinding itemChatMySendBusinessCardBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_my_send_business_card, null, false);
        ChatMySendBusinessCardModel chatMySendBusinessCardModel = new ChatMySendBusinessCardModel(itemChatMySendBusinessCardBinding, mActivity, isRead);
        itemChatMySendBusinessCardBinding.setChatMySendBusinessCardModel(chatMySendBusinessCardModel);
        return itemChatMySendBusinessCardBinding.getRoot();
    }

    private View createOtherChangeContactWayView(ChatCmdChangeContactBean chatCmdChangeContactBean, String otherPhone, boolean isChangeContact) {
        ItemChatOtherChangeContactWayBinding itemChatOtherChangeContactWayBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_other_change_contact_way, null, false);
        ChatOtherChangeContactWayModel chatOtherChangeContactWayModel = new ChatOtherChangeContactWayModel(itemChatOtherChangeContactWayBinding, mActivity, this, otherPhone, targetAvatar, isChangeContact);
        itemChatOtherChangeContactWayBinding.setChatOtherChangeContactWayModel(chatOtherChangeContactWayModel);
        return itemChatOtherChangeContactWayBinding.getRoot();
    }

    private View createChangeContactWayInfoView(String name, String otherPhone) {
        ItemChatChangeContactWayInfoBinding itemChatChangeContactWayInfoBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_change_contact_way_info, null, false);
        ChatChangeContactWayInfoModel chatChangeContactWayInfoModel = new ChatChangeContactWayInfoModel(itemChatChangeContactWayInfoBinding, mActivity, name, otherPhone);
        itemChatChangeContactWayInfoBinding.setChatChangeContactWayInfoModel(chatChangeContactWayInfoModel);
        return itemChatChangeContactWayInfoBinding.getRoot();
    }

    private View createOtherSendVoiceView(Uri voiceUri, int duration) {
        ItemChatOtherSendVoiceBinding itemChatOtherSendVoiceBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_other_send_voice, null, false);
        ChatOtherSendVoiceModel chatOtherSendVoiceModel = new ChatOtherSendVoiceModel(itemChatOtherSendVoiceBinding, mActivity, voiceUri, duration, targetAvatar);
        itemChatOtherSendVoiceBinding.setChatOtherSendVoiceModel(chatOtherSendVoiceModel);
        return itemChatOtherSendVoiceBinding.getRoot();
    }

    private View createMySendVoiceView(Uri voiceUri, int duration, boolean isRead, VoiceMessage voiceMessage, String targetId) {
        ItemChatMySendVoiceBinding itemChatMySendVoiceBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_chat_my_send_voice, null, false);
        ChatMySendVoiceModel chatMySendVoiceModel = new ChatMySendVoiceModel(itemChatMySendVoiceBinding, mActivity, voiceUri, duration, isRead, voiceMessage, targetId);
        itemChatMySendVoiceBinding.setChatMySendVoiceModel(chatMySendVoiceModel);
        return itemChatMySendVoiceBinding.getRoot();
    }

    private void hideSoftInputMethod() {
        //使底部的输入框失去焦点，隐藏软键盘
        mTvChatFriendName = mActivityChatBinding.tvChatFriendName;
        mTvChatFriendName.setFocusable(true);
        mTvChatFriendName.setFocusableInTouchMode(true);
        mTvChatFriendName.requestFocus();
        //如果在软键盘还没有弹起来的时候，就让输入框失去焦点，软键盘是不会再弹起来的；但是，如果软键盘已经弹起来了，只是让输入框失去焦点，软键盘是无法隐藏的，必须调用以下相应的API才行
        InputMethodManager imm = (InputMethodManager) CommonUtils.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mTvChatFriendName.getWindowToken(), 0);
    }

    public void openUploadPic(View v) {
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MESSAGE_CHAT_CLICK_UPLOAD_PICTURE_PLUS);

        hideSoftInputMethod();
        setUploadPicLayerVisibility(View.VISIBLE);
    }

    public void closeUploadPic(View v) {
        setUploadPicLayerVisibility(View.GONE);
    }

    public void sendText(View v) {
        sendText();
        mActivityChatBinding.etChatInput.setText("");

        v.requestFocus();
    }

    //拍照发送图片
    public void photoGraph(View v) {
        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mActivity.startActivityForResult(intentCamera, 20);
//        sendPic("/storage/sdcard1/4.jpg");
    }

    //选择相册图片发送
    public void getAlbumPic(View v) {
        Intent intentAddPicture = new Intent();
        intentAddPicture.setType("image/*");
        intentAddPicture.setAction(Intent.ACTION_GET_CONTENT);
        intentAddPicture.putExtra("crop", "true");
        intentAddPicture.putExtra("outputX", CommonUtils.dip2px(91));
        intentAddPicture.putExtra("outputY", CommonUtils.dip2px(91));
        intentAddPicture.putExtra("outputFormat", "JPEG");
        intentAddPicture.putExtra("aspectX", 1);
        intentAddPicture.putExtra("aspectY", 1);
        intentAddPicture.putExtra("return-data", true);
        mActivity.startActivityForResult(intentAddPicture, 10);
//        sendPic("/storage/sdcard1/4.jpg");
    }

    //交换联系方式
    public void sendChangeContact(View v) {
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MESSAGE_CHAT_CLICK_TELEPHONE);
        sendChangeContact();
    }

    //添加好友
    public void sendAddFriend(View v) {
        sendAddFriend();
    }

    public void switchVoiceInput(View v) {
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MESSAGE_CHAT_CLICK_SWITCH_CHARACTER_VOICE);
        switchVoiceInput();
    }

    public void switchTextInput(View v) {
        MobclickAgent.onEvent(CommonUtils.getContext(), CustomEventAnalyticsUtils.EventID.MESSAGE_CHAT_CLICK_SWITCH_CHARACTER_VOICE);
        switchTextInput();
    }

    //切换到语音输入
    public void switchVoiceInput() {
        //当切换到语音输入状态时，语音输入按钮需要隐藏，文本输入按钮需要显示
        setVoiceInputIconVisibility(View.GONE);
        setTextInputIconVisibility(View.VISIBLE);
        setInputVoiceTvVisibility(View.VISIBLE);
        setInputTextEtVisibility(View.GONE);
    }

    //切换到文本输入
    public void switchTextInput() {
        //当切换到文本输入状态时，语音输入按钮需要显示，文本输入按钮需要隐藏
        setVoiceInputIconVisibility(View.VISIBLE);
        setTextInputIconVisibility(View.GONE);
        setInputVoiceTvVisibility(View.GONE);
        setInputTextEtVisibility(View.VISIBLE);
    }

    long lastHisMsgDisplayTime = -1;
    long preHisMsgTime = -1;


    /**
     * 显示历史消息的时间间隔信息
     *
     * @param sendTime
     */
    private void displayHisMsgTimeView(long sendTime) {
        LogKit.v("displayHisMsgTimeView sendTime:" + sendTime);
        if (preHisMsgTime != -1) {
            long timeSpan = lastHisMsgDisplayTime - sendTime;
            LogKit.v("timeSpan:" + timeSpan);
            if (timeSpan >= 5 * 60 * 1000) {
                LogKit.v("-----------displayMsgTime-----------");
                View dateTimeView = createDateTimeView(preHisMsgTime);
//                View dateTimeView = createDateTimeView(sendTime);
                mLlChatContent.addView(dateTimeView, 0);
                lastHisMsgDisplayTime = preHisMsgTime;
            }
        }
        preHisMsgTime = sendTime;
        if (lastHisMsgDisplayTime == -1) {
            lastHisMsgDisplayTime = sendTime;
        }
    }

    boolean isLoadHisMsgFirst = true;

    public class ChatHistoryListener implements MsgManager.HistoryListener {

        @Override
        public void displayHistory(List<Message> messages) {
            if (isLoadHisMsgFirst && messages.size() > 0) {
                View hisInfoView = createInfoView("----以上是历史消息----");
                mLlChatContent.addView(hisInfoView);
                isLoadHisMsgFirst = false;
            }

            LogKit.v("Message Count:" + messages.size());
            for (Message message : messages) {
                long sendTime = message.getSentTime();
                displayHisMsgTimeView(sendTime);

                LogKit.v("SenderId:" + message.getSenderUserId() + "  sentTime:" + message.getSentTime() + "   Direction:" + message.getMessageDirection() + " objectName:" + message.getObjectName());
                Message.MessageDirection messageDirection = message.getMessageDirection();
                if (messageDirection == Message.MessageDirection.SEND) {
                    //自己发送的消息
                    loadSendHisMsg(message);
                } else if (messageDirection == Message.MessageDirection.RECEIVE) {
                    //接收到的消息
                    loadReceiveHisMsg(message);
                }
            }
            LogKit.v("sendReadReceipt");
//            LogKit.v(SystemClock.currentThreadTimeMillis() + "");//这个方法不能用，返回的时间不是系统时间
            LogKit.v(System.currentTimeMillis() + "");
            sendReadReceipt(System.currentTimeMillis());
        }

    }


    /**
     * 加载显示自己发送的历史消息
     *
     * @param message
     */
    private void loadSendHisMsg(Message message) {
        Message.SentStatus sentStatus = message.getSentStatus();
        boolean isRead = false;//对方是否已读
        if (sentStatus == Message.SentStatus.READ) {
            isRead = true;
        }
        long sendTime = message.getSentTime();

        String objectName = message.getObjectName();
        if (objectName.equals("RC:TxtMsg")) {
            TextMessage textMessage = (TextMessage) message.getContent();
            String extra = textMessage.getExtra();
            Gson gson = new Gson();
            //接收聊天的文本消息
            if (TextUtils.isEmpty(extra)) {
                View myTextView = createMyTextView(textMessage.getContent(), isRead, null, null);
                if (!isRead) {
                    View vReadStatus = myTextView.findViewById(R.id.tv_chat_msg_read_status);
                    SendMessageBean sendMessageBean = new SendMessageBean(sendTime, vReadStatus);
                    listSendMsg.add(sendMessageBean);
                }
                mLlChatContent.addView(myTextView, 0);
            } else {
                String content = textMessage.getContent();
                if (content.contentEquals(MsgManager.CHAT_CMD_ADD_FRIEND)) {
                    View infoView = createInfoView("您已发送添加好友请求");
                    mLlChatContent.addView(infoView, 0);
                } else if (content.contentEquals(MsgManager.CHAT_CMD_SHARE_TASK)) {
                    ChatCmdShareTaskBean chatCmdShareTaskBean = gson.fromJson(extra, ChatCmdShareTaskBean.class);
                    View myShareTaskView = createMyShareTaskView(isRead, chatCmdShareTaskBean);
                    if (!isRead) {
                        View vReadStatus = myShareTaskView.findViewById(R.id.tv_chat_msg_read_status);
                        SendMessageBean sendMessageBean = new SendMessageBean(sendTime, vReadStatus);
                        listSendMsg.add(sendMessageBean);
                    }
                    mLlChatContent.addView(myShareTaskView, 0);
                } else if (content.contentEquals(MsgManager.CHAT_CMD_BUSINESS_CARD)) {
                    View mySendBusinessCardView = createMySendBusinessCardView(isRead);
                    if (!isRead) {
                        View vReadStatus = mySendBusinessCardView.findViewById(R.id.tv_chat_msg_read_status);
                        SendMessageBean sendMessageBean = new SendMessageBean(sendTime, vReadStatus);
                        listSendMsg.add(sendMessageBean);
                    }
                    mLlChatContent.addView(mySendBusinessCardView, 0);
                } else if (content.contentEquals(MsgManager.CHAT_CMD_CHANGE_CONTACT)) {
                    View infoView = createInfoView("您已发送交换手机号请求");
                    mLlChatContent.addView(infoView, 0);
                } else if (content.contentEquals(MsgManager.CHAT_CMD_AGREE_ADD_FRIEND)) {
                    View infoView = createInfoView("您已同意添加对方为好友");
                    mLlChatContent.addView(infoView, 0);
                } else if (content.contentEquals(MsgManager.CHAT_CMD_REFUSE_ADD_FRIEND)) {
                    View infoView = createInfoView("您已拒绝添加对方为好友");
                    mLlChatContent.addView(infoView, 0);
                } else if (content.contentEquals(MsgManager.CHAT_CMD_AGREE_CHANGE_CONTACT)) {
                    try {
                        JSONObject jo = new JSONObject(extra);
                        String otherPhone = jo.getString("otherPhone");
                        View changeContactWayInfoView = createChangeContactWayInfoView(targetName, otherPhone);
                        mLlChatContent.addView(changeContactWayInfoView, 0);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (content.contentEquals(MsgManager.CHAT_CMD_REFUSE_CHANGE_CONTACT)) {
                    View infoView = createInfoView("您已拒绝和对方交换联系方式");
                    mLlChatContent.addView(infoView, 0);
                }
            }
        }
        //接收聊天的图片消息
        else if (objectName.equals("RC:ImgMsg")) {
            ImageMessage imageMessage = (ImageMessage) message.getContent();
            View myPicView = createMyPicView(imageMessage.getThumUri(), isRead, null, null);
            if (!isRead) {
                View vReadStatus = myPicView.findViewById(R.id.tv_chat_msg_read_status);
                SendMessageBean sendMessageBean = new SendMessageBean(sendTime, vReadStatus);
                listSendMsg.add(sendMessageBean);
            }
            mLlChatContent.addView(myPicView, 0);
        }
        //接受聊天的语音消息
        else if (objectName.equals("RC:VcMsg")) {
            VoiceMessage savedVoiceMessage = (VoiceMessage) message.getContent();
            View mySendVoiceView = createMySendVoiceView(savedVoiceMessage.getUri(), savedVoiceMessage.getDuration(), isRead, null, null);
            if (!isRead) {
                View vReadStatus = mySendVoiceView.findViewById(R.id.tv_chat_msg_read_status);
                SendMessageBean sendMessageBean = new SendMessageBean(sendTime, vReadStatus);
                listSendMsg.add(sendMessageBean);
            }
            mLlChatContent.addView(mySendVoiceView, 0);
        }
    }

    /**
     * 加载显示接收到的历史消息
     *
     * @param message
     */
    private void loadReceiveHisMsg(Message message) {
        String objectName = message.getObjectName();
        if (objectName.equals("RC:TxtMsg")) {
            TextMessage textMessage = (TextMessage) message.getContent();
            String extra = textMessage.getExtra();
            //接收聊天的文本消息
            if (TextUtils.isEmpty(extra)) {
                displayReceiveTextMsg(message, true);
            } else {
                //接收聊天中的其它命令消息
                displayReceiveOtherCmdMsg(message, true);
            }
        }
        //接收聊天的图片消息
        else if (objectName.equals("RC:ImgMsg")) {
            displayReceiveImageMsg(message, true);
        }
        //接受聊天的语音消息
        else if (objectName.equals("RC:VcMsg")) {
            displayReceiveVoiceMsg(message, true);
        }
    }

    private void displayReceiveTextMsg(Message message, boolean isLoadHis) {
        TextMessage textMessage = (TextMessage) message.getContent();
        String content = textMessage.getContent();
        View friendTextView = createFriendTextView(content);
        if (isLoadHis) {
            mLlChatContent.addView(friendTextView, 0);
        } else {
            mLlChatContent.addView(friendTextView);
        }
    }

    private void displayReceiveImageMsg(Message message, boolean isLoadHis) {
        ImageMessage imageMessage = (ImageMessage) message.getContent();
        Uri thumUri = imageMessage.getThumUri();
//                Uri localUri = imageMessage.getLocalUri();
        Uri remoteUri = imageMessage.getRemoteUri();
        LogKit.v("remoteUri:" + remoteUri.toString());
        View friendPicView = createFriendPicView(thumUri);
        if (isLoadHis) {
            mLlChatContent.addView(friendPicView, 0);
        } else {
            mLlChatContent.addView(friendPicView);
        }
    }

    private void displayReceiveVoiceMsg(Message message, boolean isLoadHis) {
        VoiceMessage voiceMessage = (VoiceMessage) message.getContent();
        int duration = voiceMessage.getDuration();
        Uri voiceUri = voiceMessage.getUri();
        if (isLoadHis) {
            mLlChatContent.addView(createOtherSendVoiceView(voiceUri, duration), 0);
        } else {
            mLlChatContent.addView(createOtherSendVoiceView(voiceUri, duration));
        }
    }

    private void displayReceiveOtherCmdMsg(Message message, boolean isLoadHis) {
        TextMessage textMessage = (TextMessage) message.getContent();
        String content = textMessage.getContent();
        String extra = textMessage.getExtra();

//                String name = commandMessage.getName();
//                String data = commandMessage.getData();
        Gson gson = new Gson();
        View msgView = null;
        if (content.contentEquals(MsgManager.CHAT_CMD_ADD_FRIEND)) {
            //0表示陌生人 1表示我主动加了他，他还未回复 2表示他主动加了我，我还未同意  3表示是好友关系
            boolean isCanAddFriend;
            if (friendRelationShipStatus == 2) {
                isCanAddFriend = true;
            } else {
                isCanAddFriend = false;
            }
            ChatCmdAddFriendBean chatCmdAddFriendBean = gson.fromJson(extra, ChatCmdAddFriendBean.class);
            msgView = createOtherSendAddFriendView(chatCmdAddFriendBean, isCanAddFriend);
//            mLlChatContent.addView(createOtherSendAddFriendView(chatCmdAddFriendBean));
        } else if (content.contentEquals(MsgManager.CHAT_CMD_SHARE_TASK)) {
            ChatCmdShareTaskBean chatCmdShareTaskBean = gson.fromJson(extra, ChatCmdShareTaskBean.class);
            msgView = createOtherShareTaskView(chatCmdShareTaskBean);
//            mLlChatContent.addView(createOtherShareTaskView(chatCmdShareTaskBean));
        } else if (content.contentEquals(MsgManager.CHAT_CMD_BUSINESS_CARD)) {
            ChatCmdBusinesssCardBean chatCmdBusinesssCardBean = gson.fromJson(extra, ChatCmdBusinesssCardBean.class);
            msgView = createOtherSendBusinessCardView(chatCmdBusinesssCardBean);
//            mLlChatContent.addView(createOtherSendBusinessCardView(chatCmdBusinesssCardBean));
        } else if (content.contentEquals(MsgManager.CHAT_CMD_CHANGE_CONTACT)) {
            //1标识已经交换过  -1标识尚未交换过
            boolean isChangeContact;
            if (changeContactStatus == 1) {
                isChangeContact = true;
            } else {
                isChangeContact = false;
            }
            ChatCmdChangeContactBean chatCmdChangeContactBean = gson.fromJson(extra, ChatCmdChangeContactBean.class);
            msgView = createOtherChangeContactWayView(chatCmdChangeContactBean, chatCmdChangeContactBean.phone, isChangeContact);
//            mLlChatContent.addView(createOtherChangeContactWayView(chatCmdChangeContactBean, chatCmdChangeContactBean.phone));
        } else if (content.contentEquals(MsgManager.CHAT_CMD_AGREE_ADD_FRIEND)) {
            try {
                JSONObject jo = new JSONObject(extra);
                String info = jo.getString("content");
                msgView = createInfoView(info);
//                View infoView = createInfoView(info);
//                mLlChatContent.addView(infoView);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (content.contentEquals(MsgManager.CHAT_CMD_REFUSE_ADD_FRIEND)) {
            try {
                JSONObject jo = new JSONObject(extra);
                String info = jo.getString("content");
                msgView = createInfoView(info);
//                View infoView = createInfoView(info);
//                mLlChatContent.addView(infoView);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (content.contentEquals(MsgManager.CHAT_CMD_AGREE_CHANGE_CONTACT)) {
            try {
                JSONObject jo = new JSONObject(extra);
                String phone = jo.getString("content");
                msgView = createChangeContactWayInfoView(targetName, phone);
//                View changeContactWayInfoView = createChangeContactWayInfoView(targetName, phone);
//                mLlChatContent.addView(changeContactWayInfoView);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (content.contentEquals(MsgManager.CHAT_CMD_REFUSE_CHANGE_CONTACT)) {
            try {
                JSONObject jo = new JSONObject(extra);
                String info = jo.getString("content");
                msgView = createInfoView(info);
//                View infoView = createInfoView(info);
//                mLlChatContent.addView(infoView);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (isLoadHis) {
            mLlChatContent.addView(msgView, 0);
        } else {
            mLlChatContent.addView(msgView);
        }

    }

    public void displayRelatedTask() {
        File dataDir = CommonUtils.getContext().getFilesDir();
        LogKit.v("getFilesDir():" + dataDir.getAbsolutePath());
        File relatedTaskFiles = new File(dataDir,
                "relatedTaskDir/" + LoginManager.currentLoginUserId + "to" + targetId);
        if (relatedTaskFiles.exists()) {
            FileInputStream fis = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            try {
                fis = new FileInputStream(relatedTaskFiles);
                isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);
                String jsonData = br.readLine();
                LogKit.v("relatedTask jsonData:" + jsonData);
                if (TextUtils.isEmpty(jsonData)) {
                    mActivityChatBinding.flRelatedTaskLine.setVisibility(View.GONE);
                } else {
                    mActivityChatBinding.flRelatedTaskLine.setVisibility(View.VISIBLE);
                    Gson gson = new Gson();
                    ChatTaskInfoBean chatTaskInfoBean = gson.fromJson(jsonData, ChatTaskInfoBean.class);
                    LogKit.v("chatTaskInfoBean.title:" + chatTaskInfoBean.title);
                    setRelatedTaskTitle("相关任务:" + chatTaskInfoBean.title);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IOUtils.close(br);
                IOUtils.close(isr);
                IOUtils.close(fis);
            }
            relatedTaskFiles.delete();
        } else {
            mActivityChatBinding.flRelatedTaskLine.setVisibility(View.GONE);
        }
    }

    private class ShareTaskClickListener implements View.OnClickListener {
        ChatCmdShareTaskBean chatCmdShareTaskBean;

        public ShareTaskClickListener(ChatCmdShareTaskBean chatCmdShareTaskBean) {
            this.chatCmdShareTaskBean = chatCmdShareTaskBean;
        }

        @Override
        public void onClick(View v) {
            if (chatCmdShareTaskBean.type == 1) {//需求
                Intent intentDemandDetailActivity = new Intent(CommonUtils.getContext(), DemandDetailActivity.class);
                intentDemandDetailActivity.putExtra("demandId", chatCmdShareTaskBean.tid);
                mActivity.startActivity(intentDemandDetailActivity);
            } else {//服务
                Intent intentServiceDetailActivity = new Intent(CommonUtils.getContext(), ServiceDetailActivity.class);
                intentServiceDetailActivity.putExtra("serviceId", chatCmdShareTaskBean.tid);
                mActivity.startActivity(intentServiceDetailActivity);
            }
        }
    }

    private int voiceInputIconVisibility = View.VISIBLE;
    private int textInputIconVisibility = View.GONE;
    private int inputTextEtVisibility = View.VISIBLE;
    private int inputVoiceTvVisibility = View.GONE;
    private int uploadPicLayerVisibility = View.GONE;
    private int uploadPicBtnVisibility = View.VISIBLE;
    private int sendTextBtnVisibility = View.GONE;
    private int sendVoiceCmdLayerVisibility = View.GONE;
    private int upCancelSendVoiceVisibility;
    private int relaseCancelSendVoiceVisibility;
    private String relatedTaskTitle = "相关任务:";
    private String otherUsername;
    private String otherCompanyAndPosition;
    private int loadLayerVisibility = View.GONE;

    @Bindable
    public int getLoadLayerVisibility() {
        return loadLayerVisibility;
    }

    public void setLoadLayerVisibility(int loadLayerVisibility) {
        this.loadLayerVisibility = loadLayerVisibility;
        notifyPropertyChanged(BR.loadLayerVisibility);
    }

    @Bindable
    public String getOtherCompanyAndPosition() {
        return otherCompanyAndPosition;
    }

    public void setOtherCompanyAndPosition(String otherCompanyAndPosition) {
        this.otherCompanyAndPosition = otherCompanyAndPosition;
        notifyPropertyChanged(BR.otherCompanyAndPosition);
    }

    @Bindable
    public String getOtherUsername() {
        return otherUsername;
    }

    public void setOtherUsername(String otherUsername) {
        this.otherUsername = otherUsername;
        notifyPropertyChanged(BR.otherUsername);
    }

    @Bindable
    public int getTextInputIconVisibility() {
        return textInputIconVisibility;
    }

    public void setTextInputIconVisibility(int textInputIconVisibility) {
        this.textInputIconVisibility = textInputIconVisibility;
        notifyPropertyChanged(BR.textInputIconVisibility);
    }

    @Bindable
    public int getVoiceInputIconVisibility() {
        return voiceInputIconVisibility;
    }

    public void setVoiceInputIconVisibility(int voiceInputIconVisibility) {
        this.voiceInputIconVisibility = voiceInputIconVisibility;
        notifyPropertyChanged(BR.voiceInputIconVisibility);
    }

    @Bindable
    public int getInputVoiceTvVisibility() {
        return inputVoiceTvVisibility;
    }

    public void setInputVoiceTvVisibility(int inputVoiceTvVisibility) {
        this.inputVoiceTvVisibility = inputVoiceTvVisibility;
        notifyPropertyChanged(BR.inputVoiceTvVisibility);
    }

    @Bindable
    public int getInputTextEtVisibility() {
        return inputTextEtVisibility;
    }

    public void setInputTextEtVisibility(int inputTextEtVisibility) {
        this.inputTextEtVisibility = inputTextEtVisibility;
        notifyPropertyChanged(BR.inputTextEtVisibility);
    }

    @Bindable
    public int getUploadPicLayerVisibility() {
        return uploadPicLayerVisibility;
    }

    public void setUploadPicLayerVisibility(int uploadPicLayerVisibility) {
        this.uploadPicLayerVisibility = uploadPicLayerVisibility;
        notifyPropertyChanged(BR.uploadPicLayerVisibility);
    }

    @Bindable
    public int getUploadPicBtnVisibility() {
        return uploadPicBtnVisibility;
    }

    public void setUploadPicBtnVisibility(int uploadPicBtnVisibility) {
        this.uploadPicBtnVisibility = uploadPicBtnVisibility;
        notifyPropertyChanged(BR.uploadPicBtnVisibility);
    }

    @Bindable
    public int getSendTextBtnVisibility() {
        return sendTextBtnVisibility;
    }

    public void setSendTextBtnVisibility(int sendTextBtnVisibility) {
        this.sendTextBtnVisibility = sendTextBtnVisibility;
        notifyPropertyChanged(BR.sendTextBtnVisibility);
    }

    @Bindable
    public int getSendVoiceCmdLayerVisibility() {
        return sendVoiceCmdLayerVisibility;
    }

    public void setSendVoiceCmdLayerVisibility(int sendVoiceCmdLayerVisibility) {
        this.sendVoiceCmdLayerVisibility = sendVoiceCmdLayerVisibility;
        notifyPropertyChanged(BR.sendVoiceCmdLayerVisibility);
    }

    @Bindable
    public int getUpCancelSendVoiceVisibility() {
        return upCancelSendVoiceVisibility;
    }

    public void setUpCancelSendVoiceVisibility(int upCancelSendVoiceVisibility) {
        this.upCancelSendVoiceVisibility = upCancelSendVoiceVisibility;
        notifyPropertyChanged(BR.upCancelSendVoiceVisibility);
    }

    @Bindable
    public int getRelaseCancelSendVoiceVisibility() {
        return relaseCancelSendVoiceVisibility;
    }

    public void setRelaseCancelSendVoiceVisibility(int relaseCancelSendVoiceVisibility) {
        this.relaseCancelSendVoiceVisibility = relaseCancelSendVoiceVisibility;
        notifyPropertyChanged(BR.relaseCancelSendVoiceVisibility);
    }

    @Bindable
    public String getRelatedTaskTitle() {
        return relatedTaskTitle;
    }

    public void setRelatedTaskTitle(String relatedTaskTitle) {
        this.relatedTaskTitle = relatedTaskTitle;
        notifyPropertyChanged(BR.relatedTaskTitle);
    }
}
