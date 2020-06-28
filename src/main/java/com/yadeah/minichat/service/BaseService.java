package com.yadeah.minichat.service;

import com.yadeah.minichat.common.cache.AccountCache;
import com.yadeah.minichat.common.component.Redis;
import com.yadeah.minichat.common.constant.system.HttpResponseStatus;
import com.yadeah.minichat.common.exception.BusinessException;
import com.yadeah.minichat.common.model.AccountModel;
import com.yadeah.minichat.common.request.BaseRequest;
import com.yadeah.minichat.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class BaseService {

    @Autowired
    protected Redis redis;

    @Autowired
    protected AccountMapper accountMapper;

    // region 获取AccountModel
    protected AccountModel getAccountByAccountId(BaseRequest request, String accountId) throws BusinessException {
        AccountModel accountModel = AccountCache.getAccountModel(accountId);
        return accountModel == null ?
                getAccount(request, accountId, null, null, null, "accountId", String.valueOf(accountId)) :
                accountModel;
    }

    protected AccountModel getAccountByAccount(BaseRequest request, String account) throws BusinessException {
        return getAccount(request, null, account, null, null, "account", account);
    }

    protected AccountModel getAccountByMobile(BaseRequest request, String mobile) throws BusinessException {
        return getAccount(request, null, null, mobile, null, "mobile", mobile);
    }

    protected AccountModel getAccountByEmail(BaseRequest request, String email) throws BusinessException {
        return getAccount(request, null, null, null, email, "email", email);
    }

    private AccountModel getAccount(BaseRequest request, String accountId, String account, String mobile, String email, String key, String value) throws BusinessException {
        AccountModel accountModel = accountMapper.selectAccountModelByAccountIdOrAccountOrMobileOrEmail(
                accountId, account, mobile, email);
        if (accountModel == null) {
            throw new BusinessException(request, HttpResponseStatus.ACCOUNT_ERROR);
        }
        return accountModel;
    }
    // endregion

    // region 手机验证码
    protected static final String MOBILE_CODE_KEY = "mobile-code:%s:%s";

    protected void checkMobileCode(BaseRequest request, String field, String mobile, String code) throws BusinessException {
        String key = String.format(MOBILE_CODE_KEY, field, mobile);
        if (StringUtils.isEmpty(code) || !code.equals(redis.get(key))) {
            throw new BusinessException(request, HttpResponseStatus.MOBILE_CODE_ERROR);
        }
        redis.delete(key);
    }
    // endregion

    // region 生成id

    @Value("${server.number:21}")
    private String serverNumber;

    protected String generateId() {
        return serverNumber + System.currentTimeMillis();
    }
    // endregion
}
