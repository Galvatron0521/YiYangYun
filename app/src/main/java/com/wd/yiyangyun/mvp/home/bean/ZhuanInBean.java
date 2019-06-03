package com.wd.yiyangyun.mvp.home.bean;

import java.util.List;

public class ZhuanInBean {


    /**
     * status : 0
     * data : {"list":[{"id":"4028e47b6adea8bd016adeae96db0009","fromHospitalName":"测试医院","fromDepartmentName":"消化科","fromUserName":"主治医生1","receiptState":0,"receiptTime":"","unReceiptTime":"","refuseReason":"","patientID":300,"createTime":1558514144000,"name":"检","age":55,"sex":"男","hospitalID":3},{"id":"4028e47b6adea8bd016adeb08389000a","fromHospitalName":"上海第二康复","fromDepartmentName":"心内科","fromUserName":"操作员2","receiptState":0,"receiptTime":"","unReceiptTime":"","refuseReason":"","patientID":302,"createTime":1558514271000,"name":"好的好的","age":22,"sex":"男","hospitalID":2},{"id":"4028e47b6adea8bd016adeb0ce8d000b","fromHospitalName":"上海第二康复","fromDepartmentName":"心内科","fromUserName":"操作员2","receiptState":0,"receiptTime":"","unReceiptTime":"","refuseReason":"","patientID":302,"createTime":1558514290000,"name":"好的好的","age":22,"sex":"男","hospitalID":2}]}
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
             * id : 4028e47b6adea8bd016adeae96db0009
             * fromHospitalName : 测试医院
             * fromDepartmentName : 消化科
             * fromUserName : 主治医生1
             * receiptState : 0
             * receiptTime :
             * unReceiptTime :
             * refuseReason :
             * patientID : 300
             * createTime : 1558514144000
             * name : 检
             * age : 55
             * sex : 男
             * hospitalID : 3
             */

            private String id;
            private String fromHospitalName;
            private String fromDepartmentName;
            private String fromUserName;
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

            public String getFromHospitalName() {
                return fromHospitalName;
            }

            public void setFromHospitalName(String fromHospitalName) {
                this.fromHospitalName = fromHospitalName;
            }

            public String getFromDepartmentName() {
                return fromDepartmentName;
            }

            public void setFromDepartmentName(String fromDepartmentName) {
                this.fromDepartmentName = fromDepartmentName;
            }

            public String getFromUserName() {
                return fromUserName;
            }

            public void setFromUserName(String fromUserName) {
                this.fromUserName = fromUserName;
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
