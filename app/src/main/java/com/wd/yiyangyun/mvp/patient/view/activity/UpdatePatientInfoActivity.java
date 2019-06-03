package com.wd.yiyangyun.mvp.patient.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.bean.GroupListBean;
import com.wd.yiyangyun.mvp.mine.bean.ResultBean;
import com.wd.yiyangyun.mvp.patient.bean.BingchengListBean;
import com.wd.yiyangyun.mvp.patient.bean.CardBean;
import com.wd.yiyangyun.mvp.patient.bean.PatientInfoBean;
import com.wd.yiyangyun.mvp.patient.bean.SelectGroupBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.HcUtils;
import com.wd.yiyangyun.utils.JSONUtil;
import com.wd.yiyangyun.utils.LogUtil;
import com.wd.yiyangyun.utils.ToastUtils;
import com.wd.yiyangyun.utils.idutils.IDCardInfoExtractor;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.wd.yiyangyun.mvp.patient.view.activity.AddPatientInfoActivity.ADD_PATIENT_SUCCESS;
import static com.wd.yiyangyun.mvp.patient.view.activity.FristGroupActivity.FRIST_GROUP;
import static com.wd.yiyangyun.utils.DateUtils.FORMAT_5;
import static com.wd.yiyangyun.utils.DateUtils.getDayTime;
import static com.wd.yiyangyun.utils.DateUtils.getMonthTime;
import static com.wd.yiyangyun.utils.DateUtils.getYearTime;
import static com.wd.yiyangyun.utils.SynchniceUtil.requestBingChengList;
import static com.wd.yiyangyun.utils.idutils.IDCardInfoExtractor.cityCodeMap;

