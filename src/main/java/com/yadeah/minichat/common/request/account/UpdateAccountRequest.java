package com.yadeah.minichat.common.request.account;

import com.yadeah.minichat.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@ApiModel("更新账户请求")
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UpdateAccountRequest extends BaseRequest {

    @ApiModelProperty(name = "用户名")
    private String username;

    @ApiModelProperty(name = "头像")
    private String avatar;

    @ApiModelProperty(name = "性别")
    private int gender;

    @ApiModelProperty(name = "地区")
    private String region;

    @ApiModelProperty(name = "简介")
    private String description;

    @ApiModelProperty(name = "出生日期")
    private LocalDate birthday;

    @ApiModelProperty(name = "学历")
    private String education;

    @ApiModelProperty(name = "学校")
    private String school;

    @ApiModelProperty(name = "职业")
    private String career;

    @ApiModelProperty(name = "公司")
    private String company;

}
