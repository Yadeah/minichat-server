package com.yadeah.minichat.common.exception;

import com.yadeah.minichat.common.constant.system.HttpResponseStatus;
import com.yadeah.minichat.common.request.BaseRequest;

/**
 * 业务异常
 */
public class BusinessException extends Exception {

    private final String requestId;

    private final int code;

    public BusinessException(String requestId, HttpResponseStatus status, Object...args) {
        super(String.format(status.getMessage(), args));
        this.requestId = requestId;
        this.code = status.getCode();
    }

    public BusinessException(BaseRequest request, HttpResponseStatus status, Object...args) {
        super(String.format(status.getMessage(), args));
        this.requestId = request.getRequestId();
        this.code = status.getCode();
    }

    public String getRequestId() {
        return requestId;
    }

    public int getCode() {
        return code;
    }
}
