package com.wd.yiyangyun.mvp.home.bean;

import java.util.List;

public class HospitalBean {

    /**
     * status : 0
     * data : {"list":[{"HospitalID":1,"HospitalName":"神康云"},{"HospitalID":2,"HospitalName":"上海第二康复"},{"HospitalID":3,"HospitalName":"测试医院"},{"HospitalID":4,"HospitalName":"泰安市中心医院"},{"HospitalID":5,"HospitalName":"宁阳第一人民医院"},{"HospitalID":6,"HospitalName":"东平县中医院"},{"HospitalID":7,"HospitalName":"东平县第一人民医院"},{"HospitalID":8,"HospitalName":"东平县人民医院"},{"HospitalID":9,"HospitalName":"颐博康复医院"},{"HospitalID":10,"HospitalName":"翟镇中心卫生院"},{"HospitalID":11,"HospitalName":"新泰市人民医院"},{"HospitalID":12,"HospitalName":"新泰市中医医院"},{"HospitalID":13,"HospitalName":"新泰市第二人民医院"},{"HospitalID":14,"HospitalName":"新泰市第三人民医院"},{"HospitalID":15,"HospitalName":"宁阳县中医院"},{"HospitalID":16,"HospitalName":"泰安市大汶口卫生院"},{"HospitalID":17,"HospitalName":"东平县第三人民医院"},{"HospitalID":18,"HospitalName":"宁阳县人民医院"},{"HospitalID":19,"HospitalName":"宁阳康复颐养中心"},{"HospitalID":20,"HospitalName":"泰安中心医院分院"},{"HospitalID":21,"HospitalName":"泰安市泰山区人民医院"},{"HospitalID":22,"HospitalName":"泰安市中医二院"},{"HospitalID":23,"HospitalName":"泰安市岱岳区徂徕镇卫生院"}]}
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
             * HospitalID : 1
             * HospitalName : 神康云
             */

            private int HospitalID;
            private String HospitalName;

            public int getHospitalID() {
                return HospitalID;
            }

            public void setHospitalID(int HospitalID) {
                this.HospitalID = HospitalID;
            }

            public String getHospitalName() {
                return HospitalName;
            }

            public void setHospitalName(String HospitalName) {
                this.HospitalName = HospitalName;
            }
        }
    }
}
