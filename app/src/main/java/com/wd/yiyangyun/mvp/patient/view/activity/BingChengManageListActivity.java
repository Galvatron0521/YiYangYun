package com.wd.yiyangyun.mvp.patient.view.activity;

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
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.DoctorBaseActivity;
import com.wd.yiyangyun.mvp.home.view.activity.bianzheng.AddBianZhengLunZhiActivity;
import com.wd.yiyangyun.mvp.home.view.activity.bianzheng.UpdateBianZhengLunZhiActivity;
import com.wd.yiyangyun.mvp.mine.bean.ResultBean;
import com.wd.yiyangyun.mvp.patient.adapter.BingZhangListAdapter;
import com.wd.yiyangyun.mvp.patient.bean.BingZhengListBean;
import com.wd.yiyangyun.mvp.patient.bean.PatientInfoBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.HcUtils;
import com.wd.yiyangyun.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wd.yiyangyun.mvp.home.view.activity.bianzheng.AddBianZhengLunZhiActivity.UPDATA_BINGZHENG_SUCCESS;

public class BingChengManageListActivity extends DoctorBaseActivity {

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
    private BingZhangListAdapter bingZhangListAdapter;
    private PatientInfoBean infoBean;
    private String name;
    private TextView patient_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bing_cheng_manage);
        EventBus.getDefault().register(this);
        try {
            patientID = getIntent().getStringExtra("childId");
            name = getIntent().getStringExtra("name");
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        title_name.setText("病程管理");

        bingcheng_springview.setHeader(new DefaultHeader(mContext));
        bingcheng_springview.setFooter(new DefaultFooter(mContext));

    }

    @Override
    public void initData() {

        mDatas = new ArrayList<>();
        bingZhangListAdapter = new BingZhangListAdapter(mContext, mDatas, this);
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
//        map.put("patientID", patientID);
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
        bingZhangListAdapter.setOnItemClickListener(new BingZhangListAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, UpdateBianZhengLunZhiActivity.class);
                intent.putExtra("patientID", patientID);
                intent.putExtra("recordFlag", mDatas.get(position).recordFlag);
                if(mDatas.get(position).fieldRecordDate!=null){
                    String sicknessTime = HcUtils.getData(Long.parseLong(mDatas.get(position).fieldRecordDate));
                    intent.putExtra("sicknessTime", sicknessTime);
                }
                intent.putExtra("fieldRecordSign", mDatas.get(position).fieldRecordSign);
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
        bingZhangListAdapter.setOnDeleteClickListener(new BingZhangListAdapter
                .OnDeleteClickListener() {
            @Override
            public void onDeleteClick(final int position) {
                showAffirmDialog("提示", "确认删除该病程？", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestDelete(position);
                    }
                });
            }
        });
    }

    /**
     * 删除病程
     *
     * @param position
     */
    private void requestDelete(int position) {
        showWaitDialog();
        Map<String,Object> map = new HashMap();
        map.put("fieldRecordSign", mDatas.get(position).fieldRecordSign);
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        map.put("mobileType", Constants.MOBILE_TYPE);
        DoctorNetService.requestResult(Constants.DELETE_COURSE_OF_DISEASE, map, new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        ResultBean resultBean = (ResultBean) info;
                        ToastUtils.showMessage(BingChengManageListActivity.this,resultBean.getData().getData());
                        pageNo = 0;
                        mDatas.clear();
                        requestBingZhengList();
                    }
                });
    }

    @Subscribe
    public void onEventMainThread(Map<String, Object> map) {
        String type = (String) map.get(Constants.EVENTBUS_TYEPE);
        if (type.equals(UPDATA_BINGZHENG_SUCCESS)) {
            requestBingZhengList();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideWaitDialog();
    }
}
