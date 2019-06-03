package com.wd.yiyangyun.okutils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.wd.yiyangyun.mvp.home.bean.AllPatientBean;
import com.wd.yiyangyun.mvp.home.bean.BaoGaoInfoBean;
import com.wd.yiyangyun.mvp.home.bean.BianZhengBean;
import com.wd.yiyangyun.mvp.home.bean.BianZhengDetailBean;
import com.wd.yiyangyun.mvp.home.bean.BianZhengNewBean;
import com.wd.yiyangyun.mvp.home.bean.DoctorBean;
import com.wd.yiyangyun.mvp.home.bean.HospitalBean;
import com.wd.yiyangyun.mvp.home.bean.KeShiBean;
import com.wd.yiyangyun.mvp.home.bean.NewsBean;
import com.wd.yiyangyun.mvp.home.bean.PatientListBean;
import com.wd.yiyangyun.mvp.home.bean.PdfReturnBean;
import com.wd.yiyangyun.mvp.home.bean.ResultBean2;
import com.wd.yiyangyun.mvp.home.bean.VideoListBean;
import com.wd.yiyangyun.mvp.home.bean.ZhuanInBean;
import com.wd.yiyangyun.mvp.home.bean.ZhuanOutBean;
import com.wd.yiyangyun.mvp.home.bean.ZhuanZhenInfoBean;
import com.wd.yiyangyun.mvp.mine.bean.AddGroupBean;
import com.wd.yiyangyun.mvp.mine.bean.CalendarBean;
import com.wd.yiyangyun.mvp.mine.bean.GroupListBean;
import com.wd.yiyangyun.mvp.mine.bean.MyPlanListBean;
import com.wd.yiyangyun.mvp.mine.bean.MySuggestionListBean;
import com.wd.yiyangyun.mvp.mine.bean.PatientSuiFangListBean;
import com.wd.yiyangyun.mvp.mine.bean.ResultBean;
import com.wd.yiyangyun.mvp.mine.bean.SuiFangCrfBean;
import com.wd.yiyangyun.mvp.home.view.activity.suifangfangan.ContentBean;
import com.wd.yiyangyun.mvp.home.view.activity.suifangfangan.MyPlanCRFListBean;
import com.wd.yiyangyun.mvp.patient.bean.BingZhengListBean;
import com.wd.yiyangyun.mvp.patient.bean.PatientInfoBean;
import com.wd.yiyangyun.mvp.patient.bean.SuiFangTypeBean;
import com.wd.yiyangyun.utils.JSONUtil;
import com.wd.yiyangyun.utils.LogUtil;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.wd.yiyangyun.okutils.OkHttpUtils.optBaseReturnBean;


public class DoctorNetService {

