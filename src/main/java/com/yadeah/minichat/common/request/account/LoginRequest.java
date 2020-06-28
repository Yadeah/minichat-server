package com.yadeah.minichat.common.request.account;

import com.yadeah.minichat.common.constant.account.LoginType;
import com.yadeah.minichat.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("登录请求")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LoginRequest extends BaseRequest {

    @ApiModelProperty(name = "登录方式", required = true)
    private LoginType loginType;

    @ApiModelProperty(name = "账户/邮箱/手机号", required = true)
    private String account;

    @ApiModelProperty(name = "密码/验证码", required = true)
    private String password;

}
