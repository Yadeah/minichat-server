//package com.yadeah.minichat.config.shiro;
//
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ShiroConfig {
//
//    @Bean
//    public ShiroFilterFactoryBean shiroFilter() {
//        System.out.println("ShiroConfiguration.shiroFilter()");
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        shiroFilterFactoryBean.setSecurityManager(securityManager());
//
//        return shiroFilterFactoryBean;
//    }
//
//    @Bean
//    public MyRealm myShiroRealm() {
//        return new MyRealm();
//    }
//
//    @Bean
//    public MySessionDao mySessionDAO() {
//        return new MySessionDao();
//    }
//
//    @Bean
//    public MyWebSessionManager sessionManager() {
//        MyWebSessionManager sessionManager = new MyWebSessionManager();
//        sessionManager.setSessionDAO(mySessionDAO());
//        sessionManager.setGlobalSessionTimeout(shiroTimeout);
//        return sessionManager;
//    }
//
//
//    @Bean
//    public DefaultWebSecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(myShiroRealm());
//        securityManager.setSessionManager(sessionManager());
//        return securityManager;
//    }
//
//    /**
//     * 开启shiro aop注解支持.
//     * 使用代理方式;所以需要开启代码支持;
//     *
//     * @param
//     * @return
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
//        return authorizationAttributeSourceAdvisor;
//    }
//
//    /**
//     * @author: tianyong
//     * @time: 2018/8/17 15:27
//     * @description:解决权限注解不生效问题
//     */
//    @Bean
//    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
//        return new DefaultAdvisorAutoProxyCreator();
//    }
//}
