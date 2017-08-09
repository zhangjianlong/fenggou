package com.odbpo.fenggou.util;

/**
 * @author: zjl
 * @Time: 2017/8/4 16:10
 * @Desc:
 */
public class OrderStatus {

    /**
     * 订单状态标记（订单状态 0未付款 1已付款未发货 2已发货 3已经收货 4作废 7:退货审核中 8：同意退货 9:拒绝退货 10:待商家收货 11:订单结束 12:同意退款
     * 13： 拒绝退款 14:已提交退货审核 15：已提交退款审核 16: 商家收货失败 17:已退款 18:申请退款 退款成功）
     * Created by chenpeng on 4/12/15.
     */
//    public enum OrderStatus {
//        NOPAY, YESPAY, YESSEND, YESGET, CANCEL, NULL_1, NULL_2, BACKGOODSAUDIT, AGREEBACKGOODS, REFUSEBACKGOODS,
//        WAITFORGET, DONE, AGREEDRAWBACK, REFUSEDRAWBACK, SUBMITGOODSBACK, SUBMITDRAWBACK, FAILEGET,
//        YETDRAWBACK, SUCESSDRAWBACK, BACKORDER;
//    }

    public final static int NOPAY = 0;
    public final static int YESPAY = 1;
    public final static int YESSEND = 2;
    public final static int YESGET = 3;
    public final static int CANCEL = 4;
    public final static int BACKGOODSAUDIT = 7;
    public final static int AGREEBACKGOODS = 8;
    public final static int REFUSEBACKGOODS = 9;
    public final static int WAITFORGET = 10;
    public final static int DONE = 11;
    public final static int AGREEDRAWBACK = 12;
    public final static int REFUSEDRAWBACK = 13;
    public final static int SUBMITGOODSBACK = 14;
    public final static int SUBMITDRAWBACK = 15;
    public final static int FAILEGET = 16;
    public final static int YETDRAWBACK = 17;
    public final static int SUCESSDRAWBACK = 18;
    public final static int BACKORDER = 19;
}
