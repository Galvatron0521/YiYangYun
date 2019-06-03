package com.wd.yiyangyun.mvp.mine.bean;

import java.util.List;

public class GroupListBean {

    /**
     * status : 0
     * data : {"List":[{"id":"4028807a6a47e659016a47e699060001","hospitalID":1,"groupName":"脑卒中1"},{"id":"4028807a6a47e659016a47e699060002","hospitalID":1,"groupName":"脑卒中2"}]}
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
             * id : 4028807a6a47e659016a47e699060001
             * hospitalID : 1
             * groupName : 脑卒中1
             */

            private String id;
            private int hospitalID;
            private String groupName;
            public boolean isCheck;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getHospitalID() {
                return hospitalID;
            }

            public void setHospitalID(int hospitalID) {
                this.hospitalID = hospitalID;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }
        }
    }
}
