package com.lisong.component.sms.res;

import com.coding.helpers.tool.cmp.api.AppResponse;
import lombok.Getter;
import lombok.Setter;

/** Created by Alex on 2018/11/23 */
@Getter
@Setter
public class CheckVerifyCodeResponse extends AppResponse {

    private static final long serialVersionUID = 4849411735179397382L;

    /** 校验是否通过 */
    private boolean success = false;
}
