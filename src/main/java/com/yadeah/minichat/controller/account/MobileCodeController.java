package com.yadeah.minichat.controller.account;

import com.yadeah.minichat.common.annotation.Auth;
import com.yadeah.minichat.common.constant.system.MethodOperationType;
import com.yadeah.minichat.common.exception.BusinessException;
import com.yadeah.minichat.common.request.BaseRequest;
import com.yadeah.minichat.common.response.HttpResponse;
import com.yadeah.minichat.controller.BaseController;
import com.yadeah.minichat.service.account.MobileCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("手机验证码api")
@RestController
@RequestMapping("/code")
public class MobileCodeController extends BaseController {

    @Autowired
    private MobileCodeService mobileCodeService;

    @Auth(type = MethodOperationType.OTHER)
    @ApiOperation("向当前账户手机号发送验证码")
    @GetMapping("/current/field/{field}/authenticated/{authenticated}")
    public HttpResponse<Boolean> sendCodeToCurrentAccountMobile(@PathVariable("field") String field,
                                                                @PathVariable("authenticated") boolean authenticated,
                                                                BaseRequest request) throws BusinessException {
        return responseSuccess(mobileCodeService.sendCodeToCurrentAccountMobile(field, authenticated, request));
    }

    @Auth(auth = false, type = MethodOperationType.OTHER)
    @ApiOperation("向传入手机号发送验证码")
    @GetMapping("/new/field/{field}/mobile/{mobile}")
    public HttpResponse<Boolean> sendCodeToNewMobile(@PathVariable("field") String field,
                                                     @PathVariable("mobile") String mobile,
                                                     BaseRequest request) throws BusinessException {
        return responseSuccess(mobileCodeService.sendCodeToNewMobile(field, mobile, request));
    }


}
