package com.wd.yiyangyun.mvp.mine.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.othershe.calendarview.bean.DateBean;
import com.othershe.calendarview.listener.OnSingleChooseListener;
import com.othershe.calendarview.weiget.CalendarView;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.adapter.CalendarRecycAdapter;
import com.wd.yiyangyun.mvp.mine.bean.CalendarBean;
import com.wd.yiyangyun.mvp.mine.bean.ResultBean;
import com.wd.yiyangyun.mvp.mine.view.CreateUserDialog;
import com.wd.yiyangyun.mvp.patient.bean.CardBean;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.HcUtils;
import com.wd.yiyangyun.utils.LogUtil;
import com.wd.yiyangyun.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarActivity extends BaseActivity {

    private CalendarView calendarView;
    private ImageView title_back;
    private TextView title_name;
    private ImageView image_right;
    private RecyclerView calendar_rv;
    private List<CalendarBean.DataBean.DetailsBean> mDatas;
    private CalendarRecycAdapter calendarRecycAdapter;
    private CreateUserDialog createUserDialog;
    private String newSelectDate;
    private CreateUserDialog updateDialog;
    private String newDate;
    private String selectShangWu;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private String id;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void initViews() {
        super.initViews();
        calendarView = (CalendarView) findViewById(R.id.calendar);
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        image_right = findViewById(R.id.image_right);
        calendar_rv = findViewById(R.id.calendar_rv);

        title_back.setVisibility(View.VISIBLE);
        title_name.setText("日程表");
        image_right.setVisibility(View.VISIBLE);

        //
        newDate = HcUtils.getData(System.currentTimeMillis());
        String a = HcUtils.getData2(System.currentTimeMillis());
        LogUtil.e("今天日期: "+ newDate);
        LogUtil.e("今天日期1: "+ a);
        //日历init，年月日之间用点号隔开
        calendarView
                .setStartEndDate("2018.12", "2019.12")
                .setInitDate("2019.5")
                .setSingleDate(a)
                .init();


        //单选回调
        calendarView.setOnSingleChooseListener(new OnSingleChooseListener() {
            @Override
            public void onSingleChoose(View view, DateBean date) {
                String a = String.valueOf(date.getSolar()[0]) +String.valueOf(date.getSolar()[1]) +String.valueOf(date.getSolar()[2]);
//                newSelect = calendarView.getSingleDate().getSolar()[0]+""
//                        +calendarView.getSingleDate().getSolar()[1]+""
//                        +calendarView.getSingleDate().getSolar()[2];
                newSelectDate = dateZhuanHuan(a);
                LogUtil.e("当前选择的日期为", newSelectDate);
                //
                requestCalendar(newSelectDate);

            }
        });
    }



    @Override
    protected void initData() {
        super.initData();
        mDatas = new ArrayList<>();
        //数据
        data_list = new ArrayList<String>();
        data_list.add("上午");
        data_list.add("下午");
        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        calendar_rv.setLayoutManager(linearLayoutManager);
        calendarRecycAdapter = new CalendarRecycAdapter(this, mDatas);
        calendar_rv.setAdapter(calendarRecycAdapter);
        requestCalendar2(newDate);
    }

    private void requestCalendar2(String newSelectDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        map.put("dayTime", newSelectDate);
        DoctorNetService.requestCalendarList(Constants.CALENDAR_LIST, map,
                new NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onNext(Object info) {
                        mDatas.clear();
                        CalendarBean bean = (CalendarBean) info;
                        mDatas.add(bean.getData().getDetails());
                        calendarRecycAdapter.notifyDataSetChanged();
                        if(bean.getData().getDetails().getId()==null || bean.getData().getDetails().getId().equals("")){
                            LogUtil.e("tttttttttt"+bean.getData().getDetails().getId()+"tt555");
                            LogUtil.e("tttttttttt"+bean.getData().getDetails().getId()+"tt555");
                            calendar_rv.setVisibility(View.GONE);
                        }else{
                            LogUtil.e("tttttttttttt666");
                            calendar_rv.setVisibility(View.VISIBLE);
                        }
                    }
                });

    }

    private void requestCalendar(String newSelectDate) {
        Map<String, Object> map = new HashMap<>();
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        map.put("dayTime", newSelectDate);
        DoctorNetService.requestCalendarList(Constants.CALENDAR_LIST, map,
                new NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onNext(Object info) {
                        mDatas.clear();
                        CalendarBean bean = (CalendarBean) info;
                        mDatas.add(bean.getData().getDetails());
                        calendarRecycAdapter.notifyDataSetChanged();
                        if(bean.getData().getDetails().getId()==null || bean.getData().getDetails().getId().equals("")){
                            LogUtil.e("tttttttttt"+bean.getData().getDetails().getId()+"tt555");
                            LogUtil.e("tttttttttt"+bean.getData().getDetails().getId()+"tt555");
                            calendar_rv.setVisibility(View.GONE);
                            showEditDialog();
                        }else{
                            LogUtil.e("tttttttttttt666");
                            calendar_rv.setVisibility(View.VISIBLE);
                        }
                    }
                });

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
        //添加
        image_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog();
