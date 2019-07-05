package com.lisong.service.req.manage.auth;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.lisong.service.res.manage.auth.ManageLoadSessionResponse;
import com.lisong.service.res.manage.auth.ManageSession;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManageLoadSessionRequest extends AppRequest<ManageLoadSessionResponse> {
    private static final long serialVersionUID = -2203951149544685700L;

    private ManageSession manageSession;
}
