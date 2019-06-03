package com.wd.yiyangyun.mvp.home.bean;

import java.util.List;

public class DoctorBean {

    /**
     * status : 0
     * data : {"list":[{"UserID":2,"Name":"主治医生1","HospitalID":3,"DepartmentID":3},{"UserID":4,"Name":"操作员1","HospitalID":3,"DepartmentID":3}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * UserID : 2
             * Name : 主治医生1
             * HospitalID : 3
             * DepartmentID : 3
             */

            private int UserID;
            private String Name;
            private int HospitalID;
            private int DepartmentID;

            public int getUserID() {
                return UserID;
            }

            public void setUserID(int UserID) {
                this.UserID = UserID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getHospitalID() {
                return HospitalID;
            }

            public void setHospitalID(int HospitalID) {
                this.HospitalID = HospitalID;
            }

            public int getDepartmentID() {
                return DepartmentID;
            }

            public void setDepartmentID(int DepartmentID) {
                this.DepartmentID = DepartmentID;
            }
        }
    }
}
