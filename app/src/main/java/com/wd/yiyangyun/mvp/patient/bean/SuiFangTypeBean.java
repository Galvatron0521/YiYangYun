package com.wd.yiyangyun.mvp.patient.bean;

import java.util.List;

public class SuiFangTypeBean {
    public List<FollowVisitListBean> followVisitList;


    public static class FollowVisitListBean {

        public String typeDetailCode;
        public String typeCode;
        public String typeDetailName;

    }
}
