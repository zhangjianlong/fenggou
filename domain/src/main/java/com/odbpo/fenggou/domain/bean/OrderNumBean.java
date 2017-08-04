package com.odbpo.fenggou.domain.bean;

import java.util.List;

/**
 * @author: zjl
 * @Time: 2017/8/4 15:43
 * @Desc:
 */
public class OrderNumBean {


    /**
     * total : 0
     * data : []
     */

    private int total;
    private List<?> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
