package com.wd.yiyangyun.mvp.patient.bean;

import java.util.List;

public class BingchengListBean {
    /**
     * status : 0
     * data : {"firstDiagnoseList":[{"id":"4028801e6420461f01642048f6cc0003","groupName":"冠心病","num":1},{"id":"4028801e64205fce0164207e24e30009","groupName":"高血压","num":1},{"id":"4028801e6420fa89016420fa89d30000","groupName":"高脂血症","num":1},{"id":"4028801e6420fa89016421027a090002","groupName":"扩张性心肌病","num":0},{"id":"4028801e6420fa890164210a5e410003","groupName":"心脏瓣膜病","num":0},{"id":"4028801e6420fa890164210b90530004","groupName":"心律失常","num":0},{"id":"4028e47b67e854780167e85478840000","groupName":"2型糖尿病","num":0},{"id":"4028e47b68e0a8020168e0a8026d0000","groupName":"周围血管病","num":0},{"id":"4028e47b68e0a8020168e1a7d3400011","groupName":"心功能不全","num":0},{"id":"4028e47b68e0a8020168e1f586d90030","groupName":"糖尿病前期","num":0},{"id":"4028e47b68e0a8020168e1f76abb0034","groupName":"糖尿病并发症","num":0},{"id":"4028e47b68e0a8020168e207d787003e","groupName":"脑血管病","num":0},{"id":"4028e47b68e0a8020168e20a311f0043","groupName":"慢性肾功能不全","num":0}]}
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
        private List<FirstDiagnoseListBean> firstDiagnoseList;
        public List<FirstDiagnoseListBean2> firstDiagnoseList2;

        public List<FirstDiagnoseListBean> getFirstDiagnoseList() {
            return firstDiagnoseList;
        }

        public void setFirstDiagnoseList(List<FirstDiagnoseListBean> firstDiagnoseList) {
            this.firstDiagnoseList = firstDiagnoseList;
        }

        public static class FirstDiagnoseListBean {
            /**
             * id : 4028801e6420461f01642048f6cc0003
             * groupName : 冠心病
             * num : 1
             */

            public String id;
            public String groupName;
            public int num;
            public boolean isCheck;

            public FirstDiagnoseListBean(String groupID) {
                this.id = groupID;
            }

            @Override
            public String toString() {
                return "FirstDiagnoseListBean{" +
                        "groupID='" + id + '\'' +
                        '}';
            }
        }

        public static class FirstDiagnoseListBean2 {
            /**
             * id : 4028801e6420461f01642048f6cc0003
             * groupName : 冠心病
             * num : 1
             */

            public String groupID;

            public FirstDiagnoseListBean2(String groupID) {
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
}
