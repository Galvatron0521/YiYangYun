package com.wd.yiyangyun.mvp.home.view.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.base.BaseActivity;

public class SuifangFangAnActivity extends BaseActivity {

    private RecyclerView suifangfangan_list;
    private ImageView title_back;
    private TextView title_name;
    private ImageView image_right;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_suifang_fang_an;
    }

    @Override
    protected void initViews() {
        super.initViews();
        suifangfangan_list = findViewById(R.id.suifangfangan_list);
        title_back = findViewById(R.id.title_back);
        title_name = findViewById(R.id.title_name);
        image_right = findViewById(R.id.image_right);
        title_back.setVisibility(View.VISIBLE);
        title_name.setVisibility(View.VISIBLE);
        image_right.setVisibility(View.VISIBLE);
        title_name.setText("随访方案");
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    public Context context() {
        return null;
    }
}
