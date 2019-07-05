package com.lisong.component.jwt;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public final class JwtTokenModel {

    private String username;

    private Date lastPasswordResetDate;
}
