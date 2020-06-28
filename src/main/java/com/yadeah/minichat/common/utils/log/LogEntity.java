package com.yadeah.minichat.common.utils.log;

import lombok.Data;

/**
 * 日志记录实体类
 */
@Data
public class LogEntity<T> {

    /**
     * 日志类型
     */
    private LogType logType;

    /**
     * 信息
     */
    private T message;
}
