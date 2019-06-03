package com.wd.yiyangyun.mvp.home.view.activity.bianzheng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.home.adapter.MyExpandableListViewNewAdapter;
import com.wd.yiyangyun.mvp.home.bean.AddBingZhangBean;
import com.wd.yiyangyun.mvp.home.bean.BianZhengNewBean;
import com.wd.yiyangyun.mvp.home.bean.ResultBean2;
import com.wd.yiyangyun.mvp.home.view.activity.WarpLinearLayout;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.DateUtils;
import com.wd.yiyangyun.utils.JSONUtil;
import com.wd.yiyangyun.utils.LogUtil;
import com.wd.yiyangyun.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wd.yiyangyun.app.Constants.BIANZHENGLUN_CRF;

public class AddBianZhengLunZhiActivity extends BaseActivity {

    private static final String TAG = "bianzhenglun";
    public static final String UPDATA_BINGZHENG_SUCCESS = "UPDATE_ADD_SUCCESS";
    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private ExpandableListView binazheng_list;
    private LinearLayout bingzheng_container;
    private LinearLayout bingzheng_container1;
    private ImageButton menu_button;
    private ImageView bingzheng_arrow;
    private ListView menu_list;
    private BianZhengNewBean bianZhengBean;
    private List<BianZhengNewBean.DataBean.ModuleListBean.ParentModuleBean> parentList;
    private List<List<BianZhengNewBean.DataBean.ModuleListBean.FildlistBean>> childlists;
    private MyExpandableListViewNewAdapter myExpandableListViewAdapter;
    private int currentGroupPosition;
    Map<String, RadioButton> radios = new HashMap<>();
    Map<String, RadioButton> radios2 = new HashMap<>();
    private String recordFlag;
    private String patientID;
    private AddBingZhangBean addBingZhangBean;
    private TextView select_time_tv;
    private TimePickerView pvTimeTextView;


    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_bian_zheng_lun_detail_zhi;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title_back = (ImageView) findViewById(R.id.title_back);
        title_name = (TextView) findViewById(R.id.title_name);
        title_right_tv = (TextView) findViewById(R.id.title_right_tv);
        binazheng_list = (ExpandableListView) findViewById(R.id.binazheng_list);
        bingzheng_container = (LinearLayout) findViewById(R.id.bingzheng_container);
        bingzheng_container1 = (LinearLayout) findViewById(R.id.bingzheng_container1);
        menu_button = (ImageButton) findViewById(R.id.menu_button);
        bingzheng_arrow = (ImageView) findViewById(R.id.bingzheng_arrow);
        menu_list = (ListView) findViewById(R.id.menu_list);
        select_time_tv = findViewById(R.id.select_time_tv);
        title_back.setVisibility(View.VISIBLE);
        title_name.setText("添加辨证论治");
        title_right_tv.setText("提交");
        title_right_tv.setVisibility(View.VISIBLE);

