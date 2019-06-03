package com.wd.yiyangyun.mvp.patient.view.activity;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.adapter.FristGroupAdapter;
import com.wd.yiyangyun.mvp.mine.bean.GroupListBean;
import com.wd.yiyangyun.mvp.patient.bean.BingchengListBean;
import com.wd.yiyangyun.mvp.patient.bean.PatientInfoBean;
import com.wd.yiyangyun.mvp.patient.bean.SelectGroupBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.JSONUtil;
import com.wd.yiyangyun.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wd.yiyangyun.utils.SynchniceUtil.requestBingChengList;


public class FristGroupActivity extends BaseActivity{

    private TextView title_name;
    private ImageView title_back;
    private ListView my_plan_list_cancel_listview;
    private List<GroupListBean.DataBean.ListBean> mDatas;
    private TextView title_right_tv;
    private FristGroupAdapter fristGroupAdapter;
    public static String FRIST_GROUP = "frist_group";
    private GroupListBean groupListBean;
    List<SelectGroupBean.GroupListBean> groupListBeans;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_frist_group;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        title_back.setVisibility(View.VISIBLE);
        my_plan_list_cancel_listview = (ListView) findViewById(R.id.my_plan_list_cancel_listview);

        title_back.setVisibility(View.VISIBLE);
        title_name.setText("选择分组");
        title_right_tv.setText("完成");
        title_name.setVisibility(View.VISIBLE);
        title_right_tv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<>();
        fristGroupAdapter = new FristGroupAdapter(FristGroupActivity.this,mDatas,true);
        my_plan_list_cancel_listview.setAdapter(fristGroupAdapter);

        groupListBeans = (List<SelectGroupBean.GroupListBean>) getIntent().getSerializableExtra("groupListBeans");

            if(groupListBeans!=null){
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
                mDatas.addAll(bingchengListBean.getData().getList());
                fristGroupAdapter.notifyDataSetChanged();
            }else{
                requestPatientGroup();
            }
    }

    private void requestPatientGroup() {
        Map<String, Object> map = new HashMap<>();
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID,""));
        //同步请求
        DoctorNetService.requestPatientGroup(Constants.GROUP_MANAGER, map, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        ToastUtils.showMessage(mContext, "请求患者详情失败");
                    }

                    @Override
                    public void onNext(Object info) {
                        groupListBean = (GroupListBean) info;
                        mDatas.addAll(groupListBean.getData().getList());
                        fristGroupAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void initListener() {
        super.initListener();
        fristGroupAdapter.setOnItemClickListener(new FristGroupAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mDatas.get(position).isCheck = !mDatas.get(position).isCheck;

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

                StringBuilder sb = new StringBuilder();
                StringBuilder sbName = new StringBuilder();

                for (GroupListBean.DataBean.ListBean mData : mDatas) {
                    if (mData.isCheck) {
                        mData.isCheck = true;
                        sb.append(mData.getId()+",");
                        sbName.append(mData.getGroupName()+",");
                    }
                }
                if (TextUtils.isEmpty(sb.toString().replace(",", ""))) {
                    ToastUtils.shortToast(mContext, "请至少选择一项");
                    return;
                }
                Log.e("tttttttttt", "传递的值   "+sb.toString());
                Log.e("tttttttttt", "传递的值   "+sbName.toString());

                Map<String, Object> map = new HashMap<>();
                map.put(Constants.EVENTBUS_TYEPE, FRIST_GROUP);
                map.put(Constants.EVENTBUS_VALUE, sbName.toString());
                map.put("groupId", sb.toString());
                EventBus.getDefault().post(map);
                finish();
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }
}
