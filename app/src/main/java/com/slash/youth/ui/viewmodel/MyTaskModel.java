package com.slash.youth.ui.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import com.slash.youth.BR;
import com.slash.youth.databinding.ActivityMyTaskBinding;
import com.slash.youth.domain.MyTaskBean;
import com.slash.youth.domain.MyTaskList;
import com.slash.youth.engine.MyTaskEngine;
import com.slash.youth.http.protocol.BaseProtocol;
import com.slash.youth.ui.activity.DemandChooseServiceActivity;
import com.slash.youth.ui.activity.MyBidDemandActivity;
import com.slash.youth.ui.activity.MyBidServiceActivity;
import com.slash.youth.ui.activity.MyPublishDemandActivity;
import com.slash.youth.ui.activity.MyPublishServiceActivity;
import com.slash.youth.ui.activity.PublishDemandBaseInfoActivity;
import com.slash.youth.ui.activity.PublishServiceBaseInfoActivity;
import com.slash.youth.ui.adapter.MyTaskAdapter;
import com.slash.youth.ui.view.RefreshListView;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.LogKit;
import com.slash.youth.utils.ToastUtils;

import java.util.ArrayList;

/**
 * Created by zhouyifeng on 2016/10/26.
 */
public class MyTaskModel extends BaseObservable {

    private static final int LOAD_DATA_TYPE_LOAD = 0;//首次加载数据
    private static final int LOAD_DATA_TYPE_REFRESH = 1;//刷新数据
    private static final int LOAD_DATA_TYPE_MORE = 2;//加载更多数据

    ActivityMyTaskBinding mActivityMyTaskBinding;
    Activity mActivity;

    private int offset = 0;
    private int pageSize = 20;


    private int currentFilterTaskType = MyTaskEngine.USER_TASK_ALL_TYPE;//当前过滤展示的任务类型，默认为全部，type=0
    private int currentLoadDataType = LOAD_DATA_TYPE_LOAD;

    public MyTaskModel(ActivityMyTaskBinding activityMyTaskBinding, Activity activity) {
        this.mActivityMyTaskBinding = activityMyTaskBinding;
        this.mActivity = activity;
        initData();
        initListener();
        initView();
    }

    ArrayList<MyTaskBean> listMyTask = null;

    private void initData() {
        //首次进入页面，加载我的全部任务（进行中任务，发的和抢的，不包括任务）
        currentFilterTaskType = MyTaskEngine.USER_TASK_ALL_TYPE;
        currentLoadDataType = LOAD_DATA_TYPE_LOAD;
        offset = 0;
        getMyTotalTaskList(MyTaskEngine.USER_TASK_ALL_TYPE, offset, pageSize);
    }

    MyTaskAdapter myTaskAdapter;

    private void setTotalTaskData() {
        if (listMyTask != null && listMyTask.size() > 0) {
            setMyTaskTypeText("进行中任务");
            setMyTaskListVisibility(View.VISIBLE);
            setNoTaskVisibility(View.GONE);
            myTaskAdapter = new MyTaskAdapter(listMyTask);
            mActivityMyTaskBinding.lvMyTaskList.setAdapter(myTaskAdapter);
        } else {
            setMyTaskListVisibility(View.GONE);
            setNoTaskVisibility(View.VISIBLE);
        }
    }

    private void setMyPublishTaskData() {
        setMyTaskTypeText("发布的任务");
        if (listMyTask != null && listMyTask.size() > 0) {
            myTaskAdapter = new MyTaskAdapter(listMyTask);
            mActivityMyTaskBinding.lvMyTaskList.setAdapter(myTaskAdapter);
        }
    }

    private void setMyBidTaskData() {
        setMyTaskTypeText("抢到的任务");
        if (listMyTask != null && listMyTask.size() > 0) {
            myTaskAdapter = new MyTaskAdapter(listMyTask);
            mActivityMyTaskBinding.lvMyTaskList.setAdapter(myTaskAdapter);
        }
    }

