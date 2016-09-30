package com.slash.youth.ui.viewmodel;

import android.app.AlertDialog;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import com.slash.youth.R;
import com.slash.youth.databinding.ActivitySearchBinding;
import com.slash.youth.databinding.DialogSearchCleanBinding;
import com.slash.youth.databinding.SearchActivityHotServiceBinding;
import com.slash.youth.databinding.SearchNeedResultTabBinding;
import com.slash.youth.domain.SearchNeedItem;
import com.slash.youth.domain.SearchPersonItem;
import com.slash.youth.domain.SearchServiceItem;
import com.slash.youth.domain.SkillLabelBean;
import com.slash.youth.ui.adapter.PagerSearchItemAdapter;
import com.slash.youth.ui.adapter.SearchHistoryListAdapter;
import com.slash.youth.ui.adapter.SubscribeSecondSkilllabelAdapter;
import com.slash.youth.ui.holder.SubscribeSecondSkilllabelHolder;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.LogKit;
import com.slash.youth.utils.SpUtils;

import java.util.ArrayList;

/**
 * Created by zhouyifeng on 2016/9/18.
 */
public class ActivitySearchModel extends BaseObservable {
    private  ActivitySearchBinding mActivitySearchBinding;
    private boolean isShow;
    private SearchHistoryListAdapter adapter;
    public static int  searchType =0;
    private int  searchNeed = 1;
    private int  searchService = 2;
    private int  searchPerson = 3;
    private ArrayList<SearchNeedItem> needArrayList;
    private SearchActivityHotServiceBinding searchActivityHotServiceBinding;
    private SearchActivityHotServiceModel searchActivityHotServiceModel;
    private ArrayList<SearchServiceItem> serviceArrayList;
    private ArrayList<SearchPersonItem> personArrayList;

    public ActivitySearchModel(ActivitySearchBinding activitySearchBinding) {
        this.mActivitySearchBinding = activitySearchBinding;
        //搜索框的监听，zss
        searchListener();
    }

    //zss,点击直接搜索
    public void etSearch(View v){
       // LogKit.d("点击搜索框，出现历史纪录");
        mActivitySearchBinding.rlSearchMain.setVisibility(View.GONE);
        mActivitySearchBinding.llSearchHistory.setVisibility(View.VISIBLE);
        //加载搜索框历史的数据
        initSearchHistoryData();
        //删除单条和清除所有
        cleanAll(true);
    }

    //zss 判断显示清除所有历史
    private void cleanAll(boolean isShow) {
        mActivitySearchBinding.tvCleanAll.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //点击显示对话框
    public void showDialog(View v){
        LogKit.d("显示对话框");
        //创建AlertDialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CommonUtils.getCurrentActivity());
        //数据绑定填充视图
        DialogSearchCleanBinding dialogSearchCleanBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.dialog_search_clean, null, false);
        //创建数据模型
        DialogSearchCleanModel dialogSearchCleanModel = new DialogSearchCleanModel(dialogSearchCleanBinding);
        //绑定数据模型
        dialogSearchCleanBinding.setDialogSearchCleanModel(dialogSearchCleanModel);
        //设置布局
        dialogBuilder.setView(dialogSearchCleanBinding.getRoot());//getRoot返回根布局
        //创建dialogSearch
        AlertDialog dialogSearch = dialogBuilder.create();
        dialogSearch.show();
        dialogSearchCleanModel.currentDialog = dialogSearch;
        dialogSearch.setCanceledOnTouchOutside(false);//设置点击Dialog外部任意区域关闭Dialog
        Window dialogSubscribeWindow = dialogSearch.getWindow();
        WindowManager.LayoutParams dialogParams = dialogSubscribeWindow.getAttributes();
        //定义显示的dialog的宽和高
        dialogParams.width = CommonUtils.dip2px(280);//宽度
        dialogParams.height = CommonUtils.dip2px(200);//高度
        dialogSubscribeWindow.setAttributes(dialogParams);
//        dialogSubscribeWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialogSubscribeWindow.setDimAmount(0.1f);//dialog的灰度
//        dialogBuilder.show();
    }

    //zss,退出当前的页面
    public void back(View v){
        switch (searchType){
            case 0:
                CommonUtils.getCurrentActivity().finish();
                break;
            case 1:
            case 2:
            case 3:
                mActivitySearchBinding.flSearchFirst.removeView(searchActivityHotServiceBinding.getRoot());
                mActivitySearchBinding.rlSearchMain.setVisibility(View.VISIBLE);
                mActivitySearchBinding.tvSearchResult.setText("搜索");
                searchType = 0;
                break;
            case 4:
                SearchNeedResultTabBinding searchNeedResultTabBinding = searchActivityHotServiceModel.getSearchNeedResultTabBinding();
                mActivitySearchBinding.flSearchFirst.removeView(searchNeedResultTabBinding.getRoot());
                mActivitySearchBinding.tvSearchResult.setText("取消");
                searchType =1;
                break;
        }
    }

