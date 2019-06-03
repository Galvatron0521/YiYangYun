package com.wd.yiyangyun.mvp.patient.bean;

import java.io.Serializable;
import java.util.List;

public class SelectGroupBean implements Serializable{

    private List<GroupListBean> groupList;

    public List<GroupListBean> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GroupListBean> groupList) {
        this.groupList = groupList;
    }

    public static class GroupListBean implements Serializable {
        /**
         * groupID : 4028801e6420461f01642048f6cc0003
         */

        private String groupID;

        public GroupListBean(String groupID) {
            this.groupID = groupID;
        }

        public String getGroupID() {
            return groupID;
        }

        public void setGroupID(String groupID) {
            this.groupID = groupID;
        }

        @Override
        public String toString() {
            return "FirstDiagnoseListBean{" +
                    "groupID='" + groupID + '\'' +
                    '}';
        }
    }
}
