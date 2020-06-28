package com.yadeah.minichat.common.request;

import com.yadeah.minichat.common.constant.system.ClientType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("请求基类")
@Data
public class BaseRequest {

    @ApiModelProperty(name = "请求id", hidden = true)
    private String requestId;

    @ApiModelProperty(name = "请求账户id", hidden = true)
    private String requestAccountId;

    @ApiModelProperty(name = "客户端类型", required = true)
    private ClientType clientType;

    @ApiModelProperty(name = "客户端版本", required = true)
    private String clientVersion;

}
