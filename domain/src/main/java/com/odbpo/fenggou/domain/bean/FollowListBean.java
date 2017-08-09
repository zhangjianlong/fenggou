package com.odbpo.fenggou.domain.bean;

import java.util.List;

/**
 * @author: zjl
 * @Time: 2017/8/9 11:04
 * @Desc:
 */
public class FollowListBean {


    /**
     * total : 1
     * data : [{"id":122,"spuId":100,"itemNo":"2016GT00002","barCode":"","name":"53°国台·品鉴15(500ML)","subtitle":"","addedStatus":"1","addedTime":"2016-11-28 09:58:20.000","offShelfTime":null,"stock":0,"preferPrice":1580,"costPrice":null,"marketPrice":1580,"weight":500,"creatorName":"admin","image":"http://img.feng-go.com/1480298288174.jpg!160","createTime":"2016-11-28 09:58:20.000","modifier":null,"deleter":null,"deleteTime":null,"deleteFlag":"0","thirdType":"0","thirdShopId":0,"thirdShopName":"BOSS","isbn":null,"showList":"1","showMobile":"1","isCustomerDismount":"0","auditStatus":"0","freeShipment":"0","refuseReason":null,"fictitiousSalesCount":0,"storeList":[{"id":514,"goodsInfoId":122,"wareHouseId":1,"stock":92,"price":1580,"deleteFlag":0,"wareHouse":null}],"spu":null,"specItemList":null,"spuSpecItemList":null,"paramItemList":null,"spuDesc":null,"imageList":null,"tagList":null,"number":null,"desc":null}]
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
         * id : 122
         * spuId : 100
         * itemNo : 2016GT00002
         * barCode :
         * name : 53°国台·品鉴15(500ML)
         * subtitle :
         * addedStatus : 1
         * addedTime : 2016-11-28 09:58:20.000
         * offShelfTime : null
         * stock : 0
         * preferPrice : 1580.0
         * costPrice : null
         * marketPrice : 1580.0
         * weight : 500.0
         * creatorName : admin
         * image : http://img.feng-go.com/1480298288174.jpg!160
         * createTime : 2016-11-28 09:58:20.000
         * modifier : null
         * deleter : null
         * deleteTime : null
         * deleteFlag : 0
         * thirdType : 0
         * thirdShopId : 0
         * thirdShopName : BOSS
         * isbn : null
         * showList : 1
         * showMobile : 1
         * isCustomerDismount : 0
         * auditStatus : 0
         * freeShipment : 0
         * refuseReason : null
         * fictitiousSalesCount : 0
         * storeList : [{"id":514,"goodsInfoId":122,"wareHouseId":1,"stock":92,"price":1580,"deleteFlag":0,"wareHouse":null}]
         * spu : null
         * specItemList : null
         * spuSpecItemList : null
         * paramItemList : null
         * spuDesc : null
         * imageList : null
         * tagList : null
         * number : null
         * desc : null
         */

        private int id;
        private int spuId;
        private String itemNo;
        private String barCode;
        private String name;
        private String subtitle;
        private String addedStatus;
        private String addedTime;
        private Object offShelfTime;
        private int stock;
        private double preferPrice;
        private Object costPrice;
        private double marketPrice;
        private double weight;
        private String creatorName;
        private String image;
        private String createTime;
        private Object modifier;
        private Object deleter;
        private Object deleteTime;
        private String deleteFlag;
        private String thirdType;
        private int thirdShopId;
        private String thirdShopName;
        private Object isbn;
        private String showList;
        private String showMobile;
        private String isCustomerDismount;
        private String auditStatus;
        private String freeShipment;
        private Object refuseReason;
        private int fictitiousSalesCount;
        private Object spu;
        private Object specItemList;
        private Object spuSpecItemList;
        private Object paramItemList;
        private Object spuDesc;
        private Object imageList;
        private Object tagList;
        private Object number;
        private Object desc;
        private List<StoreListBean> storeList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSpuId() {
            return spuId;
        }

        public void setSpuId(int spuId) {
            this.spuId = spuId;
        }

        public String getItemNo() {
            return itemNo;
        }

