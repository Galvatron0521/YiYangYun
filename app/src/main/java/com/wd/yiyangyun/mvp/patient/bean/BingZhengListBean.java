package com.wd.yiyangyun.mvp.patient.bean;

import java.util.List;

public class BingZhengListBean {
    public int totalCount;
    public List<ListBean> list;

    public static class ListBean {

        public String fieldRecordSign;
        public String fieldRecordDate;
        public String recordFlag;
        public List<ImageList> imagelist;
    }

    public static class ImageList {

        public String fileUrl;
        public String patientimageID;

    }
}
