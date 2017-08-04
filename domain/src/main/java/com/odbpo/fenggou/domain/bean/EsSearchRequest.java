package com.odbpo.fenggou.domain.bean;


import java.io.Serializable;
import java.util.*;

import javax.swing.SortOrder;


/**
 * @author: zjl
 * @Time: 2017/8/3 9:53
 * @Desc: 商品ES查询请求
 */


public class EsSearchRequest implements Serializable {
    /**
     * 商品类型ID列表
     */
    private List<Long> typeIds;


    private int pageNum = 0;


    /**
     * 排序字段
     */
    private List<SortItem> sorts = new ArrayList<>();


    /**
     * 查询字符串
     */
    private String queryString;


    public List<Long> getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(List<Long> typeIds) {
        this.typeIds = typeIds;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }


    public List<SortItem> getSorts() {
        return sorts;
    }

    public void setSorts(List<SortItem> sorts) {
        this.sorts = sorts;
    }


    public class SortItem {

        private String field;

        private int order;

        public SortItem() {
        }

        public SortItem(String field, int order) {
            this.field = field;
            this.order = order;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }


    }


}
