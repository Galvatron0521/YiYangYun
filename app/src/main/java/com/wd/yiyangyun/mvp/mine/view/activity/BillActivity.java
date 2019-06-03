package com.wd.yiyangyun.mvp.mine.view.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.adapter.BillListAdapter;
import com.wd.yiyangyun.mvp.mine.bean.MyTollBean;

import java.util.List;

import static com.wd.yiyangyun.utils.SynchniceUtil.myToll;

public class BillActivity extends BaseActivity {

    private MyTollBean myTollBean;
    private ImageView title_back;
    private RecyclerView bill_list;
    private TextView title_name;
    private BillListAdapter billListAdapter;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_bill;
    }

    @Override
    protected void initViews() {
        super.initViews();

        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        bill_list =  findViewById(R.id.bill_list);
        title_name.setText("账单详情");
        title_name.setVisibility(View.VISIBLE);
        title_back.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initData() {
        super.initData();

        try {
            myTollBean = myToll();
            List<MyTollBean.DataBean.ListBean> mDatas = myTollBean.getData().getList();
            billListAdapter = new BillListAdapter(this, mDatas);
            bill_list.setLayoutManager(new LinearLayoutManager(this));
            bill_list.setAdapter(billListAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    }

    @Override
    public Context context() {
        return null;
    }
}
