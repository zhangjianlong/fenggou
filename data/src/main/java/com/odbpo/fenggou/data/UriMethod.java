package com.odbpo.fenggou.data;

/**
 * @author: zjl
 * @Time: 2017/6/1 15:13
 * @Desc:
 */

public class UriMethod {

    public static final String CHECK_PHONE = "customer/registry/checkphone";
    //登录
    public static final String LOGIN = "customer/login";
    //获取客户信息
    public static final String CUSTOMERS = "customers";
    //获取客户信息
    public static final String PRODUCT_CATEGORY = "goods/mobcates";
    //获取浏览历史
    public static final String BROWSERECORD = "customers/BROWSERECORD";
    //商品搜索
    public static final String SEARCH_GOODS = "search/goods/whole";
    //订单数量接口
    public static final String ORDER_NUM = "orders/{status}/counts";
    //系统消息
    public static final String SYSTEM_NOTIFICATION = "customers/messages";
    //订单列表
    public static final String ORDER_GOODSLIST = "orders/{status}";
    //我的关注
    public static final String FOLLOW_NUM = "customers/follows/total";
    //我的历史记录
    public static final String HISTORY_NUM = "customers/browserecord/totals ";


    //我的关注列表
    public static final String FOLLOW_LIST = "customers/follows";
    //我的历史记录列表
    public static final String HISTORY_LIST = "customers/browserecord";


}
