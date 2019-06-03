package com.wd.yiyangyun.mvp.home.view.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseFragment;
import com.wd.yiyangyun.mvp.home.adapter.ZhuanOutAdapter;
import com.wd.yiyangyun.mvp.home.bean.ZhuanOutBean;
import com.wd.yiyangyun.mvp.patient.view.activity.PatientXinXiActivity;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wd.yiyangyun.mvp.home.view.activity.AddZhuanZhenActivity.SHUAIXIN;


public class ZhuanOutFragment extends BaseFragment {

    private ListView zhuanzhenlist;
    private List<ZhuanOutBean.DataBean.ListBean> mDatas;
    private ZhuanOutAdapter zhuanOutAdapter;


    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.zhuanchu_fragment;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        EventBus.getDefault().register(this);
        zhuanzhenlist = view.findViewById(R.id.zhuanzhen_list);


    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<>();
        zhuanOutAdapter = new ZhuanOutAdapter(getActivity(),mDatas);
        zhuanzhenlist.setAdapter(zhuanOutAdapter);
        requestNewsList();
    }

    @Override
    protected void initListener() {
        super.initListener();

        zhuanOutAdapter.setOnButtonClickListener(new ZhuanOutAdapter.onButtonClickListener() {
            @Override
            public void onClick(int type,int position) {
                switch (type) {
                    case 3:  //
                        Intent intent = new Intent(getActivity(), PatientXinXiActivity.class);
                        intent.putExtra("childId", mDatas.get(position).getPatientID()+"");
                        intent.putExtra("name", mDatas.get(position).getName());
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void requestNewsList() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));
        map.put("departmentID", DoctorBaseAppliction.spUtil.getString(Constants.DepartmentID, ""));
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        DoctorNetService.zhuanOut(Constants.ZHUANCHU, map,
                new NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        ZhuanOutBean zhuanZhenBean = (ZhuanOutBean) info;
                        mDatas.clear();
                        mDatas.addAll(zhuanZhenBean.getData().getList());
                        hideWaitDialog();
                        zhuanOutAdapter.notifyDataSetChanged();
                    }
                });
    }
    @Subscribe
    public void onEventMainThread(Map<String, Object> map) {
        String type = (String) map.get(Constants.EVENTBUS_TYEPE);
        if(type.equals(SHUAIXIN)){
            LogUtil.e("刷新列表");
            requestNewsList();
        }

    }


}
