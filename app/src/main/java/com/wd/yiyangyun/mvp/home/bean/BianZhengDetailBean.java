package com.wd.yiyangyun.mvp.home.bean;

import java.util.List;

public class BianZhengDetailBean {

    public List<FieldContentsListBean> fieldContentsList;


    public static class FieldContentsListBean {

        public String patientID;
        public String moduleCode;
        public long crfDate;
        public String scores;
        public String recordFlag;
        public String followRecordId;
        public String fieldID;
        public String contents;
    }
}
