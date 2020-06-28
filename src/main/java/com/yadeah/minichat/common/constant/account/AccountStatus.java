package com.yadeah.minichat.common.constant.account;

/**
 * 账户状态
 */
public enum AccountStatus {

    NORMAL(0, "正常"),

    UNAUTHORIZED(1, "未认证"),

    UNAUTHORIZED_MOBILE(11, "手机号码未认证"),

    UNAUTHORIZED_EMAIL(12, "邮箱未认证"),

    LOCKED(2, "锁定"),

    FROZEN(3, "冻结"),

    APPEALING(4, "申诉中"),

    INVALID(5, "注销")

    ;

    private int status;

    private String description;

    AccountStatus(int status, String description) {
        this.status = status;
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
