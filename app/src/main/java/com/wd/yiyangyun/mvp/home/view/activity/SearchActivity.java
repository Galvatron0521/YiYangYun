package com.wd.yiyangyun.mvp.home.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.wd.yiyangyun.utils.LogUtil;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class SearchActivity extends DoctorBaseActivity {

    private int pageCount = 30;
    private int pageNo = 0;
    private String name = "";

    private int DpageCount = 10;
    private int DpageNo = 0;
    private SpringView doctor_list_springview;
    private List<PatientListBean.ListBean> mDoctorDatas;
    private PatientListAdapter patientListAdapter;
    private ListView doctor_list_listview;
    private LinearLayout search_ll_visiable;
    private String orderBy = "desc";//asc升序 desc降序
    private TextView title_name;
    private ImageView title_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        initView();
        initData();
        initListener();
    }


    @Override
    public void initData() {
        mDoctorDatas = new ArrayList<>();
        patientListAdapter = new PatientListAdapter(this, mDoctorDatas,false);
        doctor_list_listview.setAdapter(patientListAdapter);
        requestPatientList();
    }

    @Override
    public void initListener() {
        //对软键盘的Enter进行监听
//        edit_gone.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
//                if (KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
//                    switch (keyCode){
//                        case KeyEvent.KEYCODE_ENTER:
//                            //edit失去焦点
//                            edit_gone.clearFocus();//失去焦点
//                            //关闭软键盘
//                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//                            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
//                            name = edit_gone.getText().toString().trim();
//                            pageNo = 0;
//                            mDoctorDatas.clear();
//                            requestPatientList();
//                            break;
//                    }
//                }
//                return false;
//            }
//        });
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        search_ll_visiable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(String keyword) {
                        //这里处理逻辑
                        name = keyword;
                        pageNo = 0;
                        mDoctorDatas.clear();
                        requestPatientList();
                    }
                });
                searchFragment.showFragment(getSupportFragmentManager(),SearchFragment.TAG);
            }
        });
        try {
            patientListAdapter.setOnViewClickLisener(new PatientListAdapter.OnViewClickLisener() {
                @Override
                public void onClick(int position, int type) {
                    //类型 0 头像  1 复诊  2 随访
                    switch (type) {
                        case 0:
                            Intent intent1 = new Intent(SearchActivity.this, PingGuActivity.class);
                            intent1.putExtra("patientID", mDoctorDatas.get(position).id);
                            LogUtil.e("ttttttt1",mDoctorDatas.get(position).id);
                            intent1.putExtra("name",mDoctorDatas.get(position).name);
                            startActivity(intent1);
                            break;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 上拉下拉o
        doctor_list_springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                DpageNo = 0;
                mDoctorDatas.clear();
                name = "";
                requestPatientList();
            }

            @Override
            public void onLoadmore() {
                //加载更多
                DpageNo = DpageNo + DpageCount;
                name = "";
                requestPatientList();
            }
        });
    }

    private void initView() {
//        edit_gone = findViewById(R.id.edit_gone);
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("评估报告");
        title_back.setVisibility(View.VISIBLE);
        title_name.setVisibility(View.VISIBLE);
        search_ll_visiable = (LinearLayout) findViewById(R.id.search_ll_visiable);
        doctor_list_springview = (SpringView) findViewById(R.id.doctor_list_springview);
        doctor_list_listview = (ListView) findViewById(R.id.doctor_list_listview);

        doctor_list_springview.setHeader(new DefaultHeader(this));
        doctor_list_springview.setFooter(new DefaultFooter(this));
    }


    /**
     * 请求患者列表
     */
    private void requestPatientList() {
        showWaitDialog(); //分组
        Map<String, Object> map = new HashMap<>();
        map.put("pageNo", pageNo);
        map.put("pageCount", pageCount + "");
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));
        map.put("name", name);
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
                        mDoctorDatas.clear();
                        mDoctorDatas.addAll(patientListBean.list);
                        patientListAdapter.notifyDataSetChanged();
                        doctor_list_springview.onFinishFreshAndLoad();

                    }
                });
    }
}
