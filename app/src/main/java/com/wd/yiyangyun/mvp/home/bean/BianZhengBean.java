package com.wd.yiyangyun.mvp.home.bean;

import java.util.List;

public class BianZhengBean{

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
        private List<ModuleListBean> moduleList;
        private List<RelationListBean> relationList;

        public List<ModuleListBean> getModuleList() {
            return moduleList;
        }

        public void setModuleList(List<ModuleListBean> moduleList) {
            this.moduleList = moduleList;
        }

        public List<RelationListBean> getRelationList() {
            return relationList;
        }

        public void setRelationList(List<RelationListBean> relationList) {
            this.relationList = relationList;
        }

        public static class ModuleListBean {

            private ParentModuleBean parentModule;
            private List<ChildlistBean> childlist;

            public ParentModuleBean getParentModule() {
                return parentModule;
            }

            public void setParentModule(ParentModuleBean parentModule) {
                this.parentModule = parentModule;
            }

            public List<ChildlistBean> getChildlist() {
                return childlist;
            }

            public void setChildlist(List<ChildlistBean> childlist) {
                this.childlist = childlist;
            }

            public static class ParentModuleBean {

                public boolean isSelect;
                private String moduleName;
                private String moduleCode;
                private String parentModule;
                private int orderNum;
                private String recordFlag;
                private int isHaveChild;

                public String getModuleName() {
                    return moduleName;
                }

                public void setModuleName(String moduleName) {
                    this.moduleName = moduleName;
                }

                public String getModuleCode() {
                    return moduleCode;
                }

                public void setModuleCode(String moduleCode) {
                    this.moduleCode = moduleCode;
                }

                public String getParentModule() {
                    return parentModule;
                }

                public void setParentModule(String parentModule) {
                    this.parentModule = parentModule;
                }

                public int getOrderNum() {
                    return orderNum;
                }

                public void setOrderNum(int orderNum) {
                    this.orderNum = orderNum;
                }

                public String getRecordFlag() {
                    return recordFlag;
                }

                public void setRecordFlag(String recordFlag) {
                    this.recordFlag = recordFlag;
                }

                public int getIsHaveChild() {
                    return isHaveChild;
                }

                public void setIsHaveChild(int isHaveChild) {
                    this.isHaveChild = isHaveChild;
                }
            }

