package com.wd.yiyangyun.mvp.home.view.activity.suifangmanager;

import android.app.Activity;
import android.content.Context;
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
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.adapter.MyPlanListAdapter;
import com.wd.yiyangyun.mvp.mine.adapter.SuiFangFangAnListAdapter;
import com.wd.yiyangyun.mvp.mine.bean.MyPlanListBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SuiFangFangAnActivity extends BaseActivity {

    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private ListView suifang_fangan_listview;
    private SpringView suifang_fangan_springview;
    private int pageCount = 10;
    private int pageNo = 0;
    private List<MyPlanListBean.FollowlistBean> mDatas;
    private SuiFangFangAnListAdapter suiFangFangAnListAdapter;
    public static String SELECT_FANGAN_NAME = "select_fangan_name";

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_sui_fang_fang_an;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        suifang_fangan_listview = (ListView) findViewById(R.id.suifang_fangan_listview);
        suifang_fangan_springview = (SpringView) findViewById(R.id.suifang_fangan_springview);
        title_name.setText("随访方案");
        title_back.setVisibility(View.VISIBLE);


        suifang_fangan_springview.setHeader(new DefaultHeader(mContext));
        suifang_fangan_springview.setFooter(new DefaultFooter(mContext));
    }

    @Override
    protected void initData() {
        super.initData();
            mDatas = new ArrayList<>();
            suiFangFangAnListAdapter = new SuiFangFangAnListAdapter
                    (mContext, mDatas);
            suifang_fangan_listview.setAdapter(suiFangFangAnListAdapter);
            requestFangAnList();

    }

    private void requestFangAnList() {
        showWaitDialog();
        /**
         *  "name":"",
         "pageNo":"0",
         "pageCount":"8"
         */
        Map<String, Object> map = new HashMap<>();
        map.put("pageNo", pageNo);
        map.put("pageCount", pageCount);
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID,""));
        DoctorNetService.requestMyPlanList(Constants.SELECT_FOLLOW_PLAN_LIST, map, new
                NetWorkRequestInterface() {


                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                        ToastUtils.showMessage(mContext, "查询失败");
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        suifang_fangan_springview.onFinishFreshAndLoad();
                        MyPlanListBean bean = (MyPlanListBean) info;
                        mDatas.addAll(bean.followlist);
                        suiFangFangAnListAdapter.notifyDataSetChanged();
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
        suiFangFangAnListAdapter.setOnItemClickListener(new MyPlanListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyPlanListBean.FollowlistBean listBean = mDatas.get(position);
                Map<String, Object> map = new HashMap<>();
                map.put(Constants.EVENTBUS_TYEPE, SELECT_FANGAN_NAME);
                map.put(Constants.EVENTBUS_VALUE, listBean);
                EventBus.getDefault().post(map);
                finish();
            }
        });
        suifang_fangan_springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mDatas.clear();
                pageNo = 0;
                requestFangAnList();
            }

            @Override
            public void onLoadmore() {
                mDatas.clear();
                pageNo = pageNo + pageCount;
                requestFangAnList();
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }

}
