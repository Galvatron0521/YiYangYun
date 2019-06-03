package com.wd.yiyangyun.mvp.home.bean;

import java.io.Serializable;
import java.util.List;

public class NewsBean {


    /**
     * status : 0
     * data : {"wheelList":["attachment/inforImage/20190513/1635104028e47b6ab05467016ab054d2f50003.jpg","attachment/inforImage/20190513/1635144028e47b6ab05467016ab054e3100004.jpg","attachment/inforImage/20190513/1634424028e47b6ab05467016ab05467e70000.jpg","attachment/inforImage/20190513/1634454028e47b6ab05467016ab054740a0001.jpg","attachment/inforImage/20190513/1634514028e47b6ab05467016ab05489480002.jpg"],"informationList":[{"content":"新闻资讯2222222222","createTime":1557736571000,"coverPhoto":["attachment/inforImage/20190513/1636054028e47b6ab05467016ab055ab500007.jpg"],"name":"新闻资讯2"},{"content":"新闻资讯111111111111111","createTime":1557736545000,"coverPhoto":["attachment/inforImage/20190513/1635384028e47b6ab05467016ab05541990005.jpg"],"name":"新闻资讯"}]}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> wheelList;
        private List<InformationListBean> informationList;

        public List<String> getWheelList() {
            return wheelList;
        }

        public void setWheelList(List<String> wheelList) {
            this.wheelList = wheelList;
        }

        public List<InformationListBean> getInformationList() {
            return informationList;
        }

        public void setInformationList(List<InformationListBean> informationList) {
            this.informationList = informationList;
        }

        public static class InformationListBean implements Serializable {
            /**
             * content : 新闻资讯2222222222
             * createTime : 1557736571000
             * coverPhoto : ["attachment/inforImage/20190513/1636054028e47b6ab05467016ab055ab500007.jpg"]
             * name : 新闻资讯2
             */

            private String content;
            private long createTime;
            private String name;
            private List<String> coverPhoto;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getCoverPhoto() {
                return coverPhoto;
            }

            public void setCoverPhoto(List<String> coverPhoto) {
                this.coverPhoto = coverPhoto;
            }
        }
    }
}