      //zss.监听框的搜索
    private void searchListener() {
        // 搜索框里面文字变化，出现联想搜索
        mActivitySearchBinding.etActivitySearchAssociation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //下面的首页隐藏
                mActivitySearchBinding.rlSearchMain.setVisibility(View.GONE);
                //显示搜索
                mActivitySearchBinding.llSearchHistory.setVisibility(View.VISIBLE);

                //文字监听不为0，就显示搜索内容
                if (s.length()!=0) {
                   isShow = false;
                }else if(s.length() == 0){
                    isShow =true;
                }
                adapter.showRemoveBtn(isShow);
                //显示cleanAll
                cleanAll(isShow);
            }

            //文本改变之后,输入的内容要保存到本地
            @Override
            public void afterTextChanged(Editable s) {
                //获取搜索内容
                if (s.length() != 0) {
                    String searchHistory = s.toString();
                    //searchHistoryList.add(searchHistory);
                   /*  try {
                        bw = new BufferedWriter(new FileWriter(file));
                        for(int i=0;i<searchHistoryList.size();i++){
                            bw.write(searchHistoryList.get(i));
                            bw.newLine();
                        }
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                }
            }
        });

        //设置搜索的数据，zss
        setSearchContentData(isShow);
        //点击搜索，显示搜索的结果 zss
        mActivitySearchBinding.tvSearchResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示结果页面
               // LogKit.d("显示直接搜索结果页面");
                //直接搜索结果
                 showSearchAllReasultView();
            }
        });
    }
    //zss 直接搜索结果
    private void showSearchAllReasultView() {
     mActivitySearchBinding.flSearchFirst.removeAllViews();
    ListView listView = new ListView(CommonUtils.getContext());
     //TODO 假数据 接口传入数据，zss
        needArrayList = new ArrayList<>();
        //1
        needArrayList.add(new SearchNeedItem());
        needArrayList.add(new SearchNeedItem());
        needArrayList.add(new SearchNeedItem());
        needArrayList.add(new SearchNeedItem());
        needArrayList.add(new SearchNeedItem());
        //2
        serviceArrayList = new ArrayList<>();
        serviceArrayList.add(new SearchServiceItem());
        serviceArrayList.add(new SearchServiceItem());
        serviceArrayList.add(new SearchServiceItem());
        serviceArrayList.add(new SearchServiceItem());
        serviceArrayList.add(new SearchServiceItem());
        //3
        personArrayList = new ArrayList<>();
        personArrayList.add(new SearchPersonItem());
        personArrayList.add(new SearchPersonItem());
        personArrayList.add(new SearchPersonItem());
        personArrayList.add(new SearchPersonItem());
        personArrayList.add(new SearchPersonItem());
        PagerSearchItemAdapter pagerSearchItemAdapter = new PagerSearchItemAdapter(needArrayList,serviceArrayList,personArrayList);
        listView.setAdapter(pagerSearchItemAdapter);
         mActivitySearchBinding.flSearchFirst.addView(listView);
    }

    private void initSearchHistoryData() {
         ArrayList<String> searchHistoryList = new ArrayList<String>();//存储集合
            searchHistoryList.add("独墅湖高教区");
            searchHistoryList.add("翰邻里中心2");
            searchHistoryList.add("克苏勒苏3");
            searchHistoryList.add("克苏勒苏4");
            searchHistoryList.add("克苏勒苏5");

      /*  try {
            br = new BufferedReader(new FileReader(file));
            String text = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        adapter= new SearchHistoryListAdapter(searchHistoryList,true);
        mActivitySearchBinding.lvLvSearchcontent.setAdapter(adapter);
    }



    private void setSearchContentData(boolean isShow) {
        //TODO 联想搜索的内容，假的数据从服务端获取，zss
        ArrayList<String> search_contentlist = new ArrayList<>();
        search_contentlist.add("ui设计师");
        search_contentlist.add("uiux设计师");
        search_contentlist.add("uii设计师");
        search_contentlist.add("uii设计师");
        search_contentlist.add("uii设计师");
        adapter= new SearchHistoryListAdapter(search_contentlist,isShow);
        mActivitySearchBinding.lvLvSearchcontent.setAdapter(adapter);
    }

    //搜索需求
    public void searchNeed(View v) {
    //LogKit.d("SearchNeed");
        showView("热搜需求");
        SpUtils.setString("searchType","searchNeed");
        searchType = searchNeed;
    }

    //搜索服务
    public void searchService(View v) {
       // LogKit.d("SearchService");
        showView("热搜服务");
        SpUtils.setString("searchType","searchService");
        searchType = searchService;
    }

    //搜索人
    public void searchPerson(View v){
      //  LogKit.d("SearchPerson");
        showView("搜人");
        SpUtils.setString("searchType","searchPerson");
        searchType = searchPerson;
    }

    private void showView(String title) {
        searchActivityHotServiceBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getCurrentActivity()), R.layout.search_activity_hot_service, null, false);
        searchActivityHotServiceModel = new SearchActivityHotServiceModel(searchActivityHotServiceBinding,mActivitySearchBinding);
        searchActivityHotServiceBinding.setSearchActivityHotServiceModel(searchActivityHotServiceModel);
        mActivitySearchBinding.flSearchFirst.addView(searchActivityHotServiceBinding.getRoot());
        searchActivityHotServiceBinding.tvSearchTitle.setText(title);
        mActivitySearchBinding.tvSearchResult.setText("取消");
    }
}
