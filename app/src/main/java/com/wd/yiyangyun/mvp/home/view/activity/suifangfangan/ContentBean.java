package com.wd.yiyangyun.mvp.home.view.activity.suifangfangan;

import java.util.List;

public  class ContentBean {
    public List<SelectListBean> selectList;

    public static class SelectListBean {
        /**
         * typeName : 随访基准时间
         * typeDetailCode : 1
         * typeCode : followBaseDate
         * typeDetailName : 首诊日期
         */

        public String typeName;
        public String typeDetailCode;
        public String typeCode;
        public String typeDetailName;
    }
}
