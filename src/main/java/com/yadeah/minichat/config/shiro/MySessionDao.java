//package com.yadeah.minichat.config.shiro;
//
//import com.yadeah.minichat.common.component.Redis;
//import org.apache.shiro.session.Session;
//import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class MySessionDao extends EnterpriseCacheSessionDAO {
//
//    @Autowired
//    private Redis redis;
//
//    private static final String SHIRO_SESSION_KEY = "session:%s";
//
//    @Override
//    protected Serializable doCreate(Session session) {
//        Serializable sessionId = super.doCreate(session);
//
//        String key = String.format(SHIRO_SESSION_KEY, sessionId);
//        try {
//            redis.set(key, )
//            redisTemplate.opsForValue().set(key, SerializableUtils.serialize(session), shiroConfig.shiroTimeout * 2, TimeUnit.MILLISECONDS);
//            return session.getId();
//        } catch (IOException e) {
//            log.error("Session创建遭遇异常。", e);
//            return null;
//        }
//        return super.doCreate(session);
//    }
//
//    @Override
//    protected Session doReadSession(Serializable sessionId) {
//        return super.doReadSession(sessionId);
//    }
//
//    @Override
//    protected void doUpdate(Session session) {
//        super.doUpdate(session);
//    }
//
//    @Override
//    protected void doDelete(Session session) {
//        super.doDelete(session);
//    }
//}
