package com.yadeah.minichat.common.utils.log;

import com.yadeah.minichat.common.constant.system.HttpResponseStatus;

public class LogUtils {

    public static void request(String requestId, String sessionId, String accountId, String ip, String uri, String httpMethod, String classMethod, Object[] params) {

    }

    public static void response(String requestId, long responseTime, Object response) {

    }

    public static void response(String requestId, HttpResponseStatus status) {

    }

    public static void error(Throwable throwable) {

    }

    public static void error(Throwable throwable, String message, Object... args) {

    }

    public static void error(String message, Object...args) {

    }
}
