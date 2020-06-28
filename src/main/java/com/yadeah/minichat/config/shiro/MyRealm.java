//package com.yadeah.minichat.config.shiro;
//
//import com.yadeah.minichat.common.model.AccountModel;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//
//public class MyRealm extends AuthorizingRealm {
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        return null;
//    }
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//
//        if (authenticationToken.getPrincipal() == null) {
//            return null;
//        }
//
//        MyAuthenticationToken token = (MyAuthenticationToken) authenticationToken;
//        AccountModel account = (AccountModel) token.getPrincipal();
//        return new SimpleAuthenticationInfo(account, account.getAccountId(), String.valueOf(account.getAccountId()));
//    }
//}
