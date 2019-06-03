package com.wd.yiyangyun.mvp.home.view.activity.suifangfangan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.adapter.AddMyPlanListAdapter;
import com.wd.yiyangyun.mvp.mine.bean.ResultBean;
import com.wd.yiyangyun.mvp.mine.bean.MyPlanListBean;
import com.wd.yiyangyun.mvp.patient.bean.CardBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.HcUtils;
import com.wd.yiyangyun.utils.LogUtil;
import com.wd.yiyangyun.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wd.yiyangyun.mvp.home.view.activity.suifangfangan.SelectActivity.MY_PLAN_SELECT2;


public class AddMyPlanActivity extends BaseActivity {

    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private EditText my_plan_name;
    private TextView my_subject_renqun_zhenduan;
    private TextView my_plan_jizhun_time;
    private MyListView my_plan_task_list;
    private Switch my_plan_switch;
    private ImageView my_plan_add_task;
    private List<MyPlanListBean.FollowlistBean.ProjectlistBean> mDatas;
    private AddMyPlanListAdapter addMyPlanListAdapter;
    private MyPlanListBean.FollowlistBean bean;
    private boolean isAdd;
    private OptionsPickerView pvOptions;
    private List<CardBean> items1;
    private List<List<CardBean>> items2;
    private List<List<List<CardBean>>> items3;
    private MyPlanListBean.FollowlistBean.ProjectlistBean currentSelectBean;
    private int currentSelectPosition;
    public static String CHANG_FOLLOW_SUCCESS = "Chang_Follow_Success";
    private String followprojectID;
    private List<MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean.OptionlistBean> newlist;
    private String position2;
    private Context mContext = this;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_add_my_plan;
    }

    @Override
    protected void initData() {
        super.initData();

        mDatas = new ArrayList<>();
        mCardItem = new ArrayList<>();
        addMyPlanListAdapter = new AddMyPlanListAdapter(mDatas, mContext);
        my_plan_task_list.setAdapter(addMyPlanListAdapter);

        if (isAdd) {
            //
            title_right_tv.setText("保存");
            bean = new MyPlanListBean.FollowlistBean();
            MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean = new MyPlanListBean
                    .FollowlistBean.ProjectlistBean();
            projectlistBean.project = new MyPlanListBean.FollowlistBean.ProjectlistBean
                    .ProjectBean();
            projectlistBean.project.scope = "0";
            mDatas.add(projectlistBean);
            my_plan_switch.setChecked(false);
            bean.isShared=0;
        } else {
            title_right_tv.setText("保存");
            my_plan_name.setText("修改随访方案");
            my_plan_jizhun_time.setText("1".equals(bean.baseDate) ? "首诊日期" : "其他");
            mDatas.addAll(bean.projectlist);
            if (bean.projectlist != null) {
                if (bean.projectlist.size() > 0) {
                    if (bean.projectlist.get(0).project.optionlist != null && bean.projectlist
                            .get(0).project.optionlist.size() > 0) {
                        for (MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean :
                                bean.projectlist) {
                            for (MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean
                                    .OptionlistBean
                                    optionlistBean : projectlistBean.project.optionlist) {
                                if (optionlistBean.taskOption.equals("0")) {
                                    projectlistBean.project.task_name = "量化指标";
                                } else if(optionlistBean.taskOption.equals("1")) {
                                    projectlistBean.project.task_name2 = "量表";
                                }
                                else if(optionlistBean.taskOption.equals("2")) {
                                    projectlistBean.project.task_name3 = "提醒医师填写";
                                }
                            }
                        }
                    }
                }
            }
            addMyPlanListAdapter.notifyDataSetChanged();
            changeViewState();
        }
        initPickerData();
    }


    /**
     * 改变view状态为不可编辑
     */
    private void changeViewState() {
        my_plan_name.setFocusable(false);
        my_plan_jizhun_time.setEnabled(false);
        my_plan_switch.setEnabled(false);

    }

    private void initPickerData() {
        items1 = new ArrayList<>();
        List<CardBean> tempItems2 = new ArrayList<>();
        items2 = new ArrayList<>();
        items3 = new ArrayList<>();
        List<CardBean> tempItems3 = new ArrayList<>();
        List<List<CardBean>> tempItems33 = new ArrayList<>();
        items1.add(new CardBean("后", "后"));
        for (int i = 0; i <= 30; i++) {
            tempItems2.add(new CardBean((i) + "", (i) + ""));
        }
        items2.add(tempItems2);
        items2.add(tempItems2);

        tempItems3.add(new CardBean("天", "天"));
        tempItems3.add(new CardBean("周", "周"));
        tempItems3.add(new CardBean("月", "月"));
        for (int i = 0; i <= 99; i++) {
            tempItems33.add(tempItems3);
            items3.add(tempItems33);
        }

    }

    @Override
    protected void initListener() {
        super.initListener();

        my_plan_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Log.e("Tttttttt", "changeViewState  : 111");
                    my_plan_switch.setChecked(true);
                    bean.isShared=1;
                }else{
                    my_plan_switch.setChecked(false);
                    bean.isShared=0;
                    Log.e("Tttttttt", "changeViewState  : 000");
                }
            }
        });
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title_right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();

            }
        });
        addMyPlanListAdapter.setOnButtonClick(new AddMyPlanListAdapter.onButtonClick() {
            @Override
            public void onButtonClick(int position, int type) {
                //         * @param type     0 删除 1 添加任务 2 时间选择
                currentSelectBean = mDatas.get(position);
                currentSelectPosition = position;
                switch (type) {
                    case 0:
                        if (!isAdd) return;
                        mDatas.remove(position);
                        addMyPlanListAdapter.notifyDataSetChanged();
                        break;
                    case 1: //0  量化指标
                        if (!isAdd) {
                            if (!TextUtils.isEmpty(currentSelectBean.project.task_name)) {
                                Intent intent = new Intent(mContext, SelectMyPlanListActivity
                                        .class);
                                intent.putExtra("taskOption", "0");
                                List<MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean
                                        .OptionlistBean> tempList = new ArrayList<>();
                                for (MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean
                                        .OptionlistBean optionlistBean : currentSelectBean
                                        .project.optionlist) {
                                        tempList.add(optionlistBean);
                                }
                                intent.putExtra("info", (Serializable) tempList);
                                intent.putExtra("position", currentSelectPosition);
                                intent.putExtra("followprojectID", followprojectID);
                                startActivity(intent);
                            }else {
                                ToastUtils.showMessage(mContext,"无量化指标任务项目");
                            }
                        } else {
                            if (TextUtils.isEmpty(currentSelectBean.project.beforeOrAfter) ||
                                    TextUtils
                                            .isEmpty(currentSelectBean.project.unit)) {
                                ToastUtils.shortToast(mContext, "请选择任务执行时间");
                                return;
                            }
                            MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean =
                                    mDatas.get(currentSelectPosition);
                            projectlistBean.project.task_name = "量化指标";
                            Intent intent = new Intent(mContext, SelectMyPlanListActivity.class);
                            intent.putExtra("taskOption", "0");
                            intent.putExtra("info", (Serializable) projectlistBean.project
                                    .optionlist);
                            intent.putExtra("position", currentSelectPosition);
                            startActivity(intent);
                            addMyPlanListAdapter.notifyDataSetChanged();
                        }
                        break;
                    case 2:
                        if (!isAdd) return;
                        if (TextUtils.isEmpty(my_plan_jizhun_time.getText().toString().trim())) {
                            ToastUtils.shortToast(mContext, "请选择随访基准时间");
                            return;
                        }
                        HcUtils.hideKeyboard(AddMyPlanActivity.this);
                        initOptionPicker(new TextView(mContext));
                        pvOptions.setPicker(items1, items2, items3);
                        pvOptions.show();
                        break;
                    case 3: //1  量表
                        if (!isAdd) {
                            if (!TextUtils.isEmpty(currentSelectBean.project.task_name2)) {
                                Intent intent = new Intent(mContext, SelectMyPlanListActivity
                                        .class);
                                intent.putExtra("taskOption", "1");
                                List<MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean
                                        .OptionlistBean> tempList = new ArrayList<>();
                                for (MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean
                                        .OptionlistBean optionlistBean : currentSelectBean
                                        .project.optionlist) {
                                        tempList.add(optionlistBean);
                                }
                                intent.putExtra("info", (Serializable) tempList);
                                intent.putExtra("position", currentSelectPosition);
                                intent.putExtra("followprojectID", followprojectID);
                                Log.e("ttttttttt", "onButtonClick:1 "+"查看");
                                startActivity(intent);
                            }else {
                                ToastUtils.showMessage(mContext,"无量表任务项目");
                            }
                        } else {
                            if (TextUtils.isEmpty(currentSelectBean.project.beforeOrAfter) ||
                                    TextUtils
                                            .isEmpty(currentSelectBean.project.unit)) {
                                ToastUtils.shortToast(mContext, "请选择任务执行时间");
                                return;
                            }
                            MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean =
                                    mDatas.get(currentSelectPosition);
                            projectlistBean.project.task_name2 = "量表";
                            Intent intent = new Intent(mContext, SelectMyPlanListActivity.class);
                            intent.putExtra("taskOption", "1");
                            intent.putExtra("info", (Serializable) projectlistBean.project
                                    .optionlist);
                            intent.putExtra("position", currentSelectPosition);
                            Log.e("ttttttttt", "onButtonClick:1 "+"添加");
                            startActivity(intent);
                            addMyPlanListAdapter.notifyDataSetChanged();
                        }
                        break;
                    case 4:
                        if (!isAdd) {
                            if (!TextUtils.isEmpty(currentSelectBean.project.task_name3)) {
                                Intent intent = new Intent(mContext, SelectMyPlanListActivity
                                        .class);
                                intent.putExtra("taskOption", "2");
                                List<MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean
                                        .OptionlistBean> tempList = new ArrayList<>();
                                for (MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean
                                        .OptionlistBean optionlistBean : currentSelectBean
                                        .project.optionlist) {
                                        tempList.add(optionlistBean);
                                }
                                intent.putExtra("info", (Serializable) tempList);
                                intent.putExtra("position", currentSelectPosition);
                                intent.putExtra("followprojectID", followprojectID);
                                Log.e("ttttttttt", "onButtonClick:2 "+"查看");
                                startActivity(intent);
                            }else {
                                ToastUtils.showMessage(mContext,"无量化指标任务项目");
                            }
                        }else{
                            if (TextUtils.isEmpty(currentSelectBean.project.beforeOrAfter) ||
                                    TextUtils
                                            .isEmpty(currentSelectBean.project.unit)) {
                                ToastUtils.shortToast(mContext, "请选择任务执行时间");
                                return;
                            }
                            MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean =
                                    mDatas.get(currentSelectPosition);
                            projectlistBean.project.task_name3 = "提醒医师填写";
                            Intent intent = new Intent(AddMyPlanActivity.this,SelectMyPlanListActivity.class);
                            intent.putExtra("taskOption", "2");
                            intent.putExtra("info", (Serializable) projectlistBean.project
                                    .optionlist);
                            intent.putExtra("position", currentSelectPosition);
                            Log.e("ttttttttt", "onButtonClick:2 "+"添加");
                            startActivity(intent);
                            addMyPlanListAdapter.notifyDataSetChanged();
                        }

                        break;
                }
            }
        });
        my_plan_jizhun_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HcUtils.hideKeyboard(AddMyPlanActivity.this);
                initOptionPicker(my_plan_jizhun_time);