            public static class ChildlistBean {
                /**
                 * tagModule : {"moduleName":"西医诊断","moduleCode":"SP020145","parentModule":"SP13","orderNum":1,"recordFlag":"1","isHaveChild":1}
                 * fildlist : [{"id":"4028801e6420461f016420474c2d0001","moduleCode":"SP020145","displayName":"主要诊断","fieldType":"title","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断","fieldControlName":"","childrens":[{"id":"4028801e6420461f0164204852100002","moduleCode":"SP020145","displayName":"主要诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断","fieldControlName":"主要诊断","childrens":[{"id":"4028801e6420461f01642048f6cc0003","moduleCode":"SP020145","displayName":"冠心病","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028801e64205fce01642068f4590001","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断","fieldControlName":"主要诊断_主要诊断_冠心病","childrens":[{"id":"4028801e64205fce0164206a02dc0003","moduleCode":"SP020145","displayName":"稳定性心绞痛","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_稳定性心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206c59960008","moduleCode":"SP020145","displayName":"缺血性心肌病","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_缺血性心肌病","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce01642069aca40002","moduleCode":"SP020145","displayName":"不稳定性心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_不稳定性心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19d4a520001","moduleCode":"SP020145","displayName":"初发劳力型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_初发劳力型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19db2400002","moduleCode":"SP020145","displayName":"恶化劳力型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_恶化劳力型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19e15950003","moduleCode":"SP020145","displayName":"自发型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_自发型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19e72050004","moduleCode":"SP020145","displayName":"变异型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_变异型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19edba80005","moduleCode":"SP020145","displayName":"卧位型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_卧位型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19f1dcc0006","moduleCode":"SP020145","displayName":"其他型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_其他型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1aeb74b001b","moduleCode":"SP020145","displayName":"CCS I级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CCS I级","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1af107f001c","moduleCode":"SP020145","displayName":"CCS II级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CCS II级","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1af5613001d","moduleCode":"SP020145","displayName":"CCS III级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CCS III级","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1af890c001e","moduleCode":"SP020145","displayName":"CCS IV级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CCS IV级","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206a528b0004","moduleCode":"SP020145","displayName":"PCI术后","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_PCI术后","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b67e337e50167e3585deb0045","moduleCode":"SP020145","displayName":"CABG术后","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CABG术后","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206ab8250005","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（未知部位）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（未知部位）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a490c1000a","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（前壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（前壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a4f05f000b","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（前间壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（前间壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a53c25000c","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（下壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（下壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a58825000d","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（后壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（后壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a60245000e","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（侧壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（侧壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a64c65000f","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（高侧壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（高侧壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a6a5920010","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（右室）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（右室）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19fa7400007","moduleCode":"SP020145","displayName":"STEMI","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_STEMI","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19ffbb40008","moduleCode":"SP020145","displayName":"NSTEMI","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_NSTEMI","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206b341b0006","moduleCode":"SP020145","displayName":"室壁瘤","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_室壁瘤","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206bb6d80007","moduleCode":"SP020145","displayName":"附壁血栓","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_附壁血栓","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]}]}]},{"id":"4028e47b68e0a8020168e1a7d3400011","moduleCode":"SP020145","displayName":"心功能不全","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e1a898290012","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_亚诊断","fieldControlName":"主要诊断_主要诊断_心功能不全","childrens":[{"id":"4028807a6660bf8d016660c938d80003","moduleCode":"SP020145","displayName":"NYHA I级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_亚诊断_NYHA I级","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028807a6660bf8d016660c938db0004","moduleCode":"SP020145","displayName":"NYHA II级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_亚诊断_NYHA II级","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028807a6660bf8d016660c938dd0005","moduleCode":"SP020145","displayName":"NYHA III级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_亚诊断_NYHA III级","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028807a6660bf8d016660c938e00006","moduleCode":"SP020145","displayName":"NYHA IV 级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_亚诊断_NYHA IV 级","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a96e220013","moduleCode":"SP020145","displayName":"左心衰","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_心功能不全_左心衰","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a9db790014","moduleCode":"SP020145","displayName":"右心衰","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_心功能不全_右心衰","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1aa27e70015","moduleCode":"SP020145","displayName":"全心衰","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_心功能不全_全心衰","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1ac655e0016","moduleCode":"SP020145","displayName":"HFrEF","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_心功能不全_HFrEF","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1acaf3f0017","moduleCode":"SP020145","displayName":"HFmrEF","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_心功能不全_HFmrEF","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1acfab90018","moduleCode":"SP020145","displayName":"HFpEF","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_心功能不全_HFpEF","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]}]}]},{"id":"4028801e6420fa890164210b90530004","moduleCode":"SP020145","displayName":"心律失常","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028801e6420fa89016421258bff0011","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断","fieldControlName":"主要诊断_主要诊断_心律失常","childrens":[{"id":"4028801e6420fa8901642125bd1d0012","moduleCode":"SP020145","displayName":"室性期前收缩","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_室性期前收缩","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642125ee2b0013","moduleCode":"SP020145","displayName":"房性期前收缩","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_房性期前收缩","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028801e6420fa890164212628590014","moduleCode":"SP020145","displayName":"心房颤动","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_心房颤动","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642126839f0015","moduleCode":"SP020145","displayName":"心房扑动","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_心房扑动","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642126cc310016","moduleCode":"SP020145","displayName":"II度I型房室传导阻滞","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_II度I型房室传导阻滞","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642127735b0017","moduleCode":"SP020145","displayName":"II度II型房室传导阻滞","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_II度II型房室传导阻滞","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642127a8fa0018","moduleCode":"SP020145","displayName":"III度房室传导阻滞","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_III度房室传导阻滞","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b67f944d401682380d3983281","moduleCode":"SP020145","displayName":"安装心脏起搏器状态","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_安装心脏起搏器状态","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1dec8e1001f","moduleCode":"SP020145","displayName":"预激综合征","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_预激综合征","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1df6f660020","moduleCode":"SP020145","displayName":"窦性心动过缓","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_窦性心动过缓","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1dfe9e80021","moduleCode":"SP020145","displayName":"病窦综合征","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_病窦综合征","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e1a7f70022","moduleCode":"SP020145","displayName":"I度房室传导阻滞","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_I度房室传导阻滞","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e2b2260023","moduleCode":"SP020145","displayName":"左束支传导阻滞","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_左束支传导阻滞","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e2fed80024","moduleCode":"SP020145","displayName":"右束支传导阻滞","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_右束支传导阻滞","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e3868b0025","moduleCode":"SP020145","displayName":"快慢综合征","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_快慢综合征","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]}]}]},{"id":"4028801e64205fce0164207e24e30009","moduleCode":"SP020145","displayName":"高血压","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e20675e4003b","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压_亚诊断","fieldControlName":"主要诊断_主要诊断_高血压","childrens":[{"id":"4028e47b68e0a8020168e1f355d9002d","moduleCode":"SP020145","displayName":"高血压 1级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压_亚诊断_高血压 1级","fieldControlName":"主要诊断_主要诊断_高血压_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1f38a2d002e","moduleCode":"SP020145","displayName":"高血压 2级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压_亚诊断_高血压 2级","fieldControlName":"主要诊断_主要诊断_高血压_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1f3c36c002f","moduleCode":"SP020145","displayName":"高血压3级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压_亚诊断_高血压3级","fieldControlName":"主要诊断_主要诊断_高血压_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e206a92d003c","moduleCode":"SP020145","displayName":"原发性高血压","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压_亚诊断_原发性高血压","fieldControlName":"主要诊断_主要诊断_高血压_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e206f331003d","moduleCode":"SP020145","displayName":"继发性高血压","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压_亚诊断_继发性高血压","fieldControlName":"主要诊断_主要诊断_高血压_亚诊断","childrens":[]}]}]},{"id":"4028801e6420fa89016420fa89d30000","moduleCode":"SP020145","displayName":"高脂血症","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高脂血症","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e1e492fc0026","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高脂血症_亚诊断","fieldControlName":"主要诊断_主要诊断_高脂血症","childrens":[{"id":"4028e47b68e0a8020168e1e53b100027","moduleCode":"SP020145","displayName":"高甘油三酯血症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高脂血症_亚诊断_高甘油三酯血症","fieldControlName":"主要诊断_主要诊断_高脂血症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e5e87c0028","moduleCode":"SP020145","displayName":"高胆固醇血症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高脂血症_亚诊断_高胆固醇血症","fieldControlName":"主要诊断_主要诊断_高脂血症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e736220029","moduleCode":"SP020145","displayName":"低高密度脂蛋白血症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高脂血症_亚诊断_低高密度脂蛋白血症","fieldControlName":"主要诊断_主要诊断_高脂血症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e8b1b5002a","moduleCode":"SP020145","displayName":"混合型高脂血症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高脂血症_亚诊断_混合型高脂血症","fieldControlName":"主要诊断_主要诊断_高脂血症_亚诊断","childrens":[]}]}]},{"id":"4028e47b67e854780167e85478840000","moduleCode":"SP020145","displayName":"2型糖尿病","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_2型糖尿病","fieldControlName":"主要诊断_主要诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1f586d90030","moduleCode":"SP020145","displayName":"糖尿病前期","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病前期","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e1f601de0031","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病前期_亚诊断","fieldControlName":"主要诊断_主要诊断_糖尿病前期","childrens":[{"id":"4028e47b68e0a8020168e1f6532e0032","moduleCode":"SP020145","displayName":"空腹血糖异常","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病前期_亚诊断_空腹血糖异常","fieldControlName":"主要诊断_主要诊断_糖尿病前期_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1f6adf70033","moduleCode":"SP020145","displayName":"糖耐量异常","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病前期_亚诊断_糖耐量异常","fieldControlName":"主要诊断_主要诊断_糖尿病前期_亚诊断","childrens":[]}]}]},{"id":"4028e47b68e0a8020168e1f76abb0034","moduleCode":"SP020145","displayName":"糖尿病并发症","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e1f8de630035","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症_亚诊断","fieldControlName":"主要诊断_主要诊断_糖尿病并发症","childrens":[{"id":"4028e47b68e0a8020168e1f91f170036","moduleCode":"SP020145","displayName":"糖尿病肾病","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症_亚诊断_糖尿病肾病","fieldControlName":"主要诊断_主要诊断_糖尿病并发症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e2039c620037","moduleCode":"SP020145","displayName":"糖尿病眼部并发症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症_亚诊断_糖尿病眼部并发症","fieldControlName":"主要诊断_主要诊断_糖尿病并发症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e203e7fd0038","moduleCode":"SP020145","displayName":"糖尿病足","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症_亚诊断_糖尿病足","fieldControlName":"主要诊断_主要诊断_糖尿病并发症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e2045b0c0039","moduleCode":"SP020145","displayName":"糖尿病大血管并发症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症_亚诊断_糖尿病大血管并发症","fieldControlName":"主要诊断_主要诊断_糖尿病并发症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e204c32c003a","moduleCode":"SP020145","displayName":"糖尿病周围血管病变","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症_亚诊断_糖尿病周围血管病变","fieldControlName":"主要诊断_主要诊断_糖尿病并发症_亚诊断","childrens":[]}]}]},{"id":"4028e47b68e0a8020168e207d787003e","moduleCode":"SP020145","displayName":"脑血管病","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_脑血管病","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e2083cab003f","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_脑血管病_亚诊断","fieldControlName":"主要诊断_主要诊断_脑血管病","childrens":[{"id":"4028e47b68e0a8020168e20892100040","moduleCode":"SP020145","displayName":"脑梗死后遗症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_脑血管病_亚诊断_脑梗死后遗症","fieldControlName":"主要诊断_主要诊断_脑血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e2098ac00041","moduleCode":"SP020145","displayName":"短暂性脑缺血发作","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_脑血管病_亚诊断_短暂性脑缺血发作","fieldControlName":"主要诊断_主要诊断_脑血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e209cb1e0042","moduleCode":"SP020145","displayName":"脑出血后遗症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_脑血管病_亚诊断_脑出血后遗症","fieldControlName":"主要诊断_主要诊断_脑血管病_亚诊断","childrens":[]}]}]},{"id":"4028e47b68e0a8020168e20a311f0043","moduleCode":"SP020145","displayName":"慢性肾功能不全","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e20acd090044","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"radio","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断","fieldControlName":"主要诊断_主要诊断_慢性肾功能不全","childrens":[{"id":"4028e47b68e0a8020168e20c90030045","moduleCode":"SP020145","displayName":"CKD 1期","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断_CKD 1期","fieldControlName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e20cd2960046","moduleCode":"SP020145","displayName":"CKD 2期","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断_CKD 2期","fieldControlName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e20d0b850047","moduleCode":"SP020145","displayName":"CKD 3期","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断_CKD 3期","fieldControlName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e20d40ca0048","moduleCode":"SP020145","displayName":"CKD 4期","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断_CKD 4期","fieldControlName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e20d94ba0049","moduleCode":"SP020145","displayName":"CKD 5期","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断_CKD 5期","fieldControlName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断","childrens":[]}]}]},{"id":"4028e47b68e0a8020168e0a8026d0000","moduleCode":"SP020145","displayName":"周围血管病","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e20f5e33004a","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断","fieldControlName":"主要诊断_主要诊断_周围血管病","childrens":[{"id":"4028801e6420fa89016420fc93620001","moduleCode":"SP020145","displayName":"颈动脉粥样硬化","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_颈动脉粥样硬化","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e20fda91004b","moduleCode":"SP020145","displayName":"下肢动脉粥样硬化","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_下肢动脉粥样硬化","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e21084fa004d","moduleCode":"SP020145","displayName":"主动脉粥样硬化","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_主动脉粥样硬化","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e210cb1d004e","moduleCode":"SP020145","displayName":"脑动脉粥样硬化","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_脑动脉粥样硬化","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e2112d74004f","moduleCode":"SP020145","displayName":"锁骨下动脉粥样硬化","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_锁骨下动脉粥样硬化","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e211851e0050","moduleCode":"SP020145","displayName":"肾动脉粥样硬化","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_肾动脉粥样硬化","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e211e6070051","moduleCode":"SP020145","displayName":"深静脉血栓形成","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_深静脉血栓形成","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e2122c380052","moduleCode":"SP020145","displayName":"下肢静脉曲张","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_下肢静脉曲张","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]}]}]},{"id":"4028801e6420fa89016421027a090002","moduleCode":"SP020145","displayName":"扩张性心肌病","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_扩张性心肌病","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028801e6420fa89016421216cbf0005","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_扩张性心肌病_亚诊断","fieldControlName":"主要诊断_主要诊断_扩张性心肌病","childrens":[{"id":"4028801e6420fa8901642121dbf50006","moduleCode":"SP020145","displayName":"二尖瓣关闭不全","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_扩张性心肌病_亚诊断_二尖瓣关闭不全","fieldControlName":"主要诊断_主要诊断_扩张性心肌病_亚诊断","childrens":[]},{"id":"4028801e6420fa890164212234340007","moduleCode":"SP020145","displayName":"三尖瓣关闭不全","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_扩张性心肌病_亚诊断_三尖瓣关闭不全","fieldControlName":"主要诊断_主要诊断_扩张性心肌病_亚诊断","childrens":[]}]}]},{"id":"4028801e6420fa890164210a5e410003","moduleCode":"SP020145","displayName":"心脏瓣膜病","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028801e6420fa89016421231d640008","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病","childrens":[{"id":"4028801e6420fa89016421234afd0009","moduleCode":"SP020145","displayName":"主动脉瓣狭窄","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_主动脉瓣狭窄","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa89016421238500000a","moduleCode":"SP020145","displayName":"主动脉瓣关闭不全","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_主动脉瓣关闭不全","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642123fdcb000b","moduleCode":"SP020145","displayName":"二尖瓣狭窄","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_二尖瓣狭窄","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa890164212433bf000c","moduleCode":"SP020145","displayName":"二尖瓣关闭不全","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_二尖瓣关闭不全","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa890164212474a7000d","moduleCode":"SP020145","displayName":"二尖瓣脱垂","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_二尖瓣脱垂","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642124aecb000e","moduleCode":"SP020145","displayName":"三尖瓣关闭不全","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_三尖瓣关闭不全","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642124ead7000f","moduleCode":"SP020145","displayName":"肺动脉瓣狭窄","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_肺动脉瓣狭窄","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa89016421251ce20010","moduleCode":"SP020145","displayName":"肺动脉瓣关闭不全","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_肺动脉瓣关闭不全","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028e47b67f944d40167fd2c396600dc","moduleCode":"SP020145","displayName":"心脏瓣膜置换术后","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_心脏瓣膜置换术后","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]}]}]}]}]}]
                 */

