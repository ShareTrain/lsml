package com.lisong.service.res.manage.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ManageSession implements Serializable {

    private static final long serialVersionUID = -1643161759108332240L;

    /** 【一定存在】用户ID. */
    private Long userId;

    /** 【一定存在】用户名称. */
    private String name;

    /** 用户手机号. */
    private String mobile;

    /** 【一定存在】用户管理的门店. */
    private String mgrShops;

    /** 【一定存在】用户的角色. */
    private Long roleId;

    /** 【一定存在】用户的账号. */
    private String acct;

    public String toLog() {
        return "ManageSession{" + "userId=" + userId + ", name='" + name + '\'' + '}';
    }
}
