package com.wd.yiyangyun.mvp.home.bean;

import java.util.List;

public class ZhuanZhenInfoBean {


    /**
     * status : 0
     * data : {"list":[{"id":"4028e47b6ad96f0f016ad9c58ae3001a","fromHospitalName":"神康云","fromDepartmentName":"神经科","fromUserName":"admin","toHospitalName":"测试医院","toDepartmentName":"消化科","toUserName":"主治医生1","receiptState":0,"receiptTime":"","unReceiptTime":"","createTime":1558431763000,"refuseReason":""}]}
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
             * id : 4028e47b6ad96f0f016ad9c58ae3001a
             * fromHospitalName : 神康云
             * fromDepartmentName : 神经科
             * fromUserName : admin
             * toHospitalName : 测试医院
             * toDepartmentName : 消化科
             * toUserName : 主治医生1
             * receiptState : 0
             * receiptTime :
             * unReceiptTime :
             * createTime : 1558431763000
             * refuseReason :
             */

            private String id;
            private String fromHospitalName;
            private String fromDepartmentName;
            private String fromUserName;
            private String toHospitalName;
            private String toDepartmentName;
            private String toUserName;
            private int receiptState;
            private String receiptTime;
            private String unReceiptTime;
            private long createTime;
            private String refuseReason;

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

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getRefuseReason() {
                return refuseReason;
            }

            public void setRefuseReason(String refuseReason) {
                this.refuseReason = refuseReason;
            }
        }
    }
}