                private TagModuleBean tagModule;
                private List<FildlistBean> fildlist;

                public TagModuleBean getTagModule() {
                    return tagModule;
                }

                public void setTagModule(TagModuleBean tagModule) {
                    this.tagModule = tagModule;
                }

                public List<FildlistBean> getFildlist() {
                    return fildlist;
                }

                public void setFildlist(List<FildlistBean> fildlist) {
                    this.fildlist = fildlist;
                }

                public static class TagModuleBean {
                    /**
                     * moduleName : 西医诊断
                     * moduleCode : SP020145
                     * parentModule : SP13
                     * orderNum : 1
                     * recordFlag : 1
                     * isHaveChild : 1
                     */
                    public boolean isSelect;
                    private String moduleName;
                    private String moduleCode;
                    private String parentModule;
                    private int orderNum;
                    private String recordFlag;
                    private int isHaveChild;

                    public String getModuleName() {
                        return moduleName;
                    }

                    public void setModuleName(String moduleName) {
                        this.moduleName = moduleName;
                    }

                    public String getModuleCode() {
                        return moduleCode;
                    }

                    public void setModuleCode(String moduleCode) {
                        this.moduleCode = moduleCode;
                    }

                    public String getParentModule() {
                        return parentModule;
                    }

                    public void setParentModule(String parentModule) {
                        this.parentModule = parentModule;
                    }

