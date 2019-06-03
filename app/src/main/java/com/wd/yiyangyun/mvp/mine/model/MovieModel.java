package com.wd.yiyangyun.mvp.mine.model;

import com.wd.yiyangyun.mvp.mine.view.iview.MineApi;
import com.wd.yiyangyun.utils.RetrofitManager;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class MovieModel {

    public Call<ResponseBody> requestGetHttp(String st) {
        return RetrofitManager.getDefault().create(MineApi.class).requestGetHttp(st);
    }
}
