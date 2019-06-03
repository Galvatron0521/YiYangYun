package com.wd.yiyangyun.app;

/**
 * @Author JenSenLeung.
 * @Time 2018/8/1 下午 5:33.
 * @Description This is Constants.
 */
public interface Constants {

    String USER_ID = "UserID";
    String ROLEID = "RoleID";
    String HOSPITAL_ID = "HospitalID";
    String DepartmentID = "DepartmentID";
    String LOGIN_NAME = "LoginName";
    String Name = "Name";
    String SEX = "Sex";
    String NATIONAL = "National";
    String BRITHDAY = "Brithday";
    String MOBILE = "Mobile";
    String ID_CARD = "IDCard";
    String PHOTO_ID = "PhotoID";
    String PHOTO_URL = "PhotoUrl";
    String ADDRESS = "Address";
    String FILEURL = "FileUrl";
    String FRSBOOLEAN = "fieldRecordSignBoolean";
    String GROUPID = "GroupId";
    String KESHI = "KeShi";
    String ZHICHENG = "ZhiCheng";
    String HOSPOTALNAME = "hospitalName";

    String MOBILE_TYPE = "1";
    String EVENTBUS_TYEPE = "type";
    String EVENTBUS_VALUE = "value";
    String PATIENT_IV = "http://shenkangyun.com:808/attachment/headdoctor/huanzhe.jpg";




//    //心医云
    String BASE_URL2 = "http://111.17.215.37:8023/";

    String Host2 = BASE_URL2+"skyapp_xy/api/app_patient/?";
    String SELECT_FILD_MODULE_LIST = Host2 + "act=selectFildModuleList&data=";



    //医养云
    String BASE_URL = "http://111.17.215.37:808/";
//    String BASE_URL = "http://192.168.0.250:8080/";

    String Host = BASE_URL + "skyapp_ndpt/api/app_patient/?";
    //登录
    String LOGIN = Host+"act=login&data=";
    //分组
    String GROUP = Host+"act=patientGroupList&data=";
    //分组详情
    String GROUP_INFO = Host+"act=getGroupPatientsList&data=";
    //添加患者
    String ADD_PATIENT = Host+"act=insertPatient&data=";
    //修改患者信息
    String UPADTE_PATIENT = Host+"act=UpdatePatient&data=";
    //查询患者
    String QUERY_PATIENT = Host+"act=getPatientDetails&data=";
    //分组管理
    String GROUP_MANAGER = Host+"act=selectGroupManage&data=";
    //增加管理
    String ADD_GROUP = Host+"act=addGroupItem&data=";
    //修改管理
    String UPDATE_GROUP = Host+"act=updateGroupItem&data=";
    //删除管理
    String DELETE_GROUP = Host+"act=deleteGroupItem&data=";
    //钱包
    String PURSE = Host+"act=myPurse&data=";
    //查询收费标准
    String QUERY_STANDARD = Host+"act=getDoctorChargeStandard&data=";
    //设置收费表准
    String SET_STANDARD = Host+"act=setDoctorChargeInfor&data=";
    //修改收费表准
    String UPDATE_STANDARD = Host+"act=updateDoctorChargeInfor&data=";
    //随访方案列表
    String SELECT_FOLLOW_PLAN_LIST = Host+"act=selectFollowPlanList&data=";
    //添加方案列表
    String ADD_PLAN = Host+"act=insertFollowPlan&data=";
    //修改方案列表
    String UPDATE_PLAN = Host+"act=updateFollowPlan&data=";
    //删除方案列表
    String DELECT_PLAN = Host+"act=deleteFollowPlan&data=";
    //添加患者随访信息
    String SAVE_FOLLOW = Host+"act=saveFollowInformation&data=";
    //删除随访记录
    String DELECT_FOLLOW = Host+"act=deleteFollowRecord&data=";
    //辩证论治CRF
    String BIANZHENGLUN_CRF = Host+"act=selectFildModuleList&data=";
    //随访CRF详情查询
    String SELECT_FOLLOW_RECORD_CONTENTS_DETAILS = Host +
            "act=selectFollowRecordContentsDetails&data=";
    //查询表单详情
    String QUERY_BIAODAN = Host+"act=selectFieldContentsList&data=";
    //上传头像
    String USER_ICON = BASE_URL+"public/doctorPicUpload.html?";
    //上传头像
    String UPVIDEO = BASE_URL+"skyapp_ndpt/Api/UploadServlet?";


