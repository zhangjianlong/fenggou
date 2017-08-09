package com.odbpo.fenggou.domain.bean;

import java.util.List;

/**
 * @author: zjl
 * @Time: 2017/8/8 10:36
 * @Desc:
 */
public class OrderListBean {


    /**
     * total : 10
     * data : [{"orderCode":"2017080407571610","orderId":793,"orderStatus":"4","orderPrice":598,"isBack":null,"orderLinePay":1,"orderOldCode":"20170804075716","evaluateFlag":"0","orderGoodsList":[{"goodsInfoId":126,"goodsName":"52°酒鬼酒（妙造） 450ML","goodsImg":"http://img.feng-go.com/1480300781652.jpg!160","specDesc":"450ML "}],"appraise":false},{"orderCode":"2017080307453710","orderId":769,"orderStatus":"14","orderPrice":99,"isBack":"1","orderLinePay":3,"orderOldCode":"20170803074537","evaluateFlag":"0","orderGoodsList":[{"goodsInfoId":574,"goodsName":"好孩子婴儿理发器静音儿童防水剃头刀剪发器充电宝宝电推剪家用(灰色)","goodsImg":"http://img.feng-go.com/1493098298483.jpg!352","specDesc":"洋红色 "}],"appraise":false},{"orderCode":"2017080307451010","orderId":768,"orderStatus":"14","orderPrice":460,"isBack":"1","orderLinePay":1,"orderOldCode":"20170803074510","evaluateFlag":"0","orderGoodsList":[{"goodsInfoId":121,"goodsName":"52°全兴老字号(500ML)","goodsImg":"http://img.feng-go.com/1480297996066.jpg!160","specDesc":"500ML "}],"appraise":false},{"orderCode":"2017080307445610","orderId":767,"orderStatus":"14","orderPrice":69,"isBack":"1","orderLinePay":1,"orderOldCode":"20170803074456","evaluateFlag":"0","orderGoodsList":[{"goodsInfoId":247,"goodsName":"意大利原瓶进口金熊干白葡萄酒红酒半甜金葡系列750ml(750ML)","goodsImg":"http://img.feng-go.com/1489370949145.jpg!160","specDesc":"750ML "}],"appraise":false},{"orderCode":"2017080307433610","orderId":766,"orderStatus":"15","orderPrice":129,"isBack":"2","orderLinePay":3,"orderOldCode":"20170803074336","evaluateFlag":"0","orderGoodsList":[{"goodsInfoId":123,"goodsName":"67°男人·衡水老白干(500ML)","goodsImg":"http://img.feng-go.com/1480298545977.jpg!160","specDesc":"500ML "}],"appraise":false},{"orderCode":"2017080307431910","orderId":765,"orderStatus":"4","orderPrice":199,"isBack":null,"orderLinePay":3,"orderOldCode":"20170803074319","evaluateFlag":"0","orderGoodsList":[{"goodsInfoId":578,"goodsName":"好孩子（Goodbaby） 儿童三轮车 宝宝脚踏车 儿童骑行三轮车 SR130-H003B(蓝色)","goodsImg":"http://img.feng-go.com/1493099735532.jpg!352","specDesc":"洋红色 "}],"appraise":false},{"orderCode":"2017080307425210","orderId":764,"orderStatus":"4","orderPrice":598,"isBack":null,"orderLinePay":1,"orderOldCode":"20170803074252","evaluateFlag":"0","orderGoodsList":[{"goodsInfoId":126,"goodsName":"52°酒鬼酒（妙造） 450ML","goodsImg":"http://img.feng-go.com/1480300781652.jpg!160","specDesc":"450ML "}],"appraise":false},{"orderCode":"2017080307421910","orderId":763,"orderStatus":"4","orderPrice":69,"isBack":null,"orderLinePay":1,"orderOldCode":"20170803074219","evaluateFlag":"0","orderGoodsList":[{"goodsInfoId":625,"goodsName":"百安思儿童水杯吸管杯带手柄300ml 男女宝宝夏季水壶婴儿小孩喝水(蓝色)","goodsImg":"http://img.feng-go.com/1496379035607.jpg!160","specDesc":"洋红色 "}],"appraise":false},{"orderCode":"2017080300515110","orderId":761,"orderStatus":"15","orderPrice":69,"isBack":"2","orderLinePay":3,"orderOldCode":"20170803005151","evaluateFlag":"0","orderGoodsList":[{"goodsInfoId":625,"goodsName":"百安思儿童水杯吸管杯带手柄300ml 男女宝宝夏季水壶婴儿小孩喝水(蓝色)","goodsImg":"http://img.feng-go.com/1496379035607.jpg!160","specDesc":"洋红色 "}],"appraise":false},{"orderCode":"2017080300331810","orderId":760,"orderStatus":"17","orderPrice":598,"isBack":"1","orderLinePay":1,"orderOldCode":"20170803003318","evaluateFlag":"0","orderGoodsList":[{"goodsInfoId":126,"goodsName":"52°酒鬼酒（妙造） 450ML","goodsImg":"http://img.feng-go.com/1480300781652.jpg!160","specDesc":"450ML "}],"appraise":false}]
     */

