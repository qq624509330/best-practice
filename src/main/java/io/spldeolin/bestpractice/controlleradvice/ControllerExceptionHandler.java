package io.spldeolin.bestpractice.controlleradvice;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import io.spldeolin.bestpractice.exception.RequestParamValidateException;

@RestControllerAdvice
@RequestMapping(produces = "text/html;charset=utf-8")
public class ControllerExceptionHandler {

    @ExceptionHandler(RequestParamValidateException.class)
    public String requestParamValidateException(RequestParamValidateException e) {
        return e.getFieldValidErrors().toString();
    }

    @ExceptionHandler(NumberFormatException.class)
    public String processNumberFormatException(NativeWebRequest request, NumberFormatException e) {
        return "该请求内部抛出了一个NumberFormatException异常";
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String processHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return "请求不可读(可能原因：1.没有Request Body 2.Request Body格式有误)";
    }

}
