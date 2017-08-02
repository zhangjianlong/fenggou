package com.odbpo.fenggou.domain.bean;

import java.util.List;

/**
 * @author: zjl
 * @Time: 2017/8/2 9:50
 * @Desc:
 */
public class SearchProductBean {

    private int total;
    private AggResultMapBean aggResultMap;
    private Object queryString;
    private Object validQueryString;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public AggResultMapBean getAggResultMap() {
        return aggResultMap;
    }

    public void setAggResultMap(AggResultMapBean aggResultMap) {
        this.aggResultMap = aggResultMap;
    }

    public Object getQueryString() {
        return queryString;
    }

    public void setQueryString(Object queryString) {
        this.queryString = queryString;
    }

    public Object getValidQueryString() {
        return validQueryString;
    }

    public void setValidQueryString(Object validQueryString) {
        this.validQueryString = validQueryString;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class AggResultMapBean {
        private List<BrandsBean> brands;
        private List<CatesBean> cates;

        public List<BrandsBean> getBrands() {
            return brands;
        }

        public void setBrands(List<BrandsBean> brands) {
            this.brands = brands;
        }

        public List<CatesBean> getCates() {
            return cates;
        }

        public void setCates(List<CatesBean> cates) {
            this.cates = cates;
        }

        public static class BrandsBean {
            /**
             * key : 皮埃蒙特
             * count : 17
             * childs : null
             */

            private String key;
            private int count;
            private Object childs;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public Object getChilds() {
                return childs;
            }

            public void setChilds(Object childs) {
                this.childs = childs;
            }
        }

        public static class CatesBean {
            /**
             * key : 精品酒类
             * count : 35
             * childs : null
             */

            private String key;
            private int count;
            private Object childs;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public Object getChilds() {
                return childs;
            }

            public void setChilds(Object childs) {
                this.childs = childs;
            }
        }
    }

    public static class DataBean {
        /**
         * marketPrice : 238.0
         * creatorName : qmbbc
         * brandList : [{"brandId":3,"brandNickname":null,"brandName":"皮埃蒙特","brandUrl":null,"brandLogo":null}]
         * freeShipment : 0
         * itemNo : HJ20160009
         * deleteFlag : 0
         * modifyTime : {"hour":9,"minute":6,"nano":0,"second":37,"monthValue":9,"dayOfMonth":19,"dayOfWeek":"MONDAY","dayOfYear":263,"month":"SEPTEMBER","year":2016,"chronology":{"calendarType":"iso8601","id":"ISO"}}
         * exParamList : [{"expandparamName":"年份","expandparamId":1,"expandparamValueName":"2011","expandparamValueId":9},{"expandparamName":"产地","expandparamId":2,"expandparamValueName":"西西里岛","expandparamValueId":17},{"expandparamName":"国家","expandparamId":3,"expandparamValueName":"意大利","expandparamValueId":4},{"expandparamName":"规格","expandparamId":4,"expandparamValueName":"750ML","expandparamValueId":7},{"expandparamName":"类型","expandparamId":5,"expandparamValueName":"干红","expandparamValueId":25}]
         * goodCommentPercent : 1.0
         * goodCommentNum : 663
         * addedStatus : 1
         * id : 1
         * stock : 0
         * showMobile : 1
         * thirdType : 0
         * wareList : [{"id":1,"wareId":1,"warePrice":238,"wareStock":46}]
         * image : http://fenggoyunbeta.b0.upaiyun.com/1473741908457.jpg!352
         * thirdShopName : BOSS
         * saleCount : 0
         * costPrice : null
         * weight : 750.0
         * preferPrice : 238.0
         * isCustomerDismount : 0
         * showList : 1
         * createTime : {"hour":17,"minute":7,"nano":0,"second":18,"monthValue":9,"dayOfMonth":18,"dayOfWeek":"SUNDAY","dayOfYear":262,"month":"SEPTEMBER","year":2016,"chronology":{"calendarType":"iso8601","id":"ISO"}}
         * thirdShopId : 0
         * cateId : 5010
         * brandId : 3
         * subtitle : 五星推荐 好喝才是真
         * collectionCount : 0
         * name : 意大利葡萄酒进口红枫岛干红红酒葡萄酒(通用)
         * auditStatus : 0
         * spuId : 1
         * typeId : 1
         * cateList : [{"catId":4988,"catName":"精品酒类","catParentId":0},{"catId":4993,"catName":"进口红酒","catParentId":4988},{"catId":5010,"catName":"意大利","catParentId":4993}]
         * modifierName : qmbbc
         * imageList : [{"goodsImgId":1,"imageInName":"http://fenggoyunbeta.b0.upaiyun.com/1473741908457.jpg!160","imageThumName":"http://fenggoyunbeta.b0.upaiyun.com/1473741908457.jpg!56","imageBigName":"http://fenggoyunbeta.b0.upaiyun.com/1473741908457.jpg!352","imageArtworkName":"http://fenggoyunbeta.b0.upaiyun.com/1473741908457.jpg","goodsImgSort":0}]
         */

        private double marketPrice;
        private String creatorName;
        private String freeShipment;
        private String itemNo;
        private String deleteFlag;
        private ModifyTimeBean modifyTime;
        private double goodCommentPercent;
        private int goodCommentNum;
        private String addedStatus;
        private int id;
        private int stock;
        private String showMobile;
        private String thirdType;
        private String image;
        private String thirdShopName;
        private int saleCount;
        private Object costPrice;
        private double weight;
        private double preferPrice;
        private String isCustomerDismount;
        private String showList;
        private CreateTimeBean createTime;
        private int thirdShopId;
        private int cateId;
        private int brandId;
        private String subtitle;
        private int collectionCount;
        private String name;
        private String auditStatus;
        private int spuId;
        private int typeId;
        private String modifierName;
        private List<BrandListBean> brandList;
        private List<ExParamListBean> exParamList;
        private List<WareListBean> wareList;
        private List<CateListBean> cateList;
        private List<ImageListBean> imageList;

        public double getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(double marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public String getFreeShipment() {
            return freeShipment;
        }

        public void setFreeShipment(String freeShipment) {
            this.freeShipment = freeShipment;
        }

        public String getItemNo() {
            return itemNo;
        }

        public void setItemNo(String itemNo) {
            this.itemNo = itemNo;
        }

        public String getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(String deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public ModifyTimeBean getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(ModifyTimeBean modifyTime) {
            this.modifyTime = modifyTime;
        }

        public double getGoodCommentPercent() {
            return goodCommentPercent;
        }

        public void setGoodCommentPercent(double goodCommentPercent) {
            this.goodCommentPercent = goodCommentPercent;
        }

        public int getGoodCommentNum() {
            return goodCommentNum;
        }

        public void setGoodCommentNum(int goodCommentNum) {
            this.goodCommentNum = goodCommentNum;
        }

        public String getAddedStatus() {
            return addedStatus;
        }

        public void setAddedStatus(String addedStatus) {
            this.addedStatus = addedStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getShowMobile() {
            return showMobile;
        }

        public void setShowMobile(String showMobile) {
            this.showMobile = showMobile;
        }

        public String getThirdType() {
            return thirdType;
        }

        public void setThirdType(String thirdType) {
            this.thirdType = thirdType;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getThirdShopName() {
            return thirdShopName;
        }

        public void setThirdShopName(String thirdShopName) {
            this.thirdShopName = thirdShopName;
        }

        public int getSaleCount() {
            return saleCount;
        }

        public void setSaleCount(int saleCount) {
            this.saleCount = saleCount;
        }

        public Object getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(Object costPrice) {
            this.costPrice = costPrice;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getPreferPrice() {
            return preferPrice;
        }

        public void setPreferPrice(double preferPrice) {
            this.preferPrice = preferPrice;
        }

        public String getIsCustomerDismount() {
            return isCustomerDismount;
        }

        public void setIsCustomerDismount(String isCustomerDismount) {
            this.isCustomerDismount = isCustomerDismount;
        }

        public String getShowList() {
            return showList;
        }

        public void setShowList(String showList) {
            this.showList = showList;
        }

        public CreateTimeBean getCreateTime() {
            return createTime;
        }

        public void setCreateTime(CreateTimeBean createTime) {
            this.createTime = createTime;
        }

        public int getThirdShopId() {
            return thirdShopId;
        }

        public void setThirdShopId(int thirdShopId) {
            this.thirdShopId = thirdShopId;
        }

        public int getCateId() {
            return cateId;
        }

        public void setCateId(int cateId) {
            this.cateId = cateId;
        }

        public int getBrandId() {
            return brandId;
        }

        public void setBrandId(int brandId) {
            this.brandId = brandId;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public int getCollectionCount() {
            return collectionCount;
        }

        public void setCollectionCount(int collectionCount) {
            this.collectionCount = collectionCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(String auditStatus) {
            this.auditStatus = auditStatus;
        }

        public int getSpuId() {
            return spuId;
        }

        public void setSpuId(int spuId) {
            this.spuId = spuId;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getModifierName() {
            return modifierName;
        }

        public void setModifierName(String modifierName) {
            this.modifierName = modifierName;
        }

        public List<BrandListBean> getBrandList() {
            return brandList;
        }

        public void setBrandList(List<BrandListBean> brandList) {
            this.brandList = brandList;
        }

        public List<ExParamListBean> getExParamList() {
            return exParamList;
        }

        public void setExParamList(List<ExParamListBean> exParamList) {
            this.exParamList = exParamList;
        }

        public List<WareListBean> getWareList() {
            return wareList;
        }

        public void setWareList(List<WareListBean> wareList) {
            this.wareList = wareList;
        }

        public List<CateListBean> getCateList() {
            return cateList;
        }

        public void setCateList(List<CateListBean> cateList) {
            this.cateList = cateList;
        }

        public List<ImageListBean> getImageList() {
            return imageList;
        }

        public void setImageList(List<ImageListBean> imageList) {
            this.imageList = imageList;
        }

        public static class ModifyTimeBean {
            /**
             * hour : 9
             * minute : 6
             * nano : 0
             * second : 37
             * monthValue : 9
             * dayOfMonth : 19
             * dayOfWeek : MONDAY
             * dayOfYear : 263
             * month : SEPTEMBER
             * year : 2016
             * chronology : {"calendarType":"iso8601","id":"ISO"}
             */

            private int hour;
            private int minute;
            private int nano;
            private int second;
            private int monthValue;
            private int dayOfMonth;
            private String dayOfWeek;
            private int dayOfYear;
            private String month;
            private int year;
            private ChronologyBean chronology;

            public int getHour() {
                return hour;
            }

            public void setHour(int hour) {
                this.hour = hour;
            }

            public int getMinute() {
                return minute;
            }

            public void setMinute(int minute) {
                this.minute = minute;
            }

            public int getNano() {
                return nano;
            }

            public void setNano(int nano) {
                this.nano = nano;
            }

            public int getSecond() {
                return second;
            }

            public void setSecond(int second) {
                this.second = second;
            }

            public int getMonthValue() {
                return monthValue;
            }

            public void setMonthValue(int monthValue) {
                this.monthValue = monthValue;
            }

            public int getDayOfMonth() {
                return dayOfMonth;
            }

            public void setDayOfMonth(int dayOfMonth) {
                this.dayOfMonth = dayOfMonth;
            }

            public String getDayOfWeek() {
                return dayOfWeek;
            }

            public void setDayOfWeek(String dayOfWeek) {
                this.dayOfWeek = dayOfWeek;
            }

            public int getDayOfYear() {
                return dayOfYear;
            }

            public void setDayOfYear(int dayOfYear) {
                this.dayOfYear = dayOfYear;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public ChronologyBean getChronology() {
                return chronology;
            }

            public void setChronology(ChronologyBean chronology) {
                this.chronology = chronology;
            }

            public static class ChronologyBean {
                /**
                 * calendarType : iso8601
                 * id : ISO
                 */

                private String calendarType;
                private String id;

                public String getCalendarType() {
                    return calendarType;
                }

                public void setCalendarType(String calendarType) {
                    this.calendarType = calendarType;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }

        public static class CreateTimeBean {
            /**
             * hour : 17
             * minute : 7
             * nano : 0
             * second : 18
             * monthValue : 9
             * dayOfMonth : 18
             * dayOfWeek : SUNDAY
             * dayOfYear : 262
             * month : SEPTEMBER
             * year : 2016
             * chronology : {"calendarType":"iso8601","id":"ISO"}
             */

            private int hour;
            private int minute;
            private int nano;
            private int second;
            private int monthValue;
            private int dayOfMonth;
            private String dayOfWeek;
            private int dayOfYear;
            private String month;
            private int year;
            private ChronologyBeanX chronology;

            public int getHour() {
                return hour;
            }

            public void setHour(int hour) {
                this.hour = hour;
            }

            public int getMinute() {
                return minute;
            }

            public void setMinute(int minute) {
                this.minute = minute;
            }

            public int getNano() {
                return nano;
            }

            public void setNano(int nano) {
                this.nano = nano;
            }

            public int getSecond() {
                return second;
            }

            public void setSecond(int second) {
                this.second = second;
            }

            public int getMonthValue() {
                return monthValue;
            }

            public void setMonthValue(int monthValue) {
                this.monthValue = monthValue;
            }

            public int getDayOfMonth() {
                return dayOfMonth;
            }

            public void setDayOfMonth(int dayOfMonth) {
                this.dayOfMonth = dayOfMonth;
            }

            public String getDayOfWeek() {
                return dayOfWeek;
            }

            public void setDayOfWeek(String dayOfWeek) {
                this.dayOfWeek = dayOfWeek;
            }

            public int getDayOfYear() {
                return dayOfYear;
            }

            public void setDayOfYear(int dayOfYear) {
                this.dayOfYear = dayOfYear;
            }

            public String getMonth() {
                return month;
            }

            public void setMonth(String month) {
                this.month = month;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public ChronologyBeanX getChronology() {
                return chronology;
            }

            public void setChronology(ChronologyBeanX chronology) {
                this.chronology = chronology;
            }

            public static class ChronologyBeanX {
                /**
                 * calendarType : iso8601
                 * id : ISO
                 */

                private String calendarType;
                private String id;

                public String getCalendarType() {
                    return calendarType;
                }

                public void setCalendarType(String calendarType) {
                    this.calendarType = calendarType;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }

        public static class BrandListBean {
            /**
             * brandId : 3
             * brandNickname : null
             * brandName : 皮埃蒙特
             * brandUrl : null
             * brandLogo : null
             */

            private int brandId;
            private Object brandNickname;
            private String brandName;
            private Object brandUrl;
            private Object brandLogo;

            public int getBrandId() {
                return brandId;
            }

            public void setBrandId(int brandId) {
                this.brandId = brandId;
            }

            public Object getBrandNickname() {
                return brandNickname;
            }

            public void setBrandNickname(Object brandNickname) {
                this.brandNickname = brandNickname;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public Object getBrandUrl() {
                return brandUrl;
            }

            public void setBrandUrl(Object brandUrl) {
                this.brandUrl = brandUrl;
            }

            public Object getBrandLogo() {
                return brandLogo;
            }

            public void setBrandLogo(Object brandLogo) {
                this.brandLogo = brandLogo;
            }
        }

        public static class ExParamListBean {
            /**
             * expandparamName : 年份
             * expandparamId : 1
             * expandparamValueName : 2011
             * expandparamValueId : 9
             */

            private String expandparamName;
            private int expandparamId;
            private String expandparamValueName;
            private int expandparamValueId;

            public String getExpandparamName() {
                return expandparamName;
            }

            public void setExpandparamName(String expandparamName) {
                this.expandparamName = expandparamName;
            }

            public int getExpandparamId() {
                return expandparamId;
            }

            public void setExpandparamId(int expandparamId) {
                this.expandparamId = expandparamId;
            }

            public String getExpandparamValueName() {
                return expandparamValueName;
            }

            public void setExpandparamValueName(String expandparamValueName) {
                this.expandparamValueName = expandparamValueName;
            }

            public int getExpandparamValueId() {
                return expandparamValueId;
            }

            public void setExpandparamValueId(int expandparamValueId) {
                this.expandparamValueId = expandparamValueId;
            }
        }

        public static class WareListBean {
            /**
             * id : 1
             * wareId : 1
             * warePrice : 238.0
             * wareStock : 46
             */

            private int id;
            private int wareId;
            private double warePrice;
            private int wareStock;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getWareId() {
                return wareId;
            }

            public void setWareId(int wareId) {
                this.wareId = wareId;
            }

            public double getWarePrice() {
                return warePrice;
            }

            public void setWarePrice(double warePrice) {
                this.warePrice = warePrice;
            }

            public int getWareStock() {
                return wareStock;
            }

            public void setWareStock(int wareStock) {
                this.wareStock = wareStock;
            }
        }

        public static class CateListBean {
            /**
             * catId : 4988
             * catName : 精品酒类
             * catParentId : 0
             */

            private int catId;
            private String catName;
            private int catParentId;

            public int getCatId() {
                return catId;
            }

            public void setCatId(int catId) {
                this.catId = catId;
            }

            public String getCatName() {
                return catName;
            }

            public void setCatName(String catName) {
                this.catName = catName;
            }

            public int getCatParentId() {
                return catParentId;
            }

            public void setCatParentId(int catParentId) {
                this.catParentId = catParentId;
            }
        }

        public static class ImageListBean {
            /**
             * goodsImgId : 1
             * imageInName : http://fenggoyunbeta.b0.upaiyun.com/1473741908457.jpg!160
             * imageThumName : http://fenggoyunbeta.b0.upaiyun.com/1473741908457.jpg!56
             * imageBigName : http://fenggoyunbeta.b0.upaiyun.com/1473741908457.jpg!352
             * imageArtworkName : http://fenggoyunbeta.b0.upaiyun.com/1473741908457.jpg
             * goodsImgSort : 0
             */

            private int goodsImgId;
            private String imageInName;
            private String imageThumName;
            private String imageBigName;
            private String imageArtworkName;
            private int goodsImgSort;

            public int getGoodsImgId() {
                return goodsImgId;
            }

            public void setGoodsImgId(int goodsImgId) {
                this.goodsImgId = goodsImgId;
            }

            public String getImageInName() {
                return imageInName;
            }

            public void setImageInName(String imageInName) {
                this.imageInName = imageInName;
            }

            public String getImageThumName() {
                return imageThumName;
            }

            public void setImageThumName(String imageThumName) {
                this.imageThumName = imageThumName;
            }

            public String getImageBigName() {
                return imageBigName;
            }

            public void setImageBigName(String imageBigName) {
                this.imageBigName = imageBigName;
            }

            public String getImageArtworkName() {
                return imageArtworkName;
            }

            public void setImageArtworkName(String imageArtworkName) {
                this.imageArtworkName = imageArtworkName;
            }

            public int getGoodsImgSort() {
                return goodsImgSort;
            }

            public void setGoodsImgSort(int goodsImgSort) {
                this.goodsImgSort = goodsImgSort;
            }
        }
    }
}
