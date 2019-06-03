package com.wd.yiyangyun.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tu.loadingdialog.LoadingDailog;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.view.listener.OnInputClickListener;
import com.wd.yiyangyun.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {
    private View view;
    private Unbinder unbinder;
    protected LoadingDailog.Builder loadBuilder;
    protected LoadingDailog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(provideLayoutId(), container, false);
        unbinder = ButterKnife.bind(provideBindView(), view);
        return view;
    }

    protected abstract Object provideBindView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews(view);
        //提供presenter
        initData();
        initListener();

    }

    @SuppressLint("ResourceAsColor")
    public void showInputDialog(String hint, OnInputClickListener onInputClickListener) {
        new CircleDialog.Builder(getActivity())
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
        loadBuilder = new LoadingDailog.Builder(getActivity())
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


    protected abstract int provideLayoutId();


    protected void initViews(View view) {
    }

    protected void initData() {
    }

    protected void initListener() {
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
