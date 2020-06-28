package com.yadeah.minichat.common.cache;

import com.yadeah.minichat.common.model.AccountModel;
import com.yadeah.minichat.mapper.AccountMapper;
import com.yadeah.minichat.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户信息缓存
 */
public class AccountCache {

    @Autowired
    private static AccountMapper accountMapper;

    private static Map<String, AccountModel> accountModelMap = new ConcurrentHashMap<>();

    public static void updateAccountModel(AccountModel accountModel) {
        // todo log
        accountModelMap.put(accountModel.getAccountId(), accountModel);
    }

    public static void deleteAccountModel(String accountId) {
        // todo log
        accountModelMap.remove(accountId);
    }

    public static AccountModel getAccountModel(String accountId) {
        AccountModel accountModel = accountModelMap.get(accountId);
        if (accountModel == null) {
            accountModel = accountMapper.selectAccountModelByAccountIdOrAccountOrMobileOrEmail(accountId, null, null, null);
            if (accountModel != null) {
                accountModelMap.put(accountId, accountModel);
            }
        }
        return accountModel;
    }
}
