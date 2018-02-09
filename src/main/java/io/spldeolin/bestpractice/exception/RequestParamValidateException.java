package io.spldeolin.bestpractice.exception;

import java.util.List;
import java.util.Map;

public class RequestParamValidateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private List<Map<String, String>> fieldValidErrors;

    public RequestParamValidateException() {
        super();
    }

    public RequestParamValidateException(List<Map<String, String>> fieldValidErrors) {
        super();
        this.fieldValidErrors = fieldValidErrors;
    }

    public RequestParamValidateException(String message) {
        super(message);
    }

    public RequestParamValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestParamValidateException(Throwable cause) {
        super(cause);
    }

    protected RequestParamValidateException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public List<Map<String, String>> getFieldValidErrors() {
        return fieldValidErrors;
    }

    public void setFieldValidErrors(List<Map<String, String>> fieldValidErrors) {
        this.fieldValidErrors = fieldValidErrors;
    }
}