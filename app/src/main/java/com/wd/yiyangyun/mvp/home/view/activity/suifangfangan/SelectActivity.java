package com.wd.yiyangyun.mvp.home.view.activity.suifangfangan;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.mvp.mine.adapter.MyPlanCrfListAdapter;
import com.wd.yiyangyun.mvp.mine.bean.MyPlanListBean;
import com.wd.yiyangyun.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class SelectActivity extends Activity {

    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private ListView my_plan_list_cancel_listview;
    private SpringView my_plan_list_cancel_springview;
    private Button my_plan_list_cancel;
    private Button my_plan_list_add;
    private String taskOption;
    private int pageCount = 30;
    private int pageNo = 0;
    private List<MyPlanCRFListBean.CrflistBean> mDatas;
    private MyPlanCrfListAdapter myPlanCrfListAdapter;
    private List<MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean.OptionlistBean>
            optionlist;
    public static String MY_PLAN_SELECT2 = "myPlanSelect2";

    private int position;
    private String followprojectID;
    private String taskNum;
    private Context mContext = this;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_my_plan_list);
        taskOption = getIntent().getStringExtra("taskOption");
        followprojectID = getIntent().getStringExtra("followprojectID");
        taskNum = getIntent().getStringExtra("taskNum");
        position = getIntent().getIntExtra("position", -1);
        optionlist = (List<MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean
                .OptionlistBean>)
                getIntent().getSerializableExtra("info");
        initView();
        initData();
        initListener();
    }

    private void initView() {
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        my_plan_list_cancel_listview = (ListView) findViewById(R.id.my_plan_list_cancel_listview);
        my_plan_list_cancel_springview = (SpringView) findViewById(R.id
                .my_plan_list_cancel_springview);
        my_plan_list_cancel = (Button) findViewById(R.id.my_plan_list_cancel);
        my_plan_list_add = (Button) findViewById(R.id.my_plan_list_add);

//        my_plan_list_cancel_springview.setHeader(new DefaultHeader(mContext));
        my_plan_list_cancel_springview.setFooter(new DefaultFooter(mContext));

        title_back.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(followprojectID)) {
            my_plan_list_cancel.setVisibility(View.GONE);
            my_plan_list_add.setVisibility(View.GONE);
            title_name.setText("关联表单");
        } else {
            title_name.setText("选择表单");
        }
    }

    public void initData() {
        mDatas = new ArrayList<>();
        myPlanCrfListAdapter = new MyPlanCrfListAdapter(mContext, mDatas);
        my_plan_list_cancel_listview.setAdapter(myPlanCrfListAdapter);
//        requestListDatas();

    }

    /**
     * act=selectFollowCRFListNew&data={"followPlanID":"4028801e65eabe520165eac42e6a0004",
     * "typeFlag":0,"pageNo":"0",  "pageCount":"8"}
     * <p>
     * followPlanID:随访方案计划id,如果要查询所有量表和CRF表,followPlanID不传就好,"typeFlag":0代表量化指标,1代表量表
     */
