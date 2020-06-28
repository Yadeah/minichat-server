package com.yadeah.minichat.server;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionSocketHolder {

    private static final Map<String, NioSocketChannel> CHANNEL_MAP = new ConcurrentHashMap<>(16);

//    private static final Map<String, String> SESSION_MAP = new ConcurrentHashMap<>(16);

//    public static void saveSession(String accountId, String username) {
//        SESSION_MAP.put(a, userName);
//    }
//
//    public static void removeSession(String accountId) {
//        SESSION_MAP.remove(userId);
//    }

    public static void addChannel(String accountId, NioSocketChannel channel) {
        CHANNEL_MAP.put(accountId, channel);
    }

    public static void getChannel(String accountId) {
        CHANNEL_MAP.get(accountId);
    }


}
