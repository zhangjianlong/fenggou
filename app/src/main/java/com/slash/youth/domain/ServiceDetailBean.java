package com.slash.youth.domain;

/**
 * Created by zhouyifeng on 2016/11/30.
 */
public class ServiceDetailBean {
    public int rescode;
    public Data data;

    public class Data {
        public Service service;
    }

    public class Service {
        public int anonymity;
        public int bp;
        public long cts;
        public String desc;
        public long endtime;
        public long id;
        public int instalment;
        public int iscomment;
        public int isonline;
        public double lat;
        public double lng;
        public int loop;
        public int pattern;
        public String pic;
        public String place;
        public int quote;
        public int quoteunit;
        public String remark;
        public long starttime;
        public int status;
        public String tag;
        public int timetype;
        public String title;
        public long uid;
        public long uts;
    }
}