        patientID = getIntent().getStringExtra("patientID");
        recordFlag = getIntent().getStringExtra("recordFlag");
        //时间弹框
        initTimePicker();
        initTimePickerTextView();
    }


    @Override
    protected void initData() {
        super.initData();
        parentList = new ArrayList<>();
        childlists = new ArrayList<>();
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("isCRF", "0");
        map.put("recordFlag", "1");
        DoctorNetService.requestNewFildMoudleList(BIANZHENGLUN_CRF,map, new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        bianZhengBean = (BianZhengNewBean) info;
                        for (BianZhengNewBean.DataBean.ModuleListBean moduleListBean : bianZhengBean.getData().getModuleList()) {
                            parentList.add(moduleListBean.getParentModule());
                            List<BianZhengNewBean.DataBean.ModuleListBean.FildlistBean >
                                    tagModuleBeans = new ArrayList<>();
                            for (BianZhengNewBean.DataBean.ModuleListBean.FildlistBean childlistBean : moduleListBean.getFildlist()) {
                                tagModuleBeans.add(childlistBean);
                            }
                            childlists.add(tagModuleBeans);
                        }
                        myExpandableListViewAdapter = new MyExpandableListViewNewAdapter(mContext,
                                parentList, childlists);
                        binazheng_list.setAdapter(myExpandableListViewAdapter);
                        //默认选中状态
                        BianZhengNewBean.DataBean.ModuleListBean.ParentModuleBean parentModuleBean = parentList.get(0);
                        parentModuleBean.isSelect = true;
                        BianZhengNewBean.DataBean.ModuleListBean moduleListBean = bianZhengBean.getData().getModuleList().get(0);
                        changeRightView(moduleListBean);
                        hideWaitDialog();
                    }
                });
    }

    @Override
    protected void initListener() {
        super.initListener();
        // 子类条目点击事件
//        binazheng_list.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                Toast.makeText()
//                return false;
//            }
//        });
        //选择时间
        select_time_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HcUtils.hideKeyboard(AddBianZhengLunZhiActivity.this);
                pvTimeTextView.show(select_time_tv);
            }
        });

        //点击item，关闭提起item
        binazheng_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                int count = binazheng_list.getExpandableListAdapter().getGroupCount();
                for(int k=0; k< count; k++){
                    BianZhengNewBean.DataBean.ModuleListBean.ParentModuleBean parentModuleBean = parentList.get(k);
                    if(k!=i){
                        parentModuleBean.isSelect = false;
                    }else{
                        parentModuleBean.isSelect = true;
                    }
                }
                for(int j = 0; j < count; j++){
                    if(j != i){
                        binazheng_list.collapseGroup(j);
                    }
                }
            }
        });
        //左边父类点击监听，点击父类，在右边展示相对应的子类
        binazheng_list.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ToastUtils.showMessage(AddBianZhengLunZhiActivity.this,"长安了");
                return false;
            }
        });
        binazheng_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long l) {
                HcUtils.hideKeyboard(AddBianZhengLunZhiActivity.this);
                currentGroupPosition = groupPosition;
                changeSelectItemStatu(groupPosition, -1);
                bingzheng_container.removeAllViews();
                for(BianZhengNewBean.DataBean.ModuleListBean.FildlistBean fildlistBean :
                        childlists.get(groupPosition)){
                    //肌张力检查表
                    if("SP020119".equals(fildlistBean.getModuleCode())){
                        HcUtils.hideKeyboard(AddBianZhengLunZhiActivity.this);
//                        changeSelectItemStatu(2, 0);
                        changeChildRightView(2
                                ,0);
                    }else{
                        LogUtil.e("bianzheng1111",fildlistBean.getFieldName());
                        LogUtil.e("bianzheng1111",fildlistBean.getId());
                        bingzheng_container.addView(getTitle(fildlistBean.getFieldName()));
                        //添加 右侧视图  二级
                        for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX children : fildlistBean.getChildrens()) {
                            addSecondLevelView(children);
                            //添加  右侧视图 三级
                            WarpLinearLayout warpLinearLayout3 = getLinearContainer();
                            bingzheng_container.addView(warpLinearLayout3);
                            addThreeLevelView(children, warpLinearLayout3);
                        }
                    }
                }

                if (parent.isGroupExpanded(groupPosition)) {
                    parent.collapseGroup(groupPosition);
                } else {
                    //第二个参数false表示展开时是否触发默认滚动动画
                    parent.expandGroup(groupPosition, true);
                }
                return true;
            }
        });

        binazheng_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int
                    groupPositon, int childPosition, long l) {
                currentGroupPosition = groupPositon;
                HcUtils.hideKeyboard(AddBianZhengLunZhiActivity.this);
                changeSelectItemStatu(groupPositon, childPosition);
                try {
                    if(bianZhengBean.getData().getModuleList().get(groupPositon)!=null){
                        BianZhengNewBean.DataBean.ModuleListBean moduleListBean = bianZhengBean.getData().getModuleList().get(groupPositon);
                        for (BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX moduleListBean2 : moduleListBean.getFildlist().get(childPosition).getChildrens()) {
                            LogUtil.e("setOnChildClickListener",moduleListBean2.getDisplayName());
                        }

                        changeChildRightView(groupPositon,childPosition);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return false;
            }
        });

        title_right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HcUtils.hideKeyboard(AddBianZhengLunZhiActivity.this);
                if(select_time_tv.getText().toString().equals("") || select_time_tv==null){
                    ToastUtils.showMessage(AddBianZhengLunZhiActivity.this,"请添加就诊时间");
                }else{
                    submit();
                }
            }
        });

    }

    private void submit() {
        addBingZhangBean = new AddBingZhangBean();
        addBingZhangBean.filterObj = new ArrayList<>();  //999
        for (List<BianZhengNewBean.DataBean.ModuleListBean.FildlistBean> fildlistBeans : childlists) {
            for (BianZhengNewBean.DataBean.ModuleListBean.FildlistBean fildlistBean :
                    fildlistBeans) {
                AddBingZhangBean.FilterObjBean filterObjBean = new
                        AddBingZhangBean.FilterObjBean(null);
                filterObjBean.moduleCode = fildlistBean.getModuleCode();
                filterObjBean.fieldContentsList = new ArrayList<>();
                for (BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX childrensBeanXXX :
                        fildlistBean.getChildrens()) {
                    if (!TextUtils.isEmpty(childrensBeanXXX.content) || childrensBeanXXX.isCheck) {
                        // 一级  患者病历中 治疗日期 text 文本框
                        LogUtil.e("一级数据  --" + childrensBeanXXX.content + " --- " + childrensBeanXXX.getId());
                        AddBingZhangBean.FilterObjBean.FieldContentsListBean
                                fieldContentsListBean
                                = new AddBingZhangBean.FilterObjBean
                                .FieldContentsListBean(null,null);
                        if (!TextUtils.isEmpty(childrensBeanXXX.content)) {
                            fieldContentsListBean.contents = childrensBeanXXX.content;
                        } else {
                            fieldContentsListBean.contents = childrensBeanXXX.getDisplayName();
                        }
                        fieldContentsListBean.fieldID = childrensBeanXXX.getId();
                        filterObjBean.fieldContentsList.add(fieldContentsListBean);
                    }
                    for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                            ChildrensBeanXX childrensBean : childrensBeanXXX.getChildrens()) {
                        if (!TextUtils.isEmpty(childrensBean.content) || childrensBean.isCheck) {
                            // 二级生活自理  日常生活，MMT徒手肌力
                            LogUtil.e("二级数据  --" + childrensBean.content + " --- " + childrensBean.getId());
                            AddBingZhangBean.FilterObjBean.FieldContentsListBean
                                    fieldContentsListBean
                                    = new AddBingZhangBean.FilterObjBean
                                    .FieldContentsListBean(null,null);
                            if (!TextUtils.isEmpty(childrensBean.content)) {
                                fieldContentsListBean.contents = childrensBean.content;
                            } else {
                                fieldContentsListBean.contents = childrensBean.getDisplayName();
                            }
                            fieldContentsListBean.fieldID = childrensBean.getId();
                            filterObjBean.fieldContentsList.add(fieldContentsListBean);
                        }
                        for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                                ChildrensBeanXX.ChildrensBeanX childrensBeanX : childrensBean.getChildrens()) {
                            if (!TextUtils.isEmpty(childrensBeanX.content) || childrensBeanX.isCheck) {
                                LogUtil.e("三级数据  --" + childrensBeanX.content + " --- " + childrensBeanX.getId());
                                AddBingZhangBean.FilterObjBean.FieldContentsListBean
                                        fieldContentsListBean
                                        = new AddBingZhangBean.FilterObjBean
                                        .FieldContentsListBean(null,null);
                                if (!TextUtils.isEmpty(childrensBeanX.content)) {
                                    fieldContentsListBean.contents = childrensBeanX.content;
                                } else {
                                    fieldContentsListBean.contents = childrensBeanX.getDisplayName();
                                }
                                fieldContentsListBean.fieldID = childrensBeanX.getId();
                                filterObjBean.fieldContentsList.add(fieldContentsListBean);
                            }

                            for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                                    ChildrensBeanXX.ChildrensBeanX.ChildrensBean childrensBean1 : childrensBeanX.getChildrens()) {
                                if (!TextUtils.isEmpty(childrensBean1.content) || childrensBean1.isCheck) {
                                    //四级 furl-meyer 运动量表
                                    LogUtil.e("四级数据  --" + childrensBean1.content + " --- " + childrensBean1.getId());
                                    AddBingZhangBean.FilterObjBean.FieldContentsListBean
                                            fieldContentsListBean
                                            = new AddBingZhangBean.FilterObjBean
                                            .FieldContentsListBean(null,null);
                                    if (!TextUtils.isEmpty(childrensBean1.content)) {
                                        fieldContentsListBean.contents = childrensBean1.content;
                                    } else {
                                        fieldContentsListBean.contents = childrensBean1.getDisplayName();
                                    }
                                    fieldContentsListBean.fieldID = childrensBean1.getId();
                                    filterObjBean.fieldContentsList.add(fieldContentsListBean);
                                }
                            }
                        }
                    }
                }
                if (filterObjBean.fieldContentsList.size() > 0)
                    addBingZhangBean.filterObj.add(filterObjBean);
        }

        }
        requestUpData();
    }
    private void requestUpData() {
//        showWaitDialog();
        //{"patientID":40,"diseasesID":"", "fieldRecordSign":"","mobileType":"2",
        // "optionTag":"insert","recordFlag":"0"}
        //final String sendJson = JSONUtil.parseMapToJson(params);
        Map<String, Object> map = new HashMap<>();
        map.put("patientID", patientID);
        map.put("fieldRecordSign", "");
        map.put("sicknessTime", select_time_tv.getText().toString());
        map.put("mobileType", Constants.MOBILE_TYPE);
        map.put("optionTag", "insert");
        map.put("userID", DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, ""));
        map.put("recordFlag", recordFlag);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("filterObj", addBingZhangBean.filterObj);

        String sendJson = JSONUtil.parseMapToJson(map);
        String sendJson2 = JSONUtil.parseMapToJson(map1);
        Log.e("66666666666", "bzhl  requestUpData: "+sendJson);
        Log.e("666666666666", "bzhl  requestUpData: "+sendJson2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("act", "insertOrUpdateFieldContents");
        map3.put("data", sendJson);
        map3.put("filter", sendJson2);
        String Url = Constants.Host.replace("?", "");
        DoctorNetService.requestAddOrChangeByUrl(Url, map3, new
                NetWorkRequestInterface() {

                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        ResultBean2 resultBean = (ResultBean2) info;
                        ToastUtils.showMessage(mContext, resultBean.data);

                        Map<String, Object> map = new HashMap<>();
                        map.put(Constants.EVENTBUS_TYEPE, UPDATA_BINGZHENG_SUCCESS);
                        EventBus.getDefault().post(map);
                        finish();
                        //   has leaked window DecorView@30e33f9[] that was originally added here
                    }
                });
    }
    private void changeChildRightView(int groupPositon,int childPosition) {
        bingzheng_container.removeAllViews();
        //添加 右侧视图  一级
        BianZhengNewBean.DataBean.ModuleListBean.FildlistBean fildlistBean = bianZhengBean.getData().getModuleList().get(groupPositon).getFildlist().get(childPosition);
        fildlistBean.isSelect = true;   // 点击肌张力检查表 ， 默认改变子类的字体样式
        bingzheng_container.addView(getTitle(fildlistBean.getFieldName()));
        addChildSecondLevelView(fildlistBean);
    }
    private void addChildSecondLevelView(final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean children) {
        // 肌肉维度  肌张力
        for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX
                childrensBean : children.getChildrens()) {
            if("list".equals(childrensBean.getFieldType())) {
                bingzheng_container.addView(getFuTitle(childrensBean.getDisplayName()));
                for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.
                        ChildrensBeanXXX.ChildrensBeanXX childrensBeanXX : childrensBean.getChildrens()) {
                    if ("text".equals(childrensBeanXX.getFieldType())) {
                        WarpLinearLayout editViews = getEditView(childrensBeanXX.getDisplayName(), children
                                .getSuffixName(), childrensBeanXX.content);
                        bingzheng_container.addView(editViews);
                        EditText editText = (EditText) editViews.getChildAt(1);
                        editText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i,
                                                          int i1, int i2) {
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int
                                    i1, int i2) {
                                childrensBeanXX.content = charSequence.toString();
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });
                    }
                }
            }
            //肌肉情况简评
            if("text".equals(childrensBean.getFieldType())){
                WarpLinearLayout editViews = getEditView(childrensBean.getDisplayName(), children
                        .getSuffixName(), childrensBean.content);
                bingzheng_container.addView(editViews);
                EditText editText = (EditText) editViews.getChildAt(1);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i,
                                                  int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int
                            i1, int i2) {
                        childrensBean.content = charSequence.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
        }
    }
    private void changeRightView(BianZhengNewBean.DataBean.ModuleListBean childlistBean) {
        bingzheng_container.removeAllViews();
        //添加 右侧视图  一级
        for (BianZhengNewBean.DataBean.ModuleListBean.FildlistBean fildlistBean :
                childlistBean.getFildlist()) {
            // title,textarea,checkbox,radio,text,select
            LogUtil.e("bianzheng000",fildlistBean.getFieldName());
            LogUtil.e("bianzheng000",fildlistBean.getId());
            bingzheng_container.addView(getTitle(fildlistBean.getFieldName()));
            //添加 右侧视图  二级
            for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX children : fildlistBean.getChildrens()) {
                addSecondLevelView(children);
                //添加  右侧视图 三级
                WarpLinearLayout warpLinearLayout3 = getLinearContainer();
                bingzheng_container.addView(warpLinearLayout3);
                addThreeLevelView(children, warpLinearLayout3);
            }
        }
    }
    private void addSecondLevelView(final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX children) {
        //Furk-Meyer运动量表上肢部分
        if("list".equals(children.getFieldType()) && "SP020116".equals(children.getModuleCode())){
            bingzheng_container.addView(getFuTitle(children.getDisplayName()));
            LogUtil.e("bianzheng---  座位与仰卧位",children.getDisplayName());
            for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                    ChildrensBeanXX childrensBean : children.getChildrens()) {
                if("list".equals(childrensBean.getFieldType())){
                    LogUtil.e("bianzheng---  1.有无反射活动",childrensBean.getDisplayName());
                    bingzheng_container.addView(getFuTitle(childrensBean.getDisplayName()));
                    for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                            ChildrensBeanXX.ChildrensBeanX childrensBeanX :childrensBean.getChildrens()) {
                        if ("radio".equals(childrensBeanX.getFieldType())) {
                            LogUtil.e("bianzheng--- 肱二头肌",childrensBeanX.getDisplayName());
                            bingzheng_container.addView(getFuTitle(childrensBeanX.getDisplayName()));
                            for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                                    ChildrensBeanXX.ChildrensBeanX.ChildrensBean childrensBean1 : childrensBeanX.getChildrens()) {
                                final RadioButton radioButton = getRadioButton(childrensBean1.getDisplayName(),
                                        childrensBean1.isCheck);
                                WarpLinearLayout warpLinearLayout = getLinearContainer();
                                warpLinearLayout.addView(radioButton);
                                radios.put(childrensBean1.getId(), radioButton);
                                bingzheng_container.addView(warpLinearLayout);
                                radioButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        for (BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                                                ChildrensBeanXX.ChildrensBeanX.ChildrensBean childrensBean : childrensBeanX.getChildrens()) {
                                            LogUtil.e("isCheck3",childrensBean.isCheck+"     "+childrensBean.getId());
                                            radios.get(childrensBean.getId()).setChecked(false);
                                            childrensBean.isCheck = false;
                                        }
                                        LogUtil.e("isCheck",childrensBean1.getId());
                                        radioButton.setChecked(true);
                                        childrensBean1.isCheck = true;
                                    }
                                });

                            }
                        }
                    }
                }
            }
        }
        //运动功能状态量表
        if("list".equals(children.getFieldType()) && "SP020120".equals(children.getModuleCode())){
            bingzheng_container.addView(getFuTitle(children.getDisplayName()));
            LogUtil.e("bianzheng---  肩",children.getDisplayName());
            for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                    ChildrensBeanXX childrensBean : children.getChildrens()) {
                if("radio".equals(childrensBean.getFieldType())){
                    bingzheng_container.addView(getFuTitle(childrensBean.getDisplayName()));
                    for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                            ChildrensBeanXX.ChildrensBeanX childrensBeanX : childrensBean.getChildrens()) {
                        final RadioButton radioButton = getRadioButton(childrensBeanX.getDisplayName(),
                                childrensBeanX.isCheck);
                        WarpLinearLayout warpLinearLayout = getLinearContainer();
                        warpLinearLayout.addView(radioButton);
                        radios2.put(childrensBeanX.getId(), radioButton);
                        bingzheng_container.addView(warpLinearLayout);

                        radioButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                for(BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                                        ChildrensBeanXX.ChildrensBeanX childrensBeanX : childrensBean.getChildrens()) {
                                    LogUtil.e("isCheck3",childrensBeanX.isCheck+"     "+childrensBeanX.getId());
                                    radios2.get(childrensBeanX.getId()).setChecked(false);
                                    childrensBeanX.isCheck = false;
                                }
                                LogUtil.e("isCheck",childrensBeanX.getId());
                                radioButton.setChecked(true);
                                childrensBeanX.isCheck = true;
                            }
                        });


                    }

                }
            }
        }


        //生活自理情况
        if("radio".equals(children.getFieldType())){
            bingzheng_container.addView(getFuTitle(children.getDisplayName()));
        }
        //患者病例简况
        if("date".equals(children.getFieldType())){
            addDateSelect(children);
            LogUtil.e("bianzheng---  日期",children.getDisplayName());
            for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                    ChildrensBeanXX childrensBean : children.getChildrens()) {
                if("radio".equals(childrensBean.getFieldType())){
                    bingzheng_container.addView(getFuTitle(childrensBean.getDisplayName()));
                    for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                            ChildrensBeanXX.ChildrensBeanX childrensBeanX : childrensBean.getChildrens()) {
                        final RadioButton radioButton = getRadioButton(childrensBeanX.getDisplayName(),
                                childrensBeanX.isCheck);
                        WarpLinearLayout warpLinearLayout = getLinearContainer();
                        warpLinearLayout.addView(radioButton);
                        radios.put(children.getId(), radioButton);
                        bingzheng_container.addView(warpLinearLayout);

                        radioButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                for ( BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.
                                        ChildrensBeanXX.ChildrensBeanX childrensBeanX : childrensBean.getChildrens()) {
                                    LogUtil.e("isCheck3",childrensBeanX.isCheck+"     "+childrensBeanX.getId());
                                    radios.get(childrensBeanX.getId()).setChecked(false);
                                    childrensBeanX.isCheck = false;
                                }
                                LogUtil.e("isCheck",childrensBeanX.getId());
                                radioButton.setChecked(true);
                                childrensBeanX.isCheck = true;
                            }
                        });

                    }

                }
            }
        }

        if("text".equals(children.getFieldType())){
            WarpLinearLayout editViews = getEditView(children.getDisplayName(), children
                    .getSuffixName(), children.content);
            bingzheng_container.addView(editViews);
            EditText editText = (EditText) editViews.getChildAt(1);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i,
                                              int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int
                        i1, int i2) {
                    children.content = charSequence.toString();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
        if("textarea".equals(children.getFieldType())){
            bingzheng_container.addView(getFuTitle(children.getDisplayName()));
            EditText aloneBigEdit = getAloneBigEdit(children.content);
            WarpLinearLayout warpLinearLayout = getLinearContainer();
            warpLinearLayout.addView(aloneBigEdit);
            bingzheng_container.addView(warpLinearLayout);
            aloneBigEdit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int
                        i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int
                        i2) {
                    children.content = charSequence.toString();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }
    private void addThreeLevelView(final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX children, WarpLinearLayout warpLinearLayout3) {
        //日常活动
        if("radio".equals(children.getFieldType())) {
            for (final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.ChildrensBeanXX childrensBean : children.getChildrens()) {
                final RadioButton radioButton = getRadioButton(childrensBean.getDisplayName(),
                        childrensBean.isCheck);
                radios.put(childrensBean.getId(), radioButton);
                warpLinearLayout3.addView(radioButton);
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX.ChildrensBeanXX childrensBean2 : children.getChildrens()) {
                            LogUtil.e("isCheck3日常", childrensBean2.isCheck + "     " + childrensBean2.getId());
                            radios.get(childrensBean2.getId()).setChecked(false);
                            childrensBean2.isCheck = false;
                        }
                        LogUtil.e("isCheck", childrensBean.getId());
                        radioButton.setChecked(true);
                        childrensBean.isCheck = true;
                    }
                });
            }
        }

