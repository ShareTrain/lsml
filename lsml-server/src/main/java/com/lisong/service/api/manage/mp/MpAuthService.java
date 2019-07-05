package com.lisong.service.api.manage.mp;

import com.lisong.service.req.mp.auth.MpLoadSessionRequest;
import com.lisong.service.req.mp.auth.MpLoginRequest;
import com.lisong.service.res.mp.auth.MpLoadSessionResponse;
import com.lisong.service.res.mp.auth.MpLoginResponse;
import com.lisong.service.res.mp.auth.MpSession;

public interface MpAuthService {

    /** 登录. */
    MpLoginResponse login(MpLoginRequest request);

    /** 加载权限. */
    //    MpLoadPermissionResponse loadPermission(MpLoadPermissionRequest request);

    /** 加载客户信息. */
    MpLoadSessionResponse loadSession(MpLoadSessionRequest request);

    /** 内部加载客户信息. */
    MpSession loadSession(String custId);
}
