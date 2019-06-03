package com.wd.yiyangyun.mvp.home.view.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.home.bean.AllPatientBean;
import com.wd.yiyangyun.mvp.home.bean.DoctorBean;
import com.wd.yiyangyun.mvp.home.bean.HospitalBean;
import com.wd.yiyangyun.mvp.home.bean.KeShiBean;
import com.wd.yiyangyun.mvp.mine.bean.ResultBean;
import com.wd.yiyangyun.mvp.patient.bean.CardBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.JSONUtil;
import com.wd.yiyangyun.utils.LogUtil;
import com.wd.yiyangyun.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddZhuanZhenActivity extends BaseActivity {

    private Button baocun_bt;
    private TextView doctor_select;
    private TextView keshi_select;
    private TextView hospital_select;
    private TextView patient_select;
    private TextView title_name;
    private ImageView title_back;
    private OptionsPickerView pvOptions;
    private String hospitalID;
    private String keShiID;
    private String doctorId;
    private String patientId;
    private LinearLayout select_ll;
    public static String SHUAIXIN = "shuaixizhuanzhen";

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_add_zhuan_zhen;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        select_ll = findViewById(R.id.select_ll);
        patient_select = findViewById(R.id.patient_select);
        hospital_select = findViewById(R.id.hospital_select);
        keshi_select = findViewById(R.id.keshi_select);
        doctor_select = findViewById(R.id.doctor_select);
        baocun_bt = findViewById(R.id.baocun_bt);
        title_name.setText("添加转诊");
        title_back.setVisibility(View.VISIBLE);
        title_name.setVisibility(View.VISIBLE);

        try {
            patientId = getIntent().getStringExtra("patientID");
            if(patientId!=null){
                select_ll.setVisibility(View.GONE);
            }else{
                select_ll.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initData() {
        super.initData();
        mCardItem = new ArrayList<>();
    }

    @Override
    protected void initListener() {
        super.initListener();
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        patient_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initOptionPicker(patient_select);
                requestPatient();
            }
        });
        hospital_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initOptionPicker(hospital_select);
                requestHospital();
            }
        });
        keshi_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initOptionPicker(keshi_select);
                requestKeShi();
            }
        });
        doctor_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initOptionPicker(doctor_select);
                requestDoctor();
            }
        });
        baocun_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addZhuanZhen();
            }
        });

    }
