package com.yadeah.minichat.server;

import com.yadeah.minichat.common.constant.system.ClientType;
import com.yadeah.minichat.common.constant.system.TcpMessageType;
import lombok.Data;

@Data
public class TcpMessage {

    /**
     * 消息类型
     */
    private TcpMessageType type;

    /**
     * 请求id
     */
    private String requestId;

    /**
     * session id
     */
    private String sessionId;

    /**
     * account id
     */
    private String accountId;

    /**
     * 客户端类型
     */
    private ClientType clientType;

    /**
     * 客户端版本
     */
    private String clientVersion;

}
