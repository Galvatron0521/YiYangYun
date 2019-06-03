package com.wd.yiyangyun.mvp.mine.presenter;

import android.util.Log;

import com.wd.yiyangyun.HttpCallback;
import com.wd.yiyangyun.HttpResMsg;
import com.wd.yiyangyun.mvp.mine.model.MovieModel;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author JenSenLeung.
 * @Time 2018/8/23 下午 5:00.
 * @Description This is MinePresenter.
 */
public class MinePresenter{

    private static MovieModel movieModel = new MovieModel();;

    //辩证论治
    public static void requestGetHttp(final String st, final HttpCallback callback) {
        Call<ResponseBody> call = movieModel.requestGetHttp(st);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if(response!=null){
                        String result = response.body().string();
                        Log.i("okhttp", "reqPostHttp onResponse: " + result);
                        if (callback != null) {
                            callback.onSuccess(new HttpResMsg(result));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("okhttp", "reqPostHttp onFailure: " + t.toString());
                if (callback != null) {
                    callback.onError(t.toString());
                }
            }
        });
    }

}
