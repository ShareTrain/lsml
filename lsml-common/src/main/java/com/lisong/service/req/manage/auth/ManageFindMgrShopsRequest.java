package com.lisong.service.req.manage.auth;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.lisong.service.res.manage.auth.ManageFindMgrShopsResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManageFindMgrShopsRequest extends AppRequest<ManageFindMgrShopsResponse> {
    private static final long serialVersionUID = -5809635415547066520L;
    private String mgrShops;
}
