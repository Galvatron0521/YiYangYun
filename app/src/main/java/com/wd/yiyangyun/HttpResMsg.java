package com.wd.yiyangyun;

public class HttpResMsg {
    private Integer req_id;
    private String method;
    private String data;

    public HttpResMsg() {
    }

    public HttpResMsg(String data) {
        this.data = data;
    }

    public Integer getReq_id() {
        return req_id;
    }

    public void setReq_id(Integer req_id) {
        this.req_id = req_id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
