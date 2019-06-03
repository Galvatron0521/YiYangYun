package com.wd.yiyangyun.mvp.home.bean;

import java.util.List;

public class BaoGaoInfoBean {
    public List<FileRecordAfterListBean> fileRecordAfterList;
    public List<FileRecordBeforeListBean> fileRecordBeforeList;
    public List<FileRecordModuleCodeListBean> fileRecordModuleCodeList;
    public String userName;


    public static class FileRecordModuleCodeListBean {

        public String moduleName;
        public String moduleCode;
        public List<ListBean> list;
    }

    public static class FileRecordAfterListBean {

        public String moduleName;
        public String moduleCode;
        public List<ListBean> list;


    }

    public static class FileRecordBeforeListBean {

        public String moduleName;
        public String moduleCode;
        public List<ListBean> list;

    }

    public static class ListBean {
        public String fieldType;
        public String fieldControlName;
        public String displayName;
        public String contents;
        public String suffixName;
    }
}
