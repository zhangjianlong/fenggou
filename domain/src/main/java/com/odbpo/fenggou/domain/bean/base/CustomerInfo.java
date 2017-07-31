package com.odbpo.fenggou.domain.bean.base;

import java.io.Serializable;

/**
 * @author: zjl
 * @Time: 2017/7/31 14:31
 * @Desc:
 */
public class CustomerInfo implements Serializable {
    private String birthday;
    private String email;
    private String gender;
    private String id;
    private String image;
    private String infoId;
    private String mobile;
    private String mobileVerifyStatus;
    private String nickname;
    private String username;


    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobileVerifyStatus() {
        return mobileVerifyStatus;
    }

    public void setMobileVerifyStatus(String mobileVerifyStatus) {
        this.mobileVerifyStatus = mobileVerifyStatus;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
