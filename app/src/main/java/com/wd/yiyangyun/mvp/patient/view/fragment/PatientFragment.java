package com.wd.yiyangyun.mvp.patient.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wd.yiyangyun.HttpCallback;
import com.wd.yiyangyun.HttpResMsg;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseFragment;
import com.wd.yiyangyun.mvp.patient.adapter.PatientGroupAdapter;
import com.wd.yiyangyun.mvp.patient.bean.PatientGroupBean;
import com.wd.yiyangyun.mvp.patient.view.activity.AddPatientInfoActivity;
import com.wd.yiyangyun.mvp.patient.view.activity.PatientXinXiActivity;
import com.wd.yiyangyun.utils.GlideCircleTransUtils;
import com.wd.yiyangyun.utils.JSONUtil;
import com.wd.yiyangyun.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wd.yiyangyun.app.Constants.GROUP;
import static com.wd.yiyangyun.mvp.mine.presenter.MinePresenter.requestGetHttp;
import static com.wd.yiyangyun.mvp.mine.view.activity.DoctorInfoActivity.UPDATE_ICON;

public class PatientFragment extends BaseFragment {

    private LinearLayout add_patient_ll;
    private LinearLayout huizhen_ll;
    private LinearLayout zhuanzhen_ll;
    private LinearLayout zhongdian_ll;
    private LinearLayout patient_group_ll;
    private ExpandableListView patient_el;
    boolean groupBoolean  = true;
    private PatientGroupBean patientGroupBean;
    private PatientGroupAdapter groupAdapter;
    private List<PatientGroupBean.DataBean.GroupListBean> parentList;
    private List<List<PatientGroupBean.DataBean.GroupListBean.PatientListBean>> childlists;

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.patient_fragment;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        EventBus.getDefault().register(this);
        add_patient_ll = view.findViewById(R.id.add_patient_ll);
        huizhen_ll = view.findViewById(R.id.huizhen_ll);
        zhuanzhen_ll = view.findViewById(R.id.zhuanzhen_ll);
        zhongdian_ll = view.findViewById(R.id.zhongdian_ll);
        patient_group_ll = view.findViewById(R.id.patient_group_ll);
        patient_el = view.findViewById(R.id.patient_el);
    }

    @Override
    protected void initData() {
        super.initData();
        parentList = new ArrayList<>();
        childlists = new ArrayList<>();
        requestGroup();
    }

    private void requestGroup() {
        Map<String, Object> map = new HashMap<>();
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID,""));
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID,""));
        String json = JSONUtil.parseMapToJson(map);
        LogUtil.e("json", "onResume:---+ "+json );
        requestGetHttp(GROUP + json, new HttpCallback() {
            @Override
            public void onSuccess(HttpResMsg httpResMsg) {
                try {
                    Gson gson = new Gson();
                    patientGroupBean = gson.fromJson(httpResMsg.getData(), PatientGroupBean.class);
                    if(patientGroupBean.getData().getGroupList()!=null){
                        for(PatientGroupBean.DataBean.GroupListBean groupListBean : patientGroupBean.getData().getGroupList()){
                            LogUtil.e("tttttttt",groupListBean.getGroupName());
                            try {
                                parentList.add(groupListBean);
                                List<PatientGroupBean.DataBean.GroupListBean.PatientListBean>
                                        tagModuleBeans = new ArrayList<>();
                                if(groupListBean.getPatientList()!=null){
                                    LogUtil.e("tttttttt",groupListBean.getPatientList().size()+"");
                                    for (PatientGroupBean.DataBean.GroupListBean.PatientListBean childlistBean :
                                            groupListBean.getPatientList()) {
                                        LogUtil.e("tttttttt",childlistBean.getName());
                                        tagModuleBeans.add(childlistBean);
                                    }
                                    childlists.add(tagModuleBeans);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        groupAdapter = new PatientGroupAdapter(getActivity(),parentList,childlists);
                        patient_el.setAdapter(groupAdapter);
                        initListener();
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorMsg) {

            }
        });

    }

    @Override
    protected void initListener() {
        super.initListener();
        patient_el.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPositon, int childPosition, long l) {
                List<PatientGroupBean.DataBean.GroupListBean> groupListBeans = patientGroupBean.getData().getGroupList();
                String childId = groupListBeans.get(groupPositon).getPatientList().get(childPosition).getId()+"";
                String name = groupListBeans.get(groupPositon).getPatientList().get(childPosition).getName();
                Intent intent = new Intent(getActivity(), PatientXinXiActivity.class);
                intent.putExtra("childId",childId);
                intent.putExtra("name",name);
                startActivity(intent);
                return false;
            }
        });


        //新建患者
        add_patient_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddPatientInfoActivity.class);
                intent.putExtra("isAdd",true);
                startActivity(intent);
                // 点击按钮时，抛出一个异常
//                throw new RuntimeException("For test CrashHandler!");
            }
        });
        //会诊
        huizhen_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //转诊清单
        zhuanzhen_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //重点关注
        zhongdian_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //患者分组
        patient_group_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(groupBoolean == false){
                    patient_el.setVisibility(View.VISIBLE);
                    groupBoolean = true;
                }else{
                    groupBoolean = false;
                    patient_el.setVisibility(View.GONE);
                }
            }
        });
    }

    @Subscribe
    public void onEventMainThread(Map<String, Object> map) {
        String type = (String) map.get(Constants.EVENTBUS_TYEPE);
        //添加玩患者之后的刷新
        if (type.equals(AddPatientInfoActivity.ADD_PATIENT_SUCCESS)) {
            parentList.clear();
            childlists.clear();
            requestGroup();
        }
    }

}
