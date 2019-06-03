package com.wd.yiyangyun.mvp.mine.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.bean.MyTollBean;

import static com.wd.yiyangyun.utils.SynchniceUtil.myToll;

public class MyTollActivity extends BaseActivity {

    private ImageView title_back;
    private TextView title_name;
    private LinearLayout my_toll_ll;
    private LinearLayout bill_ll;
    private String userId;
    private MyTollBean myTollBean;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_my_toll;
    }

    @Override
    protected void initViews() {
        super.initViews();

        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        my_toll_ll = (LinearLayout) findViewById(R.id.my_toll_ll);
        bill_ll = (LinearLayout) findViewById(R.id.bill_ll);
        title_name.setText("我的收益");
        title_name.setVisibility(View.VISIBLE);
        title_back.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initData() {
        super.initData();
        userId = DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, "");
        myTollBean = myToll();
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
        my_toll_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 我的钱包
                Intent intent = new Intent(MyTollActivity.this,WalletActivity.class);
                intent.putExtra("wallet",myTollBean.getData().getAllTotal()+"");
                startActivity(intent);
            }
        });
        bill_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 我的钱包
                startActivity(new Intent(MyTollActivity.this,BillActivity.class));
            }
        });

    }

    @Override
    public Context context() {
        return null;
    }
}
