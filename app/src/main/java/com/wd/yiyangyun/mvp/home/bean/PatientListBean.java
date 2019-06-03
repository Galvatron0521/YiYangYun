package com.wd.yiyangyun.mvp.home.bean;

import java.io.Serializable;
import java.util.List;

public class PatientListBean implements Serializable {


    public int pageCount;
    public int totalCount;
    public int pageNo;
    public List<ListBean> list;
    public List<FirstDiagnoseListBean> firstDiagnoseListBeans;


    public static class ListBean implements Serializable {


        public String id;
        public String num;
        public String name;
        public String age;
        public String brithday;
        public String sex;
        public String mobile;
        public String address;
        public String idCard;
        public String recordFlag;
        public String isFocus;
        public long createTime;
        public List<?> lablelist;
        public List<FirstDiagnoseListBean> firstDiagnoseListBeans;


    }

    public static class FirstDiagnoseListBean implements Serializable {
        public String id;
        public String groupName;
        public String num;
    }

}
