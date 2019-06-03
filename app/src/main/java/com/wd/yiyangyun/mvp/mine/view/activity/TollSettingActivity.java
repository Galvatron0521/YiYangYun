package com.wd.yiyangyun.mvp.mine.view.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.bean.QueryTollBean;
import com.wd.yiyangyun.mvp.mine.bean.UpdateTollBean;

import static com.wd.yiyangyun.utils.SynchniceUtil.addToll;
import static com.wd.yiyangyun.utils.SynchniceUtil.questToll;
import static com.wd.yiyangyun.utils.SynchniceUtil.updateToll;

public class TollSettingActivity extends BaseActivity {

    private TextView submit_tv;
    private TextView xinzeng_tv;
    private EditText chat_toll_et;
    private EditText chat_num_toll_et;
    private ImageView title_back;
    private TextView title_name;
    private LinearLayout update_toll_ll;
    private String tollId;
    private String chargeAmout;
    private int messageCount;
    private QueryTollBean queryTollBean;
    boolean updateFrist = false;
    private TextView baocun_tv;
    private TextView chat_toll_tv;
    private TextView chat_num_toll_tv;
    private LinearLayout update_toll_ll2;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_toll_setting;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        submit_tv = findViewById(R.id.submit_tv);
        xinzeng_tv = findViewById(R.id.xinzeng_tv);
        chat_toll_et = findViewById(R.id.chat_toll_et);
        chat_num_toll_et = findViewById(R.id.chat_num_toll_et);
        chat_toll_tv = findViewById(R.id.chat_toll_tv);
        chat_num_toll_tv = findViewById(R.id.chat_num_toll_tv);
        update_toll_ll = findViewById(R.id.update_toll_ll);
        update_toll_ll2 = findViewById(R.id.update_toll_ll2);
        baocun_tv = findViewById(R.id.baocun_tv);
        title_name.setText("咨询收费");
        title_name.setVisibility(View.VISIBLE);
        title_back.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        queryTollBean = questToll();
        if(queryTollBean.getData().getList().size()==0){
            xinzeng_tv.setVisibility(View.VISIBLE);
        }else{
            xinzeng_tv.setVisibility(View.GONE);
            update_toll_ll.setVisibility(View.GONE);
            update_toll_ll2.setVisibility(View.VISIBLE);
            tollId = queryTollBean.getData().getList().get(0).getId();
            chargeAmout = queryTollBean.getData().getList().get(0).getChargeAmout();
            messageCount = queryTollBean.getData().getList().get(0).getMessageCount();
            chat_toll_tv.setText(chargeAmout);
            chat_num_toll_tv.setText(messageCount+"");
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat_toll_et.clearFocus();//失去焦点
                chat_num_toll_et.clearFocus();//失去焦点
                //关闭软键盘
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                finish();
            }
        });
        xinzeng_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xinzeng_tv.setVisibility(View.GONE);
                update_toll_ll.setVisibility(View.VISIBLE);
                update_toll_ll2.setVisibility(View.GONE);
            }
        });
        submit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_toll_ll2.setVisibility(View.GONE);
                queryTollBean = questToll();
                String chargeAmout = queryTollBean.getData().getList().get(0).getChargeAmout();
                int messageCount = queryTollBean.getData().getList().get(0).getMessageCount();
                chat_toll_et.setText(chargeAmout);
                chat_num_toll_et.setText(messageCount+"");
                update_toll_ll.setVisibility(View.VISIBLE);
            }
        });
        baocun_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(queryTollBean.getData().getList().size()==0){
                    String chargeAmout = chat_toll_et.getText().toString();
                    String messageCount = chat_num_toll_et.getText().toString();
                    chat_toll_et.clearFocus();//失去焦点
                    chat_num_toll_et.clearFocus();//失去焦点
                    //关闭软键盘
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                    if(chargeAmout!=null && messageCount!=null){
                        Log.e("tttttt", "第一次添加");
                        UpdateTollBean updateTollBean = addToll(chargeAmout,messageCount);
                        if(updateTollBean.getStatus().equals("0")){
                            Toast.makeText(TollSettingActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                            chat_toll_tv.setText(chargeAmout);
                            chat_num_toll_tv.setText(messageCount+"");
                            update_toll_ll2.setVisibility(View.VISIBLE);
                            update_toll_ll.setVisibility(View.GONE);
                        }else{
                            Toast.makeText(TollSettingActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    String chargeAmout = chat_toll_et.getText().toString();
                    String messageCount = chat_num_toll_et.getText().toString();
                    chat_toll_et.clearFocus();//失去焦点
                    chat_num_toll_et.clearFocus();//失去焦点
                    //关闭软键盘
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                    if(chargeAmout!=null && messageCount!=null){
                        Log.e("tttttt", "修改"+tollId+chargeAmout+messageCount);
                        UpdateTollBean updateTollBean = updateToll(tollId,chargeAmout,messageCount);
                        if(updateTollBean.getStatus().equals("0")){
                            chat_toll_tv.setText(chargeAmout);
                            chat_num_toll_tv.setText(messageCount+"");
                            update_toll_ll2.setVisibility(View.VISIBLE);
                            update_toll_ll.setVisibility(View.GONE);
                            Toast.makeText(TollSettingActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(TollSettingActivity.this,"修改失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    @Override
    public Context context() {
        return null;
    }
}