                    public int getOrderNum() {
                        return orderNum;
                    }

                    public void setOrderNum(int orderNum) {
                        this.orderNum = orderNum;
                    }

                    public String getRecordFlag() {
                        return recordFlag;
                    }

                    public void setRecordFlag(String recordFlag) {
                        this.recordFlag = recordFlag;
                    }

                    public int getIsHaveChild() {
                        return isHaveChild;
                    }

                    public void setIsHaveChild(int isHaveChild) {
                        this.isHaveChild = isHaveChild;
                    }
                }

                public static class FildlistBean {
                    /**
                     * id : 4028801e6420461f016420474c2d0001
                     * moduleCode : SP020145
                     * displayName : 主要诊断
                     * fieldType : title
                     * isNewline : 0
                     * scores :
                     * suffixName :
                     * fieldName : 主要诊断
                     * fieldControlName :
                     * childrens : [{"id":"4028801e6420461f0164204852100002","moduleCode":"SP020145","displayName":"主要诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断","fieldControlName":"主要诊断","childrens":[{"id":"4028801e6420461f01642048f6cc0003","moduleCode":"SP020145","displayName":"冠心病","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028801e64205fce01642068f4590001","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断","fieldControlName":"主要诊断_主要诊断_冠心病","childrens":[{"id":"4028801e64205fce0164206a02dc0003","moduleCode":"SP020145","displayName":"稳定性心绞痛","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_稳定性心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206c59960008","moduleCode":"SP020145","displayName":"缺血性心肌病","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_缺血性心肌病","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce01642069aca40002","moduleCode":"SP020145","displayName":"不稳定性心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_不稳定性心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19d4a520001","moduleCode":"SP020145","displayName":"初发劳力型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_初发劳力型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19db2400002","moduleCode":"SP020145","displayName":"恶化劳力型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_恶化劳力型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19e15950003","moduleCode":"SP020145","displayName":"自发型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_自发型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19e72050004","moduleCode":"SP020145","displayName":"变异型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_变异型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19edba80005","moduleCode":"SP020145","displayName":"卧位型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_卧位型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19f1dcc0006","moduleCode":"SP020145","displayName":"其他型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_其他型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1aeb74b001b","moduleCode":"SP020145","displayName":"CCS I级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CCS I级","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1af107f001c","moduleCode":"SP020145","displayName":"CCS II级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CCS II级","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1af5613001d","moduleCode":"SP020145","displayName":"CCS III级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CCS III级","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1af890c001e","moduleCode":"SP020145","displayName":"CCS IV级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CCS IV级","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206a528b0004","moduleCode":"SP020145","displayName":"PCI术后","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_PCI术后","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b67e337e50167e3585deb0045","moduleCode":"SP020145","displayName":"CABG术后","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CABG术后","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206ab8250005","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（未知部位）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（未知部位）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a490c1000a","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（前壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（前壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a4f05f000b","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（前间壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（前间壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a53c25000c","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（下壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（下壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a58825000d","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（后壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（后壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a60245000e","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（侧壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（侧壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a64c65000f","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（高侧壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（高侧壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a6a5920010","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（右室）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（右室）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19fa7400007","moduleCode":"SP020145","displayName":"STEMI","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_STEMI","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19ffbb40008","moduleCode":"SP020145","displayName":"NSTEMI","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_NSTEMI","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206b341b0006","moduleCode":"SP020145","displayName":"室壁瘤","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_室壁瘤","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206bb6d80007","moduleCode":"SP020145","displayName":"附壁血栓","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_附壁血栓","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]}]}]},{"id":"4028e47b68e0a8020168e1a7d3400011","moduleCode":"SP020145","displayName":"心功能不全","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e1a898290012","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_亚诊断","fieldControlName":"主要诊断_主要诊断_心功能不全","childrens":[{"id":"4028807a6660bf8d016660c938d80003","moduleCode":"SP020145","displayName":"NYHA I级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_亚诊断_NYHA I级","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028807a6660bf8d016660c938db0004","moduleCode":"SP020145","displayName":"NYHA II级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_亚诊断_NYHA II级","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028807a6660bf8d016660c938dd0005","moduleCode":"SP020145","displayName":"NYHA III级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_亚诊断_NYHA III级","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028807a6660bf8d016660c938e00006","moduleCode":"SP020145","displayName":"NYHA IV 级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_亚诊断_NYHA IV 级","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a96e220013","moduleCode":"SP020145","displayName":"左心衰","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_心功能不全_左心衰","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a9db790014","moduleCode":"SP020145","displayName":"右心衰","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_心功能不全_右心衰","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1aa27e70015","moduleCode":"SP020145","displayName":"全心衰","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_心功能不全_全心衰","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1ac655e0016","moduleCode":"SP020145","displayName":"HFrEF","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_心功能不全_HFrEF","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1acaf3f0017","moduleCode":"SP020145","displayName":"HFmrEF","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_心功能不全_HFmrEF","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1acfab90018","moduleCode":"SP020145","displayName":"HFpEF","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心功能不全_心功能不全_HFpEF","fieldControlName":"主要诊断_主要诊断_心功能不全_亚诊断","childrens":[]}]}]},{"id":"4028801e6420fa890164210b90530004","moduleCode":"SP020145","displayName":"心律失常","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028801e6420fa89016421258bff0011","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断","fieldControlName":"主要诊断_主要诊断_心律失常","childrens":[{"id":"4028801e6420fa8901642125bd1d0012","moduleCode":"SP020145","displayName":"室性期前收缩","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_室性期前收缩","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642125ee2b0013","moduleCode":"SP020145","displayName":"房性期前收缩","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_房性期前收缩","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028801e6420fa890164212628590014","moduleCode":"SP020145","displayName":"心房颤动","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_心房颤动","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642126839f0015","moduleCode":"SP020145","displayName":"心房扑动","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_心房扑动","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642126cc310016","moduleCode":"SP020145","displayName":"II度I型房室传导阻滞","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_II度I型房室传导阻滞","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642127735b0017","moduleCode":"SP020145","displayName":"II度II型房室传导阻滞","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_II度II型房室传导阻滞","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642127a8fa0018","moduleCode":"SP020145","displayName":"III度房室传导阻滞","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_III度房室传导阻滞","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b67f944d401682380d3983281","moduleCode":"SP020145","displayName":"安装心脏起搏器状态","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_安装心脏起搏器状态","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1dec8e1001f","moduleCode":"SP020145","displayName":"预激综合征","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_预激综合征","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1df6f660020","moduleCode":"SP020145","displayName":"窦性心动过缓","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_窦性心动过缓","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1dfe9e80021","moduleCode":"SP020145","displayName":"病窦综合征","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_病窦综合征","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e1a7f70022","moduleCode":"SP020145","displayName":"I度房室传导阻滞","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_I度房室传导阻滞","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e2b2260023","moduleCode":"SP020145","displayName":"左束支传导阻滞","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_左束支传导阻滞","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e2fed80024","moduleCode":"SP020145","displayName":"右束支传导阻滞","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_右束支传导阻滞","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e3868b0025","moduleCode":"SP020145","displayName":"快慢综合征","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心律失常_亚诊断_快慢综合征","fieldControlName":"主要诊断_主要诊断_心律失常_亚诊断","childrens":[]}]}]},{"id":"4028801e64205fce0164207e24e30009","moduleCode":"SP020145","displayName":"高血压","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e20675e4003b","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压_亚诊断","fieldControlName":"主要诊断_主要诊断_高血压","childrens":[{"id":"4028e47b68e0a8020168e1f355d9002d","moduleCode":"SP020145","displayName":"高血压 1级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压_亚诊断_高血压 1级","fieldControlName":"主要诊断_主要诊断_高血压_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1f38a2d002e","moduleCode":"SP020145","displayName":"高血压 2级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压_亚诊断_高血压 2级","fieldControlName":"主要诊断_主要诊断_高血压_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1f3c36c002f","moduleCode":"SP020145","displayName":"高血压3级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压_亚诊断_高血压3级","fieldControlName":"主要诊断_主要诊断_高血压_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e206a92d003c","moduleCode":"SP020145","displayName":"原发性高血压","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压_亚诊断_原发性高血压","fieldControlName":"主要诊断_主要诊断_高血压_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e206f331003d","moduleCode":"SP020145","displayName":"继发性高血压","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高血压_亚诊断_继发性高血压","fieldControlName":"主要诊断_主要诊断_高血压_亚诊断","childrens":[]}]}]},{"id":"4028801e6420fa89016420fa89d30000","moduleCode":"SP020145","displayName":"高脂血症","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高脂血症","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e1e492fc0026","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高脂血症_亚诊断","fieldControlName":"主要诊断_主要诊断_高脂血症","childrens":[{"id":"4028e47b68e0a8020168e1e53b100027","moduleCode":"SP020145","displayName":"高甘油三酯血症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高脂血症_亚诊断_高甘油三酯血症","fieldControlName":"主要诊断_主要诊断_高脂血症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e5e87c0028","moduleCode":"SP020145","displayName":"高胆固醇血症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高脂血症_亚诊断_高胆固醇血症","fieldControlName":"主要诊断_主要诊断_高脂血症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e736220029","moduleCode":"SP020145","displayName":"低高密度脂蛋白血症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高脂血症_亚诊断_低高密度脂蛋白血症","fieldControlName":"主要诊断_主要诊断_高脂血症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1e8b1b5002a","moduleCode":"SP020145","displayName":"混合型高脂血症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_高脂血症_亚诊断_混合型高脂血症","fieldControlName":"主要诊断_主要诊断_高脂血症_亚诊断","childrens":[]}]}]},{"id":"4028e47b67e854780167e85478840000","moduleCode":"SP020145","displayName":"2型糖尿病","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_2型糖尿病","fieldControlName":"主要诊断_主要诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1f586d90030","moduleCode":"SP020145","displayName":"糖尿病前期","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病前期","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e1f601de0031","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病前期_亚诊断","fieldControlName":"主要诊断_主要诊断_糖尿病前期","childrens":[{"id":"4028e47b68e0a8020168e1f6532e0032","moduleCode":"SP020145","displayName":"空腹血糖异常","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病前期_亚诊断_空腹血糖异常","fieldControlName":"主要诊断_主要诊断_糖尿病前期_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1f6adf70033","moduleCode":"SP020145","displayName":"糖耐量异常","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病前期_亚诊断_糖耐量异常","fieldControlName":"主要诊断_主要诊断_糖尿病前期_亚诊断","childrens":[]}]}]},{"id":"4028e47b68e0a8020168e1f76abb0034","moduleCode":"SP020145","displayName":"糖尿病并发症","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e1f8de630035","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症_亚诊断","fieldControlName":"主要诊断_主要诊断_糖尿病并发症","childrens":[{"id":"4028e47b68e0a8020168e1f91f170036","moduleCode":"SP020145","displayName":"糖尿病肾病","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症_亚诊断_糖尿病肾病","fieldControlName":"主要诊断_主要诊断_糖尿病并发症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e2039c620037","moduleCode":"SP020145","displayName":"糖尿病眼部并发症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症_亚诊断_糖尿病眼部并发症","fieldControlName":"主要诊断_主要诊断_糖尿病并发症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e203e7fd0038","moduleCode":"SP020145","displayName":"糖尿病足","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症_亚诊断_糖尿病足","fieldControlName":"主要诊断_主要诊断_糖尿病并发症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e2045b0c0039","moduleCode":"SP020145","displayName":"糖尿病大血管并发症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症_亚诊断_糖尿病大血管并发症","fieldControlName":"主要诊断_主要诊断_糖尿病并发症_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e204c32c003a","moduleCode":"SP020145","displayName":"糖尿病周围血管病变","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_糖尿病并发症_亚诊断_糖尿病周围血管病变","fieldControlName":"主要诊断_主要诊断_糖尿病并发症_亚诊断","childrens":[]}]}]},{"id":"4028e47b68e0a8020168e207d787003e","moduleCode":"SP020145","displayName":"脑血管病","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_脑血管病","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e2083cab003f","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_脑血管病_亚诊断","fieldControlName":"主要诊断_主要诊断_脑血管病","childrens":[{"id":"4028e47b68e0a8020168e20892100040","moduleCode":"SP020145","displayName":"脑梗死后遗症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_脑血管病_亚诊断_脑梗死后遗症","fieldControlName":"主要诊断_主要诊断_脑血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e2098ac00041","moduleCode":"SP020145","displayName":"短暂性脑缺血发作","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_脑血管病_亚诊断_短暂性脑缺血发作","fieldControlName":"主要诊断_主要诊断_脑血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e209cb1e0042","moduleCode":"SP020145","displayName":"脑出血后遗症","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_脑血管病_亚诊断_脑出血后遗症","fieldControlName":"主要诊断_主要诊断_脑血管病_亚诊断","childrens":[]}]}]},{"id":"4028e47b68e0a8020168e20a311f0043","moduleCode":"SP020145","displayName":"慢性肾功能不全","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e20acd090044","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"radio","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断","fieldControlName":"主要诊断_主要诊断_慢性肾功能不全","childrens":[{"id":"4028e47b68e0a8020168e20c90030045","moduleCode":"SP020145","displayName":"CKD 1期","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断_CKD 1期","fieldControlName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e20cd2960046","moduleCode":"SP020145","displayName":"CKD 2期","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断_CKD 2期","fieldControlName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e20d0b850047","moduleCode":"SP020145","displayName":"CKD 3期","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断_CKD 3期","fieldControlName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e20d40ca0048","moduleCode":"SP020145","displayName":"CKD 4期","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断_CKD 4期","fieldControlName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e20d94ba0049","moduleCode":"SP020145","displayName":"CKD 5期","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断_CKD 5期","fieldControlName":"主要诊断_主要诊断_慢性肾功能不全_亚诊断","childrens":[]}]}]},{"id":"4028e47b68e0a8020168e0a8026d0000","moduleCode":"SP020145","displayName":"周围血管病","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028e47b68e0a8020168e20f5e33004a","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断","fieldControlName":"主要诊断_主要诊断_周围血管病","childrens":[{"id":"4028801e6420fa89016420fc93620001","moduleCode":"SP020145","displayName":"颈动脉粥样硬化","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_颈动脉粥样硬化","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e20fda91004b","moduleCode":"SP020145","displayName":"下肢动脉粥样硬化","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_下肢动脉粥样硬化","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e21084fa004d","moduleCode":"SP020145","displayName":"主动脉粥样硬化","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_主动脉粥样硬化","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e210cb1d004e","moduleCode":"SP020145","displayName":"脑动脉粥样硬化","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_脑动脉粥样硬化","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e2112d74004f","moduleCode":"SP020145","displayName":"锁骨下动脉粥样硬化","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_锁骨下动脉粥样硬化","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e211851e0050","moduleCode":"SP020145","displayName":"肾动脉粥样硬化","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_肾动脉粥样硬化","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e211e6070051","moduleCode":"SP020145","displayName":"深静脉血栓形成","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_深静脉血栓形成","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e2122c380052","moduleCode":"SP020145","displayName":"下肢静脉曲张","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_周围血管病_亚诊断_下肢静脉曲张","fieldControlName":"主要诊断_主要诊断_周围血管病_亚诊断","childrens":[]}]}]},{"id":"4028801e6420fa89016421027a090002","moduleCode":"SP020145","displayName":"扩张性心肌病","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_扩张性心肌病","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028801e6420fa89016421216cbf0005","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_扩张性心肌病_亚诊断","fieldControlName":"主要诊断_主要诊断_扩张性心肌病","childrens":[{"id":"4028801e6420fa8901642121dbf50006","moduleCode":"SP020145","displayName":"二尖瓣关闭不全","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_扩张性心肌病_亚诊断_二尖瓣关闭不全","fieldControlName":"主要诊断_主要诊断_扩张性心肌病_亚诊断","childrens":[]},{"id":"4028801e6420fa890164212234340007","moduleCode":"SP020145","displayName":"三尖瓣关闭不全","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_扩张性心肌病_亚诊断_三尖瓣关闭不全","fieldControlName":"主要诊断_主要诊断_扩张性心肌病_亚诊断","childrens":[]}]}]},{"id":"4028801e6420fa890164210a5e410003","moduleCode":"SP020145","displayName":"心脏瓣膜病","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病","fieldControlName":"主要诊断_主要诊断","childrens":[{"id":"4028801e6420fa89016421231d640008","moduleCode":"SP020145","displayName":"亚诊断","fieldType":"checkbox","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病","childrens":[{"id":"4028801e6420fa89016421234afd0009","moduleCode":"SP020145","displayName":"主动脉瓣狭窄","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_主动脉瓣狭窄","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa89016421238500000a","moduleCode":"SP020145","displayName":"主动脉瓣关闭不全","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_主动脉瓣关闭不全","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642123fdcb000b","moduleCode":"SP020145","displayName":"二尖瓣狭窄","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_二尖瓣狭窄","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa890164212433bf000c","moduleCode":"SP020145","displayName":"二尖瓣关闭不全","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_二尖瓣关闭不全","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa890164212474a7000d","moduleCode":"SP020145","displayName":"二尖瓣脱垂","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_二尖瓣脱垂","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642124aecb000e","moduleCode":"SP020145","displayName":"三尖瓣关闭不全","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_三尖瓣关闭不全","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa8901642124ead7000f","moduleCode":"SP020145","displayName":"肺动脉瓣狭窄","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_肺动脉瓣狭窄","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028801e6420fa89016421251ce20010","moduleCode":"SP020145","displayName":"肺动脉瓣关闭不全","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_肺动脉瓣关闭不全","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]},{"id":"4028e47b67f944d40167fd2c396600dc","moduleCode":"SP020145","displayName":"心脏瓣膜置换术后","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断_心脏瓣膜置换术后","fieldControlName":"主要诊断_主要诊断_心脏瓣膜病_亚诊断","childrens":[]}]}]}]}]
                     */