public class UpdatePatientInfoActivity extends BaseActivity {
    private Activity mContent = this;
    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private EditText add_patient_info_name;
    private EditText add_patient_info_phone;
    private EditText add_patient_info_idno;
    private EditText add_patient_info_age;
    private TextView add_patient_info_sex;
    private EditText add_patient_info_no;
    private TextView add_patient_info_address;
    private TextView add_patient_info_shengri;
    private boolean isAdd;
    private String user_id;
    private OptionsPickerView pvOptions;
    private String patientID;
    private EditText add_patient_info_beiyong_phone;
    private CityPicker mCP;
    private int age;
    private Switch zd_sw;
    private TextView patient_group;
    String isFocus = "0";
    private PatientInfoBean infoBean;
    private List<SelectGroupBean.GroupListBean> groupListBeans;
    private EditText add_patient_info_num;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_add_patient;
    }

    @Override
    protected void initViews() {
        super.initViews();
        EventBus.getDefault().register(this);

        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        add_patient_info_name = (EditText) findViewById(R.id.add_patient_info_name);
        add_patient_info_phone = (EditText) findViewById(R.id.add_patient_info_phone);
        add_patient_info_idno = (EditText) findViewById(R.id.add_patient_info_idno);
        add_patient_info_num = (EditText) findViewById(R.id.add_patient_info_num);
        add_patient_info_age = (EditText) findViewById(R.id.add_patient_info_age);
        add_patient_info_sex = (TextView) findViewById(R.id.add_patient_info_sex);
        add_patient_info_no = (EditText) findViewById(R.id.add_patient_info_no);
        add_patient_info_address = (TextView) findViewById(R.id.add_patient_info_address);
        add_patient_info_beiyong_phone = (EditText) findViewById(R.id
                .add_patient_info_beiyong_phone);
        add_patient_info_shengri = (TextView) findViewById(R.id.add_patient_info_shengri);
        zd_sw = (Switch) findViewById(R.id.zd_sw);
        patient_group = (TextView) findViewById(R.id.patient_group);

        isAdd = getIntent().getBooleanExtra("isAdd", true);
        patientID = getIntent().getStringExtra("patientID");
        if (!isAdd) {
            infoBean = (PatientInfoBean) getIntent().getSerializableExtra("info");
        }
        initTimePicker();

        if (groupListBeans!=null) {
            try {
            //是否是重点关注
            if(infoBean.patientDetails.isFocus.equals("1")){
                zd_sw.setChecked(true);
            }
            StringBuilder sbName = new StringBuilder();
            GroupListBean bingchengListBean = requestBingChengList();
            List<GroupListBean.DataBean.ListBean> firstDiagnoseListBeans =  bingchengListBean.getData().getList();
                for(int i=0; i< firstDiagnoseListBeans.size(); i++){
                    for(int j = 0; j< groupListBeans.size(); j++){
                        Log.e("tttttt", "initData:0   "+firstDiagnoseListBeans.get(i).getId());
                        Log.e("tttttt", "initData:1   "+ groupListBeans.get(j).getGroupID() );
                        if(firstDiagnoseListBeans.get(i).getId().equals(groupListBeans.get(j).getGroupID())){
                            Log.e("ttttt", "initData: "+"包含"+ groupListBeans.get(j).getGroupID());
                            firstDiagnoseListBeans.get(i).isCheck = true;
                        }
                    }
                }

                //去掉逗号
                String groupName = sbName.substring(0,sbName.length()-1);
                patient_group.setText(groupName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
            //是否是重点关注
            if(infoBean.patientDetails.isFocus.equals("1")){
                zd_sw.setChecked(true);
            }
            StringBuilder sbName = new StringBuilder();
            GroupListBean bingchengListBean = requestBingChengList();
            List<GroupListBean.DataBean.ListBean> firstDiagnoseListBeans =  bingchengListBean.getData().getList();
            groupListBeans = new ArrayList<>();

                for(int i=0; i< firstDiagnoseListBeans.size(); i++){
                    for(int j = 0; j< infoBean.patientDetails.groupList.size(); j++){
                        Log.e("tttttt", "initData:0   "+firstDiagnoseListBeans.get(i).getId());
                        Log.e("tttttt", "initData:1   "+ infoBean.patientDetails.groupList.get(j).groupID );
                        if(firstDiagnoseListBeans.get(i).getId().equals(infoBean.patientDetails.groupList.get(j).groupID)){
                            Log.e("ttttt", "initData: "+"包含"+ infoBean.patientDetails.groupList.get(j).groupID);
                            sbName.append(firstDiagnoseListBeans.get(i).getGroupName()+",");
                            SelectGroupBean.GroupListBean selectGroupBean2 =  new SelectGroupBean.GroupListBean(firstDiagnoseListBeans.get(i).getId());
                            groupListBeans.add(selectGroupBean2);
                            firstDiagnoseListBeans.get(i).isCheck = true;
                        }
                    }
                }
                String groupName = sbName.substring(0,sbName.length()-1);
                patient_group.setText(groupName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (isAdd) {
            title_name.setText("添加患者信息");
            title_right_tv.setText("提交");
        } else {
            title_name.setText("修改患者信息");
            title_right_tv.setText("保存");
        }
        title_right_tv.setVisibility(View.VISIBLE);
        title_name.setVisibility(View.VISIBLE);
        title_back.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        initListener();
    }

    @Override
    protected void initData() {
        super.initData();

        mCardItem = new ArrayList<>();
        user_id = DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, "");
        if (!isAdd) {
            add_patient_info_name.setHint("");
            patient_group.setHint("");
            add_patient_info_phone.setHint("");
            add_patient_info_beiyong_phone.setHint("");
            add_patient_info_idno.setHint("");
            add_patient_info_num.setHint("");
            add_patient_info_age.setHint("");
            add_patient_info_sex.setHint("");
            add_patient_info_no.setHint("");
            add_patient_info_address.setHint("");
            add_patient_info_shengri.setHint("");

            add_patient_info_name.setText(infoBean.patientDetails.name);
            add_patient_info_phone.setText(infoBean.patientDetails.mobile);
            add_patient_info_idno.setText(infoBean.patientDetails.idCard);
            add_patient_info_num.setText(infoBean.patientDetails.num);
            add_patient_info_age.setText(infoBean.patientDetails.age);
            add_patient_info_sex.setText(infoBean.patientDetails.sex);
            add_patient_info_no.setText(infoBean.patientDetails.num);
            add_patient_info_address.setText(infoBean.patientDetails.address);
            add_patient_info_shengri.setText(infoBean.patientDetails.brithday);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        zd_sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!isAdd){
                    if(b){
                        infoBean.patientDetails.isFocus = "1";
                    }else{
                        infoBean.patientDetails.isFocus = "0";
                    }
                }else{
                    if(b){
                        isFocus = "1";
                    }else{
                        isFocus = "0";

                    }
                }

            }
        });
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HcUtils.hideKeyboard(mContent);
                finish();
            }
        });
        title_right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isAdd){
                    update();
                }
            }
        });
        add_patient_info_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HcUtils.hideKeyboard(mContent);
                initOptionAgePicker(add_patient_info_sex);
                showSelectDialog();
            }
        });
        patient_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HcUtils.hideKeyboard(mContent);
                //第一次诊断
                Intent intent = new Intent(mContent, FristGroupActivity.class);
                intent.putExtra("groupListBeans", (Serializable) groupListBeans);
                    startActivity(intent);

            }
        });
        add_patient_info_shengri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HcUtils.hideKeyboard(mContent);
                pvTime.show(add_patient_info_shengri);

            }
        });
        add_patient_info_idno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence) && charSequence.length() == 18) {
                    IDCardInfoExtractor idCardInfo = new IDCardInfoExtractor
                            (charSequence.toString());
                    //从身份证号码中提取出生日期
                    String birthDay = idCardInfo.getYear() + "-" + idCardInfo.getMonth() + "-" +
                            idCardInfo.getDay();
                    //从身份证号中提取性别
                    String gender = idCardInfo.getGender();
                    add_patient_info_shengri.setText(birthDay);
                    add_patient_info_sex.setText(gender);
                    age = idCardInfo.getAge();
                    add_patient_info_age.setText(age + "");
                    add_patient_info_address.setText(idCardInfo.getProvince());
                    if(age==0){
                        idCardError(charSequence.toString());
                    }else{
                        Log.e("ttttttt", "onTextChanged: "+age);
                    }
                } else {
                    add_patient_info_shengri.setText("");
                    add_patient_info_sex.setText("");
                    add_patient_info_age.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        add_patient_info_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HcUtils.hideKeyboard(mContent);
                mYunCityPicher();
                mCP.show();
            }
        });
    }


    private void update() {
        // validate
        String name = add_patient_info_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入患者姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String idno = add_patient_info_idno.getText().toString().trim();
        if (TextUtils.isEmpty(idno)) {
            Toast.makeText(this, "请输入身份证号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (idno.length() != 18) {
            Toast.makeText(this, "请输入正确的18位身份证号", Toast.LENGTH_SHORT).show();
            return;
        }
        String age = add_patient_info_age.getText().toString().trim();
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "请输入患者年龄", Toast.LENGTH_SHORT).show();
            return;
        }

        String sex = add_patient_info_sex.getText().toString().trim();
        if (TextUtils.isEmpty(sex)) {
            Toast.makeText(this, "请选择患者性别", Toast.LENGTH_SHORT).show();
            return;
        }

        String num = add_patient_info_num.getText().toString().trim();
        if (TextUtils.isEmpty(num)) {
            Toast.makeText(this, "请输入患者编号", Toast.LENGTH_SHORT).show();
            return;
        }

        String address = add_patient_info_address.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请选择患者居住地", Toast.LENGTH_SHORT).show();
            return;
        }

        String shengri = add_patient_info_shengri.getText().toString().trim();
        if (TextUtils.isEmpty(shengri)) {
            Toast.makeText(this, "请选择患者出生年月", Toast.LENGTH_SHORT).show();
            return;
        }
        String phone = add_patient_info_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入联系方式", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.length() != 11) {
            Toast.makeText(this, "请输入正确的11位手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        String beiyong_phone = add_patient_info_beiyong_phone.getText().toString().trim();
        /**
         * act=insertPatient&data={"UserID":1,"mobileType":"2",
         "optionTag":"insert",
         "moduleCode":"SP0201",
         "name":"005",
         "sex":"男","age":66,
         "mobile":"18880868898",
         "idCard":"",
         "num":"1231233",
         "address":"",
         "brithday":"2018-08-08"
         }
         */
        Map<String, Object> map = new HashMap<>();
        map.put("userID", user_id);
        map.put("mobileType", Constants.MOBILE_TYPE);
        map.put("optionTag", isAdd ? "insert" : "update");
        map.put("name", name);
        map.put("sex", sex);
        map.put("age", age);
        map.put("mobile", phone);
        map.put("idCard", idno);
        map.put("num", num);
        map.put("address", address);
        map.put("brithday", shengri);
        map.put("mobile2", beiyong_phone);
        map.put("isFocus", isAdd ? isFocus : infoBean.patientDetails.isFocus);
        map.put("groupList", groupListBeans);
        map.put("id", patientID);
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));
        requestAddPatientInfo(map);
    }

    private void requestAddPatientInfo(Map<String, Object> map) {
        showWaitDialog();
        DoctorNetService.requestResult(isAdd ? Constants.ADD_PATIENT :
                Constants.UPADTE_PATIENT, map, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        ResultBean bean = (ResultBean) info;
                        ToastUtils.showMessage(mContext, bean.getData().getData());
                        //修改的时候需要用
                        Map<String, Object> map = new HashMap<>();
                        map.put(Constants.EVENTBUS_TYEPE, ADD_PATIENT_SUCCESS);
                        EventBus.getDefault().post(map);
                        HcUtils.hideKeyboard(UpdatePatientInfoActivity.this);
                        finish();
                    }
                });
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void idCardError(String idCard) {
        String address = null;
        //从身份证号码中提取出生日期
        String birthDay = idCard.substring(6,10) + "-" + idCard.substring(10,12) + "-" +
                idCard.substring(12,14);
        //从身份证号中提取性别
        String gender = idCard.substring(16,17);
        if(gender.equals("1")||gender.equals("3")||gender.equals("5")||gender.equals("7")||gender.equals("9")){
            gender = "男";
        }else{
            gender = "女";
        }
        //获取年龄
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(simpleDateFormat.format(new Date()));
        int b = Integer.parseInt(idCard.substring(6,10));
        int age = year - b;
        String provinceId = idCard.substring(0, 2);
        Set<String> key = cityCodeMap.keySet();
        for (String id : key) {
            if (id.equals(provinceId)) {
                address = cityCodeMap.get(id);
                break;
            }
        }
        add_patient_info_shengri.setText(birthDay);
        add_patient_info_sex.setText(gender);
        add_patient_info_age.setText(age+"");
        add_patient_info_address.setText(address);
        Log.e("ttttt", "onCreate: "+gender+" "+age+" "+address+" "+birthDay );
        System.out.println(birthDay+""+"\n"+gender+"\n"+age+""+"\n"+address);
    }
    /**
     * 初始化时间弹框
     */
    private void initTimePicker() {
        String yearTime = getYearTime(new Date(System.currentTimeMillis()));
        String monthTime = getMonthTime(new Date(System.currentTimeMillis()));
        String dayTime = getDayTime(new Date(System.currentTimeMillis()));
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(Integer.parseInt(yearTime), Integer.parseInt(monthTime) - 1, Integer
                .parseInt(dayTime));
        pvTime = new TimePickerView.Builder(this, new TimePickerView
                .OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {
                String found_date = com.wd.yiyangyun.utils.DateUtils.dateToString(date, FORMAT_5);
                ((TextView) v).setText(found_date);
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setCancelColor(getResources().getColor(R.color.main_color))
                .setSubmitColor(getResources().getColor(R.color.main_color))
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    /**
     * 展示下拉选择框
     */
    private void showSelectDialog() {
        mCardItem.clear();
        CardBean cardBean1 = new CardBean("1", "男");
        CardBean cardBean2 = new CardBean("0", "女");
        mCardItem.add(cardBean1);
        mCardItem.add(cardBean2);
        pvOptions.setPicker(mCardItem);
        pvOptions.show();
    }



    /**
     * 初始化弹窗
     *
     * @param view 点击的哪个view
     */
    private List<CardBean> mCardItem;

    private void initOptionAgePicker(final TextView view) {//条件选择器初始化
        pvOptions = new OptionsPickerView.Builder(mContext, new OptionsPickerView
                .OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = mCardItem.get(options1).cardItemId;
                view.setText(tx);
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

    public void mYunCityPicher() {
        mCP = new CityPicker.Builder(mContent)
                .textSize(20)
                //地址选择
                .title("")
                //文字的颜色
                .titleBackgroundColor("#ffffff")
                .titleTextColor("#3e85d2")
                .backgroundPop(0x66000000)
                .confirTextColor("#3e85d2")
                .cancelTextColor("#3e85d2")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                //滑轮文字的颜色
                .textColor(Color.parseColor("#000000"))
                //省滑轮是否循环显示
                .provinceCyclic(true)
                //市滑轮是否循环显示
                .cityCyclic(false)
                //地区（县）滑轮是否循环显示
                .districtCyclic(false)
                //滑轮显示的item个数
                .visibleItemsCount(7)
                //滑轮item间距
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();

        //监听
        mCP.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省
                String province = citySelected[0];
                //市
                String city = citySelected[1];
                //区。县。（两级联动，必须返回空）
                String district = citySelected[2];
                //邮证编码
                add_patient_info_address.setText(province + city + district);
//                mDiQuSanJiLianDong.setText(province + city + district);
            }

            @Override
            public void onCancel() {


            }
        });
    }

    @Subscribe
    public void onEventMainThread(Map<String, Object> map) {
        String type = (String) map.get(Constants.EVENTBUS_TYEPE);
        if (type.equals(FRIST_GROUP)) {
            String result = (String) map.get(Constants.EVENTBUS_VALUE);
            String groupId = (String) map.get("groupId");
            String[] split = groupId.split(",");
            groupListBeans.clear();
            for(int i=0; i< split.length; i++){
                SelectGroupBean.GroupListBean selectGroupBean2 =  new SelectGroupBean.GroupListBean(split[i]);
                groupListBeans.add(selectGroupBean2);
            }
            //去掉逗号
            String groupName = result.substring(0,result.length()-1);
            patient_group.setText(groupName);
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public Context context() {
        return null;
    }
}
