package com.wd.yiyangyun.mvp.home.view.activity.zhuanzhen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.home.adapter.ZhuanZhenAdapter;
import com.wd.yiyangyun.mvp.home.bean.ZhuanInBean;
import com.wd.yiyangyun.mvp.home.bean.ZhuanZhenInfoBean;
import com.wd.yiyangyun.mvp.home.view.activity.AddZhuanZhenActivity;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZhuanZhenInfoActivity extends BaseActivity {

    private ImageView title_back;
    private TextView title_name;
    private ImageView image_right;
    private ListView zhuanzhen_info_list;
    private List<ZhuanZhenInfoBean.DataBean.ListBean> mDatas;
    private ZhuanZhenAdapter zhuanZhenAdapter;
    private String patientID;
    private TextView patient_name;
    private String name;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_zhuan_zhen_info;
    }

    @Override
    protected void initViews() {
        super.initViews();
        zhuanzhen_info_list = findViewById(R.id.zhuanzhen_info_list);
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        image_right =  findViewById(R.id.image_right);
        patient_name = findViewById(R.id.patient_name);
        title_back.setVisibility(View.VISIBLE);
        image_right.setVisibility(View.VISIBLE);
        title_name.setText("转诊详情");

        patientID = getIntent().getStringExtra("patientID");
        name = getIntent().getStringExtra("name");
        patient_name.setText(name+"转诊情况");
    }

    @Override
    protected void initData() {
        super.initData();

        mDatas = new ArrayList<>();
        zhuanZhenAdapter = new ZhuanZhenAdapter(this,mDatas);
        zhuanzhen_info_list.setAdapter(zhuanZhenAdapter);
        requestZhuanZhenList();

    }

    private void requestZhuanZhenList() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("patientID", patientID);
        DoctorNetService.zhuanZhenList(Constants.PATIENT_ZHUANZHEN, map,
                new NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        ZhuanZhenInfoBean zhuanZhenInfoBean = (ZhuanZhenInfoBean) info;
                        mDatas.addAll(zhuanZhenInfoBean.getData().getList());
                        hideWaitDialog();
                        zhuanZhenAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    protected void initListener() {
        super.initListener();

        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        image_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZhuanZhenInfoActivity.this, AddZhuanZhenActivity.class);
                intent.putExtra("patientID",patientID);
                startActivity(intent);
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }
}