//                if (mCardItem.size() == 0) {
                requestContent();
//                } else {
//                    pvOptions.setPicker(mCardItem);
//                    pvOptions.show();
//            }
            }
        });
        my_plan_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAdd){
                    MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean = new MyPlanListBean
                            .FollowlistBean.ProjectlistBean();
                    projectlistBean.project = new MyPlanListBean.FollowlistBean.ProjectlistBean
                            .ProjectBean();
                    projectlistBean.project.scope = "0";
                    mDatas.add(projectlistBean);
                    addMyPlanListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }


    /**
     * 请求数据字典
     */
    private void requestContent() {

        Map<String, Object> map = new HashMap<>();
        map.put("typeCode", "followBaseDate");
        DoctorNetService.requestContent("http://111.17.215.37:8023/skyapp_xy/api/app_patient/?act=selectContent&data=", map, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Object info) {
                        ContentBean contentBean = (ContentBean) info;
                        mCardItem.clear();
                            if (contentBean.selectList != null && contentBean.selectList.size() > 0) {
                                for (ContentBean.SelectListBean mBean : contentBean.selectList) {
                                    CardBean bean = new CardBean(mBean.typeDetailCode, mBean
                                            .typeDetailName);
                                    mCardItem.add(bean);
                                }
                                pvOptions.setPicker(mCardItem);
                                pvOptions.show();
                            }
                    }
                });
    }

    @Override
    protected void initViews() {
        super.initViews();

        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        my_plan_name = (EditText) findViewById(R.id.my_plan_name);
        my_subject_renqun_zhenduan = (TextView) findViewById(R.id.my_subject_renqun_zhenduan);
        my_plan_jizhun_time = (TextView) findViewById(R.id.my_plan_jizhun_time);
        my_plan_task_list = (MyListView) findViewById(R.id.my_plan_task_list);
        my_plan_switch = (Switch) findViewById(R.id.my_plan_switch);
        my_plan_add_task = (ImageView) findViewById(R.id.my_plan_add_task);

        EventBus.getDefault().register(this);
        isAdd = getIntent().getBooleanExtra("isAdd", true);
        followprojectID = getIntent().getStringExtra("followprojectID");
        position2 = getIntent().getStringExtra("position");
        bean = (MyPlanListBean.FollowlistBean) getIntent().getSerializableExtra("info");


        title_back.setVisibility(View.VISIBLE);
        title_right_tv.setVisibility(View.VISIBLE);
        if(isAdd){
            title_name.setText("添加随访方案");
        }else{
            title_name.setText("修改随访方案");
        }

    }

    private void submit() {
        // validate
        String name = my_plan_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入随访方案名称", Toast.LENGTH_SHORT).show();
            return;
        }
        String jizhun_time = my_plan_jizhun_time.getText().toString().trim();
        if (TextUtils.isEmpty(jizhun_time)) {
            Toast.makeText(this, "请选择随访基准时间", Toast.LENGTH_SHORT).show();
            return;
        }
        bean.planName = name;
        bean.projectlist = new ArrayList<>();
        bean.projectlist.addAll(mDatas);
        for (int i = 0; i < bean.projectlist.size(); i++) {
            MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean = bean.projectlist.get(i);
            projectlistBean.project.taskNum = (i + 1) + "";
        }
        if (isAdd) {
            requestAddData();
        } else {
//            requestUpdateData();
        }
    }

    /**
     * 添加数据
     */
    private void requestAddData() {
        LogUtil.e("添加随访方案");
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("follow", bean);
        map.put("mobileType", Constants.MOBILE_TYPE);
        map.put("isShared", bean.isShared);
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        map.put("hospitalID",  DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));
        map.put("createUser", DoctorBaseAppliction.spUtil.getString(Constants.Name, ""));
        DoctorNetService.requestResult(Constants.ADD_PLAN, map, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                        LogUtil.e("保存失败，请重试");
//                        ToastUtils.shortToast(mContext, "保存失败，请重试");
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        ResultBean bean = (ResultBean) info;
                        ToastUtils.shortToast(mContext, bean.getData().getData());

                        Map<String, Object> map = new HashMap<>();
                        map.put(Constants.EVENTBUS_TYEPE, CHANG_FOLLOW_SUCCESS);
                        EventBus.getDefault().post(map);
                        finish();
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
                if (my_plan_jizhun_time == view) {
                    String tx = mCardItem.get(options1).cardItemName;
                    String id = mCardItem.get(options1).cardItemId;
                    bean.baseDate = id;
                    view.setText(tx);
                } else if (title_right_tv == view) {
                    MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean = mDatas.get
                            (currentSelectPosition);
                    projectlistBean.project.task_name = mCardItem.get(options1)
                            .cardItemName;
                    Intent intent = new Intent(mContext, SelectMyPlanListActivity.class);
                    intent.putExtra("taskOption", mCardItem.get(options1).cardItemId);
                    intent.putExtra("info", (Serializable) projectlistBean.project.optionlist);
                    intent.putExtra("position", currentSelectPosition);
                    startActivity(intent);
                    addMyPlanListAdapter.notifyDataSetChanged();
                } else {
                    mDatas.get(currentSelectPosition).project.unit = items3.get(0).get(0).get
                            (options3)
                            .cardItemName;
                    mDatas.get(currentSelectPosition).project.amount = items2.get(0).get(options2)
                            .cardItemName;
                    mDatas.get(currentSelectPosition).project.beforeOrAfter = items1.get(options1)
                            .cardItemName;
                    addMyPlanListAdapter.notifyDataSetChanged();
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

    @Subscribe
    public void onEventMainThread(Map<String, Object> map) {
        String type = (String) map.get(Constants.EVENTBUS_TYEPE);

        if (type.equals(MY_PLAN_SELECT2)) {
            String result = (String) map.get(Constants.EVENTBUS_VALUE);
            int position = (int) map.get("position");
            String taskOption = (String) map.get("taskOption");
            MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean2 = mDatas.get(position);
            if (projectlistBean2.project.optionlist == null) {
                projectlistBean2.project.optionlist = new ArrayList<>();
            }

            List<MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean.OptionlistBean> tichuanList = new ArrayList<>();
            for(int i=0; i< projectlistBean2.project.optionlist.size(); i++){
                Log.e("tttttt", "add原有集合   "+projectlistBean2.project.optionlist.get(i).getOptionModuleCodes()
                        +"   "+ projectlistBean2.project.optionlist.get(i).getTaskOption());
            }
            for(int i=0; i< projectlistBean2.project.optionlist.size(); i++){
                if(projectlistBean2.project.optionlist.get(i).getTaskOption().equals("0")){
                    MyPlanListBean.FollowlistBean
                            .ProjectlistBean.ProjectBean.OptionlistBean optionlistBean  = new MyPlanListBean.FollowlistBean
                            .ProjectlistBean.ProjectBean.OptionlistBean("0",projectlistBean2.project.optionlist.get(i).optionModuleCodes);
                    tichuanList.add(optionlistBean);
                }
            }
            for(int i=0; i< tichuanList.size(); i++){
                Log.e("tttttt", "add要除去的集合   "+tichuanList.get(i).getOptionModuleCodes()
                        +"   "+ tichuanList.get(i).getTaskOption());
            }
            List<MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean.OptionlistBean> newlist = null;
            try {
                newlist = new ArrayList();
                for(int i=0;i<projectlistBean2.project.optionlist.size();i++){
                    if(!tichuanList.contains(projectlistBean2.project.optionlist.get(i))){
                        //取出不同的对象
                        newlist.add(new MyPlanListBean.FollowlistBean
                                .ProjectlistBean.ProjectBean.OptionlistBean("0",projectlistBean2.project.optionlist.get(i).getOptionModuleCodes()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (projectlistBean2.project.optionlist != null) {
                projectlistBean2.project.optionlist = new ArrayList<>();
            }
            for(int i=0; i< newlist.size(); i++){
                Log.e("ttttt", "add新的集合"+newlist.get(i).getOptionModuleCodes()
                        +"   "+ newlist.get(i).getTaskOption());
                MyPlanListBean.FollowlistBean
                        .ProjectlistBean.ProjectBean.OptionlistBean optionlistBean  = new MyPlanListBean.FollowlistBean
                        .ProjectlistBean.ProjectBean.OptionlistBean("0",newlist.get(i).getOptionModuleCodes());
                projectlistBean2.project.optionlist.add(optionlistBean);
            }
            if (!TextUtils.isEmpty(result)) {
                String[] split = result.split(",");
                for(int i=0; i< split.length; i++){
                    MyPlanListBean.FollowlistBean
                            .ProjectlistBean.ProjectBean.OptionlistBean optionlistBean  = new MyPlanListBean.FollowlistBean
                            .ProjectlistBean.ProjectBean.OptionlistBean("0",split[i]);
                    projectlistBean2.project.optionlist.add(optionlistBean);
                }
            }
            for(int i=0; i< projectlistBean2.project.optionlist.size(); i++){
                Log.e("ttttt", "add最后的值为 "+projectlistBean2.project.optionlist.get(i).getOptionModuleCodes()
                        +"   "+ projectlistBean2.project.optionlist.get(i).getTaskOption());
            }
            mDatas.remove(position);
            mDatas.add(position, projectlistBean2);
            addMyPlanListAdapter.notifyDataSetChanged();
        }
    }

}
