package com.odbpo.fenggou.domain.bean;


import java.io.Serializable;
import java.util.*;


/**
 * @author: zjl
 * @Time: 2017/8/3 9:53
 * @Desc: 商品ES查询请求
 */


public class EsSearchRequest implements Serializable, Cloneable {
    /**
     * 商品类型ID列表
     */
    private List<Long> typeIds;


    private int pageNum = 0;


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


}
