package com.wd.yiyangyun.mvp.mine.view.fragment;

import android.content.Intent;
import android.net.Uri;
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
import com.wd.yiyangyun.mvp.mine.view.activity.CalendarActivity;
import com.wd.yiyangyun.mvp.mine.view.activity.DoctorInfoActivity;
import com.wd.yiyangyun.mvp.mine.view.activity.GroupManagerActivity;
import com.wd.yiyangyun.mvp.mine.view.activity.MySuggestionAct;
import com.wd.yiyangyun.mvp.mine.view.activity.MyTollActivity;
import com.wd.yiyangyun.mvp.mine.view.activity.SheZhiActivity;
import com.wd.yiyangyun.mvp.mine.view.activity.TollSettingActivity;
import com.wd.yiyangyun.utils.GlideCircleTransUtils;

public class MineFragment extends BaseFragment {

    private LinearLayout qianbao_ll;
    private LinearLayout qiandao_ll;
    private LinearLayout renwu_ll;
    private LinearLayout mingpian_ll;
    private LinearLayout richeng_ll;
    private LinearLayout setting_ll;
    private LinearLayout group_manger_ll;
    private LinearLayout doctors_info_ll;
    private TextView doctors_name_tv;
    private TextView doctors_id_tv;
    private ImageView doctors_image_iv;
    private LinearLayout shoufei_ll;
    private LinearLayout yijian_ll;

    @Override
    protected Object provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.mine_fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume", "我的" );


        String a = DoctorBaseAppliction.spUtil.getString(Constants.PHOTO_URL, "");
            Log.e("444444", "initData: "+a);
            Glide.with(getActivity())
                    .load(a)
                    .transform(new GlideCircleTransUtils(getActivity()))
                    .into(doctors_image_iv);
//            Picasso.with(getActivity()).load(Uri.parse(a)).transform(new CircleTransform(getActivity())).into(my_info_logo_iv);
        initData();
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        qianbao_ll = view.findViewById(R.id.qianbao_ll);
        qiandao_ll = view.findViewById(R.id.qiandao_ll);
        renwu_ll = view.findViewById(R.id.renwu_ll);
        mingpian_ll = view.findViewById(R.id.mingpian_ll);
        richeng_ll = view.findViewById(R.id.richeng_ll);
        setting_ll = view.findViewById(R.id.setting_ll);
        group_manger_ll = view.findViewById(R.id.group_manger_ll);
        doctors_info_ll = view.findViewById(R.id.doctors_info_ll);
        doctors_image_iv = view.findViewById(R.id.doctors_image_iv);
        doctors_name_tv = view.findViewById(R.id.doctors_name_tv);
        doctors_id_tv = view.findViewById(R.id.doctors_id_tv);
        shoufei_ll = view.findViewById(R.id.shoufei_ll);
        yijian_ll = view.findViewById(R.id.yijian_ll);

        String name = DoctorBaseAppliction.spUtil.getString(Constants.Name,"");
//        String h = DoctorBaseAppliction.spUtil.getString(Constants.ho,"");
        doctors_name_tv.setText(name);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListener() {
        super.initListener();

        //意见反馈
        yijian_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MySuggestionAct.class);
                startActivity(intent);
            }
        });
        shoufei_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TollSettingActivity.class);
                startActivity(intent);
            }
        });
        //医生详情
        doctors_info_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DoctorInfoActivity.class);
                startActivity(intent);
            }
        });
        //钱包
        qianbao_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyTollActivity.class);
                startActivity(intent);
            }
        });
        //签到
        qiandao_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),);
//                startActivity(intent);
            }
        });
        //任务奖励
        renwu_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),);
//                startActivity(intent);
            }
        });
        //分组管理
        group_manger_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GroupManagerActivity.class);
                startActivity(intent);
            }
        });
        //我的名片
        mingpian_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),);
//                startActivity(intent);
            }
        });
        //日程表 日历
        richeng_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivity(intent);
            }
        });
        //设置
        setting_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SheZhiActivity.class);
                startActivity(intent);
            }
        });

    }

 }