    private int total;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderCode : 2017080407571610
         * orderId : 793
         * orderStatus : 4
         * orderPrice : 598.0
         * isBack : null
         * orderLinePay : 1
         * orderOldCode : 20170804075716
         * evaluateFlag : 0
         * orderGoodsList : [{"goodsInfoId":126,"goodsName":"52°酒鬼酒（妙造） 450ML","goodsImg":"http://img.feng-go.com/1480300781652.jpg!160","specDesc":"450ML "}]
         * appraise : false
         */

        private String orderCode;
        private int orderId;
        private String orderStatus;
        private double orderPrice;
        private Object isBack;
        private int orderLinePay;
        private String orderOldCode;
        private String evaluateFlag;
        private boolean appraise;
        private List<OrderGoodsListBean> orderGoodsList;

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public double getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(double orderPrice) {
            this.orderPrice = orderPrice;
        }

        public Object getIsBack() {
            return isBack;
        }

        public void setIsBack(Object isBack) {
            this.isBack = isBack;
        }

        public int getOrderLinePay() {
            return orderLinePay;
        }

        public void setOrderLinePay(int orderLinePay) {
            this.orderLinePay = orderLinePay;
        }

        public String getOrderOldCode() {
            return orderOldCode;
        }

        public void setOrderOldCode(String orderOldCode) {
            this.orderOldCode = orderOldCode;
        }

        public String getEvaluateFlag() {
            return evaluateFlag;
        }

        public void setEvaluateFlag(String evaluateFlag) {
            this.evaluateFlag = evaluateFlag;
        }

        public boolean isAppraise() {
            return appraise;
        }

        public void setAppraise(boolean appraise) {
            this.appraise = appraise;
        }

        public List<OrderGoodsListBean> getOrderGoodsList() {
            return orderGoodsList;
        }

        public void setOrderGoodsList(List<OrderGoodsListBean> orderGoodsList) {
            this.orderGoodsList = orderGoodsList;
        }

        public static class OrderGoodsListBean {
            /**
             * goodsInfoId : 126
             * goodsName : 52°酒鬼酒（妙造） 450ML
             * goodsImg : http://img.feng-go.com/1480300781652.jpg!160
             * specDesc : 450ML
             */

            private int goodsInfoId;
            private String goodsName;
            private String goodsImg;
            private String specDesc;

            public int getGoodsInfoId() {
                return goodsInfoId;
            }

            public void setGoodsInfoId(int goodsInfoId) {
                this.goodsInfoId = goodsInfoId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
            }

            public String getSpecDesc() {
                return specDesc;
            }

            public void setSpecDesc(String specDesc) {
                this.specDesc = specDesc;
            }
        }
    }
}
