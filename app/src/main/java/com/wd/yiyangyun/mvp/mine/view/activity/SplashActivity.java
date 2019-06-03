package com.wd.yiyangyun.mvp.mine.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.utils.ToastUtils;

public class SplashActivity extends AppCompatActivity {
    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                skipLoginOrMain();

            }
        }, 1 * 1000);
    }

    private void skipLoginOrMain() {

        String account = DoctorBaseAppliction.spUtil.getString("account", "");
        String pwd = DoctorBaseAppliction.spUtil.getString("pwd", "");

        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(pwd)) {
            ToastUtils.showMessage(mContext, "登录成功");
            startActivity(new Intent(mContext, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(mContext, LoginActivity.class));
            finish();
        }
    }

}
