package com.yadeah.minichat.common.utils.log;

import lombok.Data;

/**
 * 访问日志实体类
 */
@Data
public class RequestLogEntity {

    /**
     * 请求标识
     */
    private String requestId;

    /**
     * sessionId
     */
    private String sessionId;

    /**
     * accountId
     */
    private String accountId;

    /**
     * ip
     */
    private String ip;

    /**
     * 请求路径
     */
    private String uri;

    /**
     * 请求方法
     */
    private String httpMethod;

    /**
     * 类名、方法名
     */
    private String classMethod;

    /**
     * 入参
     */
    private String params;

}
