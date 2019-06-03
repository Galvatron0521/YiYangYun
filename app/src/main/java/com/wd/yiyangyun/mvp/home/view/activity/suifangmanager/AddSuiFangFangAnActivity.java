package com.wd.yiyangyun.mvp.home.view.activity.suifangmanager;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.bean.ResultBean;
import com.wd.yiyangyun.mvp.mine.bean.FangAnInfoBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.ToastUtils;
import org.greenrobot.eventbus.EventBus;
import java.util.HashMap;
import java.util.Map;

public class AddSuiFangFangAnActivity extends BaseActivity {

    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private EditText add_fangan_name;
    private EditText add_fangan_detail;
    private EditText add_fangan_nian;
    private EditText add_fangan_yue;
    private EditText add_fangan_ri;
    private EditText add_fangan_ci;
    private EditText add_fangan_tian;
    private boolean isAdd;
    private String id;
    public static String ADD_FANG_AN_INFO_SUCCESS = "add_fang_an_info_success";
    private FangAnInfoBean fangAnInfoBean;


    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_add_sui_fang_fangan;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        add_fangan_name = (EditText) findViewById(R.id.add_fangan_name);
        add_fangan_detail = (EditText) findViewById(R.id.add_fangan_detail);
        add_fangan_nian = (EditText) findViewById(R.id.add_fangan_nian);
        add_fangan_yue = (EditText) findViewById(R.id.add_fangan_yue);
        add_fangan_ri = (EditText) findViewById(R.id.add_fangan_ri);
        add_fangan_ci = (EditText) findViewById(R.id.add_fangan_ci);
        add_fangan_tian = (EditText) findViewById(R.id.add_fangan_tian);

        title_back.setVisibility(View.VISIBLE);
        title_name.setText("随访方案");

    }


    @Override
    protected void initData() {
        super.initData();
        if (!isAdd) {
//            add_fangan_name.setHint("");
//            add_fangan_detail.setHint("");
//            add_fangan_nian.setHint("");
//            add_fangan_yue.setHint("");
//            add_fangan_ri.setHint("");
//            add_fangan_ci.setHint("");
//            add_fangan_tian.setHint("");
//            add_fangan_name.setEnabled(false);
//            add_fangan_detail.setEnabled(false);
//            add_fangan_nian.setEnabled(false);
//            add_fangan_yue.setEnabled(false);
//            add_fangan_ri.setEnabled(false);
//            add_fangan_ci.setEnabled(false);
//            add_fangan_tian.setEnabled(false);

            requestFangAnInfo();
            title_right_tv.setVisibility(View.VISIBLE);
            title_right_tv.setText("保存");
        } else {
            title_right_tv.setVisibility(View.VISIBLE);
            title_right_tv.setText("保存");
        }
    }

    private void requestFangAnInfo() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("optionTag", "update");
        map.put("id", id);
        DoctorNetService.requestFangAnInfo(Constants.ADD_PLAN, map, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();

                        fangAnInfoBean = (FangAnInfoBean) info;
                        add_fangan_name.setText(fangAnInfoBean.follow.followName);
                        add_fangan_detail.setText(fangAnInfoBean.follow.followDescript);
                        add_fangan_nian.setText(fangAnInfoBean.follow.followMaxYear);
                        add_fangan_yue.setText(fangAnInfoBean.follow.followMaxMonth);
                        add_fangan_ri.setText(fangAnInfoBean.follow.followMaxDay);
                        add_fangan_ci.setText(fangAnInfoBean.follow.followMaxNum);
                        add_fangan_tian.setText(fangAnInfoBean.follow.followDayNum);
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
        title_right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }


    private void submit() {
        // validate
        String name = add_fangan_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "请输入方案名称", Toast.LENGTH_SHORT).show();
            return;
        }

        String detail = add_fangan_detail.getText().toString().trim();
        if (TextUtils.isEmpty(detail)) {
            Toast.makeText(this, "请输入方案描述", Toast.LENGTH_SHORT).show();
            return;
        }

        String nian = add_fangan_nian.getText().toString().trim();
        if (TextUtils.isEmpty(nian)) {
            Toast.makeText(this, "请输入最长访问时间--年", Toast.LENGTH_SHORT).show();
            return;
        }

        String yue = add_fangan_yue.getText().toString().trim();
        if (TextUtils.isEmpty(yue)) {
            Toast.makeText(this, "请输入最长访问时间--月", Toast.LENGTH_SHORT).show();
            return;
        }

        String ri = add_fangan_ri.getText().toString().trim();
        if (TextUtils.isEmpty(ri)) {
            Toast.makeText(this, "请输入最长访问时间--日", Toast.LENGTH_SHORT).show();
            return;
        }

        String ci = add_fangan_ci.getText().toString().trim();
        if (TextUtils.isEmpty(ci)) {
            Toast.makeText(this, "请输入最多访问次数", Toast.LENGTH_SHORT).show();
            return;
        }

        String tian = add_fangan_tian.getText().toString().trim();
        if (TextUtils.isEmpty(tian)) {
            Toast.makeText(this, "请输入提前几天提醒", Toast.LENGTH_SHORT).show();
            return;
        }
        /**
         act=insertFollow&data={"mobileType":"2",
         "followName":"123",
         "followDescript":"321",
         "followMaxNum":1,
         "followMaxYear":1,
         "followMaxMonth":1,
         "followMaxDay":1,
         "followDayNum":1,
         "moduleCodeList":["SP020153","SP020114"],
         "createUser":"123",
         "followTimeMap":[{"yearNum":1,"monthNum":2,"dayNum":1}]
         }
         */
        Map<String, Object> map = new HashMap<>();
        map.put("mobileType", Constants.MOBILE_TYPE);
        map.put("followName", name);
        map.put("followDescript", detail);
        map.put("followMaxNum", ci);
        map.put("followMaxYear", nian);
        map.put("followMaxMonth", yue);
        map.put("followMaxDay", ri);
        map.put("followDayNum", tian);
        map.put("moduleCodeList", "");
        map.put("createUser", DoctorBaseAppliction.spUtil.getString(Constants.Name, ""));
        map.put("followTimeMap", "");
        if (!isAdd) {
            map.put("id", fangAnInfoBean.follow.id);
        }
        requestAddFangAnInfo(map);
    }

    /**
     * 添加随访方案信息
     *
     * @param map
     */
    private void requestAddFangAnInfo(Map<String, Object> map) {
        showWaitDialog();
        DoctorNetService.requestResult(isAdd ? Constants.ADD_PLAN : Constants
                .UPDATE_PLAN, map, new NetWorkRequestInterface() {

            @Override
            public void onError(Throwable throwable) {
                hideWaitDialog();
                ToastUtils.showMessage(mContext, "添加失败，请重试");
            }

            @Override
            public void onNext(Object info) {
                hideWaitDialog();
                ResultBean baseReturnBean = (ResultBean) info;
                ToastUtils.showMessage(mContext, baseReturnBean.getData().getData());
                Map<String, Object> map = new HashMap<>();
                map.put(Constants.EVENTBUS_TYEPE, ADD_FANG_AN_INFO_SUCCESS);
                EventBus.getDefault().post(map);
                finish();
            }
        });
    }
}
