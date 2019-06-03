package com.wd.yiyangyun.mvp.home.view.activity.bianzheng;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wd.yiyangyun.R;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.home.adapter.BingZhengMenuListAdapter;
import com.wd.yiyangyun.mvp.home.adapter.MyExpandableListViewAdapter;
import com.wd.yiyangyun.mvp.home.bean.BianZhengBean;
import com.wd.yiyangyun.mvp.home.view.activity.WarpLinearLayout;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wd.yiyangyun.app.Constants.SELECT_FILD_MODULE_LIST;

public class BianZhengLunDetailZhiActivity extends BaseActivity {

    private static final String TAG = "bianzhenglun";
    private ImageView title_back;
    private TextView title_name;
    private TextView title_right_tv;
    private ExpandableListView binazheng_list;
    private LinearLayout bingzheng_container;
    private LinearLayout bingzheng_container1;
    private ImageButton menu_button;
    private ImageView bingzheng_arrow;
    private ListView menu_list;
    private BianZhengBean bianZhengBean;
    private List<BianZhengBean.DataBean.ModuleListBean.ParentModuleBean> parentList;
    private List<List<BianZhengBean.DataBean.ModuleListBean.ChildlistBean>> childlists;
    private MyExpandableListViewAdapter myExpandableListViewAdapter;
    private List<BianZhengBean.DataBean.ModuleListBean.ChildlistBean> navChildLists;
    private boolean isShowMenuList = false;
    private EditText currentAloneBigEdit;
    private BingZhengMenuListAdapter navAdapter;
    private Map<String, List<View>> fourLevelMap = new HashMap<>();
    private Map<String, List<View>> fiveLevelMap = new HashMap<>();
    Map<String, RadioButton> radios = new HashMap<>();
    private List<String> stringArrayList = new ArrayList<>();
    private StringBuilder stringBuilderTag;
    private int currentGroupPosition;


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
        title_back.setVisibility(View.VISIBLE);
        title_name.setText("辨证论治");
        title_right_tv.setText("提交");
        title_right_tv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        parentList = new ArrayList<>();
        childlists = new ArrayList<>();
        navAdapter = new BingZhengMenuListAdapter(navChildLists, mContext);
        menu_list.setAdapter(navAdapter);
        Map<String, Object> map = new HashMap<>();
        map.put("isCRF", "0");
        map.put("recordFlag", "1");
        DoctorNetService.requestFildMoudleList(SELECT_FILD_MODULE_LIST,map, new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Object info) {
                        bianZhengBean = (BianZhengBean) info;
                            for (BianZhengBean.DataBean.ModuleListBean moduleListBean : bianZhengBean.getData().getModuleList()) {
                                parentList.add(moduleListBean.getParentModule());
                                List<BianZhengBean.DataBean.ModuleListBean.ChildlistBean>
                                        tagModuleBeans = new ArrayList<>();
                                for (BianZhengBean.DataBean.ModuleListBean.ChildlistBean childlistBean : moduleListBean.getChildlist()) {
                                    tagModuleBeans.add(childlistBean);
                                }
                                childlists.add(tagModuleBeans);
                            }
                            myExpandableListViewAdapter = new MyExpandableListViewAdapter(mContext,
                                    parentList, childlists);
                            binazheng_list.setAdapter(myExpandableListViewAdapter);
                            //默认选中状态
                            BianZhengBean.DataBean.ModuleListBean.ParentModuleBean parentModuleBean = parentList.get(0);
                            parentModuleBean.isSelect = true;
                            changeRightView(childlists.get(0).get(0));
                    }
                });

    }

    @Override
    protected void initListener() {
        super.initListener();
        //返回
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.putExtra("fieldRecordSign",fieldRecordSign);
//                setResult(100,intent);
                finish();
            }
        });
        menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShowMenuList) {
                    menu_list.setVisibility(View.GONE);
                } else {
                    menu_list.setVisibility(View.VISIBLE);
                }
                isShowMenuList = !isShowMenuList;
            }
        });
        // 子类条目点击事件
        binazheng_list.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int
                    groupPositon, int childPosition, long l) {
                currentGroupPosition = groupPositon;
                HcUtils.hideKeyboard(BianZhengLunDetailZhiActivity.this);
                changeSelectItemStatu(groupPositon, childPosition);
                final BianZhengBean.DataBean.ModuleListBean.ChildlistBean childlistBean =
                        childlists.get(groupPositon).get(childPosition);
                if(childlistBean!=null){
                    LogUtil.e("bianzheng"+groupPositon+"   "+childPosition);
                    changeRightView(childlistBean);
                }

                return false;
            }
        });
        //点击item，关闭提起item
        binazheng_list.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                int count = binazheng_list.getExpandableListAdapter().getGroupCount();
                for(int k=0; k< count; k++){
                    BianZhengBean.DataBean.ModuleListBean.ParentModuleBean parentModuleBean = parentList.get(k);
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



        //重写OnGroupClickListener，实现当展开时，ExpandableListView不自动滚动  忘了有啥用了。。。
        binazheng_list.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()

        {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition,
                                        long id) {
                HcUtils.hideKeyboard(BianZhengLunDetailZhiActivity.this);
                if (childlists.get(groupPosition).size() == 1) {
                    currentGroupPosition = groupPosition;
                    changeSelectItemStatu(groupPosition, -1);
                    bingzheng_container.removeAllViews();
                    bingzheng_container1.removeAllViews();
                    final BianZhengBean.DataBean.ModuleListBean.ChildlistBean childlistBean =
                            childlists.get(groupPosition).get(0);
                    //添加 右侧视图  一级
                    for (BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean fildlistBean :
                            childlistBean.getFildlist()) {
                        // title,textarea,checkbox,radio,text,select
                        if(fildlistBean.getFieldName().equals("症状描述")){
                            bingzheng_container.addView(getTitle("症状描述"+"78988"));
                        }else{
                            bingzheng_container.addView(getTitle(fildlistBean.getFieldName()));
                        }
                        //添加 右侧视图  二级
                        for (final BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean
                                .ChildrensBeanXXX children : fildlistBean.getChildrens()) {
                            addSecondLevelView(children);
                            //添加  右侧视图 三级
                            WarpLinearLayout warpLinearLayout = getLinearContainer();
                            bingzheng_container.addView(warpLinearLayout);
                            //bingzheng_container1.addView(warpLinearLayout);
                            addThreeLevelView(children,warpLinearLayout);
                            //添加右侧 四级 五级视图
                            for (final BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean
                                    .ChildrensBeanXXX.ChildrensBeanXX childrensBeanXX : children
                                    .getChildrens()) {
                                if (children.getFieldType().equals("checkbox") || children.getFieldType()
                                        .equals("radio")) {
                                    if (childrensBeanXX.isCheck && childrensBeanXX.getChildrens().size
                                            () > 0) {
                                        addFiveLevelView(childrensBeanXX, warpLinearLayout);
                                    }
                                }
                            }
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



    }

    /**
     * 修改 选中条目样式
     *
     * @param groupPositon
     * @param childPosition 如果等于 -1  说明是 父类
     */
    private void changeSelectItemStatu(int groupPositon, int childPosition) {
        for (List<BianZhengBean.DataBean.ModuleListBean.ChildlistBean> childlist : childlists) {
            for (BianZhengBean.DataBean.ModuleListBean.ChildlistBean childlistBean : childlist) {
                childlistBean.getTagModule().isSelect = false;
            }
        }
        if (childPosition != -1) {
            childlists.get(groupPositon).get(childPosition).getTagModule().isSelect = true;
            binazheng_list.collapseGroup(groupPositon);
            binazheng_list.expandGroup(groupPositon);
        }
    }

    private void changeRightView(BianZhengBean.DataBean.ModuleListBean.ChildlistBean childlistBean) {
        bingzheng_container.removeAllViews();
        bingzheng_container1.removeAllViews();
        //添加 右侧视图  一级
        for (BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean fildlistBean :
                childlistBean.getFildlist()) {
            // title,textarea,checkbox,radio,text,select
            if(fildlistBean.getFieldName().equals("症状描述")){
                bingzheng_container1.addView(getTitle("症状描述"));
            }else{
                bingzheng_container.addView(getTitle(fildlistBean.getFieldName()));
            }
            //添加 右侧视图  二级
            for (final BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean
                    .ChildrensBeanXXX children : fildlistBean.getChildrens()) {
                addSecondLevelView(children);
                //添加  右侧视图 三级
                //添加  右侧视图 三级
                WarpLinearLayout warpLinearLayout = getLinearContainer();
                bingzheng_container.addView(warpLinearLayout);
                addThreeLevelView(children, warpLinearLayout);
//                //添加右侧 四级 五级视图
//                for (final BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean
//                        .ChildrensBeanXXX.ChildrensBeanXX childrensBeanXX : children
//                        .getChildrens()) {
//                    if (children.getFieldType().equals("checkbox")) {
//                        if (childrensBeanXX.isCheck) {
//                            if(childrensBeanXX!=null){
//                                addFiveLevelView(childrensBeanXX, warpLinearLayout);
//                            }
//                        }
//                    }
//                }
            }
        }
    }

    private void addSecondLevelView(final BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean.ChildrensBeanXXX children) {
        if (!"text".equals(children.getFieldType())) {
            if(!children.getDisplayName().equals("主症")) {
                if(!children.getDisplayName().equals("伴随症状")){
                    if(!children.getDisplayName().equals("兼症")){
                        bingzheng_container.addView(getFuTitle(children.getDisplayName()));

                    }
                }
            }
            if(children.getDisplayName().equals("兼症")) {
                bingzheng_container1.addView(getFuTitle(children.getDisplayName()));
            }
            if(children.getDisplayName().equals("伴随症状")) {
                bingzheng_container1.addView(getFuTitle(children.getDisplayName()));
            }
            if(children.getDisplayName().equals("主症")){
                bingzheng_container1.addView(getFuTitle(children.getDisplayName()));
            }

            if ("textarea".equals(children.getFieldType())) {
                EditText aloneBigEdit = getAloneBigEdit(children.content);
                if (children.getId().equals("4028801e654a8ec201654a8f05780001")) {
//                    addPicBt(children, aloneBigEdit); 上传图片按钮 中药处方
                }
                if ("4028801e6579432901657944df0a0001".equals(children.getId()) ||
                        "4028801e6579432901657947e1290003".equals(children.getId()) ||
                        "4028801e657943290165797421160005".equals(children.getId())) {
                    currentAloneBigEdit = aloneBigEdit;
                    final StringBuilder sb = new StringBuilder();
                    sb.append(currentAloneBigEdit.getText().toString());
                    bingzheng_container1.addView(currentAloneBigEdit);
                    currentAloneBigEdit.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int
                                i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int
                                i2) {
                            children.content = charSequence.toString();
                            sb.append(children.content.replace(sb.toString(), ""));
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });
                } else {
                    bingzheng_container.addView(aloneBigEdit);
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

            } else if ("date".equals(children.getFieldType())) {
                addDateSelect(children);
            }
        } else if ("date".equals(children.getFieldType())) {
            addDateSelect(children);
        } else {
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
    }


    /**
     * 添加右侧三级视图
     *
     * @param children
     * @param warpLinearLayout
     */
    private void addThreeLevelView(final BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean
            .ChildrensBeanXXX children, final WarpLinearLayout warpLinearLayout) {
        for (final BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean
                .ChildrensBeanXXX.ChildrensBeanXX childrensBeanXX : children
                .getChildrens()) {
            if (children.getFieldType().equals("checkbox")) {
                CheckBox checkbox = getCheckbox(childrensBeanXX.getDisplayName(),
                        childrensBeanXX.isCheck);
                warpLinearLayout.addView(checkbox);
                addThreeMenuList(childrensBeanXX);
                checkbox.setTag(childrensBeanXX.getId());
                checkbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        childrensBeanXX.isCheck = !childrensBeanXX.isCheck;
                        if (childrensBeanXX.isCheck) {
                            if (childrensBeanXX.getChildrens().size() > 0) {
                                // 右侧视图 四级
                                Log.e("bar", "onClick: 1  "+"666");
                                addFiveLevelView(childrensBeanXX, warpLinearLayout);
                            }
                        } else {
                            Log.e("bar", "onClick: 2  "+"666");

                            List<View> fourViews = fourLevelMap.get
                                    (childrensBeanXX.getId());
                            if (fourViews != null && fourViews.size() > 0) {

                                for (View fourView : fourViews) {
                                    Log.e("bar", "onClick: 40  "+fourViews);
                                    bingzheng_container.removeView(fourView);
                                    warpLinearLayout.removeView(fourView);
                                }
                                List<View> fiveViews = fiveLevelMap.get
                                        (childrensBeanXX.getId());
                                for (View fiveView : fiveViews) {
                                    Log.e("bar", "onClick: 50  "+fiveViews);
                                    warpLinearLayout.removeView(fiveView);

                                }
                            }
                        }
                        /**
                         * SP020146  主症
                         * SP020147  伴随症状
                         * SP020148  兼症
                         */
                        if ("SP020146".equals(childrensBeanXX.getModuleCode()) ||
                                "SP020147".equals(childrensBeanXX.getModuleCode()) ||
                                "SP020148".equals(childrensBeanXX.getModuleCode())) {
                            if (childrensBeanXX.isCheck) {
                                //将名字添加到一个集合中
                                stringArrayList.add(childrensBeanXX.getDisplayName() + " ");
                                stringBuilderTag.append(childrensBeanXX.getDisplayName() + " ");

                                Log.e("ssssssss", "选中状态  "+stringBuilderTag.toString());
                            } else {
                                String replace = stringBuilderTag.toString().replace(childrensBeanXX
                                        .getDisplayName() + " ", "");
                                stringBuilderTag = new StringBuilder();
                                stringBuilderTag.append(replace);
                                Log.e("ssssssss", "追加之后的为: "+stringBuilderTag.toString());
                            }

//                            currentAloneBigEdit.setOnKeyListener(new View.OnKeyListener() {
//                                @Override
//                                public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                                    if (KeyEvent.ACTION_DOWN == keyEvent.getAction()) {
//                                        switch (i) {
//                                            case KeyEvent.KEYCODE_DEL:
//                                                String jqString = stringBuilderTag.substring(stringBuilderTag.length()-3,stringBuilderTag.length());
//                                                Log.e("ssssss", "截取的字符串为"+jqString);
//                                                if(stringArrayList.contains(jqString)){
//                                                    stringBuilderTag.delete(stringBuilderTag.length()-4,stringBuilderTag.length()-1);
//                                                    Log.e("ssssssss", "onKey: "+stringBuilderTag.toString());
//                                                    currentAloneBigEdit.setText(stringBuilderTag.toString());
//                                                    return true;
//                                                }else{
//                                                    Log.e("ssssss", "删除失败");
//                                                    return true;
//                                                }
//                                        }
//                                    }
//                                    return false;
//                                }
//                            });
                            Log.e("sssssss", "集合里的数量: "+stringArrayList.size());
                            for(int i=0; i< stringArrayList.size(); i++){
                                Log.e("ssssss", "addFiveLevelView: "+stringArrayList.get(i));
                            }

                            currentAloneBigEdit.setText(stringBuilderTag.toString());
                        }
                        addThreeMenuList(childrensBeanXX);
                    }
                });
                // title,textarea,checkbox,radio,text,select
            } else if ("radio".equals(children.getFieldType())) {
                final RadioButton radioButton = getRadioButton(childrensBeanXX.getDisplayName(),
                        childrensBeanXX.isCheck);
                warpLinearLayout.addView(radioButton);
                radios.put(childrensBeanXX.getId(), radioButton);
                radioButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String oldId = "";
                        for (BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean
                                .ChildrensBeanXXX.ChildrensBeanXX beanXX : children.getChildrens()) {
                            if (beanXX.isCheck) oldId = beanXX.getId();
                            radios.get(beanXX.getId()).setChecked(false);
                            beanXX.isCheck = false;
                        }
                        List<View> fourViews = fourLevelMap.get(oldId);
                        if (fourViews != null && fourViews.size() > 0) {
                            for (View fourView : fourViews) {
                                warpLinearLayout.removeView(fourView);
                            }
                            List<View> fiveViews = fiveLevelMap.get(oldId);
                            for (View fiveView : fiveViews) {
                                warpLinearLayout.removeView(fiveView);
                            }
                        }
                        radioButton.setChecked(true);
                        childrensBeanXX.isCheck = true;
                        if (childrensBeanXX.getChildrens().size() > 0) {
                            addFiveLevelView(childrensBeanXX,warpLinearLayout);
                        }
                    }
                });
            } else if ("date".equals(children.getFieldType())) {
                Log.e("nnnnnnnnnnnnnn", "addDateSelect: "+"1117");

                addDateSelect(children);
            }
        }

    }

    /**
     * 添加右侧 四级 五级视图
     *
     * @param childrensBeanXX
     * @param warpLinearLayout
     */
    private void addFiveLevelView(BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean
                                          .ChildrensBeanXXX.ChildrensBeanXX childrensBeanXX,
                                  WarpLinearLayout warpLinearLayout) {
        List<View> fourList = new ArrayList<>();
        List<View> fiveList = new ArrayList<>();
        for (final BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean.ChildrensBeanXXX
                .ChildrensBeanXX.ChildrensBeanX childrensBeanX : childrensBeanXX.getChildrens()) {
            TextView fourTitle = getTitle(childrensBeanXX.getDisplayName() + "-" + childrensBeanX
                    .getDisplayName());
            if(childrensBeanX.getDisplayName().equals("评分")){

            }else{
                warpLinearLayout.addView(fourTitle);
            }
            String a = childrensBeanXX.getDisplayName() + "-" + childrensBeanX
                    .getDisplayName();
            fourList.add(fourTitle);
            addProgressLay(fourList,a, childrensBeanX);
            for (final BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean.ChildrensBeanXXX
                    .ChildrensBeanXX.ChildrensBeanX.ChildrensBean childrensBean : childrensBeanX
                    .getChildrens()) {
                if (childrensBeanX.getFieldType().equals("checkbox")) {
                    Log.e("ppppppppp", "addFiveLevelView: "+childrensBean.getDisplayName()  + childrensBean
                            .isCheck);
                    CheckBox fiveCheckBox = getCheckbox(childrensBean.getDisplayName(), childrensBean
                            .isCheck);
                    warpLinearLayout.addView(fiveCheckBox);
//                    addFiveMenuList(childrensBean);
                    fiveCheckBox.setTag(childrensBean);
                    fiveList.add(fiveCheckBox);
                    fiveCheckBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.e("ppppppp", "onClick: "+childrensBean.isCheck);
                            childrensBean.isCheck = !childrensBean.isCheck;
//                            addFiveMenuList(childrensBean);
                        }
                    });
                } else if ("textarea".equals(childrensBean.getFieldType())) {
                    EditText aloneBigEdit = getAloneBigEdit(childrensBean.content);
                    warpLinearLayout.addView(aloneBigEdit);
                    aloneBigEdit.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int
                                i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int
                                i2) {
                            childrensBean.content = charSequence.toString();
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                        }
                    });
                } else if ("radio".equals(childrensBeanX.getFieldType())) {
                    final RadioButton radioButton = getRadioButton(childrensBean.getDisplayName(),
                            childrensBean.isCheck);
                    warpLinearLayout.addView(radioButton);
                    radioButton.setTag(childrensBean.getId());
                    radios.put(childrensBean.getId(), radioButton);
                    fiveList.add(radioButton);
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for (BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean
                                    .ChildrensBeanXXX.ChildrensBeanXX.ChildrensBeanX.ChildrensBean
                                    childrensBeanXx : childrensBeanX.getChildrens()) {
                                radios.get(childrensBeanXx.getId()).setChecked(false);
                                childrensBeanXx.isCheck = false;
                            }
                            radioButton.setChecked(true);
                            childrensBean.isCheck = true;
                        }
                    });
                }
            }
            fourLevelMap.put(childrensBeanXX.getId(), fourList);
            fiveLevelMap.put(childrensBeanXX.getId(), fiveList);
        }
    }


    /**
     * 添加进度条
     *
     * @param fourList
     * @param childrensBeanX
     * @param name
     */
    private void addProgressLay(List<View> fourList,String name, final BianZhengBean.DataBean.ModuleListBean
            .ChildlistBean.FildlistBean.ChildrensBeanXXX.ChildrensBeanXX.ChildrensBeanX
            childrensBeanX) {
        try {
            if ("score".equals(childrensBeanX.getFieldType())) {
                final LinearLayout linearLayout = new LinearLayout(mContext);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setGravity(Gravity.CENTER_VERTICAL);
                Log.e("uuuuuuuuuuuuu", "添加  addProgressLay: ...."+ childrensBeanX.content);

                SeekBar progressBar = getProgressBar(TextUtils.isEmpty
                        (childrensBeanX.content) ? "0" : childrensBeanX.content);
                final TextView normalTv = getNormalTv(TextUtils.isEmpty(childrensBeanX.content)
                        ? "0" : childrensBeanX.content);
                final LinearLayout linearLayout2 = new LinearLayout(mContext);
                linearLayout2.setOrientation(LinearLayout.VERTICAL);
                linearLayout2.setGravity(Gravity.CENTER_VERTICAL);
                linearLayout2.addView(getTitle(name));
                linearLayout.addView(progressBar);
                linearLayout.addView(normalTv);
//                bingzheng_container.addView(getTitle(name));
//                warpLinearLayout.addView(getTitle(name));
//                bingzheng_container.addView(getTitle(name));
//                bingzheng_container.addView(getFuTitle("666"));
//                bingzheng_container.addView(getFuTitle(children.displayName));
                bingzheng_container.addView(linearLayout2);
                bingzheng_container.addView(linearLayout);
                progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        normalTv.setText(progress + "");
                        Log.e("uuuuuuuuuu ", "添加 addProgressLay: "+ progress + "");

                        childrensBeanX.content = progress + "";
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
                fourList.add(linearLayout);
                fourList.add(linearLayout2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * --------------------------------------------------------------------
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
     * 获取单选
     *
     * @return
     */
    public SeekBar getProgressBar(String progress) {
        SeekBar progressBar = new SeekBar(mContext);
        progressBar.setMax(10);
        progressBar.setProgress(Integer.parseInt(progress));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(HcUtils.getScreenWidth
                (mContext) / 5 * 3, HcUtils.dp2px(mContext, 42));
        int m = HcUtils.dp2px(mContext, 8);
        params.setMargins(10, m, 0, m);
        progressBar.setLayoutParams(params);
        return progressBar;
    }

    /**
     * 单选按钮
     *
     * @return
     */
    public RadioButton getRadioButton(String displayName, boolean isCheck) {
        RadioButton radioButton = new RadioButton(mContext);
        radioButton.setChecked(isCheck);
        radioButton.setText(displayName);
        radioButton.setTextSize(16);
        return radioButton;
    }


    /**
     * 根据三级数据添加 menubutton list
     *
     * @param childrensBeanXX
     */
    private void addThreeMenuList(BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean
                                          .ChildrensBeanXXX.ChildrensBeanXX childrensBeanXX) {
        for (BianZhengBean.DataBean.RelationListBean relationListBean : bianZhengBean
                .getData().getRelationList()) {
            if (childrensBeanXX.getId().equals(relationListBean.getFieldID())) {
                for (List<BianZhengBean.DataBean.ModuleListBean.ChildlistBean>
                        childlist : childlists) {
                    for (BianZhengBean.DataBean.ModuleListBean.ChildlistBean
                            childlistBean : childlist) {
                        if (childlistBean.getTagModule().getModuleCode().equals
                                (relationListBean.getModuleCode())) {
                            if (childrensBeanXX.isCheck) {
                                if (!navChildLists.contains(childlistBean))
                                    navChildLists.add(childlistBean);
                            } else {
                                navChildLists.remove(childlistBean);
                            }
                        }
                    }
                }
            }
            if (navChildLists.size() > 0) {
                menu_button.setVisibility(View.VISIBLE);
            } else {
                isShowMenuList = false;
                menu_button.setVisibility(View.GONE);
                menu_list.setVisibility(View.GONE);
            }
            navAdapter.notifyDataSetChanged();
        }
    }



    /**
     * 获取checkbox
     *
     * @param title
     * @param isCheck
     * @return
     */
    public CheckBox getCheckbox(String title, boolean isCheck) {
        CheckBox checkBox = new CheckBox(mContext);
        checkBox.setText(title);
        checkBox.setPadding(10, 2, 10, 0);
        checkBox.setChecked(isCheck);
        return checkBox;
    }

    /**
     * 获取展示的 Edit
     *
     * @return
     */
    public EditText getNormalEdit(String content) {
        EditText editText = new EditText(mContext);
        editText.setTextSize(16);
        editText.setHint("            ");
        editText.setText(content);
        editText.setSingleLine(true);
        editText.setTextColor(Color.parseColor("#99000000"));
//        editText.setBackgroundResource(R.drawable.fangan_bg);
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

    private void addDateSelect(final BianZhengBean.DataBean.ModuleListBean.ChildlistBean.FildlistBean.ChildrensBeanXXX children) {
        final LinearLayout dateView = getDateView(children.getDisplayName(), TextUtils.isEmpty
                (children.content) ? "点击请选择" :
                children.content);
        bingzheng_container.addView(dateView);
        dateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        textView.setTextSize(16);
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
        editText.setTextSize(16);
        editText.setHint("            ");
        editText.setText(content);
        editText.setSingleLine(false);
        editText.setTextColor(Color.parseColor("#99000000"));
//        editText.setBackgroundResource(R.drawable.fangan_bg);
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
        textView.setTextSize(16);
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
        textView.setTextSize(18);
        textView.setTextColor(Color.BLACK);
        textView.setText(title);
        textView.setPadding(10, 2, 10, 0);
        textView.setBackgroundColor(Color.parseColor("#44000000"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(HcUtils.getScreenWidth
                (mContext), LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        return textView;
    }

    @Override
    public Context context() {
        return null;
    }
}
