package com.wd.yiyangyun.mvp.mine.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;

public class SheZhiActivity extends BaseActivity {

    private Button log_out;
    private TextView title_name;
    private ImageView title_back;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_she_zhi;
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        log_out = findViewById(R.id.login_out);

        title_name.setText("设置");
        title_back.setVisibility(View.VISIBLE);
        title_name.setVisibility(View.VISIBLE);
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
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorBaseAppliction.spUtil.clear();
                startActivity(new Intent(SheZhiActivity.this, LoginActivity.class));
            }
        });
    }
}
