package com.lisong.service.req.mp.auth;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.lisong.service.res.mp.auth.MpLoadSessionResponse;
import com.lisong.service.res.mp.auth.MpSession;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MpLoadSessionRequest extends AppRequest<MpLoadSessionResponse> {

    private static final long serialVersionUID = -8303921012376984369L;

    private MpSession mpSession;
}
