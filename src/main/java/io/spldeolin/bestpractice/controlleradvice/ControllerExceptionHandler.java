package io.spldeolin.bestpractice.controlleradvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public String processNumberFormatException(NativeWebRequest request, NumberFormatException e) {
        return "该请求内部抛出了一个NumberFormatException异常";
    }

}