    private void setMyHistoryTaskData() {
        setMyTaskTypeText("任务");
        if (listMyTask != null && listMyTask.size() > 0) {
            myTaskAdapter = new MyTaskAdapter(listMyTask);
            mActivityMyTaskBinding.lvMyTaskList.setAdapter(myTaskAdapter);
        }
    }

    private boolean isMoveListView = false;

    private void initListener() {

        mActivityMyTaskBinding.lvMyTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isMoveListView) {
                    isMoveListView = false;
                    return;
                }
                MyTaskBean myTaskBean = listMyTask.get(position);

                Bundle taskInfo = new Bundle();
                taskInfo.putLong("tid", myTaskBean.tid);
                taskInfo.putInt("type", myTaskBean.type);
                taskInfo.putInt("roleid", myTaskBean.roleid);

//                ToastUtils.shortToast(myTaskBean.status + "");

                if (myTaskBean.roleid == 1) {//发布者
                    if (myTaskBean.type == 1) {//我发的需求
                        switch (myTaskBean.status) {
                            case 1:
                            case 4:
                            case 5:
                                Intent intentDemandChooseServiceActivity = new Intent(CommonUtils.getContext(), DemandChooseServiceActivity.class);
                                intentDemandChooseServiceActivity.putExtras(taskInfo);
                                mActivity.startActivity(intentDemandChooseServiceActivity);
                                break;
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                                Intent intentMyPublishDemandActivity = new Intent(CommonUtils.getContext(), MyPublishDemandActivity.class);
                                intentMyPublishDemandActivity.putExtras(taskInfo);
                                mActivity.startActivity(intentMyPublishDemandActivity);
                                break;
                            default:
                                //其它情况应该跳转到需求详情页
                                break;
                        }
                    } else if (myTaskBean.type == 2) {//我发的服务
                        Intent intentMyPublishServiceActivity = new Intent(CommonUtils.getContext(), MyPublishServiceActivity.class);
                        intentMyPublishServiceActivity.putExtra("myTaskBean", listMyTask.get(position));
                        mActivity.startActivity(intentMyPublishServiceActivity);
                    }

                } else if (myTaskBean.roleid == 2) {//抢单者
                    if (myTaskBean.type == 1) {//我抢的需求
                        switch (myTaskBean.status) {
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                                Intent intentMyBidDemandActivity = new Intent(CommonUtils.getContext(), MyBidDemandActivity.class);
                                intentMyBidDemandActivity.putExtras(taskInfo);
                                mActivity.startActivity(intentMyBidDemandActivity);
                                break;
                            default:
                                //其它情况应该跳转到需求详情页
                                break;
                        }
                    } else if (myTaskBean.type == 2) {//我抢的服务(我预约的服务)
                        Intent intentMyBidServiceActivity = new Intent(CommonUtils.getContext(), MyBidServiceActivity.class);
                        intentMyBidServiceActivity.putExtra("myTaskBean", listMyTask.get(position));
                        mActivity.startActivity(intentMyBidServiceActivity);
                    }
                }
            }
        });

        mActivityMyTaskBinding.lvMyTaskList.setOnTouchListener(new View.OnTouchListener() {
            int startY = -1;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startY = (int) event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        if (startY != -1) {
                            int endY = (int) event.getRawY();
                            if (Math.abs(endY - startY) > 10) {
                                isMoveListView = true;
                                CommonUtils.getHandler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        isMoveListView = false;
                                    }
                                }, 100);
                            }
                            startY = -1;
                        }
                        break;
                }
                return false;
            }
        });

        mActivityMyTaskBinding.lvMyTaskList.setRefreshDataTask(new RefreshDataTask());
        mActivityMyTaskBinding.lvMyTaskList.setLoadMoreNewsTast(new LoadMoreNewsTask());
    }

    private void initView() {

    }


    /**
     * 获取我全部任务（进行中任务，发的和抢的，不包括任务）
     */
    public void getMyTotalTaskList(int type, int offset, final int limit) {
        //模拟数据，实际由服务端 返回
//        listMyTask.clear();
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());

        MyTaskEngine.getMyTaskList(new BaseProtocol.IResultExecutor<MyTaskList>() {
            @Override
            public void execute(MyTaskList dataBean) {
                LogKit.v("currentLoadDataType:" + currentLoadDataType);

                ArrayList<MyTaskBean> loadData = dataBean.data.list;
                if (currentLoadDataType == LOAD_DATA_TYPE_LOAD) {
                    listMyTask = loadData;
                    MyTaskModel.this.offset = listMyTask.size();
                    setTotalTaskData();
                    mActivityMyTaskBinding.lvMyTaskList.setNotLoadToLast();
                } else if (currentLoadDataType == LOAD_DATA_TYPE_REFRESH) {
                    listMyTask.clear();
                    listMyTask.addAll(loadData);
                    MyTaskModel.this.offset = listMyTask.size();
                    if (myTaskAdapter != null) {
                        myTaskAdapter.notifyDataSetChanged();
                    } else {
                        setTotalTaskData();
                    }
                    mActivityMyTaskBinding.lvMyTaskList.refreshDataFinish();
                    mActivityMyTaskBinding.lvMyTaskList.setNotLoadToLast();
                } else {
                    listMyTask.addAll(loadData);
                    MyTaskModel.this.offset = listMyTask.size();
                    LogKit.v("----------load more listMyTask.size():" + listMyTask.size());
                    if (myTaskAdapter != null) {
                        myTaskAdapter.notifyDataSetChanged();
                    } else {
                        setTotalTaskData();
                    }
                    mActivityMyTaskBinding.lvMyTaskList.loadMoreNewsFinished();
                    if (loadData.size() < limit) {
                        mActivityMyTaskBinding.lvMyTaskList.setLoadToLast();
                        ToastUtils.shortToast("已经是最后一条了");
                    }
                }

                ToastUtils.shortToast(listMyTask.size() + "");
            }

            @Override
            public void executeResultError(String result) {
                ToastUtils.shortToast("get my total task error\r\n" + result);
            }
        }, type, offset, limit);
    }

    /**
     * 获取我发布的任务
     */
    public void getMyPublishTaskList(int type, int offset, final int limit) {
        //模拟数据，实际由服务端 返回
//        listMyTask.clear();
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());

        MyTaskEngine.getMyTaskList(new BaseProtocol.IResultExecutor<MyTaskList>() {
            @Override
            public void execute(MyTaskList dataBean) {
                LogKit.v("currentLoadDataType:" + currentLoadDataType);

                ArrayList<MyTaskBean> loadData = dataBean.data.list;
                if (currentLoadDataType == LOAD_DATA_TYPE_LOAD) {
                    listMyTask = loadData;
                    MyTaskModel.this.offset = listMyTask.size();
                    setMyPublishTaskData();
                    mActivityMyTaskBinding.lvMyTaskList.setNotLoadToLast();
                } else if (currentLoadDataType == LOAD_DATA_TYPE_REFRESH) {
                    listMyTask.clear();
                    listMyTask.addAll(loadData);
                    MyTaskModel.this.offset = listMyTask.size();
                    if (myTaskAdapter != null) {
                        myTaskAdapter.notifyDataSetChanged();
                    } else {
                        setMyPublishTaskData();
                    }
                    mActivityMyTaskBinding.lvMyTaskList.refreshDataFinish();
                    mActivityMyTaskBinding.lvMyTaskList.setNotLoadToLast();
                } else {
                    listMyTask.addAll(loadData);
                    MyTaskModel.this.offset = listMyTask.size();
                    LogKit.v("----------load more listMyTask.size():" + listMyTask.size());
                    if (myTaskAdapter != null) {
                        myTaskAdapter.notifyDataSetChanged();
                    } else {
                        setMyPublishTaskData();
                    }
                    mActivityMyTaskBinding.lvMyTaskList.loadMoreNewsFinished();
                    if (loadData.size() < limit) {
                        mActivityMyTaskBinding.lvMyTaskList.setLoadToLast();
                    }
                }

                ToastUtils.shortToast(listMyTask.size() + "");
            }

            @Override
            public void executeResultError(String result) {
                ToastUtils.shortToast("get my total task error\r\n" + result);
            }
        }, type, offset, limit);
    }

    /**
     * 获取我抢的任务
     */
    public void getMyBidTaskList(int type, final int offset, final int limit) {
        //模拟数据，实际由服务端 返回
//        listMyTask.clear();
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());

        MyTaskEngine.getMyTaskList(new BaseProtocol.IResultExecutor<MyTaskList>() {
            @Override
            public void execute(MyTaskList dataBean) {
                LogKit.v("currentLoadDataType:" + currentLoadDataType);

                ArrayList<MyTaskBean> loadData = dataBean.data.list;
                if (currentLoadDataType == LOAD_DATA_TYPE_LOAD) {
                    listMyTask = loadData;
                    MyTaskModel.this.offset = listMyTask.size();
                    setMyBidTaskData();
                    mActivityMyTaskBinding.lvMyTaskList.setNotLoadToLast();
                } else if (currentLoadDataType == LOAD_DATA_TYPE_REFRESH) {
                    listMyTask.clear();
                    listMyTask.addAll(loadData);
                    MyTaskModel.this.offset = listMyTask.size();
                    if (myTaskAdapter != null) {
                        myTaskAdapter.notifyDataSetChanged();
                    } else {
                        setMyBidTaskData();
                    }
                    mActivityMyTaskBinding.lvMyTaskList.refreshDataFinish();
                    mActivityMyTaskBinding.lvMyTaskList.setNotLoadToLast();
                } else {
                    listMyTask.addAll(loadData);
                    MyTaskModel.this.offset = listMyTask.size();
                    LogKit.v("----------load more listMyTask.size():" + listMyTask.size());
                    if (myTaskAdapter != null) {
                        myTaskAdapter.notifyDataSetChanged();
                    } else {
                        setMyBidTaskData();
                    }
                    mActivityMyTaskBinding.lvMyTaskList.loadMoreNewsFinished();
                    if (loadData.size() < limit) {
                        mActivityMyTaskBinding.lvMyTaskList.setLoadToLast();
                    }
                }

                ToastUtils.shortToast(listMyTask.size() + "");

            }

            @Override
            public void executeResultError(String result) {
                ToastUtils.shortToast("get my total task error\r\n" + result);
            }
        }, type, offset, limit);
    }

    /**
     * 获取我的任务
     */
    public void getMyHistoryTaskList(int type, int offset, final int limit) {
        //模拟数据，实际由服务端 返回
//        listMyTask.clear();
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());
//        listMyTask.add(new MyTaskBean());

        MyTaskEngine.getMyTaskList(new BaseProtocol.IResultExecutor<MyTaskList>() {
            @Override
            public void execute(MyTaskList dataBean) {
                LogKit.v("currentLoadDataType:" + currentLoadDataType);

                ArrayList<MyTaskBean> loadData = dataBean.data.list;
                if (currentLoadDataType == LOAD_DATA_TYPE_LOAD) {
                    listMyTask = loadData;
                    MyTaskModel.this.offset = listMyTask.size();
                    setMyHistoryTaskData();
                    mActivityMyTaskBinding.lvMyTaskList.setNotLoadToLast();
                } else if (currentLoadDataType == LOAD_DATA_TYPE_REFRESH) {
                    listMyTask.clear();
                    listMyTask.addAll(loadData);
                    MyTaskModel.this.offset = listMyTask.size();
                    if (myTaskAdapter != null) {
                        myTaskAdapter.notifyDataSetChanged();
                    } else {
                        setMyHistoryTaskData();
                    }
                    mActivityMyTaskBinding.lvMyTaskList.refreshDataFinish();
                    mActivityMyTaskBinding.lvMyTaskList.setNotLoadToLast();
                } else {
                    listMyTask.addAll(loadData);
                    MyTaskModel.this.offset = listMyTask.size();
                    LogKit.v("----------load more listMyTask.size():" + listMyTask.size());
                    if (myTaskAdapter != null) {
                        myTaskAdapter.notifyDataSetChanged();
                    } else {
                        setMyHistoryTaskData();
                    }
                    mActivityMyTaskBinding.lvMyTaskList.loadMoreNewsFinished();
                    if (loadData.size() < limit) {
                        mActivityMyTaskBinding.lvMyTaskList.setLoadToLast();
                    }
                }

                ToastUtils.shortToast(listMyTask.size() + "");
            }

            @Override
            public void executeResultError(String result) {
                ToastUtils.shortToast("get my total task error\r\n" + result);
            }
        }, type, offset, limit);
    }

    /**
     * 下拉刷新执行的回调，执行结束后需要调用refreshDataFinish()方法，用来更新状态
     */
    public class RefreshDataTask implements RefreshListView.IRefreshDataTask {

        @Override
        public void refresh() {
            offset = 0;
            currentLoadDataType = LOAD_DATA_TYPE_REFRESH;
            if (currentFilterTaskType == MyTaskEngine.USER_TASK_ALL_TYPE) {
                getMyTotalTaskList(MyTaskEngine.USER_TASK_ALL_TYPE, offset, pageSize);
            } else if (currentFilterTaskType == MyTaskEngine.USER_TASK_MY_PUBLISH_TYPE) {
                getMyPublishTaskList(MyTaskEngine.USER_TASK_MY_PUBLISH_TYPE, offset, pageSize);
            } else if (currentFilterTaskType == MyTaskEngine.USER_TASK_MY_BID_TYPE) {
                getMyBidTaskList(MyTaskEngine.USER_TASK_MY_BID_TYPE, offset, pageSize);
            } else {
                getMyHistoryTaskList(MyTaskEngine.USER_TASK_MY_HIS_TYPE, offset, pageSize);
            }
        }
    }

    /**
     * 上拉加载更多执行的回调，执行完毕后需要调用loadMoreNewsFinished()方法，用来更新状态,如果加载到最后一页，则需要调用setLoadToLast()方法
     */
    public class LoadMoreNewsTask implements RefreshListView.ILoadMoreNewsTask {

        @Override
        public void loadMore() {
            currentLoadDataType = LOAD_DATA_TYPE_MORE;
            if (currentFilterTaskType == MyTaskEngine.USER_TASK_ALL_TYPE) {
                getMyTotalTaskList(MyTaskEngine.USER_TASK_ALL_TYPE, offset, pageSize);
            } else if (currentFilterTaskType == MyTaskEngine.USER_TASK_MY_PUBLISH_TYPE) {
                getMyPublishTaskList(MyTaskEngine.USER_TASK_MY_PUBLISH_TYPE, offset, pageSize);
            } else if (currentFilterTaskType == MyTaskEngine.USER_TASK_MY_BID_TYPE) {
                getMyBidTaskList(MyTaskEngine.USER_TASK_MY_BID_TYPE, offset, pageSize);
            } else {
                getMyHistoryTaskList(MyTaskEngine.USER_TASK_MY_HIS_TYPE, offset, pageSize);
            }
        }
    }

    public void goBack(View v) {
        mActivity.finish();
    }

    //打开或关闭筛选任务的下拉框
    public void openFilterTask(View v) {
        if (openTaskVisibility == View.GONE) {
            setOpenTaskVisibility(View.VISIBLE);
        } else {
            setOpenTaskVisibility(View.GONE);
        }
    }

    //去发布任务
    public void gotoPublishTask(View v) {
//        ToastUtils.shortToast("去发布任务");
        setPublishTaskDialogVisibility(View.VISIBLE);
    }

    //去浏览任务
    public void gotoBrowseTask(View v) {
        ToastUtils.shortToast("去浏览任务");
    }

    //关闭发布任务对话框
    public void closePublishTaskDialog(View v) {
        setPublishTaskDialogVisibility(View.GONE);
    }

    //发布需求
    public void publishDemand(View v) {
        Intent intentPublishDemandBaseInfoActivity = new Intent(CommonUtils.getContext(), PublishDemandBaseInfoActivity.class);
        mActivity.startActivity(intentPublishDemandBaseInfoActivity);
    }

    //发布服务
    public void publishService(View v) {
        Intent intentPublishServiceBaseInfoActivity = new Intent(CommonUtils.getContext(), PublishServiceBaseInfoActivity.class);
        mActivity.startActivity(intentPublishServiceBaseInfoActivity);
    }

    //筛选全部任务（进行中任务，发的和抢的，不包括任务）
    public void filterMyTotalTask(View v) {
        offset = 0;
        currentFilterTaskType = MyTaskEngine.USER_TASK_ALL_TYPE;
        currentLoadDataType = LOAD_DATA_TYPE_LOAD;
        getMyTotalTaskList(MyTaskEngine.USER_TASK_ALL_TYPE, offset, pageSize);
        setOpenTaskVisibility(View.GONE);
    }

    //筛选我发布的任务
    public void filterMyPublishTask(View v) {
        offset = 0;
        currentFilterTaskType = MyTaskEngine.USER_TASK_MY_PUBLISH_TYPE;
        currentLoadDataType = LOAD_DATA_TYPE_LOAD;
        getMyPublishTaskList(MyTaskEngine.USER_TASK_MY_PUBLISH_TYPE, offset, pageSize);
        setOpenTaskVisibility(View.GONE);
    }

    //筛选我抢的任务
    public void filterMyBidTask(View v) {
        offset = 0;
        currentFilterTaskType = MyTaskEngine.USER_TASK_MY_BID_TYPE;
        currentLoadDataType = LOAD_DATA_TYPE_LOAD;
        getMyBidTaskList(MyTaskEngine.USER_TASK_MY_BID_TYPE, offset, pageSize);
        setOpenTaskVisibility(View.GONE);
    }

    //筛选我的任务（下架的或者过期的）
    public void filterMyHistoryTask(View v) {
        offset = 0;
        currentFilterTaskType = MyTaskEngine.USER_TASK_MY_HIS_TYPE;
        currentLoadDataType = LOAD_DATA_TYPE_LOAD;
        getMyHistoryTaskList(MyTaskEngine.USER_TASK_MY_HIS_TYPE, offset, pageSize);
        setOpenTaskVisibility(View.GONE);
    }

    private int openTaskVisibility = View.GONE;
    private int myTaskListVisibility = View.GONE;
    private int noTaskVisibility = View.GONE;
    private int publishTaskDialogVisibility = View.GONE;
    private String myTaskTypeText = "进行中任务";

    @Bindable
    public int getOpenTaskVisibility() {
        return openTaskVisibility;
    }

    public void setOpenTaskVisibility(int openTaskVisibility) {
        this.openTaskVisibility = openTaskVisibility;
        notifyPropertyChanged(BR.openTaskVisibility);
    }

    @Bindable
    public int getNoTaskVisibility() {
        return noTaskVisibility;
    }

    public void setNoTaskVisibility(int noTaskVisibility) {
        this.noTaskVisibility = noTaskVisibility;
        notifyPropertyChanged(BR.noTaskVisibility);
    }

    @Bindable
    public int getMyTaskListVisibility() {
        return myTaskListVisibility;
    }

    public void setMyTaskListVisibility(int myTaskListVisibility) {
        this.myTaskListVisibility = myTaskListVisibility;
        notifyPropertyChanged(BR.myTaskListVisibility);
    }

    @Bindable
    public int getPublishTaskDialogVisibility() {
        return publishTaskDialogVisibility;
    }

    public void setPublishTaskDialogVisibility(int publishTaskDialogVisibility) {
        this.publishTaskDialogVisibility = publishTaskDialogVisibility;
        notifyPropertyChanged(BR.publishTaskDialogVisibility);
    }

    @Bindable
    public String getMyTaskTypeText() {
        return myTaskTypeText;
    }

    public void setMyTaskTypeText(String myTaskTypeText) {
        this.myTaskTypeText = myTaskTypeText;
        notifyPropertyChanged(BR.myTaskTypeText);
    }
}