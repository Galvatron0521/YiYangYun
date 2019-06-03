package com.wd.yiyangyun;

public interface HttpCallback {
    void onSuccess(HttpResMsg httpResMsg);

    void onError(String errorMsg);
}
