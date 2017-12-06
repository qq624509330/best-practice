package io.spldeolin.bestpractice.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String processNumberFormatException(NativeWebRequest request, NumberFormatException e) {
        return "该请求内部抛出了一个NumberFormatException异常";
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String processHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return "请求不可读(可能原因：1.没有Request Body 2.Request Body格式有误)";
    }

}
