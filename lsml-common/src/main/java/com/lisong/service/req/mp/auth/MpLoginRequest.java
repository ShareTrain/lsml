package com.lisong.service.req.mp.auth;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.lisong.service.res.mp.auth.MpLoginResponse;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MpLoginRequest extends AppRequest<MpLoginResponse> {
    private static final long serialVersionUID = -8722427761086500800L;

    /** wx.login获取的用户登录凭证（有效期五分钟）. */
    @NotBlank(message = "用户登录凭证code为空")
    private String code;

    /** 昵称. */
    @NotBlank(message = "昵称不允许为空")
    private String nickname;

    /** 性别. */
    @NotNull(message = "性别不允许为空")
    private Integer sex;

    /** 头像. */
    @NotBlank(message = "头像不允许为空")
    private String avator;

    /** 国家. */
    private String country;

    /** 省份. */
    private String province;

    /** 城市. */
    private String city;
}
