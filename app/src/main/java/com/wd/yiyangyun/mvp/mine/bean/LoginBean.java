package com.wd.yiyangyun.mvp.mine.bean;

public class LoginBean {

    /**
     * status : 0
     * data : {"user":{"UserID":1,"HospitalID":1,"LoginName":"admin","Name":"admin","Sex":1,"National":"1","Brithday":"2016-10-11","Mobile":"18854889207","IDCard":"","PhotoID":0,"PhotoUrl":"","Address":"","FileUrl":""}}
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
        /**
         * user : {"UserID":1,"HospitalID":1,"LoginName":"admin","Name":"admin","Sex":1,"National":"1","Brithday":"2016-10-11","Mobile":"18854889207","IDCard":"","PhotoID":0,"PhotoUrl":"","Address":"","FileUrl":""}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * UserID : 1
             * HospitalID : 1
             * LoginName : admin
             * Name : admin
             * Sex : 1
             * National : 1
             * Brithday : 2016-10-11
             * Mobile : 18854889207
             * IDCard :
             * PhotoID : 0
             * PhotoUrl :
             * Address :
             * FileUrl :
             */

            private int UserID;
            private int HospitalID;
            private int DepartmentID;
            private String LoginName;
            private String Name;
            private int Sex;
            private String National;
            private String Brithday;
            private String Mobile;
            private String IDCard;
            private int PhotoID;
            private String PhotoUrl;
            private String Address;
            private String FileUrl;

            public int getUserID() {
                return UserID;
            }

            public void setUserID(int UserID) {
                this.UserID = UserID;
            }

            public int getHospitalID() {
                return HospitalID;
            }

            public void setHospitalID(int HospitalID) {
                this.HospitalID = HospitalID;
            }

            public int getDepartmentID() {
                return HospitalID;
            }

            public void setDepartmentID(int HospitalID) {
                this.HospitalID = HospitalID;
            }


            public String getLoginName() {
                return LoginName;
            }

            public void setLoginName(String LoginName) {
                this.LoginName = LoginName;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getSex() {
                return Sex;
            }

            public void setSex(int Sex) {
                this.Sex = Sex;
            }

            public String getNational() {
                return National;
            }

            public void setNational(String National) {
                this.National = National;
            }

            public String getBrithday() {
                return Brithday;
            }

            public void setBrithday(String Brithday) {
                this.Brithday = Brithday;
            }

            public String getMobile() {
                return Mobile;
            }

            public void setMobile(String Mobile) {
                this.Mobile = Mobile;
            }

            public String getIDCard() {
                return IDCard;
            }

            public void setIDCard(String IDCard) {
                this.IDCard = IDCard;
            }

            public int getPhotoID() {
                return PhotoID;
            }

            public void setPhotoID(int PhotoID) {
                this.PhotoID = PhotoID;
            }

            public String getPhotoUrl() {
                return PhotoUrl;
            }

            public void setPhotoUrl(String PhotoUrl) {
                this.PhotoUrl = PhotoUrl;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String Address) {
                this.Address = Address;
            }

            public String getFileUrl() {
                return FileUrl;
            }

            public void setFileUrl(String FileUrl) {
                this.FileUrl = FileUrl;
            }
        }
    }
}
