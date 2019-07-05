package com.lisong.service.res.manage.auth;

import com.coding.helpers.tool.cmp.api.AppResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManageLoginResponse extends AppResponse {

    private static final long serialVersionUID = -1526172344989895021L;

    private String token;
}
