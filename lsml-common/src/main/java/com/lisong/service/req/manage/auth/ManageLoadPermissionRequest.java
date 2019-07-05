package com.lisong.service.req.manage.auth;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.lisong.service.res.manage.auth.ManageLoadPermissionResponse;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ManageLoadPermissionRequest extends AppRequest<ManageLoadPermissionResponse> {
    private static final long serialVersionUID = -2776067352474722352L;

    @NotNull(message = "用户ID不允许为空")
    private Long id;

    @NotBlank(message = "用户名不允许为空")
    private String acct;

    @NotNull(message = "角色不允许为空")
    private Long roleId;
}
