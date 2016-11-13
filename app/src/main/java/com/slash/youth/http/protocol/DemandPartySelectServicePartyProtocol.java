package com.slash.youth.http.protocol;

import com.google.gson.Gson;
import com.slash.youth.domain.CommonResultBean;
import com.slash.youth.global.GlobalConstants;

import org.xutils.http.RequestParams;

/**
 * Created by zhouyifeng on 2016/10/6.
 */
public class DemandPartySelectServicePartyProtocol extends BaseProtocol<CommonResultBean> {

    private String id;//需求ID
    private String uid;//服务方UID

    public DemandPartySelectServicePartyProtocol(String id, String uid) {
        this.id = id;
        this.uid = uid;
    }

    @Override
    public String getUrlString() {
        return GlobalConstants.HttpUrl.DEMAND_PARTY_SELECT_SERVICE_PARTY;
    }

    @Override
    public void addRequestParams(RequestParams params) {
        params.addBodyParameter("id", id);
        params.addBodyParameter("uid", uid);
    }

    @Override
    public CommonResultBean parseData(String result) {
        Gson gson = new Gson();
        CommonResultBean commonResultBean = gson.fromJson(result, CommonResultBean.class);
        return commonResultBean;
    }

    @Override
    public boolean checkJsonResult(String result) {
        Gson gson = new Gson();
        CommonResultBean commonResultBean = gson.fromJson(result, CommonResultBean.class);
        if (commonResultBean.rescode == 0 && commonResultBean.data.status == 1) {
            return true;
        } else {
            return false;
        }
    }
}
