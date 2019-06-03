package com.wd.yiyangyun.mvp.mine.view.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mylhyl.circledialog.view.listener.OnInputClickListener;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.adapter.MySuggestionListAdapter;
import com.wd.yiyangyun.mvp.mine.bean.MySuggestionListBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySuggestionAct extends BaseActivity {

    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private ListView my_suggestion_listview;
    private List<MySuggestionListBean.ListBean> mDatas;
    private MySuggestionListAdapter mySuggestionListAdapter;


    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_my_suggestion;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        my_suggestion_listview = (ListView) findViewById(R.id.my_suggestion_listview);

        title_back.setVisibility(View.VISIBLE);
        title_name.setText("我的意见");
        title_right_tv.setText("添加");
        title_right_tv.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<>();
        mySuggestionListAdapter = new MySuggestionListAdapter(mContext, mDatas);
        my_suggestion_listview.setAdapter(mySuggestionListAdapter);
        requestMySuggestionList();
    }

    private void requestMySuggestionList() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        DoctorNetService.requestSuggestionList(Constants.YIJIAN_FANKUI, map, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        MySuggestionListBean mySuggestionListBean = (MySuggestionListBean) info;
                        mDatas.clear();
                        mDatas.addAll(mySuggestionListBean.list);
                        mySuggestionListAdapter.notifyDataSetChanged();
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
                showInputDialog("请输入您的意见", new OnInputClickListener() {
                    @Override
                    public void onClick(String text, View v) {
                        if (TextUtils.isEmpty(text)) {
                            ToastUtils.shortToast(mContext, "内容不能为空");
                            return;
                        }
                        requestUpData(text);
                    }
                });
            }
        });

    }

    /**
     * 新增意见
     *
     * @param text ={"myOpinion":"我的意见内容","userID":1, "mobileType":"2"
     */
    private void requestUpData(String text) {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("myOpinion", text);
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        map.put("mobileType", Constants.MOBILE_TYPE);
        DoctorNetService.requestResult(Constants.ADD_YIJIAN, map, new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        requestMySuggestionList();
                    }
                });
    }

    @Override
    public Context context() {
        return null;
    }
}