//            if ("text".equals(children.getFieldType())) {
//                bingzheng_container.addView(getFuTitle(children.getDisplayName()));
//
//                if ("textarea".equals(children.getFieldType())) {
//                    EditText aloneBigEdit = getAloneBigEdit(children.content);
//                    currentAloneBigEdit = aloneBigEdit;
//                    final StringBuilder sb = new StringBuilder();
//                    sb.append(currentAloneBigEdit.getText().toString());
//                    bingzheng_container1.addView(currentAloneBigEdit);
//                    currentAloneBigEdit.addTextChangedListener(new TextWatcher() {
//                        @Override
//                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int
//                                i2) {
//
//                        }
//
//                        @Override
//                        public void onTextChanged(CharSequence charSequence, int i, int i1, int
//                                i2) {
//                            children.content = charSequence.toString();
//                            sb.append(children.content.replace(sb.toString(), ""));
//                        }
//
//                        @Override
//                        public void afterTextChanged(Editable editable) {
//
//                        }
//                    });
//                }
//            }

    }

    /**
     * 单选按钮----------------------------------------------------
     *
     * @return
     */
    public RadioButton getRadioButton(String displayName, boolean isCheck) {
        RadioButton radioButton = new RadioButton(mContext);
        radioButton.setChecked(isCheck);
        radioButton.setText(displayName);
        radioButton.setTextSize(14);
        return radioButton;
    }


    /**
     * 修改 选中条目样式
     *
     * @param groupPositon
     * @param childPosition 如果等于 -1  说明是 父类
     */
    private void changeSelectItemStatu(int groupPositon, int childPosition) {
        for (List<BianZhengNewBean.DataBean.ModuleListBean.FildlistBean> childlist : childlists) {
            for (BianZhengNewBean.DataBean.ModuleListBean.FildlistBean childlistBean : childlist) {
                childlistBean.isSelect = false;
            }
        }
        if (childPosition != -1) {
            childlists.get(groupPositon).get(childPosition).isSelect = true;
            binazheng_list.collapseGroup(groupPositon);
            binazheng_list.expandGroup(groupPositon);
        }
    }

    /**
     * 获取输入框样式
     * suffixName  单位   displayName 名称
     *
     * @param name
     * @param displayName 名称
     * @param suffixName  单位
     * @param name        录入内容
     * @return
     */
    public WarpLinearLayout getEditView(String displayName, String suffixName, String name) {
        WarpLinearLayout linearContainer = getLinearContainer();
        linearContainer.addView(getNormalTv(displayName + ""));
        linearContainer.addView(getNormalEdit(name));
        linearContainer.addView(getNormalTv(suffixName + ""));
        return linearContainer;
    }

    /**
     * 获取展示的 Edit
     *
     * @return
     */
    public EditText getNormalEdit(String content) {
        EditText editText = new EditText(mContext);
        editText.setTextSize(14);
        editText.setHint("            ");
        editText.setText(content);
        editText.setSingleLine(true);
        editText.setTextColor(Color.parseColor("#99000000"));
        editText.setBackgroundResource(R.drawable.fangan_bg);
        editText.setPadding(16, 2, 16, 0);
        return editText;
    }

    /**
     * 获取Linaer
     *
     * @return
     */
    public WarpLinearLayout getLinearContainer() {
        WarpLinearLayout fixGridLayout = new WarpLinearLayout(mContext);
        fixGridLayout.setGrivate(1);
        return fixGridLayout;
    }

    /*
     * 日期选择器
     * */

    private void addDateSelect(final BianZhengNewBean.DataBean.ModuleListBean.FildlistBean.ChildrensBeanXXX children) {
        final LinearLayout dateView = getDateView(children.getDisplayName(), TextUtils.isEmpty
                (children.content) ? "点击请选择" :
                children.content);
        bingzheng_container.addView(dateView);
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HcUtils.hideKeyboard(AddBianZhengLunZhiActivity.this);
                pvTime.show(dateView);
            }
        });
        ((TextView) dateView.getChildAt(1)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int
                    i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                children.content = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 获取选择样式
     * suffixName  单位   displayName 名称
     *
     * @param name
     * @param displayName 名称
     * @param name        录入内容
     * @return
     */
    public LinearLayout getDateView(String displayName, String name) {

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(getNormalTv(displayName + ""));
        TextView normalTv = getNormalTv(name + "");
        if (name.equals("点击请选择")) {
            normalTv.setTextColor(Color.parseColor("#0aa2ed"));
        }
        linearLayout.addView(normalTv);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
        return linearLayout;
    }

    /**
     * 获取展示的 TextView
     *
     * @param title
     * @return
     */
    public TextView getNormalTv(String title) {
        TextView textView = new TextView(mContext);
        textView.setTextSize(14);
        textView.setTextColor(Color.parseColor("#99000000"));
        textView.setText(title);
        textView.setPadding(10, 10, 10, 10);
        return textView;
    }

    /**
     * 获取单独的大录入框
     *
     * @return
     */
    public EditText getAloneBigEdit(String content) {
        EditText editText = new EditText(mContext);
        editText.setTextSize(14);
        editText.setHint("            ");
        editText.setText(content);
        editText.setSingleLine(false);
        editText.setTextColor(Color.parseColor("#99000000"));
        editText.setBackgroundResource(R.drawable.fangan_bg);
        int m = HcUtils.dp2px(mContext, 8);
        editText.setPadding(m, m, m, m);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(HcUtils.getScreenWidth
                (mContext) / 3 * 2, HcUtils.dp2px(mContext, 68));
        params.setMargins(10, m, m, m);
        editText.setLayoutParams(params);
        return editText;
    }

    /**
     * 获取二级标题
     *
     * @param title
     * @return
     */
    private TextView getFuTitle(String title) {
        TextView textView = new TextView(mContext);
        textView.setTextSize(14);
        textView.setTextColor(Color.parseColor("#55000000"));
        textView.setText(title);
        textView.setPadding(10, 2, 10, 0);
        return textView;
    }

    /**
     * 获取一级标题
     *
     * @param title
     * @return
     */
    public TextView getTitle(String title) {
        TextView textView = new TextView(mContext);
        textView.setTextSize(16);
        textView.setTextColor(Color.BLACK);
        textView.setText(title);
        textView.setPadding(10, 2, 10, 0);
        textView.setBackgroundColor(Color.parseColor("#44000000"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(HcUtils.getScreenWidth
                (mContext), LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        return textView;
    }

    /**
     * 初始化时间弹框
     */
    private void initTimePicker() {
        String yearTime = com.wd.yiyangyun.utils.HcUtils.getYearTime(new Date(System.currentTimeMillis()));
        String monthTime = com.wd.yiyangyun.utils.HcUtils.getMonthTime(new Date(System.currentTimeMillis()));
        String dayTime = com.wd.yiyangyun.utils.HcUtils.getDayTime(new Date(System.currentTimeMillis()));
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1988, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2030, 0, 1);
        pvTime = new TimePickerView.Builder(this, new TimePickerView
                .OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {
                String found_date = DateUtils.dateToString(date, DateUtils.FORMAT_5);
                TextView tv = (TextView) ((LinearLayout) v).getChildAt(1);
                tv.setText(found_date);
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(16)
                .setDate(selectedDate)
                .setCancelColor(getResources().getColor(R.color.main_color))
                .setSubmitColor(getResources().getColor(R.color.main_color))
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    /**
     * 初始化时间弹框
     */
    private void initTimePickerTextView() {
        String yearTime = com.wd.yiyangyun.utils.HcUtils.getYearTime(new Date(System.currentTimeMillis()));
        String monthTime = com.wd.yiyangyun.utils.HcUtils.getMonthTime(new Date(System.currentTimeMillis()));
        String dayTime = com.wd.yiyangyun.utils.HcUtils.getDayTime(new Date(System.currentTimeMillis()));
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1988, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2030, 0, 1);
        //年月日时分秒 的显示与否，不设置则默认全部显示
        //设置外部遮罩颜色
        //年月日时分秒 的显示与否，不设置则默认全部显示
//设置外部遮罩颜色
        pvTimeTextView = new TimePickerView.Builder(this, new TimePickerView
                .OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {
                String found_date = DateUtils.dateToString(date, DateUtils.FORMAT_5);
                ((TextView) v).setText(found_date);
            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(16)
                .setDate(selectedDate)
                .setCancelColor(getResources().getColor(R.color.main_color))
                .setSubmitColor(getResources().getColor(R.color.main_color))
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    @Override
    public Context context() {
        return null;
    }
}
