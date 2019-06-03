package com.wd.yiyangyun.mvp.mine.bean;

public class CalendarBean {

    /**
     * status : 0
     * data : {"details":{"id":"4028e47b6ad2eff4016ad2eff4f90000","menzhenTime":1,"menzhenAddress":"门诊大厅1楼心内科12号诊室"}}
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
         * details : {"id":"4028e47b6ad2eff4016ad2eff4f90000","menzhenTime":1,"menzhenAddress":"门诊大厅1楼心内科12号诊室"}
         */

        private DetailsBean details;

        public DetailsBean getDetails() {
            return details;
        }

        public void setDetails(DetailsBean details) {
            this.details = details;
        }

        public static class DetailsBean {
            /**
             * id : 4028e47b6ad2eff4016ad2eff4f90000
             * menzhenTime : 1
             * menzhenAddress : 门诊大厅1楼心内科12号诊室
             */

            private String id;
            private int menzhenTime;
            private String menzhenAddress;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getMenzhenTime() {
                return menzhenTime;
            }

            public void setMenzhenTime(int menzhenTime) {
                this.menzhenTime = menzhenTime;
            }

            public String getMenzhenAddress() {
                return menzhenAddress;
            }

            public void setMenzhenAddress(String menzhenAddress) {
                this.menzhenAddress = menzhenAddress;
            }
        }
    }
}
