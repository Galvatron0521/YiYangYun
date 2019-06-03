package com.wd.yiyangyun.mvp.home.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.base.DoctorBaseActivity;
import com.wd.yiyangyun.mvp.home.adapter.BingZhangListAdapter2;
import com.wd.yiyangyun.mvp.home.view.activity.bianzheng.AddBianZhengLunZhiActivity;
import com.wd.yiyangyun.mvp.patient.adapter.BingZhangListAdapter;
import com.wd.yiyangyun.mvp.patient.bean.BingZhengListBean;
import com.wd.yiyangyun.mvp.patient.bean.PatientInfoBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PingGuActivity extends DoctorBaseActivity {

    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private TextView bingcheng_add;
    private ListView bingcheng_listview;
    private SpringView bingcheng_springview;
    private String patientID;
    private int pageCount = 10;
    private int pageNo = 0;
    private List<BingZhengListBean.ListBean> mDatas;
    private BingZhangListAdapter2 bingZhangListAdapter;
    private PatientInfoBean infoBean;
    private String name;
    private TextView patient_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binggu);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        bingcheng_add = (TextView) findViewById(R.id.bingcheng_add);
        bingcheng_listview = (ListView) findViewById(R.id.bingcheng_listview);
        bingcheng_springview = (SpringView) findViewById(R.id.bingcheng_springview);
        patient_name = findViewById(R.id.patient_name);

        title_back.setVisibility(View.VISIBLE);
        title_name.setText("报告管理");

        bingcheng_springview.setHeader(new DefaultHeader(mContext));
        bingcheng_springview.setFooter(new DefaultFooter(mContext));

        patientID = getIntent().getStringExtra("patientID");
        LogUtil.e("ttttttt1111",patientID);
        name = getIntent().getStringExtra("name");

    }

    @Override
    public void initData() {

        mDatas = new ArrayList<>();
        bingZhangListAdapter = new BingZhangListAdapter2(mContext, mDatas, this);
        bingcheng_listview.setAdapter(bingZhangListAdapter);
        requestBingZhengList();
        patient_name.setText(name);

    }

    /**
     * 请求病症列表
     */
    private void requestBingZhengList() {
        Map<String, Object> map = new HashMap<>();
        map.put("patientID", patientID);
        map.put("pageNo", pageNo + "");
        map.put("pageCount", pageCount + "");
        DoctorNetService.requestBingZhengList(Constants.SELECT_FIELD_RECORD_SIGN_LIST, map, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        mDatas.clear();
                        BingZhengListBean bingZhengListBean = (BingZhengListBean) info;
                        mDatas.addAll(bingZhengListBean.list);
                        bingZhangListAdapter.notifyDataSetChanged();
                        bingcheng_springview.onFinishFreshAndLoad();
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

        bingcheng_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(mContext, AddBianZhengLunZhiActivity.class);
                    intent.putExtra("patientID", patientID);
                    if (mDatas.size() == 0) {
                        intent.putExtra("recordFlag", "1");
                    } else {
                        intent.putExtra("recordFlag", "0");
                    }
                    startActivity(intent);

            }
        });
        bingZhangListAdapter.setOnItemClickListener(new BingZhangListAdapter2.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(PingGuActivity.this,BingChengBaoGaoActivity.class);
                intent.putExtra("fieldRecordSign",mDatas.get(position).fieldRecordSign);
                LogUtil.e("ttttttt1",patientID);
                intent.putExtra("patientID",patientID);
                startActivity(intent);
            }
        });

        bingcheng_springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mDatas.clear();
                pageNo = 0;
                requestBingZhengList();
            }

            @Override
            public void onLoadmore() {
                pageNo = pageNo + pageCount;
                requestBingZhengList();
            }
        });
        bingZhangListAdapter.setOnDeleteClickListener(new BingZhangListAdapter2
                .OnDeleteClickListener() {
            @Override
            public void onDeleteClick(final int position) {
                Intent intent = new Intent(PingGuActivity.this,BingChengBaoGaoActivity.class);
                intent.putExtra("fieldRecordSign",mDatas.get(position).fieldRecordSign);
                intent.putExtra("patientID",patientID);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideWaitDialog();
    }
}
