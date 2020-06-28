package com.yadeah.minichat.service.account;

import com.yadeah.minichat.common.cache.AccountCache;
import com.yadeah.minichat.common.component.SessionManager;
import com.yadeah.minichat.common.constant.account.AccountStatus;
import com.yadeah.minichat.common.constant.account.AccountType;
import com.yadeah.minichat.common.constant.system.HttpResponseStatus;
import com.yadeah.minichat.common.exception.BusinessException;
import com.yadeah.minichat.common.model.AccountModel;
import com.yadeah.minichat.common.request.BaseRequest;
import com.yadeah.minichat.common.request.account.LoginRequest;
import com.yadeah.minichat.common.request.account.RegisterRequest;
import com.yadeah.minichat.common.utils.CryptoUtils;
import com.yadeah.minichat.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class AccountService extends BaseService {

    @Autowired
    private SessionManager sessionManager;

    // region 登录
    public AccountModel login(LoginRequest request, HttpServletResponse response) throws BusinessException {
        // 获取账户信息
        AccountModel accountModel = null;
        switch (request.getLoginType()) {
            case PASSWORD:
                accountModel = loginByPassword(request);
                break;
            case MOBILE_CODE:
                accountModel = loginByMobileCode(request);
                break;
            default:
                throw new BusinessException(request, HttpResponseStatus.FAIL);
        }

        // 生成session，存入session管理器
        String sessionId = sessionManager.createSession(accountModel.getAccountId());
        // 设置account缓存
        AccountCache.updateAccountModel(accountModel);
        // 放入响应信息头部
        response.addHeader("minichat-session", sessionId);

        return accountModel;
    }

    private AccountModel loginByPassword(LoginRequest request) throws BusinessException {
        AccountModel accountModel = accountMapper.selectAccountModelByPassword(request.getAccount(), CryptoUtils.SHAEncryption(request.getPassword()));
        if (accountModel == null) {
            List<AccountModel> accountModelList = accountMapper.selectAccountModelsByAccountOrMobileOrEmail(request.getAccount());
            if (CollectionUtils.isEmpty(accountModelList)) {
                throw new BusinessException(request, HttpResponseStatus.ACCOUNT_ERROR);
            } else {
                throw new BusinessException(request, HttpResponseStatus.PASSWORD_ERROR);
            }
        }
        return accountModel;
    }

    private AccountModel loginByMobileCode(LoginRequest request) throws BusinessException {
        checkMobileCode(request, "login", request.getAccount(), request.getPassword());
        return getAccountByMobile(request, request.getAccount());
    }
    // endregion

    // region 登出
    public AccountModel logout(BaseRequest request) {
        // 删除session
        sessionManager.deleteSession(request.getRequestAccountId());
        // 删除缓存
        AccountCache.deleteAccountModel(request.getRequestAccountId());
        return null;
    }
    // endregion

    // region 注册
    public AccountModel register(RegisterRequest request) throws BusinessException {
        switch (request.getRegisterType()) {
            case PASSWORD: // 使用账号+密码方式注册
                return registerByAccount(request);
            case MOBILE_CODE: // 使用手机号+验证码方式注册
                return registerByMobile(request);
            default:
                throw new BusinessException(request, HttpResponseStatus.FAIL);
        }
    }

    private AccountModel registerByAccount(RegisterRequest request) throws BusinessException {
        // 判断账号是否存在
        if (super.getAccountByAccount(request, request.getAccount()) != null) {
            throw new BusinessException(request, HttpResponseStatus.ACCOUNT_EXIST);
        }

        AccountModel accountModel = createAccount();
        accountModel.setAccount(request.getAccount());
        accountModel.setPassword(CryptoUtils.SHAEncryption(request.getPassword()));
        accountModel.setUsername(request.getAccount());

        // 开始插入
        int insertRow = accountMapper.insert(accountModel);
        if (insertRow == 0) {
            throw new BusinessException(request, HttpResponseStatus.ACCOUNT_EXIST);
        }

        return accountModel;
    }

    private AccountModel registerByMobile(RegisterRequest request) throws BusinessException {
        // 判断账号是否存在
        if (super.getAccountByMobile(request, request.getAccount()) != null) {
            throw new BusinessException(request, HttpResponseStatus.MOBILE_EXIST);
        }

        // 验证验证码
        super.checkMobileCode(request, "register", request.getAccount(), request.getPassword());

        AccountModel accountModel = createAccount();
        accountModel.setMobile(request.getAccount());
        accountModel.setUsername(request.getAccount());

        // 开始插入
        int insertRow = accountMapper.insert(accountModel);
        if (insertRow == 0) {
            throw new BusinessException(request, HttpResponseStatus.MOBILE_EXIST);
        }

        return accountModel;
    }

    private AccountModel createAccount() {
        AccountModel accountModel = new AccountModel();
        accountModel.setAccountId(generateId());
        accountModel.setAccountType(AccountType.ACCOUNT);
        accountModel.setAccountStatus(AccountStatus.NORMAL.getStatus());
        return accountModel;
    }
    // endregion
}
