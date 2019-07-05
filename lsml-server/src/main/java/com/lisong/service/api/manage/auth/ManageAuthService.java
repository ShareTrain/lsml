package com.lisong.service.api.manage.auth;

import com.lisong.service.req.manage.auth.*;
import com.lisong.service.res.manage.auth.*;

import javax.servlet.http.HttpServletRequest;

public interface ManageAuthService {

    /** 登录校验. */
    ManageLoginResponse login(ManageLoginRequest request);

    /** 加载用户缓存. */
    ManageLoadSessionResponse loadSession(ManageLoadSessionRequest request);

    /** 加载用户权限. */
    ManageLoadPermissionResponse loadPermission(ManageLoadPermissionRequest request);

    /** 根据用户获取管理的门店列表. */
    ManageFindMgrShopsResponse findMgrShops(ManageFindMgrShopsRequest request);

    /** 内部加载客户信息. */
    ManageSession loadSession(String userId);

    ManageLogoutResponse logout(ManageLogoutRequest request, HttpServletRequest originRequest);
}