                    private String id;
                    private String moduleCode;
                    private String displayName;
                    private String fieldType;
                    private int isNewline;
                    private String scores;
                    private String suffixName;
                    private String fieldName;
                    private String fieldControlName;
                    private List<ChildrensBeanXXX> childrens;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getModuleCode() {
                        return moduleCode;
                    }

                    public void setModuleCode(String moduleCode) {
                        this.moduleCode = moduleCode;
                    }

                    public String getDisplayName() {
                        return displayName;
                    }

                    public void setDisplayName(String displayName) {
                        this.displayName = displayName;
                    }

                    public String getFieldType() {
                        return fieldType;
                    }

                    public void setFieldType(String fieldType) {
                        this.fieldType = fieldType;
                    }

                    public int getIsNewline() {
                        return isNewline;
                    }

                    public void setIsNewline(int isNewline) {
                        this.isNewline = isNewline;
                    }

                    public String getScores() {
                        return scores;
                    }

                    public void setScores(String scores) {
                        this.scores = scores;
                    }

                    public String getSuffixName() {
                        return suffixName;
                    }

                    public void setSuffixName(String suffixName) {
                        this.suffixName = suffixName;
                    }

                    public String getFieldName() {
                        return fieldName;
                    }

                    public void setFieldName(String fieldName) {
                        this.fieldName = fieldName;
                    }

