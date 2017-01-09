package com.slash.youth.ui.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;
import android.widget.AdapterView;

import com.slash.youth.databinding.PagerHomeInfoBinding;
import com.slash.youth.domain.ConversationListBean;
import com.slash.youth.engine.MsgManager;
import com.slash.youth.http.protocol.BaseProtocol;
import com.slash.youth.ui.activity.ChatActivity;
import com.slash.youth.ui.activity.MyTaskActivity;
import com.slash.youth.ui.activity.SearchActivity;
import com.slash.youth.ui.adapter.HomeInfoListAdapter;
import com.slash.youth.utils.CommonUtils;

import java.util.ArrayList;

/**
 * Created by zhouyifeng on 2016/10/11.
 */
public class PagerHomeInfoModel extends BaseObservable {
    PagerHomeInfoBinding mPagerHomeInfoBinding;
    Activity mActivity;

    int conversationListOffset = 0;
    int conversationListLimit = 20;

    public PagerHomeInfoModel(PagerHomeInfoBinding pagerHomeInfoBinding, Activity activity) {
        this.mPagerHomeInfoBinding = pagerHomeInfoBinding;
        this.mActivity = activity;
        initView();
        initListener();
    }

    ArrayList<ConversationListBean.ConversationInfo> listConversation = new ArrayList<ConversationListBean.ConversationInfo>();

    private void initView() {
        getDataFromServer();
    }

    private void initListener() {
        mPagerHomeInfoBinding.lvPagerHomeInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConversationListBean.ConversationInfo conversationInfo = listConversation.get(position);
                long uid = conversationInfo.uid;
                Intent intentChatActivity = new Intent(CommonUtils.getContext(), ChatActivity.class);
                intentChatActivity.putExtra("targetId", uid + "");
                mActivity.startActivity(intentChatActivity);
            }
        });
    }

    public void getDataFromServer() {
        listConversation.clear();
        MsgManager.getConversationList(new BaseProtocol.IResultExecutor<ConversationListBean>() {
            @Override
            public void execute(ConversationListBean dataBean) {
                listConversation = dataBean.data.list;
                if (listConversation != null) {
                    for (int i = 0; i < listConversation.size(); i++) {
                        ConversationListBean.ConversationInfo conversationInfo = listConversation.get(i);
                        MsgManager.conversationUidList.add(conversationInfo.uid + "");
                    }
                    setConversationList();
                }
            }

            @Override
            public void executeResultError(String result) {

            }
        }, conversationListOffset + "", conversationListLimit + "");
    }

    HomeInfoListAdapter homeInfoListAdapter;

    public void setConversationList() {
        if (homeInfoListAdapter == null) {
            homeInfoListAdapter = new HomeInfoListAdapter(listConversation);
            mPagerHomeInfoBinding.lvPagerHomeInfo.setAdapter(homeInfoListAdapter);
        } else {
            homeInfoListAdapter.notifyDataSetChanged();
        }
    }


    //跳转到我的任务界面
    public void gotoMyTask(View v) {
        Intent intentMyTaskActivity = new Intent(CommonUtils.getContext(), MyTaskActivity.class);
        mActivity.startActivity(intentMyTaskActivity);
    }

    public void gotoSearchActivity(View v) {
        Intent intentSearchActivity = new Intent(CommonUtils.getContext(), SearchActivity.class);
        mActivity.startActivity(intentSearchActivity);
    }
}
