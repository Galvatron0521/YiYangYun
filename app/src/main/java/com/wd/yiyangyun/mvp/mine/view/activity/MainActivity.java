package com.wd.yiyangyun.mvp.mine.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;
import com.wd.yiyangyun.base.BaseActivity2;
import com.wd.yiyangyun.mvp.mine.bean.LoginBean;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.home.view.fragment.HomeFragment;
import com.wd.yiyangyun.mvp.mine.view.fragment.MineFragment;
import com.wd.yiyangyun.mvp.news.NewsFragment;
import com.wd.yiyangyun.mvp.patient.view.fragment.PatientFragment;
import com.wd.yiyangyun.mvp.patient.TitileJianbianActivity;
import com.wd.yiyangyun.utils.JSONUtil;
import com.wd.yiyangyun.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity2 {

    private BottomTabBar btbMain;
    private TextView mTitleName;
    private TextView mTitleRightTv;
    private ImageView imageRight;
    private int imageRightPotition;
    private LoginBean loginBean;

    @Override
    protected Activity provideBindView() {
        return this;
    }


    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        super.initViews();
        imageRight = (ImageView) findViewById(R.id.image_right);
        mTitleName = (TextView) findViewById(R.id.title_name);
        mTitleRightTv = (TextView) findViewById(R.id.title_right_tv);
        btbMain = findViewById(R.id.btb_main);
    }

    @Override
    protected void initData() {
        super.initData();
        btbMain.init(getSupportFragmentManager())
                .setFontSize(12)
                .addTabItem("首页", R.mipmap.tab_icon_home_nor, HomeFragment.class)
                .addTabItem("患者", R.mipmap.tab_icon_huanzhe_nor, PatientFragment.class)
                .addTabItem("资讯", R.mipmap.tab_icon_zixun_nor, NewsFragment.class)
                .addTabItem("我的", R.mipmap.tab_icon_wode_nor, MineFragment.class)
                .setCurrentTab(0)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name, View view) {
                        //暂时就返回了这俩参数，如果还有什么用的比较多的参数，欢迎留言告诉我，我继续添加上
                        mTitleName.setText(name);
                        if(name.equals("首页")){
                            imageRight.setVisibility(View.VISIBLE);
                            imageRightPotition = 0;
                            Log.e("eeeee", "onClick:1 "+imageRightPotition);
                        }
                        if(name.equals("患者")){
                            imageRight.setVisibility(View.VISIBLE);
                            imageRightPotition = 1;
                            Log.e("eeeee", "onClick:2 "+imageRightPotition);
                        }
                        if(name.equals("咨询") || name.equals("我的")){
                            imageRight.setVisibility(View.GONE);
                        }
                    }
                })
                .isShowDivider(false);
        imageRight.setVisibility(View.VISIBLE);
        mTitleName.setText("首页");
    }

    @Override
    protected void onResume() {
        super.onResume();
            Map<String,Object> map = new HashMap<>();
            map.put("username", "18854889207");
            map.put("password", "123456");
            String json = JSONUtil.parseMapToJson(map);
            Log.e("ok","onResume: "+json );
    }

    @Override
    protected void initListener() {
        super.initListener();

            imageRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("eeeee", "onClick: "+imageRightPotition);
                    if(imageRightPotition==0) {
                        Intent intent = new Intent(MainActivity.this,TitileJianbianActivity.class);
                        startActivity(intent);
                    }
                    if(imageRightPotition==1) {
                        ToastUtils.showMessage(MainActivity.this,"提醒");
                    }
                }
            });
    }

    @Override
    public Context context() {
        return this;
    }

    private long firstTime = 0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK://点击返回键
                if (keyCode == event.KEYCODE_BACK) {
                    Log.e("ttttttt", "退出");
                    long secondTime = System.currentTimeMillis();//以毫秒为单位
                    if (secondTime - firstTime > 2000) {
                        Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                        firstTime = secondTime;
                    } else {
                        finish();
                        System.exit(0);
                    }
                }

        }
        return true;
    }
}
