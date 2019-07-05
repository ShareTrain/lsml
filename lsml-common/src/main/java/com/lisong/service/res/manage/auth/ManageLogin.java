package com.lisong.service.res.manage.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ManageLogin implements Serializable {
    private static final long serialVersionUID = 1906082578900405272L;

    /** 接口调用令牌. */
    private String token;
}