        public void setItemNo(String itemNo) {
            this.itemNo = itemNo;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getAddedStatus() {
            return addedStatus;
        }

        public void setAddedStatus(String addedStatus) {
            this.addedStatus = addedStatus;
        }

        public String getAddedTime() {
            return addedTime;
        }

        public void setAddedTime(String addedTime) {
            this.addedTime = addedTime;
        }

        public Object getOffShelfTime() {
            return offShelfTime;
        }

        public void setOffShelfTime(Object offShelfTime) {
            this.offShelfTime = offShelfTime;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public double getPreferPrice() {
            return preferPrice;
        }

        public void setPreferPrice(double preferPrice) {
            this.preferPrice = preferPrice;
        }

        public Object getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(Object costPrice) {
            this.costPrice = costPrice;
        }

        public double getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(double marketPrice) {
            this.marketPrice = marketPrice;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getModifier() {
            return modifier;
        }

        public void setModifier(Object modifier) {
            this.modifier = modifier;
        }

        public Object getDeleter() {
            return deleter;
        }

        public void setDeleter(Object deleter) {
            this.deleter = deleter;
        }

        public Object getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(Object deleteTime) {
            this.deleteTime = deleteTime;
        }

        public String getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getThirdType() {
            return thirdType;
        }

        public void setThirdType(String thirdType) {
            this.thirdType = thirdType;
        }

        public int getThirdShopId() {
            return thirdShopId;
        }

        public void setThirdShopId(int thirdShopId) {
            this.thirdShopId = thirdShopId;
        }

        public String getThirdShopName() {
            return thirdShopName;
        }

        public void setThirdShopName(String thirdShopName) {
            this.thirdShopName = thirdShopName;
        }

        public Object getIsbn() {
            return isbn;
        }

        public void setIsbn(Object isbn) {
            this.isbn = isbn;
        }

        public String getShowList() {
            return showList;
        }

        public void setShowList(String showList) {
            this.showList = showList;
        }

        public String getShowMobile() {
            return showMobile;
        }

        public void setShowMobile(String showMobile) {
            this.showMobile = showMobile;
        }

        public String getIsCustomerDismount() {
            return isCustomerDismount;
        }

        public void setIsCustomerDismount(String isCustomerDismount) {
            this.isCustomerDismount = isCustomerDismount;
        }

        public String getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(String auditStatus) {
            this.auditStatus = auditStatus;
        }

        public String getFreeShipment() {
            return freeShipment;
        }

        public void setFreeShipment(String freeShipment) {
            this.freeShipment = freeShipment;
        }

        public Object getRefuseReason() {
            return refuseReason;
        }

        public void setRefuseReason(Object refuseReason) {
            this.refuseReason = refuseReason;
        }

        public int getFictitiousSalesCount() {
            return fictitiousSalesCount;
        }

        public void setFictitiousSalesCount(int fictitiousSalesCount) {
            this.fictitiousSalesCount = fictitiousSalesCount;
        }

        public Object getSpu() {
            return spu;
        }

        public void setSpu(Object spu) {
            this.spu = spu;
        }

        public Object getSpecItemList() {
            return specItemList;
        }

        public void setSpecItemList(Object specItemList) {
            this.specItemList = specItemList;
        }

        public Object getSpuSpecItemList() {
            return spuSpecItemList;
        }

        public void setSpuSpecItemList(Object spuSpecItemList) {
            this.spuSpecItemList = spuSpecItemList;
        }

        public Object getParamItemList() {
            return paramItemList;
        }

        public void setParamItemList(Object paramItemList) {
            this.paramItemList = paramItemList;
        }

        public Object getSpuDesc() {
            return spuDesc;
        }

        public void setSpuDesc(Object spuDesc) {
            this.spuDesc = spuDesc;
        }

        public Object getImageList() {
            return imageList;
        }

        public void setImageList(Object imageList) {
            this.imageList = imageList;
        }

        public Object getTagList() {
            return tagList;
        }

        public void setTagList(Object tagList) {
            this.tagList = tagList;
        }

        public Object getNumber() {
            return number;
        }

        public void setNumber(Object number) {
            this.number = number;
        }

        public Object getDesc() {
            return desc;
        }

        public void setDesc(Object desc) {
            this.desc = desc;
        }

        public List<StoreListBean> getStoreList() {
            return storeList;
        }

        public void setStoreList(List<StoreListBean> storeList) {
            this.storeList = storeList;
        }

        public static class StoreListBean {
            /**
             * id : 514
             * goodsInfoId : 122
             * wareHouseId : 1
             * stock : 92
             * price : 1580.0
             * deleteFlag : 0
             * wareHouse : null
             */

            private int id;
            private int goodsInfoId;
            private int wareHouseId;
            private int stock;
            private double price;
            private int deleteFlag;
            private Object wareHouse;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getGoodsInfoId() {
                return goodsInfoId;
            }

            public void setGoodsInfoId(int goodsInfoId) {
                this.goodsInfoId = goodsInfoId;
            }

            public int getWareHouseId() {
                return wareHouseId;
            }

            public void setWareHouseId(int wareHouseId) {
                this.wareHouseId = wareHouseId;
            }

            public int getStock() {
                return stock;
            }

            public void setStock(int stock) {
                this.stock = stock;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public int getDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(int deleteFlag) {
                this.deleteFlag = deleteFlag;
            }

            public Object getWareHouse() {
                return wareHouse;
            }

            public void setWareHouse(Object wareHouse) {
                this.wareHouse = wareHouse;
            }
        }
    }
}
