package com.wd.yiyangyun.mvp.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseFragment;
import com.wd.yiyangyun.mvp.home.adapter.HomeRecycAdapter;
import com.wd.yiyangyun.mvp.home.bean.NewsBean;
import com.wd.yiyangyun.mvp.home.view.activity.NewsInfoActivity;
import com.wd.yiyangyun.mvp.news.adapter.NewsAdapter;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsFragment extends BaseFragment {

    private Banner banner;
    private RecyclerView news_list;
    private NewsAdapter newsAdapter;
    private List<NewsBean.DataBean.InformationListBean> mDatas;
    private NewsBean newsBean;

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.news_fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume", "资讯" );

    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        news_list.setLayoutManager(linearLayoutManager);
        newsAdapter = new NewsAdapter(getActivity(),mDatas);
        news_list.setAdapter(newsAdapter);
        requestNewsList();

        newsAdapter.setSetOnClick(new HomeRecycAdapter.setOnClick() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), NewsInfoActivity.class);
                NewsBean.DataBean.InformationListBean listBean = mDatas.get(position);
                intent.putExtra("newsBean", listBean);
                startActivity(intent);
            }
        });
    }

    private void requestNewsList() {
        Map<String, Object> map = new HashMap<>();
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));
        DoctorNetService.requestNewsList(Constants.NewsList, map,
                new NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onNext(Object info) {
                        newsBean = (NewsBean) info;
                        mDatas.addAll(newsBean.getData().getInformationList());
                        newsAdapter.notifyDataSetChanged();

                        //轮播图
                        List<String> list = newsBean.getData().getWheelList();
                        List<String> urlList = new ArrayList<>();
                         for(int i=0; i< list.size(); i++){
                             String url = "http://shenkangyun.com:808/" +list.get(i);
                             urlList.add(url);
                         }
                        banner.setImages(urlList);
                        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
                        banner.setBannerAnimation(Transformer.Default);
                        banner.setImageLoader(new MyImagerLoader());
                        banner.setDelayTime(2000);
                        banner.setIndicatorGravity(BannerConfig.CENTER);
                        banner.start();

                    }
                });

    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        banner = view.findViewById(R.id.banner);
        news_list = view.findViewById(R.id.news_list);
        news_list.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动

    }



    private class MyImagerLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
//            Uri uri = Uri.parse((String) path);
//            imageView.setImageURI(uri);
             Glide.with(context).load(path).into(imageView);
        }
        @Override
        public ImageView createImageView(Context context) {
            ImageView imageView = new ImageView(context);
            return imageView;
        }
    }
}
