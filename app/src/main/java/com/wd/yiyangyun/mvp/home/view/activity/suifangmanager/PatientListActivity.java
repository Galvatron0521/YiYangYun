package com.wd.yiyangyun.mvp.home.view.activity.suifangmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.DoctorBaseActivity;
import com.wd.yiyangyun.mvp.home.adapter.PatientListAdapter;
import com.wd.yiyangyun.mvp.home.bean.PatientListBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientListActivity extends DoctorBaseActivity {

    private boolean isTimePx; //排序方式标识 默认时间
    private int pageCount = 20;
    private int pageNo = 0;
    private String name = "";
    private String orderBy = "desc";//asc升序 desc降序
    private List<PatientListBean.ListBean> mDatas;
    private PatientListAdapter patientListAdapter;
    private String user_id;
    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private ListView patient_listview;
    private SpringView patient_springview;
    public static String SELECT_PATIENT_INFO = "Select_patient_info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        patient_listview = (ListView) findViewById(R.id.patient_listview);
        patient_springview = (SpringView) findViewById(R.id.patient_springview);
        patient_springview.setHeader(new DefaultHeader(mContext));
        patient_springview.setFooter(new DefaultFooter(mContext));

        title_name.setText("选择患者");
        title_back.setVisibility(View.VISIBLE);
    }

    @Override
    public void initData() {
        isTimePx = true;
        mDatas = new ArrayList<>();
        user_id = DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, "");
        patientListAdapter = new PatientListAdapter(mContext, mDatas,true);
        patient_listview.setAdapter(patientListAdapter);
        requestPatientList();
    }

    private void requestPatientList() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("pageNo", pageNo);
        map.put("pageCount", pageCount + "");
        map.put("userID", user_id);
        map.put("name", name);
        map.put("diseasesID", "");
        map.put("orderBy", orderBy);
        DoctorNetService.requestPatientList(Constants.PATIENTS_LIST, map, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        PatientListBean patientListBean = (PatientListBean) info;
                        try {
                            mDatas.addAll(patientListBean.list);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        patientListAdapter.notifyDataSetChanged();
                        patient_springview.onFinishFreshAndLoad();
                    }
                });
    }

    @Override
    public void initListener() {
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //list点击
        patient_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map<String, Object> map = new HashMap<>();
                map.put(Constants.EVENTBUS_TYEPE, SELECT_PATIENT_INFO);
                map.put(Constants.EVENTBUS_VALUE, mDatas.get(i));
                EventBus.getDefault().post(map);
                finish();
            }
        });
        // 上拉下拉o
        patient_springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                pageNo = 0;
                mDatas.clear();
                requestPatientList();
            }

            @Override
            public void onLoadmore() {
                //加载更多
                pageNo = pageNo + pageCount;
                requestPatientList();
            }
        });
    }
}

