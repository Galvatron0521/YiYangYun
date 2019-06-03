package com.wd.yiyangyun.mvp.home.view.activity.zhuanzhen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.home.adapter.ZhuanZhenManageAdapter;
import com.wd.yiyangyun.mvp.home.view.activity.AddZhuanZhenActivity;
import com.wd.yiyangyun.mvp.home.view.fragment.ZhuanOutFragment;
import com.wd.yiyangyun.mvp.home.view.fragment.ZhuanInFragment;

import java.util.ArrayList;
import java.util.List;

public class ZhuanZhenActivity extends BaseActivity {


    private ImageView title_back;
    private TextView title_name;
    private ImageView image_right;
    private TabLayout zhuanzhen_manege_tab;
    private ViewPager zhuanzhen_manege_viewpager;
    private ZhuanZhenManageAdapter zhuanZhenManageAdapter;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_zhuan_zhen;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        image_right =  findViewById(R.id.image_right);
        zhuanzhen_manege_tab = (TabLayout) findViewById(R.id.zhuanzhen_manege_tab);
        zhuanzhen_manege_viewpager = (ViewPager) findViewById(R.id.zhuanzhen_manege_viewpager);
        title_back.setVisibility(View.VISIBLE);
        image_right.setVisibility(View.VISIBLE);
        title_name.setText("转诊");
    }

    @Override
    protected void initData() {
        super.initData();
        zhuanZhenManageAdapter = new ZhuanZhenManageAdapter(getSupportFragmentManager());
        zhuanzhen_manege_viewpager.setAdapter(zhuanZhenManageAdapter);
        List<Fragment> list = new ArrayList<>();
        list.add(new ZhuanOutFragment());
        list.add(new ZhuanInFragment());
        zhuanZhenManageAdapter.setList(list);
        zhuanzhen_manege_tab.setupWithViewPager(zhuanzhen_manege_viewpager);//将TabLayout和ViewPager关联起来。

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
        image_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZhuanZhenActivity.this,AddZhuanZhenActivity.class));
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }
}
