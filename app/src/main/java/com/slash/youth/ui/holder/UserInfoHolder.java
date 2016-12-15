package com.slash.youth.ui.holder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;

import com.slash.youth.R;
import com.slash.youth.databinding.ActivityUserinfoBinding;
import com.slash.youth.databinding.ItemUserinfoBinding;
import com.slash.youth.domain.NewDemandAandServiceBean;
import com.slash.youth.domain.NewTaskUserInfoBean;
import com.slash.youth.domain.UserInfoItemBean;
import com.slash.youth.engine.FirstPagerManager;
import com.slash.youth.engine.UserInfoEngine;
import com.slash.youth.global.GlobalConstants;
import com.slash.youth.ui.viewmodel.ItemUserInfoModel;
import com.slash.youth.utils.BitmapKit;
import com.slash.youth.utils.CommonUtils;
import com.slash.youth.utils.DistanceUtils;
import com.slash.youth.utils.TimeUtils;

/**
 * Created by zss on 2016/11/1.
 */
public class UserInfoHolder extends BaseHolder<NewDemandAandServiceBean.DataBean.ListBean> {


    private ItemUserinfoBinding itemUserinfoBinding;

    @Override
    public View initView() {
        itemUserinfoBinding = DataBindingUtil.inflate(LayoutInflater.from(CommonUtils.getContext()), R.layout.item_userinfo, null, false);
        ItemUserInfoModel itemUserInfoModel = new ItemUserInfoModel(itemUserinfoBinding);
        itemUserinfoBinding.setItemUserInfoModel(itemUserInfoModel);
        return itemUserinfoBinding.getRoot();
    }

    @Override
    public void refreshView(NewDemandAandServiceBean.DataBean.ListBean data) {
        long starttime = data.getStarttime();
        long endtime = data.getEndtime();
        String startData = TimeUtils.getData(starttime);
        String endData = TimeUtils.getData(endtime);
        itemUserinfoBinding.tvTime.setText(UserInfoEngine.TASK_TIME_TITLE+startData+"-"+endData);

        String avatar = data.getAvatar();
        if(avatar!=null&&avatar.equals("")){
            BitmapKit.bindImage(itemUserinfoBinding.ivAvater, GlobalConstants.HttpUrl.IMG_DOWNLOAD + "?fileId=" + avatar);
        }

        int isauth = data.getIsauth();
        switch (isauth){
            case 0:
                itemUserinfoBinding.ivIsAuth.setVisibility(View.GONE);
                break;
            case 1:
                itemUserinfoBinding.ivIsAuth.setVisibility(View.VISIBLE);
                break;
        }

        String title = data.getTitle();
        itemUserinfoBinding.tvTitle.setText(title);

        String name = data.getName();
        itemUserinfoBinding.tvName.setText(name);

       /* long quote = data.getQuote();
        int quoteunit = data.getQuoteunit();
        String quoteString = FirstPagerManager.QUOTE + quote +"元"+"/"+FirstPagerManager.QUOTEUNITS[quoteunit + 1];
        itemUserinfoBinding.tvQuote.setText(quoteString);*/

        int pattern = data.getPattern();
        switch (pattern){
            case 0:
                itemUserinfoBinding.tvPattern.setText(FirstPagerManager.PATTERN_UP);
                break;
            case 1:
                itemUserinfoBinding.tvPattern.setText(FirstPagerManager.PATTERN_DOWN);
                break;
        }

        int instalment = data.getInstalment();
        switch (instalment){
            case 0:
                itemUserinfoBinding.tvInstalment.setVisibility(View.GONE);
                break;
            case 1:
                itemUserinfoBinding.tvInstalment.setVisibility(View.VISIBLE);
                break;
        }

        String place = data.getPlace();
        itemUserinfoBinding.tvLocation.setText(place);

        //目标经纬度
        double lat = data.getLat();
        double lng = data.getLng();
        //用户的经纬度


        // new DistanceUtils().getLatAndLng(CommonUtils.getApplication());

        // System.out.println("====第三方第三方===="+DistanceUtils.getDistance(22.75424,112.76535 , 23.014171, 113.10111));
        itemUserinfoBinding.tvDistance.setText("<4.2KM");


    }



}
