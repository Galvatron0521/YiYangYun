package com.wd.yiyangyun.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.bigkoo.pickerview.TimePickerView;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.view.listener.OnCreateBodyViewListener;
import com.mylhyl.circledialog.view.listener.OnInputClickListener;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.utils.LogUtil;
import com.youngfeng.snake.Snake;
import com.youngfeng.snake.annotations.EnableDragToClose;

import butterknife.ButterKnife;


@EnableDragToClose
public abstract class BaseActivity extends AppCompatActivity {
    boolean them = false;
    protected Context mContext = this;
    protected TimePickerView pvTime;
    protected LoadingDailog.Builder loadBuilder;
    protected LoadingDailog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        ButterKnife.bind(provideBindView());
        Snake.host(this);
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


    @SuppressLint("ResourceAsColor")
    public void showInputDialog(String hint, OnInputClickListener onInputClickListener) {
        new CircleDialog.Builder(this)
                //添加标题，参考普通对话框
                .setInputHint(hint)//提示
                .setTitle("请输入")
                .setWidth(0.7f)
                .setMaxHeight(0.4f)
                .setInputCounterColor(R.color.text_color)//最大字符数文字的颜色值
                .autoInputShowKeyboard()//自动弹出键盘
                .setPositiveInput("确定", onInputClickListener)
                .show();
    }



    public void showWaitDialog() {
//        if (dialog.isShowing()) return;
        loadBuilder = new LoadingDailog.Builder(this)
                .setCancelable(true)
                .setShowMessage(false)
                .setCancelOutside(false);
        dialog = loadBuilder.create();
        dialog.show();
    }

    public void hideWaitDialog() {
        if (dialog != null) {
            dialog.cancel();
        }
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
