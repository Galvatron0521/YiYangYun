package com.wd.yiyangyun.mvp.mine.bean;

import com.wd.yiyangyun.mvp.home.bean.BianZhengBean;
import com.wd.yiyangyun.mvp.home.bean.BianZhengNewBean;

import java.util.List;

public class SuiFangCrfBean {
    public String optionTag;
    public List<BianZhengNewBean.DataBean.ModuleListBean.FildlistBean> fildlist;
    public List<FieldContentsListBean> fieldContentsList;

    public static class FieldContentsListBean {

        public int fieldRecordID;
        public int patientID;
        public String moduleCode;
        public long crfDate;
        public String scores;
        public String recordFlag;
        public int followRecordId;
        public String fieldID;
        public String contents;
    }
}
