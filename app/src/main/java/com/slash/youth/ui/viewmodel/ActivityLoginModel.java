package com.slash.youth.ui.viewmodel;

import android.Manifest;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.slash.youth.databinding.ActivityLoginBinding;
import com.slash.youth.domain.PhoneLoginResultBean;
import com.slash.youth.domain.SendPinResultBean;
import com.slash.youth.domain.ThirdPartyLoginResultBean;
import com.slash.youth.engine.LoginManager;
import com.slash.youth.http.protocol.BaseProtocol;
import com.slash.youth.ui.activity.HomeActivity;
import com.slash.youth.ui.activity.LoginActivity;
import com.slash.youth.ui.activity.PerfectInfoActivity;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.LogKit;
import com.slash.youth.utils.SpUtils;
import com.slash.youth.utils.ToastUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UmengTool;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by zhouyifeng on 2016/9/5.
 */
public class ActivityLoginModel extends BaseObservable {
    public static final int PAGE_STATE_REGISTER = 1000;//"新手注册"状态
    public static final int PAGE_STATE_GOTOLOGIN = 1001;//"我有账号,去登录"状态

    ActivityLoginBinding mActivityLoginBinding;
    LoginActivity.QQLoginUiListener qqLoginUiListener;
    LoginActivity loginActivity;
    SsoHandler mSsoHandler;
    int currentPageState = PAGE_STATE_GOTOLOGIN;//当前的页面状态，默认为"我有账号,去登录"状态


    public ActivityLoginModel(ActivityLoginBinding activityLoginBinding, LoginActivity.QQLoginUiListener qqLoginUiListener, LoginActivity loginActivity, SsoHandler ssoHandler) {
        this.mActivityLoginBinding = activityLoginBinding;
        this.qqLoginUiListener = qqLoginUiListener;
        this.loginActivity = loginActivity;
        this.mSsoHandler = ssoHandler;
        initData();
        initView();
    }

    private void initData() {
    }

    private void initView() {
//        setRegisterAndLoginTextVisibility();
    }


