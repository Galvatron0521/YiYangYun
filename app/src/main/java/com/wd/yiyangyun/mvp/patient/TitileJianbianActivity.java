package com.wd.yiyangyun.mvp.patient;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.home.adapter.HomeRecycAdapter;
import com.wd.yiyangyun.mvp.patient.view.GradationScrollView;

import java.util.ArrayList;
import java.util.List;

public class TitileJianbianActivity extends BaseActivity implements GradationScrollView.ScrollViewListener {

    private RecyclerView search_recyc;
    private ImageView ivBlurViewMovieInfo;
    private int imageHeight;
    private GradationScrollView gsvMovieInfo;
    private LinearLayout llTitleMovieInfo;
    private TextView tvTitleMovieInfo;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        super.initViews();
        search_recyc = findViewById(R.id.search_recyc);
        ivBlurViewMovieInfo = findViewById(R.id.jb_image);
        gsvMovieInfo = findViewById(R.id.gsv_movie_info);
        llTitleMovieInfo = findViewById(R.id.ll_title_movie_info);
        tvTitleMovieInfo = findViewById(R.id.tv_title_movie_info);
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<String> list2 = new ArrayList<>();
        for(int i=0; i< 10; i++){
            list2.add("医养云咨询"+i);
        }

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        search_recyc.setLayoutManager(linearLayoutManager);
//        HomeRecycAdapter newsAdapter = new HomeRecycAdapter(this,list2);
//        search_recyc.setAdapter(newsAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        /**
         * 获取顶部图片高度后，设置滚动监听
         */
        ViewTreeObserver viewTreeObserver = ivBlurViewMovieInfo.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivBlurViewMovieInfo.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                imageHeight = ivBlurViewMovieInfo.getHeight();
                gsvMovieInfo.setScrollViewListener(TitileJianbianActivity.this);
            }
        });

    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            llTitleMovieInfo.setBackgroundColor(Color.argb((int) 0, 211, 65, 60));
        } else if (y > 0 && y <= imageHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / imageHeight;
            float alpha = (255 * scale);
            tvTitleMovieInfo.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            llTitleMovieInfo.setBackgroundColor(Color.argb((int) alpha, 211, 65, 60));
        } else {    //滑动到banner下面设置普通颜色
            llTitleMovieInfo.setBackgroundColor(Color.argb((int) 255, 211, 65, 60));
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
