package com.yadeah.minichat.common.component;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * session管理器
 */
@Component
public class SessionManager {

    private Map<String, String> sessionMap = new ConcurrentHashMap<>();

    public String createSession(String accountId) {
        String sessionId = UUID.randomUUID().toString();
        sessionMap.put(accountId, sessionId);
        return sessionId;
    }

    public void deleteSession(String accountId) {
        sessionMap.remove(accountId);
    }

    public String getSession(String accountId) {
        return sessionMap.get(accountId);
    }
}
