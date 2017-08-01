package com.odbpo.fenggou.domain.bean;

import java.util.List;

import javax.swing.text.html.HTML;

/**
 * @author: zjl
 * @Time: 2017/6/19 13:12
 * @Desc:
 */

public class CategoryResultBean {
    private List<Tag1> tag1;

    public List<Tag1> getTag1() {
        return tag1;
    }

    public void setTag1(List<Tag1> tag1) {
        this.tag1 = tag1;
    }


    public static class Tag1 {
        public ProductCategoryBean.DataBean getDataBean() {
            return dataBean;
        }

        public void setDataBean(ProductCategoryBean.DataBean dataBean) {
            this.dataBean = dataBean;
        }

        public List<Tag2> getTag2() {
            return tag2;
        }

        public void setTag2(List<Tag2> tag2) {
            this.tag2 = tag2;
        }

        private ProductCategoryBean.DataBean dataBean;
        private List<Tag2> tag2;


    }


    public static class Tag2 {

        public Tag2(ProductCategoryBean.DataBean dataBean, List<Tag3> tag3List) {
            this.dataBean = dataBean;
            this.tag3 = tag3List;
        }

        public ProductCategoryBean.DataBean getDataBean() {
            return dataBean;
        }

        public void setDataBean(ProductCategoryBean.DataBean dataBean) {
            this.dataBean = dataBean;
        }

        public List<Tag3> getTag3() {
            return tag3;
        }

        public void setTag3(List<Tag3> tag3) {
            this.tag3 = tag3;
        }

        private ProductCategoryBean.DataBean dataBean;
        private List<Tag3> tag3;


    }

    public static class Tag3 {
        public Tag3(ProductCategoryBean.DataBean dataBean) {
            this.dataBean = dataBean;
        }

        public ProductCategoryBean.DataBean getDataBean() {
            return dataBean;
        }

        public void setDataBean(ProductCategoryBean.DataBean dataBean) {
            this.dataBean = dataBean;
        }

        private ProductCategoryBean.DataBean dataBean;
    }

}

