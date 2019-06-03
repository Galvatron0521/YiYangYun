package com.wd.yiyangyun.mvp.home.bean;

import java.util.List;

public class ZhuanOutBean {


    /**
     * status : 0
     * data : {"list":[{"id":"4028e47b6ad96f0f016ad96f0fbc0000","toHospitalName":"测试医院","toDepartmentName":"消化科","toUserName":"主治医生1","receiptState":0,"receiptTime":"","unReceiptTime":"","refuseReason":"","patientID":308,"createTime":1558426095000,"name":"测试患者002","age":31,"sex":"男","hospitalID":1},{"id":"4028e47b6ad96f0f016ad977e7c30001","toHospitalName":"测试医院","toDepartmentName":"消化科","toUserName":"主治医生1","receiptState":0,"receiptTime":"","unReceiptTime":"","refuseReason":"","patientID":308,"createTime":1558426675000,"name":"测试患者002","age":31,"sex":"男","hospitalID":1},{"id":"4028e47b6ad96f0f016ad99eef840016","toHospitalName":"测试医院","toDepartmentName":"消化科","toUserName":"主治医生1","receiptState":0,"receiptTime":"","unReceiptTime":"","refuseReason":"","patientID":312,"createTime":1558429233000,"name":"黑腹","age":33,"sex":"女","hospitalID":1},{"id":"4028e47b6ad96f0f016ad9a26cae0017","toHospitalName":"测试医院","toDepartmentName":"消化科","toUserName":"主治医生1","receiptState":0,"receiptTime":"","unReceiptTime":"","refuseReason":"","patientID":311,"createTime":1558429461000,"name":"舒服的","age":25,"sex":"女","hospitalID":1},{"id":"4028e47b6ad96f0f016ad9a583870018","toHospitalName":"测试医院","toDepartmentName":"消化科","toUserName":"主治医生1","receiptState":0,"receiptTime":"","unReceiptTime":"","refuseReason":"","patientID":312,"createTime":1558429664000,"name":"黑腹","age":33,"sex":"女","hospitalID":1},{"id":"4028e47b6ad96f0f016ad9a5b7e50019","toHospitalName":"测试医院","toDepartmentName":"消化科","toUserName":"主治医生1","receiptState":0,"receiptTime":"","unReceiptTime":"","refuseReason":"","patientID":312,"createTime":1558429677000,"name":"黑腹","age":33,"sex":"女","hospitalID":1},{"id":"4028e47b6ad96f0f016ad9c58ae3001a","toHospitalName":"测试医院","toDepartmentName":"消化科","toUserName":"主治医生1","receiptState":0,"receiptTime":"","unReceiptTime":"","refuseReason":"","patientID":309,"createTime":1558431763000,"name":"测试患者003","age":31,"sex":"男","hospitalID":1},{"id":"4028e47b6add62a0016ade8b20490047","toHospitalName":"测试医院","toDepartmentName":"消化科","toUserName":"主治医生1","receiptState":0,"receiptTime":"","unReceiptTime":"","refuseReason":"","patientID":312,"createTime":1558511820000,"name":"黑腹","age":33,"sex":"女","hospitalID":1}]}
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
             * id : 4028e47b6ad96f0f016ad96f0fbc0000
             * toHospitalName : 测试医院
             * toDepartmentName : 消化科
             * toUserName : 主治医生1
             * receiptState : 0
             * receiptTime :
             * unReceiptTime :
             * refuseReason :
             * patientID : 308
             * createTime : 1558426095000
             * name : 测试患者002
             * age : 31
             * sex : 男
             * hospitalID : 1
             */

            private String id;
            private String toHospitalName;
            private String toDepartmentName;
            private String toUserName;
            private int receiptState;
            private String receiptTime;
            private String unReceiptTime;
            private String refuseReason;
            private int patientID;
            private long createTime;
            private String name;
            private int age;
            private String sex;
            private int hospitalID;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getToHospitalName() {
                return toHospitalName;
            }

            public void setToHospitalName(String toHospitalName) {
                this.toHospitalName = toHospitalName;
            }

            public String getToDepartmentName() {
                return toDepartmentName;
            }

            public void setToDepartmentName(String toDepartmentName) {
                this.toDepartmentName = toDepartmentName;
            }

            public String getToUserName() {
                return toUserName;
            }

            public void setToUserName(String toUserName) {
                this.toUserName = toUserName;
            }

            public int getReceiptState() {
                return receiptState;
            }

            public void setReceiptState(int receiptState) {
                this.receiptState = receiptState;
            }

            public String getReceiptTime() {
                return receiptTime;
            }

            public void setReceiptTime(String receiptTime) {
                this.receiptTime = receiptTime;
            }

            public String getUnReceiptTime() {
                return unReceiptTime;
            }

            public void setUnReceiptTime(String unReceiptTime) {
                this.unReceiptTime = unReceiptTime;
            }

            public String getRefuseReason() {
                return refuseReason;
            }

            public void setRefuseReason(String refuseReason) {
                this.refuseReason = refuseReason;
            }

            public int getPatientID() {
                return patientID;
            }

            public void setPatientID(int patientID) {
                this.patientID = patientID;
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

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getHospitalID() {
                return hospitalID;
            }

            public void setHospitalID(int hospitalID) {
                this.hospitalID = hospitalID;
            }
        }
    }
}
