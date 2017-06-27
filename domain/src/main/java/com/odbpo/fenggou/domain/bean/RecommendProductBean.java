package com.odbpo.fenggou.domain.bean;

/**
 * @author: zjl
 * @Time: 2017/6/27 10:25
 * @Desc:
 */

public class RecommendProductBean {
    private String productName;
    private String productPrice;
    private String productUrl;

    public RecommendProductBean(String productName, String productUrl, String productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productUrl = productUrl;

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
}
