package com.lisong.component.sms.req;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.lisong.component.sms.res.CheckVerifyCodeResponse;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/** Created by Alex on 2018/11/23 */
@Getter
@Setter
public class CheckVerifyCodeRequest extends AppRequest<CheckVerifyCodeResponse> {

    private static final long serialVersionUID = 3615636209563616597L;

    /** 输入的验证码 */
    @NotNull(message = "当前验证码不能为空")
    private String nowVerifyCode;

    /** 手机号 */
    @NotNull(message = "手机号不能空")
    private String mobile;
}