    /**
     * 登录按钮点击事件
     *
     * @param v
     */
    public void login(View v) {
        String phoenNum = mActivityLoginBinding.etActivityLoginPhonenum.getText().toString();
        String pin = mActivityLoginBinding.etActivityLoginVerificationCode.getText().toString();
        if (TextUtils.isEmpty(phoenNum) || TextUtils.isEmpty(pin)) {
            ToastUtils.shortToast("手机号或者验证码不能为空");
            return;
        }
        LoginManager.phoneLogin(new BaseProtocol.IResultExecutor<PhoneLoginResultBean>() {
            @Override
            public void execute(PhoneLoginResultBean dataBean) {
                //如果登录失败，dataBean.data可能是null  {  "rescode": 7  }
                if (dataBean.data == null) {
                    ToastUtils.shortToast("登录失败:" + dataBean.rescode);
                    return;
                }

                String rongToken = dataBean.data.rongToken;//融云token
                String token = dataBean.data.token;
                long uid = dataBean.data.uid;

                if (dataBean.rescode == 0) {
                    //登陆成功，老用户
                    savaLoginState(uid, token, rongToken);

                    Intent intentHomeActivity = new Intent(CommonUtils.getContext(), HomeActivity.class);
                    intentHomeActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CommonUtils.getContext().startActivity(intentHomeActivity);
                } else if (dataBean.rescode == 11) {
                    //登陆成功，新用户
                    savaLoginState(uid, token, rongToken);

                    Intent intentPerfectInfoActivity = new Intent(CommonUtils.getContext(), PerfectInfoActivity.class);
                    intentPerfectInfoActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CommonUtils.getContext().startActivity(intentPerfectInfoActivity);
                } else {
                    ToastUtils.shortToast("登录失败:" + dataBean.rescode);
                }
            }

            @Override
            public void executeResultError(String result) {
                //这里不会执行
            }
        }, phoenNum, pin, "", "");

        //TODO 具体的登录逻辑，等服务端相关接口完成以后再实现
//                Intent intentPerfectInfoActivity = new Intent(CommonUtils.getContext(), PerfectInfoActivity.class);
//        intentPerfectInfoActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        CommonUtils.getContext().startActivity(intentPerfectInfoActivity);

//        Intent intentHomeActivity = new Intent(CommonUtils.getContext(), HomeActivity.class);
//        intentHomeActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        CommonUtils.getContext().startActivity(intentHomeActivity);

//        String phoenNum = mActivityLoginBinding.etActivityLoginPhonenum.getText().toString();
//        String pin = mActivityLoginBinding.etActivityLoginVerificationCode.getText().toString();
//        LoginManager.checkPhoneVerificationCode(phoenNum, pin);

        //这里跳转至聊天界面只是为了测试聊天界面

     /*   Intent intentChatActivity = new Intent(CommonUtils.getContext(), ChatActivity.class);
        intentChatActivity.putExtra("chatCmdName", "sendShareTask");
        intentChatActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        CommonUtils.getContext().startActivity(intentChatActivity);
*/

        // Intent intentChatActivity = new Intent(CommonUtils.getContext(), ChatActivity.class);
////        intentChatActivity.putExtra("chatCmdName", "sendShareTask");
//
//        Bundle taskInfoBundle = new Bundle();
//        taskInfoBundle.putLong("tid", 111);
//        taskInfoBundle.putInt("type", 1);
//        taskInfoBundle.putString("title", "APP开发");
//        intentChatActivity.putExtra("taskInfo", taskInfoBundle);
//
//        intentChatActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        CommonUtils.getContext().startActivity(intentChatActivity);


//        Intent intentChatActivity = new Intent(CommonUtils.getContext(), ChatActivity.class);
////        intentChatActivity.putExtra("chatCmdName", "sendShareTask");
//
//        Bundle taskInfoBundle = new Bundle();
//        taskInfoBundle.putLong("tid", 111);
//        taskInfoBundle.putInt("type", 1);
//        taskInfoBundle.putString("title", "APP开发");
//        intentChatActivity.putExtra("taskInfo", taskInfoBundle);
//
//        intentChatActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        CommonUtils.getContext().startActivity(intentChatActivity);


    }

    /**
     * @param uid
     * @param token
     * @param rongToken
     */
    private void savaLoginState(long uid, String token, String rongToken) {
        LoginManager.currentLoginUserId = uid;
        LoginManager.token = token;
        LoginManager.rongToken = rongToken;

        SpUtils.setLong("uid", uid);
        SpUtils.setString("token", token);
        SpUtils.setString("rongToken", rongToken);
    }

    public void sendPhoneVerificationCode(View v) {
        String phoenNum = mActivityLoginBinding.etActivityLoginPhonenum.getText().toString();
        if (TextUtils.isEmpty(phoenNum)) {
            ToastUtils.shortToast("手机号不能为空");
            return;
        }
        LogKit.v(phoenNum);
        //调用发送手机验证码接口，将验证码发送到手机上
        LoginManager.getPhoneVerificationCode(new BaseProtocol.IResultExecutor<SendPinResultBean>() {
            @Override
            public void execute(SendPinResultBean dataBean) {
                ToastUtils.shortToast("获取验证码成功");
            }

            @Override
            public void executeResultError(String result) {
                ToastUtils.shortToast("获取验证码失败");
            }
        }, phoenNum);

    }