//    private void requestListDatas() {
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("typeFlag", taskOption);
//        map.put("followPlanID", followprojectID);
//        map.put("pageNo", pageNo + "");
//        map.put("pageCount", pageCount + "");
//        map.put("taskNum", taskNum);
//        Log.e("ttttttttt", ":2"+taskNum);
//        DoctorNetService.requestFollowCRFList(Constants.SELECT_FOLLOW_CRF_LIST_NEW, map, new
//                NetWorkRequestInterface() {
//
//                    @Override
//                    public void onError(Throwable throwable) {
//
//                    }
//
//                    @Override
//                    public void onNext(Object info) {
//
//                        MyPlanCRFListBean myPlanCRFListBean = (MyPlanCRFListBean) info;
//                        Log.e("tttttt", "onNext  : "+followprojectID);
//                        if (!TextUtils.isEmpty(followprojectID)) {
//                            if (optionlist != null) {
//                                for(int i=0; i<optionlist.size(); i++){
//                                    Log.e("tttttt", "model 修改 "+optionlist.get(i).getOptionModuleCodes()+"   "+optionlist.get(i).getTaskOption());
//                                }
//                                if (taskOption.equals("0")) {
//                                    for (MyPlanCRFListBean.CrflistBean crflistBean : myPlanCRFListBean
//                                            .crflist) {
//                                        for (MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean
//                                                .OptionlistBean optionlistBean : optionlist) {
//                                            if (optionlistBean.optionModuleCodes.equals(crflistBean.moduleCode)  && taskOption.equals(optionlistBean.taskOption)){
//                                                mDatas.add(new MyPlanCRFListBean.CrflistBean(crflistBean
//                                                        .moduleName, crflistBean.moduleCode, false,"0"));
//                                            }
//                                        }
//                                    }
//                                }
//                                if(taskOption.equals("1")){
//                                    for (MyPlanCRFListBean.ScaleListBean scaleListBean :
//                                            myPlanCRFListBean.scaleList) {
//                                        for (MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean
//                                                .OptionlistBean optionlistBean : optionlist) {
//                                            if (optionlistBean.optionModuleCodes.equals(scaleListBean.moduleCode)  && taskOption.equals(optionlistBean.taskOption)){
//                                                mDatas.add(new MyPlanCRFListBean.CrflistBean(scaleListBean
//                                                        .moduleName, scaleListBean.moduleCode, false,"1"));
//                                            }
//                                        }
//                                    }
//                                }
//                                if(taskOption.equals("2")){
//                                    for (MyPlanCRFListBean.AllListBean allListBean :
//                                            myPlanCRFListBean.allList) {
//                                        for (MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean
//                                                .OptionlistBean optionlistBean : optionlist) {
//                                            if (optionlistBean.optionModuleCodes.equals(allListBean.moduleCode)  && taskOption.equals(optionlistBean.taskOption)){
//                                                mDatas.add(new MyPlanCRFListBean.CrflistBean(allListBean
//                                                        .moduleName, allListBean.moduleCode, false,"2"));
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//
//                            myPlanCrfListAdapter.setCanEdit(false);
//                        } else {
//                            if (taskOption.equals("0")) {
//                                for (MyPlanCRFListBean.CrflistBean crflistBean : myPlanCRFListBean
//                                        .crflist) {
//                                    mDatas.add(new MyPlanCRFListBean.CrflistBean(crflistBean
//                                            .moduleName, crflistBean.moduleCode, false,"0"));
//                                }
//                            }
//                            if(taskOption.equals("1")) {
//                                for (MyPlanCRFListBean.ScaleListBean scaleListBean :
//                                        myPlanCRFListBean.scaleList) {
//                                    mDatas.add(new MyPlanCRFListBean.CrflistBean(scaleListBean
//                                            .moduleName, scaleListBean.moduleCode, false,"1"));
//                                }
//                            }
//                            if(taskOption.equals("2")) {
//                                for (MyPlanCRFListBean.AllListBean scaleListBean :
//                                        myPlanCRFListBean.allList) {
//                                    mDatas.add(new MyPlanCRFListBean.CrflistBean(scaleListBean
//                                            .moduleName, scaleListBean.moduleCode, false,"2"));
//                                }
//                            }
//                            myPlanCrfListAdapter.setCanEdit(true);
//                            if(taskOption.equals("0")) {
//                                if (optionlist != null) {
//                                    for(int i=0; i<optionlist.size(); i++){
//                                        Log.e("tttttt", "model 添加 "+optionlist.get(i).getOptionModuleCodes()+"   "+optionlist.get(i).getTaskOption());
//                                    }
//
//                                    for (MyPlanCRFListBean.CrflistBean mData : mDatas) {
//                                        for (MyPlanListBean.FollowlistBean.ProjectlistBean
//                                                .ProjectBean.OptionlistBean
//                                                optionlistBean : optionlist) {
//                                            if (mData.moduleCode.equals(optionlistBean
//                                                    .optionModuleCodes) && taskOption.equals(optionlistBean.taskOption)) {
//                                                mData.isCheck = true;
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                            if(taskOption.equals("1")) {
//                                if (optionlist != null) {
//                                    for(int i=0; i<optionlist.size(); i++){
//                                        Log.e("tttttt", "model 添加 "+optionlist.get(i).getOptionModuleCodes()+"   "+optionlist.get(i).getTaskOption());
//                                    }
//
//                                    for (MyPlanCRFListBean.CrflistBean mData : mDatas) {
//                                        for (MyPlanListBean.FollowlistBean.ProjectlistBean
//                                                .ProjectBean.OptionlistBean
//                                                optionlistBean : optionlist) {
//                                            if (mData.moduleCode.equals(optionlistBean
//                                                    .optionModuleCodes) && taskOption.equals(optionlistBean.taskOption)) {
//                                                mData.isCheck = true;
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                            if(taskOption.equals("2")){
//                                if (optionlist != null) {
//                                    for(int i=0; i<optionlist.size(); i++){
//                                        Log.e("tttttt", "model 添加 "+optionlist.get(i).getOptionModuleCodes()+"   "+optionlist.get(i).getTaskOption());
//                                    }
//                                }
//
//                                for (MyPlanCRFListBean.CrflistBean mData : mDatas) {
//                                    for (MyPlanListBean.FollowlistBean.ProjectlistBean
//                                            .ProjectBean.OptionlistBean
//                                            optionlistBean : optionlist) {
//                                        if (mData.moduleCode.equals(optionlistBean
//                                                .optionModuleCodes) && taskOption.equals(optionlistBean.taskOption)) {
//                                            mData.isCheck = true;
//                                        }
//                                    }
//                                }
//                            }
//
//
//                        }
//                        LogUtil.e("ttttt----" + mDatas.size());
//                        myPlanCrfListAdapter.notifyDataSetChanged();
//                        my_plan_list_cancel_springview.onFinishFreshAndLoad();
//                    }
//                });
//    }

    public void initListener() {
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        myPlanCrfListAdapter.setOnButtonClickListener(new MyPlanCrfListAdapter
                .onButtonClickListener() {
            @Override
            public void onClick(String type, int position) {
                if(type.equals("0")){
                    mDatas.get(position).isCheck = !mDatas.get(position).isCheck;
                }
                if(type.equals("1")){
                    mDatas.get(position).isCheck = !mDatas.get(position).isCheck;
                }
                if(type.equals("2")){
                    mDatas.get(position).isCheck = !mDatas.get(position).isCheck;
                }


            }
        });
        my_plan_list_cancel_springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
//                mDatas.clear();
//                pageNo = 0;
//                requestListDatas();
                my_plan_list_cancel_springview.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                pageNo = pageNo + pageCount;
//                requestListDatas();
            }
        });
        /**
         * 添加
         */
        my_plan_list_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                for (MyPlanCRFListBean.CrflistBean mData : mDatas) {
                    if (mData.isCheck) {
                        sb.append(mData.moduleCode + ",");
                    }
                }
                if (TextUtils.isEmpty(sb.toString().replace(",", ""))) {
                    ToastUtils.shortToast(mContext, "请至少选择一项");
                    return;
                }
                Log.e("tttttttttt", "传递的值1或2   "+sb.toString());

                    Map<String, Object> map = new HashMap<>();
                    map.put(Constants.EVENTBUS_TYEPE, MY_PLAN_SELECT2);
                    map.put(Constants.EVENTBUS_VALUE, sb.toString());
                    map.put("position", position);
                    map.put("taskOption", taskOption);
                    EventBus.getDefault().post(map);
                    finish();
            }
        });
        /**
         * 取消
         */
        my_plan_list_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