//                Intent intent = new Intent(CalendarActivity.this,AddCalendarActivity.class);
//                startActivity(intent);
//                requestAddCalendar();
            }
        });
        //侧滑删除
        calendarRecycAdapter.setOnDeleteClickListener(new CalendarRecycAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(final int position) {
                showAffirmDialog("提示", "确认删除该病程？", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String id = mDatas.get(position).getId();
                        requestDelete(id);
                    }
                });
            }
        });
        //点击修改
        calendarRecycAdapter.setOnItemClickListener(new CalendarRecycAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String time = mDatas.get(position).getMenzhenTime()+"";
                String address = mDatas.get(position).getMenzhenAddress();
                id = mDatas.get(position).getId();
                if(time.equals("1")){
                    time = "上午";
                }else{
                    time = "下午";
                }
                showUpdateDialog(time,address);
            }
        });
    }

    private void showUpdateDialog(String time,String address) {
        updateDialog = new CreateUserDialog(this, onUpdateClickListener,OnItemClickListener);
        updateDialog.show();
//        updateDialog.text_name.sett
        updateDialog.text_info.setText(address);
    }

    private void requestDelete(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        DoctorNetService.requestResult(Constants.DELETE_CALENDAR, map,
                new NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onNext(Object info) {
                        ResultBean bean = (ResultBean) info;
                        ToastUtils.showMessage(CalendarActivity.this,bean.getData().getData());
                        createUserDialog.dismiss();
                        requestCalendar(newSelectDate);
                    }
                });

    }

    private void requestAddCalendar(String menzhenTime, String menzhenAddress, final String date) {
        Map<String, Object> map = new HashMap<>();
        map.put("optionTag","insert");
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        map.put("menzhenTime",menzhenTime);
        map.put("menzhenAddress",menzhenAddress);
        map.put("dayTime", date);
        DoctorNetService.requestResult(Constants.ADD_CALENDAR, map,
                new NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onNext(Object info) {
                        ResultBean bean = (ResultBean) info;
                        ToastUtils.showMessage(CalendarActivity.this,bean.getData().getData());
                        createUserDialog.dismiss();
                        requestCalendar(newSelectDate);
                    }
                });
    }

    private void requestUpdateCalendar(String menzhenTime,String menzhenAddress,String date) {
        Map<String, Object> map = new HashMap<>();
        map.put("optionTag","update");
        map.put("id",id);
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        map.put("menzhenTime",menzhenTime);
        map.put("menzhenAddress",menzhenAddress);
        map.put("dayTime", date);
        DoctorNetService.requestResult(Constants.UPDATE_CALENDAR, map,
                new NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                    }

                    @Override
                    public void onNext(Object info) {
                        ResultBean bean = (ResultBean) info;
                        ToastUtils.showMessage(CalendarActivity.this,bean.getData().getData());
                        updateDialog.dismiss();
                        requestCalendar(newSelectDate);
                    }
                });
    }

    public void showEditDialog() {
        createUserDialog = new CreateUserDialog(this, onClickListener,OnItemClickListener);
        createUserDialog.show();

    }

    private View.OnClickListener onUpdateClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_save_pop:
                    String menzhenTime = selectShangWu;
                    String mobile = updateDialog.text_mobile.getText().toString().trim();
                    String menzhenAddress = updateDialog.text_info.getText().toString().trim();
                    System.out.println(menzhenTime+"——"+newSelectDate+"——"+menzhenAddress);
                    if(menzhenTime.equals("上午")){
                        requestUpdateCalendar("1",menzhenAddress,newSelectDate);
                    }else{
                        requestUpdateCalendar("2",menzhenAddress,newSelectDate);
                    }
                    break;
            }
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_save_pop:
                    String menzhenTime = selectShangWu;
                    String mobile = createUserDialog.text_mobile.getText().toString().trim();
                    String menzhenAddress = createUserDialog.text_info.getText().toString().trim();
                    System.out.println(menzhenTime+"——"+newSelectDate+"——"+menzhenAddress);
                    if(menzhenTime.equals("上午")){
                        requestAddCalendar("1",menzhenAddress,newSelectDate);
                    }else{
                        requestAddCalendar("2",menzhenAddress,newSelectDate);
                    }
                    break;
            }
        }
    };

    private AdapterView.OnItemSelectedListener OnItemClickListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            selectShangWu = adapterView.getItemAtPosition(i).toString();
            LogUtil.e("Ttttttttt000",selectShangWu);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


    private String dateZhuanHuan(String newSelect) {
        if(newSelect.length()==8){
            String year = newSelect.substring(0,4);
            String yue = newSelect.substring(4,6);
            String day = newSelect.substring(6,8);
            newSelect = year+"-"+yue+"-"+day;
        }
        if(newSelect.length()==7){
            String year = newSelect.substring(0,4);
            String yue = newSelect.substring(4,5);
            String day = newSelect.substring(5,7);
            if(yue.length()==1){   //2019612
                newSelect = year+"-0"+yue+"-"+day;
            }
            if(yue.length()==2){  // 2019122
                newSelect = year+"-"+yue+"-0"+day;
            }
        }
        if(newSelect.length()==6){
            String year = newSelect.substring(0,4);
            String yue = newSelect.substring(4,5);
            String day = newSelect.substring(5,6);
            newSelect = year+"-0"+yue+"-0"+day;
        }
        return newSelect;
    }


    @Override
    public Context context() {
        return null;
    }
}
