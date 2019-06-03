package com.wd.yiyangyun.mvp.home.view.activity.suifangfangan;

import java.util.List;

public class MyPlanCRFListBean {
    public int pageCount;
    public int pageNo;
    public List<CrflistBean> crflist;
    public List<ScaleListBean> scaleList;
    public List<AllListBean> allList;


    public static class CrflistBean {

        public String moduleName;
        public String moduleCode;
        public boolean isCheck;
        public String type;
        public List<CrfBean> crf;

        public CrflistBean(String moduleName, String moduleCode, boolean isCheck,String type) {
            this.moduleName = moduleName;
            this.moduleCode = moduleCode;
            this.isCheck = isCheck;
            this.type = type;
        }

        public static class CrfBean {

            public String id;
            public String moduleCode;
            public String displayName;
            public String fieldType;
            public int isNewline;
            public String scores;
            public String suffixName;
            public String fieldName;
            public String fieldControlName;
            public List<ChildrensBean> childrens;

            public static class ChildrensBean {

                public String id;
                public String moduleCode;
                public String displayName;
                public String fieldType;
                public int isNewline;
                public String scores;
                public String suffixName;
                public String fieldName;
                public String fieldControlName;
                public List<?> childrens;
            }
        }
    }
    public static class ScaleListBean {

        public String moduleName;
        public String moduleCode;
        public boolean isCheck;
        public List<ScaleBean> crf;


        public static class ScaleBean {

            public String id;
            public String moduleCode;
            public String displayName;
            public String fieldType;
            public int isNewline;
            public String scores;
            public String suffixName;
            public String fieldName;
            public String fieldControlName;
            public List<ChildrensBean> childrens;

            public static class ChildrensBean {

                public String id;
                public String moduleCode;
                public String displayName;
                public String fieldType;
                public int isNewline;
                public String scores;
                public String suffixName;
                public String fieldName;
                public String fieldControlName;
                public List<?> childrens;
            }
        }
    }
    public static class AllListBean {

        public String moduleName;
        public String moduleCode;
        public boolean isCheck;
        public List<ScaleBean> crf;

        public AllListBean(String moduleName, String moduleCode, boolean isCheck) {
            this.moduleName = moduleName;
            this.moduleCode = moduleCode;
            this.isCheck = isCheck;
        }

        public static class ScaleBean {

            public String id;
            public String moduleCode;
            public String displayName;
            public String fieldType;
            public int isNewline;
            public String scores;
            public String suffixName;
            public String fieldName;
            public String fieldControlName;
            public List<ChildrensBean> childrens;

            public static class ChildrensBean {

                public String id;
                public String moduleCode;
                public String displayName;
                public String fieldType;
                public int isNewline;
                public String scores;
                public String suffixName;
                public String fieldName;
                public String fieldControlName;
                public List<?> childrens;
            }
        }
    }
}
