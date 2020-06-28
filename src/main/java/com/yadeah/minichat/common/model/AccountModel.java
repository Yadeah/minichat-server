package com.yadeah.minichat.common.model;

import com.yadeah.minichat.common.constant.account.AccountType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 账户实体类
 */
@Data
public class AccountModel {

    /**
     * id
     */
    private String accountId;

    /**
     * 账户类型
     */
    private AccountType accountType;

    /**
     * 账号
     */
    private String account;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 账户状态
     */
    private int accountStatus;

    /**
     * 注册时间
     */
    private LocalDateTime registerTime;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     * 0：无；1：男；2：女
     */
    private int gender;

    /**
     * 地区
     */
    private String region;

    /**
     * 简介
     */
    private String description;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    /**
     * 学历
     */
    private String education;

    /**
     *
     */

//    =================================     //
    /**
     * 年龄
     */
    private int age;

    /**
     * 星座
     */
    private String constellation;
}
