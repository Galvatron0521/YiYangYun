package com.wd.yiyangyun.mvp.patient.bean;

import java.util.List;

public class BzImageBean {
    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * fieldRecordDate : 1548345600000
         * fieldRecordSign : 4028e47b6883bc180168842d4d380472
         * imagelist : []
         * recordFlag : 1
         */

        private String fieldRecordDate;
        private String fieldRecordSign;
        private String recordFlag;
        private List<?> imagelist;

        public String getFieldRecordDate() {
            return fieldRecordDate;
        }

        public void setFieldRecordDate(String fieldRecordDate) {
            this.fieldRecordDate = fieldRecordDate;
        }

        public String getFieldRecordSign() {
            return fieldRecordSign;
        }

        public void setFieldRecordSign(String fieldRecordSign) {
            this.fieldRecordSign = fieldRecordSign;
        }

        public String getRecordFlag() {
            return recordFlag;
        }

        public void setRecordFlag(String recordFlag) {
            this.recordFlag = recordFlag;
        }

        public List<?> getImagelist() {
            return imagelist;
        }

        public void setImagelist(List<?> imagelist) {
            this.imagelist = imagelist;
        }
    }
}
