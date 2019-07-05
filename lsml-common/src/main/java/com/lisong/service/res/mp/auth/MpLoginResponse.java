package com.lisong.service.res.mp.auth;

import com.coding.helpers.tool.cmp.api.AppResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MpLoginResponse extends AppResponse {
    private static final long serialVersionUID = -4655154612005582023L;

    private String token;
}
