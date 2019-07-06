package com.lisong.service.req.manage.auth;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.lisong.service.res.manage.auth.ManageLoginResponse;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ManageLoginRequest extends AppRequest<ManageLoginResponse> {

    private static final long serialVersionUID = -3311087092095173701L;

    @NotBlank(message = "用户名不允许为空")
    private String acct;

    @NotBlank(message = "用户密码不允许为空")
    private String pwd;
}
