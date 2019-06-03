package com.wd.yiyangyun.mvp.mine.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.view.cebianlan.GoodMan;
import com.wd.yiyangyun.mvp.mine.view.cebianlan.MyAdapter;
import com.wd.yiyangyun.mvp.mine.view.cebianlan.QuickIndexBar;
import com.youngfeng.snake.Snake;
import com.youngfeng.snake.annotations.EnableDragToClose;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SettingActivity extends BaseActivity{

    private List<GoodMan> mDatas;
    private ListView mLv_data;
    private TextView mTv_tip;
    private MyAdapter mMyAdapter;
    private QuickIndexBar mQuickIndexBar;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_setting;
    }


    @Override
    protected void initViews() {
        super.initViews();
        mLv_data = (ListView) findViewById(R.id.lv_data);
        mTv_tip = (TextView)findViewById(R.id.tv_tip);
        mQuickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
        initData2();
        mMyAdapter = new MyAdapter(this,mDatas);
        mLv_data.setAdapter(mMyAdapter);

        mQuickIndexBar.setOnLetterChangeListener(new QuickIndexBar.OnLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {

                showLetter(letter);

                for (int i = 0;i<mDatas.size();i++){
                    if(TextUtils.equals(letter,mDatas.get(i).pinyin.charAt(0)+"")){
                        mLv_data.setSelection(i);
                        break;
                    }
                }
            }
        });

    }

    @Override
    public Context context() {
        return null;
    }

    private void initData2() {
        mDatas = new ArrayList<GoodMan>();
        for(int i = 0;i< 3;i++){
            GoodMan goodMan = new GoodMan("张三"+i);
            mDatas.add(goodMan);
        }
        for(int i = 0;i< 3;i++){
            GoodMan goodMan = new GoodMan("李四"+i);
            mDatas.add(goodMan);
        }
        for(int i = 0;i< 3;i++){
            GoodMan goodMan = new GoodMan("王五"+i);
            mDatas.add(goodMan);
        }
        for(int i = 0;i< 3;i++){
            GoodMan goodMan = new GoodMan("应六"+i);
            mDatas.add(goodMan);
        }
        for(int i = 0;i< 3;i++){
            GoodMan goodMan = new GoodMan("好八"+i);
            mDatas.add(goodMan);
        }
        for(int i = 0;i< 3;i++){
            GoodMan goodMan = new GoodMan("大四"+i);
            mDatas.add(goodMan);
        }
        for(int i = 0;i< 3;i++){
            GoodMan goodMan = new GoodMan("管tr"+i);
            mDatas.add(goodMan);
        }
        //对list中的数据进行排序
        Collections.sort(mDatas);

    }

    Handler mHandler = new Handler();

    /**
     * 显示字母提示
     * @param letter
     */
    public void showLetter(String letter){
        mTv_tip.setVisibility(View.VISIBLE);
        mTv_tip.setText(letter);
        //取消掉刚刚所有的演示操作
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTv_tip.setVisibility(View.GONE);
            }
        },1000);

    }

}