    //单独修改共享
    String SUIFANGSHARE = Host + "act=isSharedFollowPlan&data=";

    //数据字典
    String SELECT_CONTENT = Host + "act=selectContent&data=";

    //查询我的随访方案任务项目
    String SELECT_FOLLOW_CRF_LIST_NEW = Host + "act=selectFollowCRFListNew&data=";
    //咨询列表
    String NewsList = Host + "act=getInformationList&data=";
    //查看患者转诊的详情
    String PATIENT_ZHUANZHEN = Host + "act=getPatientZhuanZhenList&data=";
    //转诊 --转入
    String ZHUANRU = Host + "act=selectZhuanZhenInPatients&data=";
    //转诊 --转出
    String ZHUANCHU = Host + "act=selectZhuanZhenOutPatients&data=";
    //添加转诊
    String ADD_ZHUANZHEN = Host + "act=addPatientZhuanZhen&data=";
    //查询医院
    String QUERY_HOSPITAL = Host + "act=selectHospital&data={}";
    //查询科室
    String QUERY_KESHI = Host + "act=getDepartmentByHospitalID&data=";
    //查询医生
    String QUERY_DOCTOR = Host + "act=getUsersByDepartmentID&data=";
    //查询患者
    String QUERY_PATIENTZIDIAN = Host + "act=getPatientListByHospital&data=";
    //确认转诊
    String QUEREN = Host + "act=receiptZhuanZhen&data=";
    //拒绝转诊
    String JUJUE = Host + "act=refuseZhuanZhen&data=";
    //钱包
    String QIANBAO = Host + "act=myPurse&data=";
    //患者随访列表
    String SELECT_FOLLOW_PATIENT_NEW_LIST = Host + "act=selectFollowPatientNewList&data=";
    //病症管理列表
    String SELECT_FIELD_RECORD_SIGN_LIST = Host + "act=selectFieldRecordSignList&data=";
    //删除病程
    String DELETE_COURSE_OF_DISEASE = Host + "act=deleteCourseOfDisease&data=";
    //患者信息
    String GET_PATIENT_DETAILS = Host + "act=getPatientDetails&data=";

    //意见反馈
    String YIJIAN_FANKUI = Host + "act=selectMyOpinionsList&data=";
    //添加意见反馈
    String ADD_YIJIAN = Host + "act=insertMyOpinion&data=";
    String FOLLOW_TYPE_LIST = Host+"act=FollowTypeList&data=";
    //辨证论治的回显
    String SELECT_FIELD_CONTENTS_LIST = Host + "act=selectFieldContentsList&data=";
    //影视资料
    String SELECT_FILE_SIGN_LIST_NEW = Host + "act=getImageDataList&data=";
    //添加日程
    String ADD_CALENDAR = Host + "act=insertOrUpdateCalendar&data=";
    //查看日程
    String CALENDAR_LIST = Host + "act=getCalendarDetails&data=";
    //删除日程
    String DELETE_CALENDAR = Host + "act=deleteCalendar&data=";
    //修改日程
    String UPDATE_CALENDAR = Host + "act=insertOrUpdateCalendar&data=";
    //患者列表
    String PATIENTS_LIST = Host + "act=getPatientslist&data=";
    //删除患者
    String DELETE_PATIENT = Host + "act=DeletePatient&data=";
    //查询病程报告
    String SELECT_FIELD_RECORD_LIST_FORM = Host + "act=selectFieldRecordListForm&data=";
    //获取pdf下载链接
    String TO_PDF = Host + "act=toPdf&data=";
}
