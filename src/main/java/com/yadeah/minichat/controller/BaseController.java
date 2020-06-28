package com.yadeah.minichat.controller;

import com.yadeah.minichat.common.constant.system.HttpResponseStatus;
import com.yadeah.minichat.common.response.HttpResponse;

/**
 * 控制器基类
 */
public class BaseController {

    protected <T> HttpResponse<T> responseSuccess(T data) {
        HttpResponse<T> response = new HttpResponse<>();
        response.setCode(HttpResponseStatus.SUCCESS.getCode());
        response.setMessage(HttpResponseStatus.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }
}
