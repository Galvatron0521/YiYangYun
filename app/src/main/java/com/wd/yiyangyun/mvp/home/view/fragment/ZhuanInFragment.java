package com.wd.yiyangyun.mvp.home.view.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.mylhyl.circledialog.view.listener.OnInputClickListener;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseFragment;
import com.wd.yiyangyun.mvp.home.adapter.ZhuanInAdapter;
import com.wd.yiyangyun.mvp.home.bean.ZhuanInBean;
import com.wd.yiyangyun.mvp.mine.bean.ResultBean;
import com.wd.yiyangyun.mvp.patient.view.activity.PatientXinXiActivity;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZhuanInFragment extends BaseFragment {

    private List<ZhuanInBean.DataBean.ListBean> mDatas;
    private ZhuanInAdapter zhuanInAdapter;
    private ListView zhuanzhen_list;
    private ZhuanInBean zhuanInBean;


    @Override
    protected Object provideBindView() {

        return this;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        zhuanzhen_list = view.findViewById(R.id.zhuanzhen_list);

    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<>();
        zhuanInAdapter = new ZhuanInAdapter(getActivity(),mDatas);
        zhuanzhen_list.setAdapter(zhuanInAdapter);
        requestNewsList();
    }

    @Override
    protected void initListener() {
        super.initListener();
        zhuanInAdapter.setOnButtonClickListener(new ZhuanInAdapter.onButtonClickListener() {
            @Override
            public void onClick(int type, final int position) {
                switch (type) {
                    case 0:  //确认转诊
                        requestQueRen(zhuanInBean.getData().getList().get(position).getId());
                    break;
                    case 1:  //拒绝转诊
                        showInputDialog("请输入您拒绝的原因", new OnInputClickListener() {
                            @Override
                            public void onClick(String text, View v) {
                                if (TextUtils.isEmpty(text)) {
                                    ToastUtils.shortToast(getActivity(), "内容不能为空");
                                    return;
                                }
                                requestJuJue(text, zhuanInBean.getData().getList().get(position).getId());
                            }
                        });
                    break;
                    case 3:  //
                        Intent intent =new Intent(getActivity(), PatientXinXiActivity.class);
                        intent.putExtra("childId",mDatas.get(position).getPatientID()+"");
                        intent.putExtra("name",mDatas.get(position).getName());
                        startActivity(intent);
                        break;
                }
            }
        });

    }

    private void requestNewsList() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("toHospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));
        map.put("departmentID", DoctorBaseAppliction.spUtil.getString(Constants.DepartmentID, ""));
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        DoctorNetService.zhuanIn(Constants.ZHUANRU, map,
                new NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        zhuanInBean = (ZhuanInBean) info;
                        mDatas.clear();
                        mDatas.addAll(zhuanInBean.getData().getList());
                        zhuanInAdapter.notifyDataSetChanged();
                    }
                });

    }

    private void requestQueRen(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        DoctorNetService.requestResult(Constants.QUEREN, map, new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onNext(Object info) {
                        ResultBean resultBean = (ResultBean) info;
                        ToastUtils.showMessage(getActivity(),resultBean.getData().getData());
                        requestNewsList();

//                        Map<String, Object> map = new HashMap<>();
//                        map.put(Constants.EVENTBUS_TYEPE, SHUAIXIN);
//                        EventBus.getDefault().post(map);
                    }
                });
    }

    private void requestJuJue(String text,String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("refuseReason", text);
        DoctorNetService.requestResult(Constants.JUJUE, map, new
                NetWorkRequestInterface() {


                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onNext(Object info) {
                        ResultBean resultBean = (ResultBean) info;
                        ToastUtils.showMessage(getActivity(),resultBean.getData().getData());
                        requestNewsList();

//                        Map<String, Object> map = new HashMap<>();
//                        map.put(Constants.EVENTBUS_TYEPE, SHUAIXIN);
//                        EventBus.getDefault().post(map);
                    }
                });
    }




    @Override
    protected int provideLayoutId() {
        return R.layout.zhuanchu_fragment;
    }
}
