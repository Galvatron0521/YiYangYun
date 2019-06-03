package com.wd.yiyangyun.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.mvp.mine.bean.AddGroupBean;
import com.wd.yiyangyun.mvp.mine.bean.DeleteGroupBean;
import com.wd.yiyangyun.mvp.mine.bean.GroupListBean;
import com.wd.yiyangyun.mvp.mine.bean.MyPlanListBean;
import com.wd.yiyangyun.mvp.mine.bean.MyTollBean;
import com.wd.yiyangyun.mvp.mine.bean.QueryTollBean;
import com.wd.yiyangyun.mvp.mine.bean.UpdateTollBean;
import com.wd.yiyangyun.mvp.patient.bean.BingchengListBean;
import com.wd.yiyangyun.okutils.BaseReturnBean;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import static android.support.constraint.Constraints.TAG;
import static com.wd.yiyangyun.app.Constants.ADD_GROUP;
import static com.wd.yiyangyun.app.Constants.BASE_URL;
import static com.wd.yiyangyun.app.Constants.DELECT_FOLLOW;
import static com.wd.yiyangyun.app.Constants.DELETE_GROUP;
import static com.wd.yiyangyun.app.Constants.GROUP_MANAGER;
import static com.wd.yiyangyun.app.Constants.QUERY_STANDARD;
import static com.wd.yiyangyun.app.Constants.SET_STANDARD;
import static com.wd.yiyangyun.app.Constants.UPDATE_GROUP;
import static com.wd.yiyangyun.app.Constants.UPDATE_STANDARD;
import static com.wd.yiyangyun.okutils.OkHttpUtils.optBaseReturnBean;

public class SynchniceUtil {

    private static SuiFangShareBean suiFangShareBean;

    //病程集合
    public static GroupListBean requestBingChengList() {
        final Map<String, Object> map = new HashMap<>();
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID,""));
        String json = JSONUtil.parseMapToJson(map);
        String url = Constants.GROUP_MANAGER+json;
        String a = getSyncBusiness(url);
        Gson gson = new Gson();
        GroupListBean bingchengListBean = gson.fromJson(a,GroupListBean.class);
        return bingchengListBean;
    }

    //我的收益
    public static MyTollBean myToll() {
        MyTollBean myTollBean = null;
        try {
            String userId = DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, "");
            final Map<String, Object> map = new HashMap<>();
            map.put("userID", userId);
            String json = JSONUtil.parseMapToJson(map);
            String url = Constants.QIANBAO+json;
            String a = getSyncBusiness(url);
            Gson gson = new Gson();
            Log.e("fffff", "requestPatientId: "+a );
            myTollBean = gson.fromJson(a,MyTollBean.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return myTollBean;
    }

    public static MyPlanListBean myPlanListBean(int pageNo, int pageCount) {
        final Map<String, Object> map = new HashMap<>();
        map.put("pageNo", pageNo + "");
        map.put("pageCount", pageCount + "");
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID, ""));

        String json = JSONUtil.parseMapToJson(map);
        String url = Constants.SELECT_FOLLOW_PLAN_LIST+json;
        String a = getSyncBusiness(url);
        Gson gson = new Gson();
        BaseReturnBean bean = optBaseReturnBean(a);
        Log.e("xxxxxxxxxxx", "发布出诊"+a);
        MyPlanListBean myPlanListBean = gson.fromJson(bean.data,MyPlanListBean.class);
        return myPlanListBean;
    }

    //查询收费
    public static QueryTollBean questToll() {
        String userID = DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, "");
        final Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        String json = JSONUtil.parseMapToJson(map);
        String url = QUERY_STANDARD+json;
        String a = getSyncBusiness(url);
        Gson gson = new Gson();
        QueryTollBean patientInfoBean = gson.fromJson(a,QueryTollBean.class);
        return patientInfoBean;
    }
    //添加收费
    public static UpdateTollBean addToll(String chargeAmout, String messageCount) {
        String userID = DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, "");
        final Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("chargeProjectID", "1");
        map.put("chargeAmout", chargeAmout);
        map.put("messageCount", messageCount);
        String json = JSONUtil.parseMapToJson(map);
        String url = SET_STANDARD+json;
        String a = getSyncBusiness(url);
        Gson gson = new Gson();
        UpdateTollBean patientInfoBean = gson.fromJson(a,UpdateTollBean.class);
        return patientInfoBean;
    }
    //修改收费
    public static UpdateTollBean updateToll(String id, String chargeAmout, String messageCount) {
        String userID = DoctorBaseAppliction.spUtil.getString(Constants.USER_ID, "");
        final Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("id", id);
        map.put("chargeAmout", chargeAmout);
        map.put("messageCount", messageCount);
        String json = JSONUtil.parseMapToJson(map);
        String url = UPDATE_STANDARD+json;
        String a = getSyncBusiness(url);
        Gson gson = new Gson();
        Log.e("tttttttt", "修改收费url"+url);
        Log.e("tttttttt", "修改收费"+a);
        UpdateTollBean patientInfoBean = gson.fromJson(a,UpdateTollBean.class);
        return patientInfoBean;
    }


    //添加分组
    public static GroupListBean queryGroupBean(String json) {
        String a = getSyncBusiness(GROUP_MANAGER+json);
        Gson gson = new Gson();
        Log.e(TAG, "queryGroupBean: "+a);
        GroupListBean addGroupBean = gson.fromJson(a,GroupListBean.class);
        return addGroupBean;
    }

    //添加分组
    public static AddGroupBean addGroupBean(String json) {
        String a = getSyncBusiness(ADD_GROUP+json);
        Gson gson = new Gson();
        Log.e(TAG, "addGroupBean: "+a);
        AddGroupBean addGroupBean = gson.fromJson(a,AddGroupBean.class);
        return addGroupBean;
    }

    //删除分组
    public static DeleteGroupBean deleteGroupBean(String json) {
        String a = getSyncBusiness(DELETE_GROUP+json);
        Gson gson = new Gson();
        DeleteGroupBean bingchengListBean = gson.fromJson(a,DeleteGroupBean.class);
        return bingchengListBean;
    }

    //修改分组
    public static DeleteGroupBean updateGroupBean(String json) {
        String a = getSyncBusiness(UPDATE_GROUP+json);
        Gson gson = new Gson();
        DeleteGroupBean bingchengListBean = gson.fromJson(a,DeleteGroupBean.class);
        return bingchengListBean;
    }

    public static GroupListBean requestBingChengList(String json) {
        String a = getSyncBusiness(GROUP_MANAGER+json);
        Gson gson = new Gson();
        GroupListBean bingchengListBean = gson.fromJson(a,GroupListBean.class);
        return bingchengListBean;
    }

    public static String getSyncBusiness(final String url){
        try {
            FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    URL u = new URL(url);
                    LogUtil.e(TAG, "requestBingChengList: "+u);
                    HttpURLConnection connection = (HttpURLConnection) u.openConnection();
                    connection.setDoInput(true);
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStream in = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf8"));
                    final StringBuilder sb = new StringBuilder();
                    String line = null;
                    while((line = br.readLine())!=null){
                        sb.append(line);
                    }
                    return sb.toString();
                }
            });

            new Thread(task).start();

            return task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return url;
    }
}
