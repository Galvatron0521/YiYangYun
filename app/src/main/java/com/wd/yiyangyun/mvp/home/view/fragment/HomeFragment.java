package com.wd.yiyangyun.mvp.home.view.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseFragment;
import com.wd.yiyangyun.mvp.home.adapter.HomeRecycAdapter;
import com.wd.yiyangyun.mvp.home.adapter.HomeRecycGridAdapter;
import com.wd.yiyangyun.mvp.home.bean.NewsBean;
import com.wd.yiyangyun.mvp.home.view.activity.NewsInfoActivity;
import com.wd.yiyangyun.mvp.home.view.activity.SearchActivity;
import com.wd.yiyangyun.mvp.home.view.activity.VideoActivity;
import com.wd.yiyangyun.mvp.home.view.activity.suifangmanager.SuiFangManageActivity;
import com.wd.yiyangyun.mvp.home.view.activity.zhuanzhen.ZhuanZhenActivity;
import com.wd.yiyangyun.mvp.home.view.activity.bianzheng.BianZhengLunDetailZhiActivity;
import com.wd.yiyangyun.mvp.home.view.activity.bianzheng.AddBianZhengLunZhiActivity;
import com.wd.yiyangyun.mvp.home.view.activity.suifangfangan.MyPlanListActivity;
import com.wd.yiyangyun.mvp.mine.view.activity.CalendarActivity;
import com.wd.yiyangyun.mvp.patient.view.activity.AddPatientInfoActivity;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.GlideCircleTransUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wd.yiyangyun.mvp.mine.view.activity.DoctorInfoActivity.UPDATE_ICON;

public class HomeFragment extends BaseFragment {

    private TextView doctors_name_tv;
    private RecyclerView home_gridview;
    private HomeRecycGridAdapter homeGridAdapter;
    private ImageView doctors_name_iv;
    private RecyclerView home_news_list;
    private TextView hospital_tv;
    private LinearLayout yingshi_ll;
    private LinearLayout shiping_ll;
    private LinearLayout chuandai_ll;
    private LinearLayout suifangfangan_ll;
    private LinearLayout suifangjilu_ll;
    private LinearLayout pinggu_ll;
    private LinearLayout chuzhen_ll;
    private LinearLayout dianhua_ll;
    private LinearLayout tuwen_ll;
    private LinearLayout zhuanzhen_ll;
    private HomeRecycAdapter newsAdapter;
    private List<NewsBean.DataBean.InformationListBean> mDatas;

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        EventBus.getDefault().register(this);
        doctors_name_tv = view.findViewById(R.id.doctors_name_tv);
        hospital_tv = view.findViewById(R.id.hospital_tv);
        doctors_name_iv = view.findViewById(R.id.doctors_name_iv);
        home_gridview = view.findViewById(R.id.home_gridview);
        home_news_list = view.findViewById(R.id.home_news_list);
        home_news_list.setHasFixedSize(true);
        home_news_list.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        zhuanzhen_ll = view.findViewById(R.id.zhuanzhen_ll);
        tuwen_ll = view.findViewById(R.id.tuwen_ll);
        dianhua_ll = view.findViewById(R.id.dianhua_ll);
        chuzhen_ll = view.findViewById(R.id.chuzhen_ll);
        pinggu_ll = view.findViewById(R.id.pinggu_ll);
        suifangjilu_ll = view.findViewById(R.id.suifangjilu_ll);
        suifangfangan_ll = view.findViewById(R.id.suifangfangan_ll);
        yingshi_ll = view.findViewById(R.id.yingshi_ll);
        shiping_ll = view.findViewById(R.id.shiping_ll);
        chuandai_ll = view.findViewById(R.id.chuandai_ll);


        String a = DoctorBaseAppliction.spUtil.getString(Constants.PHOTO_URL, "");
            Glide.with(this).load(a).transform(new GlideCircleTransUtils(getActivity())).into(doctors_name_iv);
//            Picasso.with(getActivity()).load(Uri.parse(a)).transform(new CircleTransform(getActivity())).into(my_info_logo_iv);
        String doctorName = DoctorBaseAppliction.spUtil.getString(Constants.Name,"");
        doctors_name_tv.setText(doctorName);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume", "首页: ");

//        List<String> list = new ArrayList<>();
//         for(int i=0; i< 20; i++){
//              list.add("转诊"+i);
//         }
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
//        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
//        home_gridview.setLayoutManager(gridLayoutManager);
//        homeGridAdapter = new HomeRecycGridAdapter(getActivity(),list);
//        home_gridview.setAdapter(homeGridAdapter);
    }

    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        home_news_list.setLayoutManager(linearLayoutManager);
        newsAdapter = new HomeRecycAdapter(getActivity(),mDatas);
        home_news_list.setAdapter(newsAdapter);
        requestNewsList();

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
                        NewsBean bean = (NewsBean) info;
                        mDatas.addAll(bean.getData().getInformationList());
                        newsAdapter.notifyDataSetChanged();
                    }
                });

    }

    @Override
    protected void initListener() {
        super.initListener();
        doctors_name_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),BianZhengLunDetailZhiActivity.class));
            }
        });
        hospital_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddBianZhengLunZhiActivity.class));
            }
        });
        //转诊
        zhuanzhen_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ZhuanZhenActivity.class));
            }
        });
        //图文
        tuwen_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddBianZhengLunZhiActivity.class));
            }
        });
        //电话
        dianhua_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddBianZhengLunZhiActivity.class));
            }
        }); //出诊
        chuzhen_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),CalendarActivity.class));
            }
        }); //评估
        pinggu_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SearchActivity.class));
            }
        }); //随访管理
        suifangjilu_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SuiFangManageActivity.class));
            }
        }); //随访方案
        suifangfangan_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyPlanListActivity.class);
                startActivity(intent);
            }
        }); //影视资料
        yingshi_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),VideoActivity.class));
            }
        }); //视频
        shiping_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddBianZhengLunZhiActivity.class));
            }
        }); //穿戴
        chuandai_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AddBianZhengLunZhiActivity.class));
            }
        });
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

       @Subscribe
    public void onEventMainThread(Map<String, Object> map) {
        String type = (String) map.get(Constants.EVENTBUS_TYEPE);
        if (type.equals(UPDATE_ICON)) {
            String personlIcon = (String) map.get("personlIcon");
            Glide.with(getActivity())
                    .load(personlIcon)
                    .transform(new GlideCircleTransUtils(getActivity()))
                    .into(doctors_name_iv);
        }
    }




}
