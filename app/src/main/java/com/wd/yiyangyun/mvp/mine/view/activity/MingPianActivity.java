package com.wd.yiyangyun.mvp.mine.view.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.base.BaseActivity;

public class MingPianActivity extends BaseActivity {

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_ming_pian;
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
