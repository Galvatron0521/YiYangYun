package com.wd.yiyangyun.mvp.home.view.activity.suifangfangan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
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
import com.wd.yiyangyun.mvp.mine.adapter.AddMyPlanListAdapter;
import com.wd.yiyangyun.mvp.mine.bean.ResultBean;
import com.wd.yiyangyun.mvp.mine.bean.MyPlanListBean;
import com.wd.yiyangyun.mvp.patient.bean.CardBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.HcUtils;
import com.wd.yiyangyun.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wd.yiyangyun.mvp.home.view.activity.suifangfangan.SelectActivity.MY_PLAN_SELECT2;

public class UpdateMyPlanActivity extends AppCompatActivity {

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
    private int position;
    private List<MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean.OptionlistBean> newlist;
    private String name;
    private String name2;
    private Context mContext = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_plan);
        EventBus.getDefault().register(this);
        isAdd = getIntent().getBooleanExtra("isAdd", false);
        followprojectID = getIntent().getStringExtra("followprojectID");
        name = getIntent().getStringExtra("name");
        position = getIntent().getIntExtra("position",0);
        bean = (MyPlanListBean.FollowlistBean) getIntent().getSerializableExtra("info");
        initView();
        initData();
        initListener();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void initData() {
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
        } else {
            title_right_tv.setText("保存");
            my_plan_name.setText(name);
            my_plan_jizhun_time.setText("1".equals(bean.baseDate) ? "首诊日期" : "其他");
            mDatas.addAll(bean.projectlist);
            if (bean.projectlist != null) {
                if (bean.projectlist.size() > 0) {
                    if (bean.projectlist.get(0).project.optionlist != null && bean.projectlist
                            .get(0).project.optionlist.size() > 0) {
                        for (MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean :
                                bean.projectlist) {
                                    projectlistBean.project.task_name2 = "量表";
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
        my_plan_name.setFocusable(true);
        my_plan_jizhun_time.setEnabled(true);
        my_plan_switch.setEnabled(true);
        if(bean.isShared==1){
            Log.e("Tttttttt", "changeViewState  : 111"+bean.isShared);
            my_plan_switch.setChecked(true);

        }else{
            my_plan_switch.setChecked(false);
            Log.e("Tttttttt", "changeViewState  : 000"+bean.isShared);
        }
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
        for (int i = 0; i <= 30; i++) {
            tempItems33.add(tempItems3);
            items3.add(tempItems33);
        }

    }

    public void initListener() {
        my_plan_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Log.e("Tttttttt", "changeViewState  : 111"+bean.id);
                    my_plan_switch.setChecked(true);
                    bean.isShared=1;
//                    suiFangShareBean(bean.id,bean.isShared);
                }else{
                    my_plan_switch.setChecked(false);
                    bean.isShared=0;
                    Log.e("Tttttttt", "changeViewState  : 000"+bean.id);
//                    suiFangShareBean(bean.id,bean.isShared);
                }
            }
        });
        my_plan_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                        isAdd = true;
                        MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean = new MyPlanListBean
                                .FollowlistBean.ProjectlistBean();
                        projectlistBean.project = new MyPlanListBean.FollowlistBean.ProjectlistBean
                                .ProjectBean();
                        projectlistBean.project.scope = "0";
                        mDatas.add(projectlistBean);
                        addMyPlanListAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
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
                        mDatas.remove(position);
                        addMyPlanListAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        if (TextUtils.isEmpty(my_plan_jizhun_time.getText().toString().trim())) {
                            ToastUtils.shortToast(mContext, "请选择随访基准时间");
                            return;
                        }
                        HcUtils.hideKeyboard(UpdateMyPlanActivity.this);
                        initOptionPicker(new TextView(mContext));
                        pvOptions.setPicker(items1, items2, items3);
                        pvOptions.show();
                        break;
                    case 3: //1  量表
                        if (!isAdd) {
                                Intent intent = new Intent(mContext, UpdateSelectMyPlanListActivity
                                        .class);
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
                                intent.putExtra("taskNum", currentSelectBean.project.taskNum);
                                currentSelectBean.project.task_name2 = "量表";
                                startActivity(intent);
                                addMyPlanListAdapter.notifyDataSetChanged();
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
                            intent.putExtra("info", (Serializable) projectlistBean.project
                                    .optionlist);
                            intent.putExtra("position", currentSelectPosition);
                            intent.putExtra("isBaoCun", true);

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
                HcUtils.hideKeyboard(UpdateMyPlanActivity.this);
                initOptionPicker(my_plan_jizhun_time);
                requestContent();
            }
        });
    }


    /**
     * 请求数据字典
     */
    private void requestContent() {
        Map<String, Object> map = new HashMap<>();
        map.put("typeCode", "followBaseDate");
        DoctorNetService.requestContent(Constants.SELECT_CONTENT, map, new
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

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void initView() {
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        my_plan_name = (EditText) findViewById(R.id.my_plan_name);
        my_subject_renqun_zhenduan = (TextView) findViewById(R.id.my_subject_renqun_zhenduan);
        my_plan_jizhun_time = (TextView) findViewById(R.id.my_plan_jizhun_time);
        my_plan_task_list = (MyListView) findViewById(R.id.my_plan_task_list);
        my_plan_switch = (Switch) findViewById(R.id.my_plan_switch);
        my_plan_add_task = (ImageView) findViewById(R.id.my_plan_add_task);

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
        name2 = my_plan_name.getText().toString().trim();
        if (TextUtils.isEmpty(name2)) {
            Toast.makeText(this, "请输入随访方案名称", Toast.LENGTH_SHORT).show();
            return;
        }
        String jizhun_time = my_plan_jizhun_time.getText().toString().trim();
        if (TextUtils.isEmpty(jizhun_time)) {
            Toast.makeText(this, "请选择随访基准时间", Toast.LENGTH_SHORT).show();
            return;
        }
        bean.planName = name2;
        bean.projectlist = new ArrayList<>();
        bean.projectlist.addAll(mDatas);
        for (int i = 0; i < bean.projectlist.size(); i++) {
            MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean = bean.projectlist.get(i);
            projectlistBean.project.taskNum = (i + 1) + "";
        }

        requestUpdateData();

    }

    /**
     * 修改数据
     */
    private void requestUpdateData() {
        
        Map<String, Object> map = new HashMap<>();
        map.put("follow", bean);
        map.put("mobileType", Constants.MOBILE_TYPE);
//        map.put("isShared", bean.isShared);
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        map.put("hospitalID",  DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));
        map.put("createUser", DoctorBaseAppliction.spUtil.getString(Constants.Name, ""));
        map.put("followPlanID", followprojectID);
        DoctorNetService.requestResult(Constants.UPDATE_PLAN, map, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        ToastUtils.shortToast(mContext, "保存失败，请重试");
                    }

                    @Override
                    public void onNext(Object info) {

                        ResultBean bean = (ResultBean) info;
                        ToastUtils.shortToast(mContext, bean.getData().getData());
                        if(name2==null || name2.equals("")){
                            Map<String, Object> map = new HashMap<>();
                            map.put(Constants.EVENTBUS_TYEPE, CHANG_FOLLOW_SUCCESS);
                            map.put("name", name);
                            EventBus.getDefault().post(map);
                        }else{
                            Map<String, Object> map = new HashMap<>();
                            map.put(Constants.EVENTBUS_TYEPE, CHANG_FOLLOW_SUCCESS);
                            map.put("name", name2);
                            EventBus.getDefault().post(map);
                        }

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
                    Intent intent = new Intent(mContext, UpdateSelectMyPlanListActivity.class);
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
            MyPlanListBean.FollowlistBean.ProjectlistBean projectlistBean2 = mDatas.get(position);
            if (projectlistBean2.project.optionlist == null) {
                projectlistBean2.project.optionlist = new ArrayList<>();
            }

            List<MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean.OptionlistBean> tichuanList = new ArrayList<>();
            for(int i=0; i< projectlistBean2.project.optionlist.size(); i++){
                Log.e("tttttt", "update原有集合   "+projectlistBean2.project.optionlist.get(i).getOptionModuleCodes()
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
                Log.e("tttttt", "update要除去的集合   "+tichuanList.get(i).getOptionModuleCodes()
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
                Log.e("ttttt", "update新的集合"+newlist.get(i).getOptionModuleCodes()
                        +"   "+ newlist.get(i).getTaskOption());
                MyPlanListBean.FollowlistBean
                        .ProjectlistBean.ProjectBean.OptionlistBean optionlistBean  = new MyPlanListBean.FollowlistBean
                        .ProjectlistBean.ProjectBean.OptionlistBean( "0",newlist.get(i).getOptionModuleCodes());
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
                Log.e("ttttt", "update最后的值为 "+projectlistBean2.project.optionlist.get(i).getOptionModuleCodes()
                        +"   "+ projectlistBean2.project.optionlist.get(i).getTaskOption());
            }
            mDatas.remove(position);
            mDatas.add(position, projectlistBean2);
            addMyPlanListAdapter.notifyDataSetChanged();
        }
    }
}

