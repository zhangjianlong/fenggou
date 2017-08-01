package com.odbpo.fenggou.domain.bean;

import java.util.List;

/**
 * @author: zjl
 * @Time: 2017/8/1 10:22
 * @Desc:
 */
public class ProductCategoryBean {

    /**
     * total : 66
     * data : [{"id":28,"cateId":4988,"name":"精品酒类","imgSrc":null,"grade":1,"sort":1,"parentId":0,"isUsing":"1","delflag":0},{"id":50,"cateId":4778,"name":"个护化妆","imgSrc":null,"grade":1,"sort":2,"parentId":0,"isUsing":"1","delflag":0},{"id":58,"cateId":4777,"name":"家用电器","imgSrc":null,"grade":1,"sort":3,"parentId":0,"isUsing":"1","delflag":0},{"id":82,"cateId":4779,"name":"母婴用品","imgSrc":null,"grade":1,"sort":4,"parentId":0,"isUsing":"1","delflag":0},{"id":107,"cateId":5017,"name":"健康保健","imgSrc":null,"grade":1,"sort":5,"parentId":0,"isUsing":"1","delflag":0},{"id":29,"cateId":5010,"name":"进口红酒","imgSrc":null,"grade":2,"sort":1,"parentId":28,"isUsing":"1","delflag":0},{"id":79,"cateId":4865,"name":"大家电","imgSrc":null,"grade":2,"sort":1,"parentId":58,"isUsing":"1","delflag":0},{"id":83,"cateId":4867,"name":"妈妈专区","imgSrc":null,"grade":2,"sort":1,"parentId":82,"isUsing":"1","delflag":0},{"id":108,"cateId":5018,"name":"保健食品","imgSrc":null,"grade":2,"sort":1,"parentId":107,"isUsing":"1","delflag":0},{"id":52,"cateId":5844,"name":"面部护理","imgSrc":null,"grade":2,"sort":2,"parentId":50,"isUsing":"1","delflag":0},{"id":60,"cateId":4924,"name":"生活电器","imgSrc":null,"grade":2,"sort":2,"parentId":58,"isUsing":"1","delflag":0},{"id":84,"cateId":4940,"name":"婴儿用品","imgSrc":null,"grade":2,"sort":2,"parentId":82,"isUsing":"1","delflag":0},{"id":88,"cateId":5813,"name":"洗护用品","imgSrc":null,"grade":2,"sort":2,"parentId":82,"isUsing":"1","delflag":0},{"id":61,"cateId":5783,"name":"厨房电器","imgSrc":null,"grade":2,"sort":3,"parentId":58,"isUsing":"1","delflag":0},{"id":62,"cateId":5784,"name":"个人护理","imgSrc":null,"grade":2,"sort":4,"parentId":58,"isUsing":"1","delflag":0},{"id":86,"cateId":5805,"name":"喂养用品","imgSrc":null,"grade":2,"sort":4,"parentId":82,"isUsing":"1","delflag":0},{"id":87,"cateId":5808,"name":"童车童床","imgSrc":null,"grade":2,"sort":5,"parentId":82,"isUsing":"1","delflag":0},{"id":89,"cateId":5814,"name":"出行安全","imgSrc":null,"grade":2,"sort":7,"parentId":82,"isUsing":"1","delflag":0},{"id":39,"cateId":5010,"name":"意大利","imgSrc":"http://img.feng-go.com/1494823057117.jpg","grade":3,"sort":1,"parentId":29,"isUsing":"1","delflag":0},{"id":54,"cateId":5845,"name":"面膜","imgSrc":"http://img.feng-go.com/1494824683004.jpg","grade":3,"sort":1,"parentId":52,"isUsing":"1","delflag":0},{"id":63,"cateId":5828,"name":"空气净化器","imgSrc":"http://img.feng-go.com/1494836896886.jpg","grade":3,"sort":1,"parentId":60,"isUsing":"1","delflag":0},{"id":70,"cateId":5796,"name":"美容器","imgSrc":"http://img.feng-go.com/1494837328927.jpg","grade":3,"sort":1,"parentId":62,"isUsing":"1","delflag":0},{"id":74,"cateId":5788,"name":"电饭煲","imgSrc":"http://img.feng-go.com/1494896859444.jpg","grade":3,"sort":1,"parentId":61,"isUsing":"1","delflag":0},{"id":80,"cateId":5830,"name":"电热水器","imgSrc":"http://img.feng-go.com/1494898813482.jpg","grade":3,"sort":1,"parentId":79,"isUsing":"1","delflag":0},{"id":91,"cateId":5024,"name":"婴儿背带","imgSrc":"http://img.feng-go.com/1494899209816.jpg","grade":3,"sort":1,"parentId":83,"isUsing":"1","delflag":0},{"id":92,"cateId":5819,"name":"儿童餐具","imgSrc":"http://img.feng-go.com/1494901164494.jpg","grade":3,"sort":1,"parentId":86,"isUsing":"1","delflag":0},{"id":96,"cateId":4947,"name":"婴儿推车","imgSrc":"http://img.feng-go.com/1494901727173.jpg","grade":3,"sort":1,"parentId":87,"isUsing":"1","delflag":0},{"id":100,"cateId":5025,"name":"理发器","imgSrc":"http://img.feng-go.com/1494903507092.jpg","grade":3,"sort":1,"parentId":88,"isUsing":"1","delflag":0},{"id":105,"cateId":5000,"name":"安全座椅","imgSrc":"http://img.feng-go.com/1494904545542.jpg","grade":3,"sort":1,"parentId":89,"isUsing":"1","delflag":0},{"id":106,"cateId":5859,"name":"爬行毯/爬行垫","imgSrc":"http://img.feng-go.com/1494904752003.jpg","grade":3,"sort":1,"parentId":84,"isUsing":"1","delflag":0},{"id":109,"cateId":5019,"name":"饮料冲调","imgSrc":"http://img.feng-go.com/1494910605068.jpg","grade":3,"sort":1,"parentId":108,"isUsing":"1","delflag":0},{"id":41,"cateId":5012,"name":"法国","imgSrc":"http://img.feng-go.com/1494823056166.jpg","grade":3,"sort":2,"parentId":29,"isUsing":"1","delflag":0},{"id":55,"cateId":5846,"name":"卸妆","imgSrc":"http://img.feng-go.com/1494824723592.jpg","grade":3,"sort":2,"parentId":52,"isUsing":"1","delflag":0},{"id":64,"cateId":5001,"name":"吸尘器","imgSrc":"http://img.feng-go.com/1494836963481.jpg","grade":3,"sort":2,"parentId":60,"isUsing":"1","delflag":0},{"id":71,"cateId":5787,"name":"电动牙刷","imgSrc":"http://img.feng-go.com/1494837437860.jpg","grade":3,"sort":2,"parentId":62,"isUsing":"1","delflag":0},{"id":75,"cateId":5791,"name":"电磁炉","imgSrc":"http://img.feng-go.com/1494896898459.jpg","grade":3,"sort":2,"parentId":61,"isUsing":"1","delflag":0},{"id":81,"cateId":5832,"name":"燃气热水器","imgSrc":"http://img.feng-go.com/1494898852233.jpg","grade":3,"sort":2,"parentId":79,"isUsing":"1","delflag":0},{"id":93,"cateId":5812,"name":"儿童保温杯","imgSrc":"http://img.feng-go.com/1494901216841.jpg","grade":3,"sort":2,"parentId":86,"isUsing":"1","delflag":0},{"id":97,"cateId":5027,"name":"儿童滑板车","imgSrc":"http://img.feng-go.com/1494902724796.jpg","grade":3,"sort":2,"parentId":87,"isUsing":"1","delflag":0},{"id":101,"cateId":4945,"name":"湿巾","imgSrc":"http://img.feng-go.com/1494903929369.jpg","grade":3,"sort":2,"parentId":88,"isUsing":"1","delflag":0},{"id":110,"cateId":5020,"name":"米面主食","imgSrc":"http://img.feng-go.com/1494910698493.jpg","grade":3,"sort":2,"parentId":108,"isUsing":"1","delflag":0},{"id":42,"cateId":5772,"name":"西班牙","imgSrc":"http://img.feng-go.com/1494823056845.jpg","grade":3,"sort":3,"parentId":29,"isUsing":"1","delflag":0},{"id":56,"cateId":5847,"name":"洁面","imgSrc":"http://img.feng-go.com/1494824760771.jpg","grade":3,"sort":3,"parentId":52,"isUsing":"1","delflag":0},{"id":65,"cateId":5002,"name":"除螨仪","imgSrc":"http://img.feng-go.com/1494837010089.jpg","grade":3,"sort":3,"parentId":60,"isUsing":"1","delflag":0},{"id":72,"cateId":5824,"name":"按摩器","imgSrc":"http://img.feng-go.com/1494837725792.jpg","grade":3,"sort":3,"parentId":62,"isUsing":"1","delflag":0},{"id":76,"cateId":5835,"name":"电水壶/热水瓶","imgSrc":"http://img.feng-go.com/1494897018962.jpg","grade":3,"sort":3,"parentId":61,"isUsing":"1","delflag":0},{"id":94,"cateId":4944,"name":"儿童水杯","imgSrc":"http://img.feng-go.com/1494901251461.jpg","grade":3,"sort":3,"parentId":86,"isUsing":"1","delflag":0},{"id":98,"cateId":5855,"name":"儿童电动车","imgSrc":"http://img.feng-go.com/1494901806356.jpg","grade":3,"sort":3,"parentId":87,"isUsing":"1","delflag":0},{"id":102,"cateId":5026,"name":"洗护","imgSrc":"http://img.feng-go.com/1494903958890.jpg","grade":3,"sort":3,"parentId":88,"isUsing":"1","delflag":0},{"id":111,"cateId":5021,"name":"河南特产 铁棍山药","imgSrc":"http://img.feng-go.com/1494910753746.jpg","grade":3,"sort":3,"parentId":108,"isUsing":"1","delflag":0},{"id":43,"cateId":5769,"name":"澳大利亚","imgSrc":"http://img.feng-go.com/1494823056046.jpg","grade":3,"sort":4,"parentId":29,"isUsing":"1","delflag":0},{"id":57,"cateId":5853,"name":"护肤套装","imgSrc":"http://img.feng-go.com/1494824806436.jpg","grade":3,"sort":4,"parentId":52,"isUsing":"1","delflag":0},{"id":66,"cateId":5003,"name":"挂烫机","imgSrc":"http://img.feng-go.com/1494837060948.jpg","grade":3,"sort":4,"parentId":60,"isUsing":"1","delflag":0},{"id":73,"cateId":5823,"name":"电子秤/厨房秤","imgSrc":"http://img.feng-go.com/1494897984351.jpg","grade":3,"sort":4,"parentId":62,"isUsing":"1","delflag":0},{"id":77,"cateId":5794,"name":"榨汁机","imgSrc":"http://img.feng-go.com/1494897094533.jpg","grade":3,"sort":4,"parentId":61,"isUsing":"1","delflag":0},{"id":95,"cateId":5858,"name":"餐椅","imgSrc":"http://img.feng-go.com/1494901286972.jpg","grade":3,"sort":4,"parentId":86,"isUsing":"1","delflag":0},{"id":99,"cateId":5857,"name":"自行车/三轮车","imgSrc":"http://img.feng-go.com/1494901921887.jpg","grade":3,"sort":4,"parentId":87,"isUsing":"1","delflag":0},{"id":103,"cateId":5856,"name":"浴盆/澡盆","imgSrc":"http://img.feng-go.com/1494904076971.jpg","grade":3,"sort":4,"parentId":88,"isUsing":"1","delflag":0},{"id":112,"cateId":5797,"name":"茶萃","imgSrc":"http://img.feng-go.com/1494910797884.jpg","grade":3,"sort":4,"parentId":108,"isUsing":"1","delflag":0},{"id":44,"cateId":5770,"name":"智利","imgSrc":"http://img.feng-go.com/1494823122584.jpg","grade":3,"sort":5,"parentId":29,"isUsing":"1","delflag":0},{"id":67,"cateId":5022,"name":"扫地机器人","imgSrc":"http://img.feng-go.com/1494837083664.jpg","grade":3,"sort":5,"parentId":60,"isUsing":"1","delflag":0},{"id":78,"cateId":5827,"name":"脂肪秤/体脂仪","imgSrc":"http://img.feng-go.com/1494837842617.jpg","grade":3,"sort":5,"parentId":62,"isUsing":"1","delflag":0},{"id":104,"cateId":5854,"name":"洗浴巾","imgSrc":"http://img.feng-go.com/1494904133611.jpg","grade":3,"sort":5,"parentId":88,"isUsing":"1","delflag":0},{"id":49,"cateId":5771,"name":"美国","imgSrc":"http://img.feng-go.com/1494823056318.jpg","grade":3,"sort":6,"parentId":29,"isUsing":"1","delflag":0},{"id":68,"cateId":5833,"name":"除湿机","imgSrc":"http://img.feng-go.com/1494837127605.jpg","grade":3,"sort":6,"parentId":60,"isUsing":"1","delflag":0},{"id":69,"cateId":5834,"name":"干衣机","imgSrc":"http://img.feng-go.com/1494837160521.jpg","grade":3,"sort":7,"parentId":60,"isUsing":"1","delflag":0}]
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
         * id : 28
         * cateId : 4988
         * name : 精品酒类
         * imgSrc : null
         * grade : 1
         * sort : 1
         * parentId : 0
         * isUsing : 1
         * delflag : 0
         */

        private int id;
        private int cateId;
        private String name;
        private String imgSrc;
        private int grade;
        private int sort;
        private int parentId;
        private String isUsing;
        private int delflag;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCateId() {
            return cateId;
        }

        public void setCateId(int cateId) {
            this.cateId = cateId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImgSrc() {
            return imgSrc;
        }

        public void setImgSrc(String imgSrc) {
            this.imgSrc = imgSrc;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getIsUsing() {
            return isUsing;
        }

        public void setIsUsing(String isUsing) {
            this.isUsing = isUsing;
        }

        public int getDelflag() {
            return delflag;
        }

        public void setDelflag(int delflag) {
            this.delflag = delflag;
        }
    }
}
