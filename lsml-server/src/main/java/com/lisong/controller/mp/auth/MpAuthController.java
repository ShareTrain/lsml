package com.lisong.controller.mp.auth;

import com.lisong.common.cache.redis.annotation.MpCache;
import com.lisong.service.api.manage.mp.MpAuthService;
import com.lisong.service.req.mp.auth.MpLoadSessionRequest;
import com.lisong.service.req.mp.auth.MpLoginRequest;
import com.lisong.service.res.mp.auth.MpLoadSessionResponse;
import com.lisong.service.res.mp.auth.MpLoginResponse;
import com.lisong.service.res.mp.auth.MpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小程序授权相关接口
 *
 * @author wtf
 */
@RestController
@Slf4j
public class MpAuthController {
    @Autowired private MpAuthService mpAuthService;

    @PostMapping("/lsml/mp/auth/login")
    public MpLoginResponse login(@RequestBody MpLoginRequest request) {
        return mpAuthService.login(request);
    }

    @PostMapping("/lsml/mp/auth/session")
    public MpLoadSessionResponse loadSession(
            @MpCache MpSession mpSession, @RequestBody MpLoadSessionRequest request) {
        request.setMpSession(mpSession);
        return mpAuthService.loadSession(request);
    }
}
