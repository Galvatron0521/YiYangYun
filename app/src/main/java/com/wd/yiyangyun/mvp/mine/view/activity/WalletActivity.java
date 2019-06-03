package com.wd.yiyangyun.mvp.mine.view.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.base.BaseActivity;

public class WalletActivity extends BaseActivity {

    private TextView wallet_tv;
    private ImageView title_back;
    private TextView title_name;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initViews() {
        super.initViews();

        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_name.setText("我的钱包");
        title_name.setVisibility(View.VISIBLE);
        title_back.setVisibility(View.VISIBLE);
        wallet_tv = findViewById(R.id.wallet_tv);

    }

    @Override
    protected void initData() {
        super.initData();
        try {
            String wallet = getIntent().getStringExtra("wallet");
            Log.e("tttttttt", "initData: "+wallet);
            if(wallet==null || wallet.equals("")){
                wallet_tv.setText("0.00元");
            }else{
                wallet_tv.setText(wallet+"元");
            }
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
