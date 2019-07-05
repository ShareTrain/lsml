package com.lisong.service.res.mp.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MpLogin implements Serializable {
    private static final long serialVersionUID = -7785322827656031006L;

    /** 接口调用令牌. */
    private String token;

    /** 会话密钥. */
    private String sessionKey;
}
