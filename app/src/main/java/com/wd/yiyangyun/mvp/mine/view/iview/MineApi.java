package com.wd.yiyangyun.mvp.mine.view.iview;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MineApi {

    @GET
    Call<ResponseBody> requestGetHttp(@Url String url);
}
