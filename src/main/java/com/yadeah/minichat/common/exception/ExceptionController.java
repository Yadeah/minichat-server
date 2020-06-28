package com.yadeah.minichat.common.exception;

import com.yadeah.minichat.common.constant.system.HttpResponseStatus;
import com.yadeah.minichat.common.response.HttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = BusinessException.class)
    public <T> HttpResponse<T> catchBusinessException(BusinessException exception) {
        HttpResponse<T> response = new HttpResponse<>();
        response.setCode(exception.getCode());
        response.setMessage(exception.getMessage());
        // todo 日志记录

        return response;
    }

    @ExceptionHandler(value = Exception.class)
    public HttpResponse<Void> catchException(Exception exception) {
        HttpResponse<Void> response = new HttpResponse<>();
        response.setCode(HttpResponseStatus.FAIL.getCode());
        response.setMessage(HttpResponseStatus.FAIL.getMessage());
        // todo 记录日志
        exception.printStackTrace();
        return response;
    }
}