                    public String getFieldControlName() {
                        return fieldControlName;
                    }

                    public void setFieldControlName(String fieldControlName) {
                        this.fieldControlName = fieldControlName;
                    }

                    public List<ChildrensBeanXXX> getChildrens() {
                        return childrens;
                    }

                    public void setChildrens(List<ChildrensBeanXXX> childrens) {
                        this.childrens = childrens;
                    }

                    public static class ChildrensBeanXXX {

                        private String id;
                        private String moduleCode;
                        private String displayName;
                        private String fieldType;
                        private int isNewline;
                        private String scores;
                        private String suffixName;
                        private String fieldName;
                        private String fieldControlName;
                        private List<ChildrensBeanXX> childrens;
                        public boolean isCheck; // 是否选中
                        public String content; // 录入内容

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }

                        public String getModuleCode() {
                            return moduleCode;
                        }

                        public void setModuleCode(String moduleCode) {
                            this.moduleCode = moduleCode;
                        }

                        public String getDisplayName() {
                            return displayName;
                        }

                        public void setDisplayName(String displayName) {
                            this.displayName = displayName;
                        }

                        public String getFieldType() {
                            return fieldType;
                        }

                        public void setFieldType(String fieldType) {
                            this.fieldType = fieldType;
                        }

                        public int getIsNewline() {
                            return isNewline;
                        }

