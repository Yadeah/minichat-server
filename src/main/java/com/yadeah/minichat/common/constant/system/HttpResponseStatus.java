package com.yadeah.minichat.common.constant.system;

public enum  HttpResponseStatus {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 异常
     */
    FAIL(1, "请求失败"),

    /**
     * 非法请求
     */
    ILLEGAL(2, "非法请求"),

    /**
     * 入参校验失败
     */
    PARAMS_NULL(3, "%s为空"),

    /**
     * 账户类错误:10xx
     */
    AUTH_FAIL(10001, "用户未登录"),
    ACCOUNT_ERROR(10002, "用户不存在"),
    PASSWORD_ERROR(10003, "密码错误"),
    ACCOUNT_EXIST(10004, "账号已被注册"),
    EMAIL_EXIST(10005, "邮箱已被绑定"),
    MOBILE_EXIST(10006, "手机号已被注册"),
    MOBILE_UNAUTHENTICATED(10007, "手机号未认证"),


    /**
     * 手机验证码类错误:11xx
     */
    MOBILE_CODE_ERROR(11001, "验证码错误"),
    MOBILE_CODE_LOCK(11001, "操作频繁，请等待一分钟后再次发送。")
    ;

    private int code;

    private String message;

    HttpResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
