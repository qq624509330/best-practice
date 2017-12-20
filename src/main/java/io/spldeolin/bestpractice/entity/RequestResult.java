package io.spldeolin.bestpractice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestResult {

    private Boolean result;

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

    public static RequestResult success(Object data) {
        RequestResult instance = new RequestResult();
        instance.setResult(true);
        instance.setData(data);
        return instance;
    }

    public static RequestResult failure(String errmsg) {
        RequestResult instance = new RequestResult();
        instance.setResult(false);
        instance.setErrmsg(errmsg);
        return instance;
    }

}