                        public void setIsNewline(int isNewline) {
                            this.isNewline = isNewline;
                        }

                        public String getScores() {
                            return scores;
                        }

                        public void setScores(String scores) {
                            this.scores = scores;
                        }

                        public String getSuffixName() {
                            return suffixName;
                        }

                        public void setSuffixName(String suffixName) {
                            this.suffixName = suffixName;
                        }

                        public String getFieldName() {
                            return fieldName;
                        }

                        public void setFieldName(String fieldName) {
                            this.fieldName = fieldName;
                        }

                        public String getFieldControlName() {
                            return fieldControlName;
                        }

                        public void setFieldControlName(String fieldControlName) {
                            this.fieldControlName = fieldControlName;
                        }

                        public List<ChildrensBeanXX> getChildrens() {
                            return childrens;
                        }

                        public void setChildrens(List<ChildrensBeanXX> childrens) {
                            this.childrens = childrens;
                        }

                        public static class ChildrensBeanXX {

                            private String id;
                            private String moduleCode;
                            private String displayName;
                            private String fieldType;
                            private int isNewline;
                            private String scores;
                            private String suffixName;
                            private String fieldName;
                            private String fieldControlName;
                            private List<ChildrensBeanX> childrens;
                            public boolean isCheck; // 是否选中
                            public String content; // 录入内容

                            public String getId() {
                                return id;
                            }

                            public void setId(String id) {
                                this.id = id;
                            }

                            public String getModuleCode() {
                                return moduleCode;
                            }

                            public void setModuleCode(String moduleCode) {
                                this.moduleCode = moduleCode;
                            }

                            public String getDisplayName() {
                                return displayName;
                            }

                            public void setDisplayName(String displayName) {
                                this.displayName = displayName;
                            }

                            public String getFieldType() {
                                return fieldType;
                            }

                            public void setFieldType(String fieldType) {
                                this.fieldType = fieldType;
                            }

                            public int getIsNewline() {
                                return isNewline;
                            }

                            public void setIsNewline(int isNewline) {
                                this.isNewline = isNewline;
                            }

                            public String getScores() {
                                return scores;
                            }

                            public void setScores(String scores) {
                                this.scores = scores;
                            }

                            public String getSuffixName() {
                                return suffixName;
                            }

                            public void setSuffixName(String suffixName) {
                                this.suffixName = suffixName;
                            }

                            public String getFieldName() {
                                return fieldName;
                            }

                            public void setFieldName(String fieldName) {
                                this.fieldName = fieldName;
                            }

                            public String getFieldControlName() {
                                return fieldControlName;
                            }

                            public void setFieldControlName(String fieldControlName) {
                                this.fieldControlName = fieldControlName;
                            }

                            public List<ChildrensBeanX> getChildrens() {
                                return childrens;
                            }

                            public void setChildrens(List<ChildrensBeanX> childrens) {
                                this.childrens = childrens;
                            }

                            public static class ChildrensBeanX {
                                /**
                                 * id : 4028801e64205fce01642068f4590001
                                 * moduleCode : SP020145
                                 * displayName : 亚诊断
                                 * fieldType : checkbox
                                 * isNewline : 0
                                 * scores :
                                 * suffixName :
                                 * fieldName : 主要诊断_主要诊断_冠心病_亚诊断
                                 * fieldControlName : 主要诊断_主要诊断_冠心病
                                 * childrens : [{"id":"4028801e64205fce0164206a02dc0003","moduleCode":"SP020145","displayName":"稳定性心绞痛","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_稳定性心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206c59960008","moduleCode":"SP020145","displayName":"缺血性心肌病","fieldType":"","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_缺血性心肌病","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce01642069aca40002","moduleCode":"SP020145","displayName":"不稳定性心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_不稳定性心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19d4a520001","moduleCode":"SP020145","displayName":"初发劳力型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_初发劳力型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19db2400002","moduleCode":"SP020145","displayName":"恶化劳力型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_恶化劳力型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19e15950003","moduleCode":"SP020145","displayName":"自发型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_自发型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19e72050004","moduleCode":"SP020145","displayName":"变异型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_变异型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19edba80005","moduleCode":"SP020145","displayName":"卧位型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_卧位型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19f1dcc0006","moduleCode":"SP020145","displayName":"其他型心绞痛","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_其他型心绞痛","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1aeb74b001b","moduleCode":"SP020145","displayName":"CCS I级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CCS I级","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1af107f001c","moduleCode":"SP020145","displayName":"CCS II级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CCS II级","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1af5613001d","moduleCode":"SP020145","displayName":"CCS III级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CCS III级","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1af890c001e","moduleCode":"SP020145","displayName":"CCS IV级","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CCS IV级","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206a528b0004","moduleCode":"SP020145","displayName":"PCI术后","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_PCI术后","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b67e337e50167e3585deb0045","moduleCode":"SP020145","displayName":"CABG术后","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_CABG术后","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206ab8250005","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（未知部位）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（未知部位）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a490c1000a","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（前壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（前壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a4f05f000b","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（前间壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（前间壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a53c25000c","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（下壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（下壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a58825000d","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（后壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（后壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a60245000e","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（侧壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（侧壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a64c65000f","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（高侧壁）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（高侧壁）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e1a6a5920010","moduleCode":"SP020145","displayName":"陈旧性心肌梗死（右室）","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_陈旧性心肌梗死（右室）","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19fa7400007","moduleCode":"SP020145","displayName":"STEMI","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_STEMI","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028e47b68e0a8020168e19ffbb40008","moduleCode":"SP020145","displayName":"NSTEMI","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_NSTEMI","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206b341b0006","moduleCode":"SP020145","displayName":"室壁瘤","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_室壁瘤","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]},{"id":"4028801e64205fce0164206bb6d80007","moduleCode":"SP020145","displayName":"附壁血栓","fieldType":"choices","isNewline":0,"scores":"","suffixName":"","fieldName":"主要诊断_主要诊断_冠心病_亚诊断_附壁血栓","fieldControlName":"主要诊断_主要诊断_冠心病_亚诊断","childrens":[]}]
                                 */

