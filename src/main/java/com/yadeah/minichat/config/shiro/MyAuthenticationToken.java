//package com.yadeah.minichat.config.shiro;
//
//import com.yadeah.minichat.common.model.AccountModel;
//import org.apache.shiro.authc.AuthenticationToken;
//
//public class MyAuthenticationToken implements AuthenticationToken {
//
//    /**
//     * 实体
//     */
//    private final AccountModel accountModel;
//
//    public MyAuthenticationToken(AccountModel accountModel) {
//        this.accountModel = accountModel;
//    }
//
//    @Override
//    public Object getPrincipal() {
//        return this.accountModel;
//    }
//
//    @Override
//    public Object getCredentials() {
//        return this.accountModel.getAccountId();
//    }
//
//}
