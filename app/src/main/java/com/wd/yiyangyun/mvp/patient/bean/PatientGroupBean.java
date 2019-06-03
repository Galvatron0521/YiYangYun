package com.wd.yiyangyun.mvp.patient.bean;

import java.util.List;

public class PatientGroupBean {


    /**
     * status : 0
     * data : {"groupList":[{"id":1,"groupName":"重点关注","num":4,"patientList":[{"id":304,"num":"6374774","name":"也如","age":22,"brithday":"1997-01-29","sex":"男","mobile":"18612106931","address":"河南","idCard":"410923199701292415","createTime":1557296811000,"mobile2":"","openid":"","isFocus":1},{"id":303,"num":"132831","name":"好的好的","age":25,"brithday":"1994-07-29","sex":"男","mobile":"18612125893","address":"河南","idCard":"410923199407292419","createTime":1557288396000,"mobile2":"","openid":"","isFocus":1},{"id":302,"num":"","name":"好的好的","age":22,"brithday":"1997-01-29","sex":"男","mobile":"18612106933","address":"河南","idCard":"410923199701292415","createTime":1557279332000,"mobile2":"","openid":"","isFocus":1},{"id":301,"num":"1231233","name":"666","age":66,"brithday":"2018-08-08","sex":"男","mobile":"18880868898","address":"","idCard":"","createTime":1557278333000,"mobile2":"","openid":"","isFocus":1}]},{"id":0,"groupName":"未分组","num":0,"patientList":[]},{"id":"4028e47b6a9514db016a958c61750004","groupName":"脑卒中","num":2,"patientList":[]},{"id":"4028e47b6a9514db016a958cac4a0005","groupName":"脑血管","num":0,"patientList":[]},{"id":"4028e47b6a9514db016a958cda3c0006","groupName":"脑哽塞","num":2,"patientList":[]}]}
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
        private List<GroupListBean> groupList;

        public List<GroupListBean> getGroupList() {
            return groupList;
        }

        public void setGroupList(List<GroupListBean> groupList) {
            this.groupList = groupList;
        }

        public static class GroupListBean {
            /**
             * id : 1
             * groupName : 重点关注
             * num : 4
             * patientList : [{"id":304,"num":"6374774","name":"也如","age":22,"brithday":"1997-01-29","sex":"男","mobile":"18612106931","address":"河南","idCard":"410923199701292415","createTime":1557296811000,"mobile2":"","openid":"","isFocus":1},{"id":303,"num":"132831","name":"好的好的","age":25,"brithday":"1994-07-29","sex":"男","mobile":"18612125893","address":"河南","idCard":"410923199407292419","createTime":1557288396000,"mobile2":"","openid":"","isFocus":1},{"id":302,"num":"","name":"好的好的","age":22,"brithday":"1997-01-29","sex":"男","mobile":"18612106933","address":"河南","idCard":"410923199701292415","createTime":1557279332000,"mobile2":"","openid":"","isFocus":1},{"id":301,"num":"1231233","name":"666","age":66,"brithday":"2018-08-08","sex":"男","mobile":"18880868898","address":"","idCard":"","createTime":1557278333000,"mobile2":"","openid":"","isFocus":1}]
             */

            private String id;
            private String groupName;
            private int num;
            private List<PatientListBean> patientList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public List<PatientListBean> getPatientList() {
                return patientList;
            }

            public void setPatientList(List<PatientListBean> patientList) {
                this.patientList = patientList;
            }

            public static class PatientListBean {
                /**
                 * id : 304
                 * num : 6374774
                 * name : 也如
                 * age : 22
                 * brithday : 1997-01-29
                 * sex : 男
                 * mobile : 18612106931
                 * address : 河南
                 * idCard : 410923199701292415
                 * createTime : 1557296811000
                 * mobile2 :
                 * openid :
                 * isFocus : 1
                 */

                private int id;
                private String num;
                private String name;
                private int age;
                private String brithday;
                private String sex;
                private String mobile;
                private String address;
                private String idCard;
                private long createTime;
                private String mobile2;
                private String openid;
                private int isFocus;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getAge() {
                    return age;
                }

                public void setAge(int age) {
                    this.age = age;
                }

                public String getBrithday() {
                    return brithday;
                }

                public void setBrithday(String brithday) {
                    this.brithday = brithday;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public String getIdCard() {
                    return idCard;
                }

                public void setIdCard(String idCard) {
                    this.idCard = idCard;
                }

                public long getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(long createTime) {
                    this.createTime = createTime;
                }

                public String getMobile2() {
                    return mobile2;
                }

                public void setMobile2(String mobile2) {
                    this.mobile2 = mobile2;
                }

                public String getOpenid() {
                    return openid;
                }

                public void setOpenid(String openid) {
                    this.openid = openid;
                }

                public int getIsFocus() {
                    return isFocus;
                }

                public void setIsFocus(int isFocus) {
                    this.isFocus = isFocus;
                }
            }
        }
    }
}