                                private String id;
                                private String moduleCode;
                                private String displayName;
                                private String fieldType;
                                private int isNewline;
                                private String scores;
                                private String suffixName;
                                private String fieldName;
                                private String fieldControlName;
                                private List<ChildrensBean> childrens;
                                public boolean isCheck; // 是否选中
                                public String content; // 录入内容


                                public String getId() {
                                    return id;
                                }

                                public void setId(String id) {
                                    this.id = id;
                                }

                                public String getModuleCode() {
                                    return moduleCode;
                                }

                                public void setModuleCode(String moduleCode) {
                                    this.moduleCode = moduleCode;
                                }

                                public String getDisplayName() {
                                    return displayName;
                                }

                                public void setDisplayName(String displayName) {
                                    this.displayName = displayName;
                                }

                                public String getFieldType() {
                                    return fieldType;
                                }

                                public void setFieldType(String fieldType) {
                                    this.fieldType = fieldType;
                                }

                                public int getIsNewline() {
                                    return isNewline;
                                }

                                public void setIsNewline(int isNewline) {
                                    this.isNewline = isNewline;
                                }

                                public String getScores() {
                                    return scores;
                                }

                                public void setScores(String scores) {
                                    this.scores = scores;
                                }

                                public String getSuffixName() {
                                    return suffixName;
                                }

                                public void setSuffixName(String suffixName) {
                                    this.suffixName = suffixName;
                                }

                                public String getFieldName() {
                                    return fieldName;
                                }

                                public void setFieldName(String fieldName) {
                                    this.fieldName = fieldName;
                                }

                                public String getFieldControlName() {
                                    return fieldControlName;
                                }

                                public void setFieldControlName(String fieldControlName) {
                                    this.fieldControlName = fieldControlName;
                                }

                                public List<ChildrensBean> getChildrens() {
                                    return childrens;
                                }

                                public void setChildrens(List<ChildrensBean> childrens) {
                                    this.childrens = childrens;
                                }

                                public static class ChildrensBean {
                                    /**
                                     * id : 4028801e64205fce0164206a02dc0003
                                     * moduleCode : SP020145
                                     * displayName : 稳定性心绞痛
                                     * fieldType :
                                     * isNewline : 0
                                     * scores :
                                     * suffixName :
                                     * fieldName : 主要诊断_主要诊断_冠心病_亚诊断_稳定性心绞痛
                                     * fieldControlName : 主要诊断_主要诊断_冠心病_亚诊断
                                     * childrens : []
                                     */

                                    private String id;
                                    private String moduleCode;
                                    private String displayName;
                                    private String fieldType;
                                    private int isNewline;
                                    private String scores;
                                    private String suffixName;
                                    private String fieldName;
                                    private String fieldControlName;
                                    private List<?> childrens;

                                    public boolean isCheck; // 是否选中
                                    public String content; // 录入内容

                                    public String getId() {
                                        return id;
                                    }

                                    public void setId(String id) {
                                        this.id = id;
                                    }

                                    public String getModuleCode() {
                                        return moduleCode;
                                    }

                                    public void setModuleCode(String moduleCode) {
                                        this.moduleCode = moduleCode;
                                    }

                                    public String getDisplayName() {
                                        return displayName;
                                    }

                                    public void setDisplayName(String displayName) {
                                        this.displayName = displayName;
                                    }

                                    public String getFieldType() {
                                        return fieldType;
                                    }

                                    public void setFieldType(String fieldType) {
                                        this.fieldType = fieldType;
                                    }

                                    public int getIsNewline() {
                                        return isNewline;
                                    }

                                    public void setIsNewline(int isNewline) {
                                        this.isNewline = isNewline;
                                    }

                                    public String getScores() {
                                        return scores;
                                    }

                                    public void setScores(String scores) {
                                        this.scores = scores;
                                    }

                                    public String getSuffixName() {
                                        return suffixName;
                                    }

                                    public void setSuffixName(String suffixName) {
                                        this.suffixName = suffixName;
                                    }

                                    public String getFieldName() {
                                        return fieldName;
                                    }

                                    public void setFieldName(String fieldName) {
                                        this.fieldName = fieldName;
                                    }

                                    public String getFieldControlName() {
                                        return fieldControlName;
                                    }

                                    public void setFieldControlName(String fieldControlName) {
                                        this.fieldControlName = fieldControlName;
                                    }

                                    public List<?> getChildrens() {
                                        return childrens;
                                    }

                                    public void setChildrens(List<?> childrens) {
                                        this.childrens = childrens;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        public static class RelationListBean {
            /**
             * id : 1
             * moduleCode : SP020139
             * fieldID : 4028801e6420461f01642048f6cc0003
             */

            private String id;
            private String moduleCode;
            private String fieldID;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getModuleCode() {
                return moduleCode;
            }

            public void setModuleCode(String moduleCode) {
                this.moduleCode = moduleCode;
            }

            public String getFieldID() {
                return fieldID;
            }

            public void setFieldID(String fieldID) {
                this.fieldID = fieldID;
            }
        }
    }
}
