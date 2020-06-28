package com.yadeah.minichat.common.constant.system;

public enum ConfigKey {

    TCP_PORT("tcp.port", "tcp端口"),
    TCP_QUEUE_SIZE("tcp.queue.size", "tcp模块队列长度"),
    TCP_IDLE_TIME("tcp.idle.time", "tcp模块闲置超时时间"),

    SERVER_NUMBER("server.number", "服务器编号"),
    ;

    String value;

    String desc;

    ConfigKey(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}
