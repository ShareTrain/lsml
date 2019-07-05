package com.lisong.component.sms.req;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.lisong.component.sms.res.MatchVerifyCodeResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchVerifyCodeRequest extends AppRequest<MatchVerifyCodeResponse> {

    private static final long serialVersionUID = -1543617062182406334L;

    private String mobile;

    private String verifyCode;
}