    public void wechatLogin(View v) {
        //  LoginManager.loginWeChat();
        UMShareAPI mShareAPI = UMShareAPI.get(loginActivity);
        mShareAPI.doOauthVerify(loginActivity, SHARE_MEDIA.WEIXIN, umAuthListener);
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(loginActivity, mPermissionList, 123);
        }

    }

    public void qqLogin(View v) {
//        LoginManager.loginQQ(qqLoginUiListener, loginActivity);
        UMShareAPI mShareAPI = UMShareAPI.get(loginActivity);
        mShareAPI.doOauthVerify(loginActivity, SHARE_MEDIA.QQ, umAuthListener);
    }

    public void weiboLogin(View v) {
//        mSsoHandler.authorize(new SlashWeiboAuthListener());

        //验证包名和签名,是微信那边的
        UmengTool.getSignature(loginActivity);

       /* UMShareAPI mShareAPI = UMShareAPI.get(loginActivity);
        mShareAPI.doOauthVerify(loginActivity, SHARE_MEDIA.SINA, umAuthListener);*/
    }


    public class SlashWeiboAuthListener implements WeiboAuthListener {
        @Override
        public void onComplete(Bundle values) {
            // 从 Bundle 中解析 Token
            Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(values);
            if (mAccessToken.isSessionValid()) {
                // 保存 Token 到 SharedPreferences
                String token = mAccessToken.getToken();
                String uid = mAccessToken.getUid();
                LogKit.v("weibo token:" + token + "    weibo QQ_uid:" + uid);
            } else {
                // 当您注册的应用程序签名不正确时，就会收到 Code，请确保签名正确
                String code = values.getString("code", "");

            }
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onWeiboException(WeiboException e) {
        }
    }

    String QQ_access_token;
    String QQ_uid;
    String WEIXIN_access_token;
    String WEIXIN_unionid;

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            ToastUtils.shortToast("Authorize succeed");
            UMShareAPI mShareAPI = UMShareAPI.get(loginActivity);
            switch (platform) {
                case QQ:
                    LogKit.v("qq data size:" + data.size());
                    for (String key : data.keySet()) {
                        LogKit.v("-----------QQ Login------------" + key + ":" + data.get(key));
                    }
                    QQ_access_token = data.get("access_token");
                    QQ_uid = data.get("uid");
                    SpUtils.setString("QQ_token", QQ_access_token);
                    SpUtils.setString("QQ_uid", QQ_uid);
                    LogKit.v("QQ_access_token:" + QQ_access_token + " QQ_uid:" + QQ_uid);
                    if (TextUtils.isEmpty(QQ_access_token) || TextUtils.isEmpty(QQ_uid)) {
                        ToastUtils.shortToast("QQ登录失败");
                        return;
                    }

                    mShareAPI.getPlatformInfo(loginActivity, SHARE_MEDIA.QQ, umAuthListenerForUserInfo);
                    break;
                case WEIXIN:
                    LogKit.v("weixin data size:" + data.size());
                    for (String key : data.keySet()) {
                        LogKit.v("-----------WEIXIN Login------------" + key + ":" + data.get(key));
                    }
                    WEIXIN_access_token = data.get("access_token");
                    WEIXIN_unionid = data.get("unionid");
                    SpUtils.setString("WEIXIN_token", WEIXIN_access_token);
                    SpUtils.setString("WEIXIN_uid", WEIXIN_unionid);
                    LogKit.v("WEIXIN_access_token:" + WEIXIN_access_token + " openid:" + WEIXIN_unionid);
                    if (TextUtils.isEmpty(WEIXIN_access_token) || TextUtils.isEmpty(WEIXIN_unionid)) {
                        ToastUtils.shortToast("微信登录失败");
                        return;
                    }

                    mShareAPI.getPlatformInfo(loginActivity, SHARE_MEDIA.WEIXIN, umAuthListenerForUserInfo);
                    if (Build.VERSION.SDK_INT >= 23) {
                        String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
                        ActivityCompat.requestPermissions(loginActivity, mPermissionList, 123);
                    }
                    break;
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            ToastUtils.shortToast("Authorize fail");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            ToastUtils.shortToast("Authorize cancel");
        }
    };

    String QQ_nickname;
    String QQ_gender;
    String QQ_avatar;
    String QQ_province;
    String QQ_city;
    String WEIXIN_nickname;
    String WEIXIN_gender;
    String WEIXIN_avatar;
    String WEIXIN_province;
    String WEIXIN_city;


    private UMAuthListener umAuthListenerForUserInfo = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            ToastUtils.shortToast("GetUserInfo succeed");
            switch (platform) {
                case QQ:
                    LogKit.v("qq data size:" + data.size());
                    for (String key : data.keySet()) {
                        LogKit.v("-----------QQ UserInfo------------" + key + ":" + data.get(key));
                    }
                    QQ_nickname = data.get("screen_name");
                    String gender = data.get("gender");
                    if (gender.equals("男")) {
                        QQ_gender = "1";
                    } else {
                        QQ_gender = "2";
                    }
                    QQ_avatar = data.get("profile_image_url");
                    QQ_province = data.get("province");
                    QQ_city = data.get("city");
//                    name = data.get("screen_name");
//                    gender = data.get("gender");
//                    city = data.get("city");
//                    LogKit.v("name:" + name + "  gender:" + gender + "  city:" + city);
                    LogKit.v("QQ_access_token:" + QQ_access_token + "  QQ_uid:" + QQ_uid);
                    LoginManager.serverThirdPartyLogin(onThirdPartyLoginFinished, QQ_access_token, QQ_uid, "2");
                    break;
                case WEIXIN:
                    LogKit.v("weixin data size:" + data.size());
                    for (String key : data.keySet()) {
                        LogKit.v("-----------WEIXIN UserInfo------------" + key + ":" + data.get(key));
                    }
                    WEIXIN_nickname = data.get("screen_name");
                    WEIXIN_gender = data.get("gender");
                    WEIXIN_avatar = data.get("profile_image_url");
                    WEIXIN_province = data.get("province");
                    WEIXIN_city = data.get("city");

//                    name = data.get("screen_name");
//                    gender = data.get("gender");
//                    city = data.get("city");
//                    LogKit.v("name:" + name + "  gender:" + gender + "  city:" + city);
                    LogKit.v("WEIXIN_access_token:" + WEIXIN_access_token + "  WEIXIN_unionid:" + WEIXIN_unionid);
                    LoginManager.serverThirdPartyLogin(onThirdPartyLoginFinished, WEIXIN_access_token, WEIXIN_unionid, "1");
                    break;
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            ToastUtils.shortToast("GetUserInfo fail");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            ToastUtils.shortToast("GetUserInfo cancel");
        }
    };

    public BaseProtocol.IResultExecutor onThirdPartyLoginFinished = new BaseProtocol.IResultExecutor<ThirdPartyLoginResultBean>() {
        @Override
        public void execute(ThirdPartyLoginResultBean dataBean) {
            if (dataBean.rescode == 9) {//新用户，需要绑定手机号
                LogKit.v("新用户，需要绑定手机号");
                String _3ptoken = dataBean.data.token;

                Intent intentPerfectInfoActivity = new Intent(CommonUtils.getContext(), PerfectInfoActivity.class);
                intentPerfectInfoActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                CommonUtils.getContext().startActivity(intentPerfectInfoActivity);
            } else if (dataBean.rescode == 0) {//已经登录过的用户
                LogKit.v("已经登录过的用户");
                String token = dataBean.data.token;
                long uid = dataBean.data.uid;
                LoginManager.token = token;
                LoginManager.currentLoginUserId = uid;
                SpUtils.setString("token", token);
                SpUtils.setLong("uid", uid);
                //跳转到首页
                Intent intentHomeActivity = new Intent(CommonUtils.getContext(), HomeActivity.class);
                intentHomeActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                CommonUtils.getContext().startActivity(intentHomeActivity);
            } else {
                LogKit.v("服务端第三方登录失败");
                ToastUtils.shortToast("服务端第三方登录失败");
            }
        }

        @Override
        public void executeResultError(String result) {
            //这里不会执行
        }
    };

}
