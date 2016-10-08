package com.slash.youth.http.protocol;

import com.slash.youth.global.GlobalConstants;

import org.xutils.http.RequestParams;

/**
 * 十二、[需求]-服务方完成任务(应该是 需求方确认完成 ？？？)
 * Created by zhouyifeng on 2016/10/8.
 */
public class DemandPartyConfirmCompleteProtocol extends BaseProtocol<String> {
    private String id;// 需求ID

    public DemandPartyConfirmCompleteProtocol(String id) {
        this.id = id;
    }

    @Override
    public String getUrlString() {
        return GlobalConstants.HttpUrl.DEMAND_PARTY_CONFIRM_COMPLETE;
    }

    @Override
    public void addRequestParams(RequestParams params) {
        params.addBodyParameter("id", id);
    }

    @Override
    public String parseData(String result) {
        return result;
    }

    @Override
    public boolean checkJsonResult(String result) {
        return true;
    }
}
