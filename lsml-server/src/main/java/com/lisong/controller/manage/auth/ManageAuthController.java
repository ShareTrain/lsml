package com.lisong.controller.manage.auth;

import com.lisong.common.cache.redis.annotation.ManageCache;
import com.lisong.service.api.manage.auth.ManageAuthService;
import com.lisong.service.req.manage.auth.*;
import com.lisong.service.res.manage.auth.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class ManageAuthController {

    @Autowired
    private ManageAuthService manageAuthService;

    @GetMapping(value = {"/lsml/manage/auth/loginPage", "/"})
    public String loginPage() {
        return "index/index";
    }

    @ResponseBody
    @PostMapping("/lsml/manage/auth/login")
    public ManageLoginResponse login(@RequestBody ManageLoginRequest request) {
        ManageLoginResponse response;
        log.info("【用户登录】账号={}", request.getAcct());
        response = manageAuthService.login(request);
        log.info("【用户登录】登录成功,账号={}", request.getAcct());
        return response;
    }

    @ResponseBody
    @PostMapping("/lsml/manage/auth/session")
    public ManageLoadSessionResponse loadSession(
            @RequestBody ManageLoadSessionRequest request,
            @ManageCache ManageSession manageSession) {
        request.setManageSession(manageSession);
        return manageAuthService.loadSession(request);
    }

    @ResponseBody
    @PostMapping("/lsml/manage/auth/permission")
    public ManageLoadPermissionResponse loginPermission(
            @RequestBody ManageLoadPermissionRequest request,
            @ManageCache ManageSession manageSession) {
        ManageLoadPermissionResponse response;
        request.setId(manageSession.getUserId());
        request.setAcct(manageSession.getAcct());
        request.setRoleId(manageSession.getRoleId());
        response = manageAuthService.loadPermission(request);
        return response;
    }

    @ResponseBody
    @PostMapping("/lsml/manage/auth/findMgrShops")
    public ManageFindMgrShopsResponse findMgrShops(
            @RequestBody ManageFindMgrShopsRequest request,
            @ManageCache ManageSession manageSession) {
        String mgrShops = manageSession.getMgrShops();
        request.setMgrShops(mgrShops);
        return manageAuthService.findMgrShops(request);
    }

    @ResponseBody
    @PostMapping("/lsml/manage/auth/logout")
    public ManageLogoutResponse logout(
            @RequestBody ManageLogoutRequest request, HttpServletRequest originRequest) {
        return manageAuthService.logout(request, originRequest);
    }
}
