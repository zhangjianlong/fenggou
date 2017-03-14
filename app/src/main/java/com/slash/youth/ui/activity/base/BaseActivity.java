package com.slash.youth.ui.activity.base;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.slash.youth.R;
import com.slash.youth.ui.activity.MessageActivity;
import com.slash.youth.ui.dialog.offline.OfflineDialog;
import com.slash.youth.ui.dialog.offline.OfflineViewModel;
import com.slash.youth.utils.CommonUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by zhouyifeng on 2017/2/25.
 */
public class BaseActivity extends RxAppCompatActivity {

    private OfflineDialog offlineDialog;

    private boolean isOffline = false;

    View msgIconLayer;
    ImageView ivMsgIcon;
    boolean isAddMsgIconLayer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //向没个Ativity都添加进入消息列表的icon
//        msgIconLayer = View.inflate(CommonUtils.getContext(), R.layout.layer_every_msg_icon, null);
//        ivMsgIcon = (ImageView) msgIconLayer.findViewById(R.id.iv_msg_icon);
//        ivMsgIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentMessageActivity = new Intent(CommonUtils.getContext(), MessageActivity.class);
//                startActivity(intentMessageActivity);
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();

//        if (msgIconLayer != null) {
//            if (!isAddMsgIconLayer) {
//                if (this instanceof SplashActivity || this instanceof LoginActivity || this instanceof PerfectInfoActivity || this instanceof ChooseSkillActivity || this instanceof MessageActivity || this instanceof GuidActivity || this instanceof ChatActivity) {
//
//                } else {
//                    this.addContentView(msgIconLayer, new ViewGroup.LayoutParams(-1, -1));
//                    isAddMsgIconLayer = true;
//                }
//            }
//        }
    }


    public void offline() {
        if (offlineDialog == null) {
            offlineDialog = new OfflineDialog(this, new OfflineViewModel(this));
        }
        if (!offlineDialog.isShowing()) {
            offlineDialog.show();
        }
        isOffline = true;
    }

}
