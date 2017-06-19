package com.odbpo.fenggou.domain.bean;

import java.util.List;

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
        private String tagName;
        private List<Tag2> tag2;

        public Tag1(String tagName, List<Tag2> tag2) {
            this.tagName = tagName;
            this.tag2 = tag2;

        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public List<Tag2> getTag2() {
            return tag2;
        }

        public void setTag2(List<Tag2> tag2) {
            this.tag2 = tag2;
        }

    }


    public static class Tag2 {
        private String tagName;
        private List<Tag3> tag3;

        public Tag2(String tagName, List<Tag3> tag3) {
            this.tagName = tagName;
            this.tag3 = tag3;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public List<Tag3> getTag3() {
            return tag3;
        }

        public void setTag3(List<Tag3> tag3) {
            this.tag3 = tag3;
        }
    }

    public static class Tag3 {
        public Tag3(String tagName, String imageUrl) {
            this.tagName = tagName;
            this.imageUrl = imageUrl;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        private String tagName;
        private String imageUrl;
    }

}

