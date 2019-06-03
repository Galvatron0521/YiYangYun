package com.wd.yiyangyun.mvp.home.bean;

import java.util.List;

public class VideoBean {

    /**
     * status : 0
     * data : {"resList":[{"fileId":97,"fileType":1,"uploadMsg":"UPLOAD_SUCCESS","uploadStatus":0,"fileName":"115144903.png","fileUrl":"/skyapp_ndpt/attachment/pic/20190515/115144903.png"}]}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ResListBean> resList;

        public List<ResListBean> getResList() {
            return resList;
        }

        public void setResList(List<ResListBean> resList) {
            this.resList = resList;
        }

        public static class ResListBean {
            /**
             * fileId : 97
             * fileType : 1
             * uploadMsg : UPLOAD_SUCCESS
             * uploadStatus : 0
             * fileName : 115144903.png
             * fileUrl : /skyapp_ndpt/attachment/pic/20190515/115144903.png
             */

            private int fileId;
            private int fileType;
            private String uploadMsg;
            private int uploadStatus;
            private String fileName;
            private String fileUrl;

            public int getFileId() {
                return fileId;
            }

            public void setFileId(int fileId) {
                this.fileId = fileId;
            }

            public int getFileType() {
                return fileType;
            }

            public void setFileType(int fileType) {
                this.fileType = fileType;
            }

            public String getUploadMsg() {
                return uploadMsg;
            }

            public void setUploadMsg(String uploadMsg) {
                this.uploadMsg = uploadMsg;
            }

            public int getUploadStatus() {
                return uploadStatus;
            }

            public void setUploadStatus(int uploadStatus) {
                this.uploadStatus = uploadStatus;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public String getFileUrl() {
                return fileUrl;
            }

            public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
            }
        }
    }
}
