package com.yadeah.minichat.service.account;

import com.yadeah.minichat.common.constant.account.AccountStatus;
import com.yadeah.minichat.common.constant.system.HttpResponseStatus;
import com.yadeah.minichat.common.exception.BusinessException;
import com.yadeah.minichat.common.model.AccountModel;
import com.yadeah.minichat.common.request.BaseRequest;
import com.yadeah.minichat.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

@Service
public class MobileCodeService extends BaseService {

    private static final String MOBILE_CODE_LOCK_KEY = "mobile-code:lock:%s";

    public boolean sendCodeToCurrentAccountMobile(String field, boolean authenticated, BaseRequest request) throws BusinessException {
        AccountModel account = super.getAccountByAccountId(request, request.getRequestAccountId());

        // 判断手机号是否存在
        // 需要认证时，状态必须是手机号已经认证过的
        if (StringUtils.isEmpty(account.getMobile())
                || (authenticated && (account.getAccountStatus() == AccountStatus.UNAUTHORIZED.getStatus()
                || account.getAccountStatus() == AccountStatus.UNAUTHORIZED_MOBILE.getStatus()))) {
            throw new BusinessException(request, HttpResponseStatus.MOBILE_UNAUTHENTICATED);
        }

        return this.sendCode(field, account.getMobile(), request);
    }

    public boolean sendCodeToNewMobile(String field, String mobile, BaseRequest request) throws BusinessException {
        return this.sendCode(field, mobile, request);
    }

    private boolean sendCode(String field, String mobile, BaseRequest request) throws BusinessException {
        // 判断是否操作频繁
        String lockKey = String.format(MOBILE_CODE_LOCK_KEY, mobile);
        String lockValue = redis.getAndSet(lockKey, "1", 55);
        if ("1".equals(lockValue)) {
            throw new BusinessException(request, HttpResponseStatus.MOBILE_CODE_LOCK);
        }

        // 生成code
        String code = this.createMobileCode();
        String key = String.format(MOBILE_CODE_KEY, field, mobile);
        redis.set(key, code, 310);

        // todo 发送

        return true;
    }

    private String createMobileCode() {
        int code = 0;
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code = code * 10 + random.nextInt(10);
        }
        return String.valueOf(code);
    }
}
