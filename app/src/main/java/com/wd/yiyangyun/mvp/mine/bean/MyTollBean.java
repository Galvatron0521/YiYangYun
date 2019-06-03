package com.wd.yiyangyun.mvp.mine.bean;

import java.util.List;

public class MyTollBean {
    /**
     * status : 0
     * data : {"allTotal":0.03,"List":[{"orderID":"1554353041406","patientName":"测试聊天","id":"o8xCbPiFZMwN2wLxN4JM4BIbEinJkV2e","payItemName":"短信咨询","timeEnd":"20190404124141","totalFee":"1"},{"orderID":"1554352595202","patientName":"测试聊天","id":"PR5ZOxf9laBKWXtoYFuiMi4OOivGEXGb","payItemName":"短信咨询","timeEnd":"20190404123415","totalFee":"1"},{"orderID":"1554352925189","patientName":"测试聊天","id":"Te9HyWtGRuwYKAFcFcc5YsnNH36F7gIQ","payItemName":"短信咨询","timeEnd":"20190404123944","totalFee":"1"}]}
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
         * allTotal : 0.03
         * List : [{"orderID":"1554353041406","patientName":"测试聊天","id":"o8xCbPiFZMwN2wLxN4JM4BIbEinJkV2e","payItemName":"短信咨询","timeEnd":"20190404124141","totalFee":"1"},{"orderID":"1554352595202","patientName":"测试聊天","id":"PR5ZOxf9laBKWXtoYFuiMi4OOivGEXGb","payItemName":"短信咨询","timeEnd":"20190404123415","totalFee":"1"},{"orderID":"1554352925189","patientName":"测试聊天","id":"Te9HyWtGRuwYKAFcFcc5YsnNH36F7gIQ","payItemName":"短信咨询","timeEnd":"20190404123944","totalFee":"1"}]
         */

        private double allTotal;
        private java.util.List<ListBean> List;

        public double getAllTotal() {
            return allTotal;
        }

        public void setAllTotal(double allTotal) {
            this.allTotal = allTotal;
        }

        public java.util.List<ListBean> getList() {
            return List;
        }

        public void setList(List<ListBean> List) {
            this.List = List;
        }

        public static class ListBean {
            /**
             * orderID : 1554353041406
             * patientName : 测试聊天
             * id : o8xCbPiFZMwN2wLxN4JM4BIbEinJkV2e
             * payItemName : 短信咨询
             * timeEnd : 20190404124141
             * totalFee : 1
             */

            private String orderID;
            private String patientName;
            private String id;
            private String payItemName;
            private String timeEnd;
            private String totalFee;

            public String getOrderID() {
                return orderID;
            }

            public void setOrderID(String orderID) {
                this.orderID = orderID;
            }

            public String getPatientName() {
                return patientName;
            }

            public void setPatientName(String patientName) {
                this.patientName = patientName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPayItemName() {
                return payItemName;
            }

            public void setPayItemName(String payItemName) {
                this.payItemName = payItemName;
            }

            public String getTimeEnd() {
                return timeEnd;
            }

            public void setTimeEnd(String timeEnd) {
                this.timeEnd = timeEnd;
            }

            public String getTotalFee() {
                return totalFee;
            }

            public void setTotalFee(String totalFee) {
                this.totalFee = totalFee;
            }
        }
    }
}
