package com.wd.yiyangyun.mvp.mine.bean;

public class QueryTollBean {

    /**
     * status : 0
     * data : {"List":[{"id":"4028e47b69c89ee90169cc5c7dc6001e","userID":1,"chargeAmout":"10","messageCount":20}]}
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

        public java.util.List<ListBean> getList() {
            return List;
        }

        public void setList(java.util.List<ListBean> List) {
            this.List = List;
        }

        public static class ListBean {
            /**
             * id : 4028e47b69c89ee90169cc5c7dc6001e
             * userID : 1
             * chargeAmout : 10
             * messageCount : 20
             */

            private String id;
            private int userID;
            private String chargeAmout;
            private int messageCount;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getUserID() {
                return userID;
            }

            public void setUserID(int userID) {
                this.userID = userID;
            }

            public String getChargeAmout() {
                return chargeAmout;
            }

            public void setChargeAmout(String chargeAmout) {
                this.chargeAmout = chargeAmout;
            }

            public int getMessageCount() {
                return messageCount;
            }

            public void setMessageCount(int messageCount) {
                this.messageCount = messageCount;
            }
        }
    }
}
