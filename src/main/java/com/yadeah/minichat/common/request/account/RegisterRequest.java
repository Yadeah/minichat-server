package com.yadeah.minichat.common.request.account;

import com.yadeah.minichat.common.constant.account.LoginType;
import com.yadeah.minichat.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("注册请求")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RegisterRequest extends BaseRequest {

    @ApiModelProperty(name = "注册类型", required = true)
    private LoginType registerType;

    @ApiModelProperty(name = "账号/邮箱/手机号", required = true)
    private String account;

    @ApiModelProperty(name = "密码", required = true)
    private String password;

}
