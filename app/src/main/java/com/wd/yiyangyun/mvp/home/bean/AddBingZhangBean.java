package com.wd.yiyangyun.mvp.home.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chong.han on 2018/8/27.
 */

public class AddBingZhangBean implements Serializable{


    public List<FilterObjBean> filterObj;

    @Override
    public String toString() {
        return "AddBingZhangBean{" +
                "filterObj=" + filterObj +
                '}';
    }

    public static class FilterObjBean implements Serializable {

        public String moduleCode;
        public String fieldRecordID;
        public String scores;
        public List<FieldContentsListBean> fieldContentsList;

        public FilterObjBean(String moduleCode) {
            this.moduleCode = moduleCode;
        }

        @Override
        public String toString() {
            return "FilterObjBean{" +
                    "moduleCode='" + moduleCode + '\'' +
                    ", scores='" + scores + '\'' +
                    ", fieldContentsList=" + fieldContentsList +
                    '}';
        }

        public static class FieldContentsListBean implements Serializable {

            public String fieldID;
            public String contents;

            public FieldContentsListBean(String fieldID, String contents) {
                this.fieldID = fieldID;
                this.contents = contents;
            }

            @Override
            public String toString() {
                return "FieldContentsListBean{" +
                        "fieldID='" + fieldID + '\'' +
                        ", contents='" + contents + '\'' +
                        '}';
            }
        }
    }
}
