package com.wd.yiyangyun.mvp.home.view.activity.suifangmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.DoctorBaseFragment;
import com.wd.yiyangyun.mvp.home.view.activity.suifangjilu.AddSuiFangJiluActivity;
import com.wd.yiyangyun.mvp.mine.adapter.SuiFangListAdapter;
import com.wd.yiyangyun.mvp.mine.bean.PatientSuiFangListBean;
import com.wd.yiyangyun.mvp.patient.view.activity.SuiFangListActivity;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaiSuiFangJiLuFragment extends DoctorBaseFragment {
    private ListView suifang_jilu_listview;
    private SpringView suifang_jilu_springview;
    private ImageButton suifang_jilu_add;
    private SuiFangListAdapter suiFangJiLuListAdapter;
    private int pageCount = 20;
    private int pageNo = 0;
    private List<PatientSuiFangListBean.ListBean> mDatas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View view = View.inflate(getContext(), R.layout.sufang_jilu_fragment, null);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initView(View view) {
        suifang_jilu_listview = (ListView) view.findViewById(R.id.suifang_jilu_listview);
        suifang_jilu_springview = (SpringView) view.findViewById(R.id.suifang_jilu_springview);

        suifang_jilu_springview.setHeader(new DefaultHeader(getContext()));
        suifang_jilu_springview.setFooter(new DefaultFooter(getContext()));
        suifang_jilu_add = (ImageButton) view.findViewById(R.id.suifang_jilu_add);
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<>();
        suiFangJiLuListAdapter = new SuiFangListAdapter(getContext(), mDatas);
        suifang_jilu_listview.setAdapter(suiFangJiLuListAdapter);
        requestSuiFangList();
    }

    /**
     * "patientID":0,
     * "pageNo":"0",
     * "pageCount":"8",
     * "followPatientListType":"1","endFlag":"0"
     * followPatientListType:患者随访列表 0已随访记录、1待随访列
     */
    private void requestSuiFangList() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("patientID", "0");
        map.put("pageNo", pageNo + "");
        map.put("pageCount", pageCount + "");
        map.put("followPatientListType",  "1");
        map.put("hospitalID",  DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));
        DoctorNetService.requestPatientSuiFangList(Constants.SELECT_FOLLOW_PATIENT_NEW_LIST, map,
                new
                        NetWorkRequestInterface() {

                            @Override
                            public void onError(Throwable throwable) {
                                hideWaitDialog();
                            }

                            @Override
                            public void onNext(Object info) {
                                hideWaitDialog();
                                PatientSuiFangListBean bean = (PatientSuiFangListBean) info;
                                mDatas.addAll(bean.list);
                                suiFangJiLuListAdapter.notifyDataSetChanged();
                                suifang_jilu_springview.onFinishFreshAndLoad();
                            }
                        });
    }

    @Override
    public void initListener() {

        //刷新
        suifang_jilu_springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mDatas.clear();
                pageNo = 0;
                requestSuiFangList();
            }

            @Override
            public void onLoadmore() {
                pageNo = pageNo + pageCount;
                requestSuiFangList();
            }
        });
        //添加
        suifang_jilu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddSuiFangJiluActivity.class);
                intent.putExtra("isAdd", true);
                startActivity(intent);
            }
        });
        suifang_jilu_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PatientSuiFangListBean.ListBean listBean = mDatas.get(i);
                Intent intent = new Intent(getActivity(), AddSuiFangJiluActivity.class);
                intent.putExtra("isAdd", false);
                intent.putExtra("patientID", listBean.patientID);
                intent.putExtra("followRecordId", listBean.id);
                intent.putExtra("followID", listBean.followID);
                intent.putExtra("PatientSuiFangListBean", mDatas.get(i));
                startActivity(intent);
            }
        });
    }

    @Subscribe
    public void onEventMainThread(Map<String, Object> map) {
        String type = (String) map.get(Constants.EVENTBUS_TYEPE);
        if (type.equals(AddSuiFangJiluActivity.ADD_SUIFANG_INFO_SUCCESS)) {
            mDatas.clear();
            pageNo = 0;
            requestSuiFangList();
        }
    }

}
