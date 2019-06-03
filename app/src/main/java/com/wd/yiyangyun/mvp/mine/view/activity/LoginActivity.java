package com.wd.yiyangyun.mvp.mine.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wd.yiyangyun.HttpCallback;
import com.wd.yiyangyun.HttpResMsg;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.base.BaseActivity2;
import com.wd.yiyangyun.mvp.mine.bean.LoginBean;
import com.wd.yiyangyun.utils.HcUtils;
import com.wd.yiyangyun.utils.JSONUtil;
import com.wd.yiyangyun.utils.ToastUtils;
import com.wd.yiyangyun.utils.permisson.PermissionHelper;
import com.wd.yiyangyun.utils.permisson.PermissionInterface;

import java.util.HashMap;
import java.util.Map;

import static com.wd.yiyangyun.app.Constants.LOGIN;
import static com.wd.yiyangyun.mvp.mine.presenter.MinePresenter.requestGetHttp;

public class LoginActivity extends BaseActivity2 implements PermissionInterface {

    private LoginBean loginBean;
    private EditText login_account;
    private EditText login_pwd;
    private Button login_bt_login;
    private PermissionHelper permissionHelper;
    private ImageView image_yan;
    //读写权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //请求状态码
    private static int REQUEST_PERMISSION_CODE = 1;


    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        super.initViews();


        permissionHelper = new PermissionHelper(this, this);
        permissionHelper.requestPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, 1);
        permissionHelper.requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, 1);

        login_account = (EditText) findViewById(R.id.login_account);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        login_bt_login = (Button) findViewById(R.id.login_bt);
        image_yan = findViewById(R.id.image_yan);

//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
//            }
//        }

    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_PERMISSION_CODE) {
//            for (int i = 0; i < permissions.length; i++) {
//                Log.i("MainActivity", "申请的权限为：" + permissions[i] + ",申请结果：" + grantResults[i]);
//            }
//        }
//    }



    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
                Log.e("ttttttt", "checkPermission: 授权！");
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.e("ttttttt", "checkPermission: 已经授权！");
        }
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

    private void savegetData(LoginBean loginBean) {
        DoctorBaseAppliction.spUtil.putString(Constants.USER_ID, loginBean.getData().getUser().getUserID()+"");
        DoctorBaseAppliction.spUtil.putString(Constants.HOSPITAL_ID, loginBean.getData().getUser().getHospitalID()+"");
        DoctorBaseAppliction.spUtil.putString(Constants.DepartmentID, loginBean.getData().getUser().getDepartmentID()+"");
        DoctorBaseAppliction.spUtil.putString(Constants.LOGIN_NAME, loginBean.getData().getUser().getLoginName());
        DoctorBaseAppliction.spUtil.putString(Constants.Name, loginBean.getData().getUser().getName());
        DoctorBaseAppliction.spUtil.putString(Constants.SEX, loginBean.getData().getUser().getSex()+"");
        DoctorBaseAppliction.spUtil.putString(Constants.NATIONAL, loginBean.getData().getUser().getNational());
        DoctorBaseAppliction.spUtil.putString(Constants.BRITHDAY, loginBean.getData().getUser().getBrithday());
        DoctorBaseAppliction.spUtil.putString(Constants.MOBILE, loginBean.getData().getUser().getMobile());
        DoctorBaseAppliction.spUtil.putString(Constants.ID_CARD, loginBean.getData().getUser().getIDCard());
        DoctorBaseAppliction.spUtil.putString(Constants.PHOTO_ID, loginBean.getData().getUser().getPhotoID()+"");
        DoctorBaseAppliction.spUtil.putString(Constants.PHOTO_URL, loginBean.getData().getUser().getPhotoUrl());
        DoctorBaseAppliction.spUtil.putString(Constants.ADDRESS, loginBean.getData().getUser().getAddress());
        DoctorBaseAppliction.spUtil.putString(Constants.KESHI, loginBean.getData().getUser().getFileUrl());

    }

    @Override
    protected void initListener() {
        super.initListener();
        login_bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    private void submit() {
        // validate
        String account = login_account.getText().toString().trim();
        HcUtils.setEtFilter(this,login_account);
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = login_pwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        requestLogin(account, pwd);
    }

    private void requestLogin(final String account, final String pwd) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", account);
        map.put("password", pwd);
        String json = JSONUtil.parseMapToJson(map);
        Log.e("json", "onResume: "+json );
        requestGetHttp(LOGIN+json, new HttpCallback() {
            @Override
            public void onSuccess(HttpResMsg httpResMsg) {
                Gson gson = new Gson();
                loginBean = gson.fromJson(httpResMsg.getData(), LoginBean.class);
                //设置数据保存
                savegetData(loginBean);
                DoctorBaseAppliction.spUtil.putString("account", account);
                DoctorBaseAppliction.spUtil.putString("pwd", pwd);
                ToastUtils.showMessage(mContext, "登录成功");
                startActivity(new Intent(mContext, MainActivity.class));
                finish();
            }

            @Override
            public void onError(String errorMsg) {
                ToastUtils.showMessage(mContext, "登录失败，请重试");
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void requestPermissionsSuccess(int callBackCode) {

    }

    @Override
    public void requestPermissionsFail(int callBackCode) {

    }
}
