package com.lisong.service.req.manage.auth;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.lisong.service.res.manage.auth.ManageLogoutResponse;
import com.lisong.service.res.manage.auth.ManageSession;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManageLogoutRequest extends AppRequest<ManageLogoutResponse> {
    private static final long serialVersionUID = -8936463553032823574L;

    private ManageSession manageSession;
}
