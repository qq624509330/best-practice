package io.spldeolin.bestpractice.entity;

public class RequestResult {

    private boolean result;

    private Object data;

    private String errmsg;

    public boolean isResult() {
        return result;
    }

    private RequestResult() {}

    public static RequestResult success() {
        RequestResult instance = new RequestResult();
        instance.setResult(true);
        return instance;
    }

    public static RequestResult failure() {
        RequestResult instance = new RequestResult();
        instance.setResult(false);
        return instance;
    }

    public RequestResult data(Object data) {
        this.data = data;
        return this;
    }

    public RequestResult errmsg(String errmsg) {
        this.errmsg = errmsg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}
