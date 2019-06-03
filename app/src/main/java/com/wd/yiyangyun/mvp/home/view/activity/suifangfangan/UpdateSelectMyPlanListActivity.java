package com.wd.yiyangyun.mvp.home.view.activity.suifangfangan;

import android.app.Activity;
import android.content.Context;
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
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.adapter.MyPlanCrfListAdapter;
import com.wd.yiyangyun.mvp.mine.bean.MyPlanListBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.LogUtil;
import com.wd.yiyangyun.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UpdateSelectMyPlanListActivity extends BaseActivity {

    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private ListView my_plan_list_cancel_listview;
    private SpringView my_plan_list_cancel_springview;
    private Button my_plan_list_cancel;
    private Button my_plan_list_add;
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
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_select_my_plan_list;
    }

    @Override
    protected void initViews() {
        super.initViews();
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

        followprojectID = getIntent().getStringExtra("followprojectID");
        taskNum = getIntent().getStringExtra("taskNum");
        position = getIntent().getIntExtra("position", -1);
        optionlist = (List<MyPlanListBean.FollowlistBean.ProjectlistBean.ProjectBean
                .OptionlistBean>)
                getIntent().getSerializableExtra("info");


        if (TextUtils.isEmpty(followprojectID)) {
            my_plan_list_cancel.setVisibility(View.GONE);
            my_plan_list_add.setVisibility(View.GONE);
            title_name.setText("关联表单");
        } else {
            title_name.setText("选择表单");
        }
        my_plan_list_add.setText("保存");

        Log.e("ttttt", "onCreate: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("ttttt", "onResume: ");
    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<>();
        myPlanCrfListAdapter = new MyPlanCrfListAdapter(mContext, mDatas);
        my_plan_list_cancel_listview.setAdapter(myPlanCrfListAdapter);
        requestListDatas();

    }

    /**
     * act=selectFollowCRFListNew&data={"followPlanID":"4028801e65eabe520165eac42e6a0004",
     * "typeFlag":0,"pageNo":"0",  "pageCount":"8"}
     * <p>
     * followPlanID:随访方案计划id,如果要查询所有量表和CRF表,followPlanID不传就好,"typeFlag":0代表量化指标,1代表量表
     */
    private void requestListDatas() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
//        map.put("typeFlag", taskOption);
        map.put("followPlanID", "");
        map.put("pageNo", pageNo + "");
        map.put("pageCount", pageCount + "");
        map.put("taskNum", taskNum);
        DoctorNetService.requestFollowCRFList(Constants.SELECT_FOLLOW_CRF_LIST_NEW, map, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        MyPlanCRFListBean myPlanCRFListBean = (MyPlanCRFListBean) info;
                        Log.e("tttttt", "onNext  : "+followprojectID);


                        for (MyPlanCRFListBean.CrflistBean scaleListBean :
                                myPlanCRFListBean.crflist) {
                            mDatas.add(new MyPlanCRFListBean.CrflistBean(scaleListBean
                                    .moduleName, scaleListBean.moduleCode, false,"0"));
                        }

                        myPlanCrfListAdapter.setCanEdit(true);

                        if (optionlist != null) {
                            for(int i=0; i<optionlist.size(); i++){
                                Log.e("tttttt", "model 添加1 "+optionlist.get(i).getOptionModuleCodes()+"   "+optionlist.get(i).getTaskOption());
                            }

                            for (MyPlanCRFListBean.CrflistBean mData : mDatas) {
                                for (MyPlanListBean.FollowlistBean.ProjectlistBean
                                        .ProjectBean.OptionlistBean
                                        optionlistBean : optionlist) {
                                    if (mData.moduleCode.equals(optionlistBean
                                            .optionModuleCodes)) {
                                        mData.isCheck = true;
                                    }
                                }
                            }
                        }
                        LogUtil.e("ttttt----" + mDatas.size());
                        myPlanCrfListAdapter.notifyDataSetChanged();
                        my_plan_list_cancel_springview.onFinishFreshAndLoad();
                    }

                });
    }


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
                    mDatas.get(position).isCheck = !mDatas.get(position).isCheck;
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
                requestListDatas();
            }
        });
        /**
         * 添加
         */
        my_plan_list_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< mDatas.size(); i++){
                    if (mDatas.get(i).isCheck) {
                        sb.append(mDatas.get(i).moduleCode + ",");
                    }
                }

                if (TextUtils.isEmpty(sb.toString().replace(",", ""))) {
                    ToastUtils.shortToast(mContext, "请至少选择一项");
                    return;
                }
                Log.e("tttttttttt", "update传递的值1或2   "+sb.toString());

                Map<String, Object> map = new HashMap<>();
                map.put(Constants.EVENTBUS_TYEPE, MY_PLAN_SELECT2);
                map.put(Constants.EVENTBUS_VALUE, sb.toString());
                map.put("position", position);
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

    @Override
    public Context context() {
        return null;
    }
}