//
    private void addZhuanZhen() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("patientID",patientId);
        map.put("fromHospitalID",DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));
        map.put("toHospitalID",hospitalID);
        map.put("fromDepartmentID",DoctorBaseAppliction.spUtil.getString(Constants.DepartmentID, ""));
        map.put("toDepartmentID",keShiID);
        map.put("fromUserID",DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        map.put("toUserID",doctorId);
        String json = JSONUtil.parseMapToJson(map);
        LogUtil.e("添加患者转诊",json);
        DoctorNetService.requestResult(Constants.ADD_ZHUANZHEN, map,
                new NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        ResultBean zhuanZhenBean = (ResultBean) info;
                        ToastUtils.showMessage(AddZhuanZhenActivity.this,zhuanZhenBean.getData().getData());
                        Map<String, Object> map = new HashMap<>();
                        map.put(Constants.EVENTBUS_TYEPE,SHUAIXIN);
                        EventBus.getDefault().post(map);
                        finish();
                    }
                });

    }


    /**
     * 请求数据字典
     */
    private void requestHospital() {
        DoctorNetService.requestHospitals(Constants.QUERY_HOSPITAL, new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        LogUtil.e("eorrrrrrrrrrrr");
                    }

                    @Override
                    public void onNext(Object info) {
                        HospitalBean contentBean = (HospitalBean) info;
                        mCardItem.clear();
                        if (contentBean.getData().getList() != null && contentBean.getData().getList().size() > 0) {
                            for (HospitalBean.DataBean.ListBean mBean : contentBean.getData().getList()) {
                                CardBean bean = new CardBean(mBean.getHospitalID()+"", mBean
                                        .getHospitalName());
                                mCardItem.add(bean);
                            }
                            pvOptions.setPicker(mCardItem);
                            pvOptions.show();
                        }
                    }
                });
    }


    /**
     * 请求数据字典
     */
    private void requestKeShi() {
        Map<String, Object> map = new HashMap<>();
        map.put("hospitalID", hospitalID);
        DoctorNetService.requestKeShi(Constants.QUERY_KESHI,map, new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        LogUtil.e("eorrrrrrrrrrrr");
                    }

                    @Override
                    public void onNext(Object info) {
                        KeShiBean contentBean = (KeShiBean) info;
                        mCardItem.clear();
                        if (contentBean.getData().getList() != null && contentBean.getData().getList().size() > 0) {
                            for (KeShiBean.DataBean.ListBean mBean : contentBean.getData().getList()) {
                                CardBean bean = new CardBean(mBean.getId()+"", mBean
                                        .getDepartmentName());
                                mCardItem.add(bean);
                            }
                            pvOptions.setPicker(mCardItem);
                            pvOptions.show();
                        }
                    }
                });
    }

    /**
     * 请求数据字典
     */
    private void requestDoctor() {
        Map<String, Object> map = new HashMap<>();
        map.put("hospitalID", hospitalID);
        map.put("departmentID", keShiID);
        DoctorNetService.requestDoctor(Constants.QUERY_DOCTOR, map,new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        LogUtil.e("eorrrrrrrrrrrr");
                    }

                    @Override
                    public void onNext(Object info) {
                        DoctorBean contentBean = (DoctorBean) info;
                        mCardItem.clear();
                        if (contentBean.getData().getList() != null && contentBean.getData().getList().size() > 0) {
                            for (DoctorBean.DataBean.ListBean mBean : contentBean.getData().getList()) {
                                CardBean bean = new CardBean(mBean.getUserID()+"", mBean
                                        .getName());
                                mCardItem.add(bean);
                            }
                            pvOptions.setPicker(mCardItem);
                            pvOptions.show();
                        }
                    }
                });
    }


    /**
     * 请求数据字典
     */
    private void requestPatient() {
        Map<String, Object> map = new HashMap<>();
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID,""));
        DoctorNetService.requestPatient(Constants.QUERY_PATIENTZIDIAN, map,new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        LogUtil.e("eorrrrrrrrrrrr");
                    }

                    @Override
                    public void onNext(Object info) {
                        AllPatientBean contentBean = (AllPatientBean) info;
                        mCardItem.clear();
                        if (contentBean.getData().getList() != null && contentBean.getData().getList().size() > 0) {
                            for (AllPatientBean.DataBean.ListBean mBean : contentBean.getData().getList()) {
                                CardBean bean = new CardBean(mBean.getId()+"", mBean
                                        .getName());
                                mCardItem.add(bean);
                            }
                            pvOptions.setPicker(mCardItem);
                            pvOptions.show();
                        }
                    }
                });
    }



    /**
     * 初始化弹窗
     *
     * @param view 点击的哪个view
     */
    private List<CardBean> mCardItem;

    private void initOptionPicker(final TextView view) {//条件选择器初始化
        pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView
                .OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                if (hospital_select == view) {
                    String tx = mCardItem.get(options1).cardItemName;
                    hospitalID = mCardItem.get(options1).cardItemId;
                    view.setText(tx);
                } else if (keshi_select == view) {
                    String tx = mCardItem.get(options1).cardItemName;
                    keShiID = mCardItem.get(options1).cardItemId;
                    view.setText(tx);
                } else if (doctor_select == view) {
                    String tx = mCardItem.get(options1).cardItemName;
                    doctorId = mCardItem.get(options1).cardItemId;
                    view.setText(tx);
                } else if (patient_select == view) {
                    String tx = mCardItem.get(options1).cardItemName;
                    patientId = mCardItem.get(options1).cardItemId;
                    view.setText(tx);
                }
            }
        })
                .setContentTextSize(20)//设置滚轮文字大小
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setCancelColor(getResources().getColor(R.color.main_color))
                .setSubmitColor(getResources().getColor(R.color.main_color))
                .setLineSpacingMultiplier(2.0f)//设置两横线之间的间隔倍数
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
    }



    @Override
    public Context context() {
        return null;
    }
}
