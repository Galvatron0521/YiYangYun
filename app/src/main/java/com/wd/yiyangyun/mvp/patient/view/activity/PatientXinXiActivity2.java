//package com.wd.yiyangyun.mvp.patient.view.activity;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.wd.yiyangyun.R;
//import com.wd.yiyangyun.app.Constants;
//import com.wd.yiyangyun.app.DoctorBaseAppliction;
//import com.wd.yiyangyun.base.BaseActivity;
//import com.wd.yiyangyun.mvp.home.view.activity.zhuanzhen.ZhuanZhenActivity;
//import com.wd.yiyangyun.mvp.home.view.activity.zhuanzhen.ZhuanZhenInfoActivity;
//import com.wd.yiyangyun.mvp.patient.bean.PatientInfoBean;
//import com.wd.yiyangyun.okutils.DoctorNetService;
//import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
//import com.wd.yiyangyun.utils.GlideCircleTransUtils;
//import com.wd.yiyangyun.utils.LogUtil;
//import com.wd.yiyangyun.utils.ToastUtils;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class PatientXinXiActivity extends BaseActivity {
//
//    private LinearLayout suifang_xinxi_ll;
//    private LinearLayout bingcheng_ll;
//    private LinearLayout patient_info_ll;
//    private TextView patient_info_tv;
//    private TextView patient_name_tv;
//    private String childId;
//    private String name;
//    private TextView title_name;
//    private ImageView title_back;
//    private PatientInfoBean patientInfoBean;
//    private ImageView patient_iv;
//    private LinearLayout zhuanzhen_ll;
//
//    @Override
//    protected Activity provideBindView() {
//        return this;
//    }
//
//    @Override
//    protected int provideLayoutId() {
//        return R.layout.activity_patient_xin_xi;
//    }
//
//    @Override
//    protected void initViews() {
//        super.initViews();
//        EventBus.getDefault().register(this);
//
//        title_back = (ImageView) findViewById(R.id.title_back);
//        title_name = (TextView) findViewById(R.id.title_name);
//        patient_iv = findViewById(R.id.patient_iv);
//        patient_name_tv = findViewById(R.id.patient_name_tv);
//        patient_info_tv = findViewById(R.id.patient_info_tv);
//        patient_info_ll = findViewById(R.id.patient_info_ll);
//        bingcheng_ll = findViewById(R.id.bingcheng_ll);
//        suifang_xinxi_ll = findViewById(R.id.suifang_xinxi_ll);
//        zhuanzhen_ll = findViewById(R.id.zhuanzhen_ll);
//        title_name.setText("患者详情");
//        title_back.setVisibility(View.VISIBLE);
//        title_name.setVisibility(View.VISIBLE);
//
//
//        childId = getIntent().getStringExtra("childId");
//        name = getIntent().getStringExtra("name");
//        LogUtil.e("ttttttt",childId);
//        LogUtil.e("ttttttt0",name);
//        if(name!=null){
//            patient_name_tv.setText(name);
//        }
//        Glide.with(this)
//                .load(Constants.PATIENT_IV)
//                .transform(new GlideCircleTransUtils(this))
//                .into(patient_iv);
//
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
//        requestPatientInfo();
//
//    }
//
//    private void requestPatientInfo() {
//        showWaitDialog();
//        Map<String, Object> map = new HashMap<>();
//        map.put("patientID", childId);
//        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
//        DoctorNetService.requestPatientDetail(Constants.GET_PATIENT_DETAILS, map, new
//                NetWorkRequestInterface() {
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                        hideWaitDialog();
//                        ToastUtils.showMessage(mContext, "请求患者详情失败");
//                    }
//
//                    @Override
//                    public void onNext(Object info) {
//                        hideWaitDialog();
//                        patientInfoBean = (PatientInfoBean) info;
//                        patient_name_tv.setText(patientInfoBean.patientDetails.name);
//                    }
//                });
//    }
//
//    @Override
//    protected void initListener() {
//        super.initListener();
//        //
//        title_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//             finish();
//            }
//        });
//
//        zhuanzhen_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(PatientXinXiActivity.this,ZhuanZhenInfoActivity.class);
//                intent.putExtra("patientID",childId);
//                intent.putExtra("name",patientInfoBean.patientDetails.name);
//                startActivity(intent);
//            }
//        });
//
//        //基本信息 (修改)
//        patient_info_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(PatientXinXiActivity.this,UpdatePatientInfoActivity.class);
//                intent.putExtra("isAdd",false);
//                intent.putExtra("info", patientInfoBean);
//                intent.putExtra("patientID", childId);
//                startActivity(intent);
//            }
//        });
//        //病程
//        bingcheng_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(PatientXinXiActivity.this,BingChengManageListActivity.class);
//                intent.putExtra("childId",childId);
//                intent.putExtra("name",name);
//                startActivity(intent);
//            }
//        });
//        //随访信息
//        suifang_xinxi_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, SuiFangListActivity.class);
//                intent.putExtra("patientID", childId);
//                intent.putExtra("name", patientInfoBean.patientDetails.name);
//                startActivity(intent);
//            }
//        });
//
//    }
//
//    @Subscribe
//    public void onEventMainThread(Map<String, Object> map) {
//        String type = (String) map.get(Constants.EVENTBUS_TYEPE);
////        if (type.equals(SelectPatientTagActivity.CHANGE_TAG_SUCCESS) || type.equals
////                (TagManageActivity.TAG_HAS_CHANGE)) {
////            requestPatientInfo();
////        }
//        if (type.equals(AddPatientInfoActivity.ADD_PATIENT_SUCCESS)) {
//            requestPatientInfo();
//        }
//    }
//
//
//
//    @Override
//    public Context context() {
//        return null;
//    }
//}
