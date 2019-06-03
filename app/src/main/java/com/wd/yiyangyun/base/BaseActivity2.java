package com.wd.yiyangyun.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bigkoo.pickerview.TimePickerView;
import com.mylhyl.circledialog.CircleDialog;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.patient.view.GradationScrollView;

import butterknife.ButterKnife;



public abstract class BaseActivity2 extends AppCompatActivity {
    boolean them = false;
    protected Context mContext = this;
    protected TimePickerView pvTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        ButterKnife.bind(provideBindView());
        initViews();
        initData();
        initListener();
        setStatusBar();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//    }

    protected void setStatusBar() {
    }

    @SuppressLint("ResourceAsColor")
    public void showAffirmDialog(String title, String message, View.OnClickListener
            onClickListener) {
        new CircleDialog.Builder(this)
                .setTitle(title)
                .setText(message)//内容
                .setTextColor(R.color.text_color)//内容字体色值
                .setPositive("确定", onClickListener)
                .setNegative("取消", null)
                .setWidth(0.7f)
                .setMaxHeight(0.4f)
                .show();
    }


    protected abstract Activity provideBindView();

    protected abstract int provideLayoutId();


    protected void initViews() {
    }

    protected void initData() {
    }

    protected void initListener() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public abstract Context context();
}
