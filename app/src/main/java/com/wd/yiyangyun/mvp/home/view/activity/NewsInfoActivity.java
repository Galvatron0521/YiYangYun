package com.wd.yiyangyun.mvp.home.view.activity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.home.bean.NewsBean;
import com.wd.yiyangyun.utils.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewsInfoActivity extends BaseActivity {

    private NewsBean.DataBean.InformationListBean listBean;
    private TextView title_tv;
    private ImageView news_image;
    private TextView content_tv;
    private TextView title_name;
    private TextView news_time;
    private ImageView title_back;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_news_info;
    }

    @Override
    protected void initViews() {
        super.initViews();

        listBean = (NewsBean.DataBean.InformationListBean) getIntent().getSerializableExtra("newsBean");
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_tv = findViewById(R.id.title_tv);
        news_image = findViewById(R.id.news_image);
        news_time = findViewById(R.id.news_time);
        content_tv = findViewById(R.id.content_tv);
        title_tv.setText(listBean.getName());
        LogUtil.e("tttttttttt", listBean.getCoverPhoto() + "");
        Glide.with(mContext).load("http://shenkangyun.com:808/" + listBean.getCoverPhoto().get(0))
                .into(news_image);
        content_tv.setText(listBean.getContent());
        title_name.setText("资讯详情");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(new Date(listBean.getCreateTime()));
        news_time.setText(format);
        title_name.setVisibility(View.VISIBLE);
        title_back.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
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
    }

    @Override
    public Context context() {
        return null;
    }
}
