package com.yadeah.minichat.controller.account;

import com.yadeah.minichat.common.annotation.Auth;
import com.yadeah.minichat.common.constant.system.MethodOperationType;
import com.yadeah.minichat.common.exception.BusinessException;
import com.yadeah.minichat.common.model.AccountModel;
import com.yadeah.minichat.common.request.BaseRequest;
import com.yadeah.minichat.common.request.account.LoginRequest;
import com.yadeah.minichat.common.request.account.RegisterRequest;
import com.yadeah.minichat.common.request.account.UpdateAccountRequest;
import com.yadeah.minichat.common.response.HttpResponse;
import com.yadeah.minichat.controller.BaseController;
import com.yadeah.minichat.service.account.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api("账户接口")
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    @Auth(auth = false)
    @ApiOperation("登陆")
    @PostMapping("/login")
    public HttpResponse<AccountModel> login(@RequestBody LoginRequest request, HttpServletResponse response) throws BusinessException {
        return responseSuccess(accountService.login(request, response));
    }

    @Auth(auth = false)
    @ApiOperation("登出")
    @PostMapping("/logout")
    public HttpResponse<AccountModel> logout(BaseRequest request) {
        return responseSuccess(accountService.logout(request));
    }

    @Auth(auth = false, type = MethodOperationType.CREATE)
    @ApiOperation("注册")
    @PostMapping("/register")
    public HttpResponse<AccountModel> register(@RequestBody RegisterRequest request) throws BusinessException {
        return responseSuccess(accountService.register(request));
    }

    @Auth(type = MethodOperationType.UPDATE)
    @ApiOperation("更新账户")
    @PostMapping
    public AccountModel updateAccount(@RequestBody UpdateAccountRequest request) {

    }
//
//    @ApiOperation("注销账户")
//    @DeleteMapping
//    public Void deleteAccount(BaseRequest request) {
//
//    }

}
