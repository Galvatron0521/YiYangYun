package com.wd.yiyangyun.mvp.home.bean;

import java.util.List;

public class VideoListBean {

    /**
     * status : 0
     * data : {"List":[{"id":"4028e47b6abaf9d3016abf3e8928000c","imageName":"","imageDescribe":"","fileUrl":"attachment/pic/20190515/140506584.jpg","fileType":0,"userID":1,"createTime":1557986707000,"name":"admin"},{"id":"4028e47b6abaf9d3016abf37bb8f000b","imageName":"","imageDescribe":"","fileUrl":"attachment/pic/20190515/13574041.jpg","fileType":0,"userID":1,"createTime":1557986261000,"name":"admin"},{"id":"4028e47b6abaf9d3016abf37bb7f000a","imageName":"","imageDescribe":"","fileUrl":"attachment/pic/20190515/135740931.jpg","fileType":0,"userID":1,"createTime":1557986261000,"name":"admin"},{"id":"4028e47b6abaf9d3016abee891d60009","imageName":"","imageDescribe":"","fileUrl":"attachment/pic/20190515/123112588.jpg","fileType":0,"userID":1,"createTime":1557981073000,"name":"admin"},{"id":"4028e47b6abaf9d3016abee8869f0008","imageName":"","imageDescribe":"","fileUrl":"attachment/pic/20190515/123109114.jpg","fileType":0,"userID":1,"createTime":1557981071000,"name":"admin"},{"id":"4028e47b6abaf9d3016abec46d020007","imageName":"","imageDescribe":"","fileUrl":"attachment/pic/20190515/115144903.png","fileType":0,"userID":1,"createTime":1557978705000,"name":"admin"}]}
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
        private java.util.List<ListBean> List;

        public List<ListBean> getList() {
            return List;
        }

        public void setList(List<ListBean> List) {
            this.List = List;
        }

        public static class ListBean {
            /**
             * id : 4028e47b6abaf9d3016abf3e8928000c
             * imageName :
             * imageDescribe :
             * fileUrl : attachment/pic/20190515/140506584.jpg
             * fileType : 0
             * userID : 1
             * createTime : 1557986707000
             * name : admin
             */

            private String id;
            private String imageName;
            private String imageDescribe;
            private String fileUrl;
            private int fileType;
            private int userID;
            private long createTime;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImageName() {
                return imageName;
            }

            public void setImageName(String imageName) {
                this.imageName = imageName;
            }

            public String getImageDescribe() {
                return imageDescribe;
            }

            public void setImageDescribe(String imageDescribe) {
                this.imageDescribe = imageDescribe;
            }

            public String getFileUrl() {
                return fileUrl;
            }

            public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
            }

            public int getFileType() {
                return fileType;
            }

            public void setFileType(int fileType) {
                this.fileType = fileType;
            }

            public int getUserID() {
                return userID;
            }

            public void setUserID(int userID) {
                this.userID = userID;
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
        }
    }
}