    /**
     * 请求病理报告PDF
     */
    public static void requestGetPdf(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                PdfReturnBean bean = OkHttpUtils.initPdfGetHttp(url, param);
                LogUtil.e("hcccccc---->>>:" + bean);
                if (isSuccess(bean.status)) {
                    subscriber.onNext(bean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    /**
     * 请求病理报告
     *
     * @param param
     */
    public static void requestFieldRecordListForm(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                BaseReturnBean bean = optBaseReturnBean(result);
                BaoGaoInfoBean infoBean = JSONUtil.parseJsonToBean(bean.data,
                        BaoGaoInfoBean.class);
                if (isSuccess(bean.code)) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    /**
     * 请求患者列表
     *
     * @param param
     */
    public static void requestPatientList(final String url, final Map<String, Object> param, final
    NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String sendJson = JSONUtil.parseMapToJson(param);
                Log.e("vvvvvvvvvvvvvv", "call: "+url+sendJson);
                String result = OkHttpUtils.initGetHttp(url, param);
                BaseReturnBean bean = optBaseReturnBean(result);
                LogUtil.e("hcccccc----请求患者列表>>>:" + bean.data);
                PatientListBean patientListBean = JSONUtil.parseJsonToBean(bean.data,
                        PatientListBean.class);
                if (isSuccess(bean.code)) {
                    subscriber.onNext(patientListBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    /**
     * 查询患者随访记录,方案
     *
     * @param param
     */
    public static void requestCalendarList(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                CalendarBean infoBean = gson.fromJson(result,CalendarBean.class);
                if(isSuccess(infoBean.getStatus())){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    /**
     * 获取病症管理列表
     *
     * @param param
     */
    public static void requestBingZhengDetail(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                BaseReturnBean bean = optBaseReturnBean(result);
                LogUtil.e("hcccccc---->>>:最后添加的回显" + bean);
                BianZhengDetailBean infoBean = JSONUtil.parseJsonToBean(bean.data,
                        BianZhengDetailBean.class);
                if (isSuccess(bean.code)) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    /**
     * 查询患者随访记录,方案
     *
     * @param param
     */
    public static void requestNewsList(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                NewsBean infoBean = gson.fromJson(result,NewsBean.class);
                if(isSuccess(infoBean.getStatus())){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }


    /**
     * 根据URL修改或者添加数据  只返回状态的
     */
    public static void requestAddOrChangeByUrl(final String url, final Map<String, Object>
            param, final
                                               NetWorkRequestInterface requestInterface) {
        LogUtil.e("URL -- >> " + url);
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                BaseReturnBean bean = OkHttpUtils.initPostRequest(url, param);
                LogUtil.e(" 修改论治请求的结果   hcccccc---->>>:" + bean);
                ResultBean2 infoBean = JSONUtil.parseJsonToBean(bean.data, ResultBean2.class);
                if (isSuccess(bean.code)) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    /**
     * 查询随访方式
     *
     * @param param
     */
    public static void requestPicList(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                VideoListBean infoBean = JSONUtil.parseJsonToBean(result,
                        VideoListBean.class);
                if (isSuccess(infoBean.getStatus())) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    /**
     * 查询随访方式
     *
     * @param param
     */
    public static void requestFangAnType(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                BaseReturnBean bean = optBaseReturnBean(result);
                SuiFangTypeBean infoBean = JSONUtil.parseJsonToBean(bean.data,
                        SuiFangTypeBean.class);
                if (isSuccess(bean.code)) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    /**
     * 分组
     * @param param
     */
    public static void requestPatientGroup(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                GroupListBean infoBean = JSONUtil.parseJsonToBean(result,
                        GroupListBean.class);
                if (isSuccess(infoBean.getStatus())) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }


    /**
     * 患者详情
     * @param param
     */
    public static void requestPatientDetail(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                BaseReturnBean bean = optBaseReturnBean(result);
                PatientInfoBean infoBean = JSONUtil.parseJsonToBean(bean.data,
                        PatientInfoBean.class);
                if (isSuccess(bean.code)) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    /**
     * 获取病症管理列表
     *
     * @param param
     */
    public static void requestBingZhengList(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                BaseReturnBean bean = optBaseReturnBean(result);
                BingZhengListBean infoBean = JSONUtil.parseJsonToBean(bean.data,
                        BingZhengListBean.class);
                if (isSuccess(bean.code)) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    //随访记录列表
    public static void requestPatientSuiFangList(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                BaseReturnBean bean = optBaseReturnBean(result);
                PatientSuiFangListBean infoBean = gson.fromJson(bean.data,PatientSuiFangListBean.class);
                if(bean.data!=null){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    //全部医生
    public static void requestDoctor(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                DoctorBean infoBean = gson.fromJson(result,DoctorBean.class);
                if(isSuccess(infoBean.getStatus())){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    //全部科室
    public static void requestKeShi(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                KeShiBean infoBean = gson.fromJson(result,KeShiBean.class);
                if(isSuccess(infoBean.getStatus())){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    //全部患者
    public static void requestPatient(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                AllPatientBean infoBean = gson.fromJson(result,AllPatientBean.class);
                if(isSuccess(infoBean.getStatus())){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    //全部医院
    public static void requestHospitals(final String url,final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, null);
                Gson gson = new Gson();
                HospitalBean infoBean = gson.fromJson(result,HospitalBean.class);
                if(isSuccess(infoBean.getStatus())){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    //意见反馈
    public static void requestSuggestionList(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                BaseReturnBean bean = optBaseReturnBean(result);
                MySuggestionListBean infoBean = gson.fromJson(bean.data,MySuggestionListBean.class);
                if(bean.data!=null){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    //患者转诊详情
    public static void zhuanZhenList(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                ZhuanZhenInfoBean infoBean = gson.fromJson(result,ZhuanZhenInfoBean.class);
                if(isSuccess(infoBean.getStatus())){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    //转出
    public static void zhuanOut(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                ZhuanOutBean infoBean = gson.fromJson(result,ZhuanOutBean.class);
                if(isSuccess(infoBean.getStatus())){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    //转入
    public static void zhuanIn(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                ZhuanInBean infoBean = gson.fromJson(result,ZhuanInBean.class);
                if(isSuccess(infoBean.getStatus())){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    /**
     * 查询患者随访记录,方案
     *
     * @param param
     */
    public static void requestMyPlanList(final String url, final Map<String, Object>
            param, final
                                         NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                BaseReturnBean bean = optBaseReturnBean(result);
                LogUtil.e("suifangleibiao",bean.data);
                MyPlanListBean infoBean = gson.fromJson(bean.data,MyPlanListBean.class);
                if(bean.data!=null){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }



    /**
     * 查询 我的随访方案 关联  任务项目
     * selectFollowCRFListNew
     */
    public static void requestFollowCRFList(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                BaseReturnBean bean = optBaseReturnBean(result);
                LogUtil.e("biaodan",bean.data);
                MyPlanCRFListBean infoBean = gson.fromJson(bean.data,MyPlanCRFListBean.class);
                if(bean.data!=null){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }



    /**
     * 查询数据字典
     */
    public static void requestContent(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                BaseReturnBean bean = optBaseReturnBean(result);
                ContentBean infoBean = gson.fromJson(bean.data,ContentBean.class);
                if(bean.data!=null){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    /**
     * 修改或者添加数据  只返回状态的
     *
     * @param param
     */
    public static void requestResult(final String url, final Map<String, Object> param, final
    NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                LogUtil.e("suifangleibiao",result);
                ResultBean infoBean = gson.fromJson(result,ResultBean.class);
                LogUtil.e("suifangleibiao",infoBean.getStatus());
                if(result!=null || infoBean.getStatus().equals("0")){
                    subscriber.onNext(infoBean);
                }else{
                    subscriber.onError(new Exception());
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }


    // 随访方案--------新
    public static void requestFangAnInfo(final String url, final Map<String, Object> param, final
    NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                BianZhengNewBean infoBean = gson.fromJson(result,BianZhengNewBean.class);
                if (isSuccess(infoBean.getStatus())) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    public static void requestSuiFCrfDetail(final String url, final Map<String, Object>
            param, final NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                BaseReturnBean bean = optBaseReturnBean(result);
                SuiFangCrfBean infoBean = JSONUtil.parseJsonToBean(bean.data,
                        SuiFangCrfBean.class);
                if (isSuccess(bean.code)) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }


    // 辩证论治--------新
    public static void requestNewFildMoudleList(final String url, final Map<String, Object> param, final
    NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                BianZhengNewBean infoBean = gson.fromJson(result,BianZhengNewBean.class);
                if (isSuccess(infoBean.getStatus())) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    // 心医云的辩证论治
    public static void requestFildMoudleList(final String url, final Map<String, Object> param, final
    NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                BianZhengBean infoBean = gson.fromJson(result,BianZhengBean.class);
                if (isSuccess(infoBean.getStatus())) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    //查询
    public static void requestGroupList(final String url, final Map<String, Object> param, final
    NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                GroupListBean infoBean = gson.fromJson(result,GroupListBean.class);
                if (isSuccess(infoBean.getStatus())) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }
    //修改
    public static void updateGroupList(final String url, final Map<String, Object> param, final
    NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                AddGroupBean infoBean = gson.fromJson(result,AddGroupBean.class);
                if (isSuccess(infoBean.getStatus())) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    public static void addGroupList(final String url, final Map<String, Object> param, final
    NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                AddGroupBean infoBean = gson.fromJson(result,AddGroupBean.class);
                if (isSuccess(infoBean.getStatus())) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }

    public static void deleteGroupList(final String url, final Map<String, Object> param, final
    NetWorkRequestInterface requestInterface) {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String result = OkHttpUtils.initGetHttp(url, param);
                Gson gson = new Gson();
                AddGroupBean infoBean = gson.fromJson(result,AddGroupBean.class);
                if (isSuccess(infoBean.getStatus())) {
                    subscriber.onNext(infoBean);
                } else {
                    subscriber.onError(new Exception());
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
                requestInterface.onError(throwable);
            }

            @Override
            public void onNext(Object info) {
                requestInterface.onNext(info);
            }
        });
    }


    public static boolean isSuccess(String result) {
        if (!TextUtils.isEmpty(result)) {
            return result.endsWith("0000") || "0".equals(result);
        } else {
            return false;
        }
    }

}
