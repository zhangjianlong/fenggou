package com.slash.youth.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.tool.reflection.Callable;
import android.net.sip.SipAudioCall;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.slash.youth.R;
import com.slash.youth.databinding.ActivityResultAllBinding;
import com.slash.youth.databinding.ActivitySearchBinding;
import com.slash.youth.databinding.SearchActivityHotServiceBinding;
import com.slash.youth.databinding.SearchListviewAssociationBinding;
import com.slash.youth.databinding.SearchNeedResultTabBinding;
import com.slash.youth.databinding.SearchPagerFirstBinding;
import com.slash.youth.ui.viewmodel.ActivitySearchModel;
import com.slash.youth.ui.viewmodel.SearchActivityHotServiceModel;
import com.slash.youth.ui.viewmodel.SearchNeedResultTabModel;
import com.slash.youth.ui.viewmodel.SearchPagerFirstModel;
import com.slash.youth.ui.viewmodel.SearchResultAllModel;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.LogKit;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.game.UMGameAgent;
import com.umeng.analytics.social.UMPlatformData;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;

import java.util.Map;


/**
 * Created by zss on 2016/9/18.
 */
public class SearchActivity extends Activity {
    public SearchNeedResultTabBinding searchNeedResultTabBinding;
    public SearchActivityHotServiceBinding searchActivityHotServiceBinding;
    public ActivitySearchBinding activitySearchBinding;
    public ActivityResultAllBinding activityResultAllBinding;
    public SearchPagerFirstBinding searchPagerFirstBinding;
    public String checkedFirstLabel = "行业类别";
    public int mPage = 0;
    private SearchPagerFirstModel searchPagerFirstModel;
    public SearchListviewAssociationBinding searchListviewAssociationBinding;
    public static int titleHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtils.setCurrentActivity(this);
        //加载搜索框页面
        activitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        ActivitySearchModel activitySearchModel = new ActivitySearchModel(activitySearchBinding,this);
        activitySearchBinding.setActivitySearchModel(activitySearchModel);
        CommonUtils.setCurrentActivity(SearchActivity.this);

        activitySearchBinding.rlSeachBar.measure(0,0);
        titleHeight = activitySearchBinding.rlSeachBar.getMeasuredHeight();

        initView();
        removeView();
    }

    //加载页面
    private void initView() {
        //加载首页
        searchPagerFirstBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getApplication()), R.layout.search_pager_first, null, false);
        searchPagerFirstModel = new SearchPagerFirstModel(searchPagerFirstBinding,activitySearchBinding);
        searchPagerFirstBinding.setSearchPagerFirstModel(searchPagerFirstModel);

        //加载搜索技能页面
        searchActivityHotServiceBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getCurrentActivity()), R.layout.search_activity_hot_service, null, false);

        //加载搜索结果页面
        searchNeedResultTabBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getCurrentActivity()), R.layout.search_need_result_tab, null, false);

        //加载搜索所有结果页面
        activityResultAllBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getCurrentActivity()), R.layout.activity_result_all, null, false);

        //搜索历史和搜索记录的页面
        searchListviewAssociationBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getCurrentActivity()), R.layout.search_listview_association, null, false);

        //默认首页,page=0
        changeView(mPage);

    }

    //切换页面
    public void changeView(int page) {
        activitySearchBinding.flSearchFirst.removeAllViews();
        switch (page) {
            case 0:
                activitySearchBinding.flSearchFirst.addView(searchPagerFirstBinding.getRoot());
                break;
            case 1:
                activitySearchBinding.flSearchFirst.addView(searchActivityHotServiceBinding.getRoot());
                break;
            case 2:
                activitySearchBinding.flSearchFirst.addView(searchNeedResultTabBinding.getRoot());
                break;
            case 3:
                activitySearchBinding.flSearchFirst.addView(activityResultAllBinding.getRoot());
                break;
            case 4:
                activitySearchBinding.flSearchFirst.addView(searchListviewAssociationBinding.getRoot());
                break;
            case 5:
                activitySearchBinding.flSearchFirst.addView(searchNeedResultTabBinding.getRoot());
                break;

        }
        mPage = page;
    }

    //删除页面
    private void removeView() {
        activitySearchBinding.ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mPage) {
                    case 0:
                        CommonUtils.getCurrentActivity().finish();
                        break;
                    case 1:
                        changeView(0);
                        mPage = 0;
                        break;
                    case 2:
                        changeView(1);
                        mPage = 1;
                          listener.OnBackClick();
                        break;
                    case 3:
                        changeView(0);
                        mPage = 0;
                        break;
                    case 4:
                        changeView(0);
                        mPage = 0;
                        break;
                    case 5:
                        changeView(3);
                        mPage = 1;
                        break;
                }
                if (activitySearchBinding.etActivitySearchAssociation.getText() != null) {
                    activitySearchBinding.etActivitySearchAssociation.setText(null);
                }

            }
        });
    }
    //监听回调返回键
    public interface OnBacklickCListener{
        void OnBackClick();
    }

    private OnBacklickCListener listener;
    public void setOnBackClickListener(OnBacklickCListener listener) {
        this.listener = listener;
    }
}
