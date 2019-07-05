package com.lisong.common.webconfig;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.lisong.util.UrlUtils;
import com.lisong.component.jwt.JwtSecurityConstant;
import com.lisong.component.jwt.JwtTokenContextHolder;
import com.lisong.component.jwt.JwtTokenUtil;
import com.lisong.component.sms.SmsService;
import com.lisong.component.sms.req.CheckVerifyCodeRequest;
import com.lisong.exception.AppStatus;
import com.lisong.service.res.manage.auth.ManageSession;
import com.lisong.service.res.mp.auth.MpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SmsService smsService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = UrlUtils.buildRequestUrl(request);
        if (antPathMatcher.match(JwtSecurityConstant.PLATFORM_URL_PATTERN, url)) {
            if (antPathMatcher.match(JwtSecurityConstant.PLATFORM_URL_PATTERN_IGNORE, url)) {
                return super.preHandle(request, response, handler);
            } else {
                String plantformToken =
                        request.getParameter(JwtSecurityConstant.PLATFORM_TOKEN_PARAMETER);
                if (StringUtils.isEmpty(plantformToken)) {
                    log.error("【SecurityInterceptor】口令缺失！");
                    throw new AppException(AppStatus.AUTH_UNAUTHENTICATION, "口令缺失！");
                }
                if (!checkPlatformToken(plantformToken)) {
                    log.error("【SecurityInterceptor】口令校验失败！");
                    throw new AppException(AppStatus.AUTH_UNAUTHENTICATION, "口令校验失败！");
                }
                return super.preHandle(request, response, handler);
            }
        }

        if (JwtTokenContextHolder.isManageRequest(url)) {
            ManageSession manageSession =
                    JwtTokenContextHolder.getManageContext().getManageSession();
            if (manageSession == null) {
                log.error("【SecurityInterceptor】manage接口请求未认证！");
                throw new AppException(AppStatus.AUTH_UNAUTHENTICATION, "未认证！");
            }
        } else if (JwtTokenContextHolder.isMpRequest(url)) {
            MpSession mpSession = JwtTokenContextHolder.getMpContext().getMpSession();
            if (mpSession == null) {
                log.error("【SecurityInterceptor】mp接口请求未认证！");
                throw new AppException(AppStatus.AUTH_UNAUTHENTICATION, "未认证！");
            }
        } else {
            /*String httpMethod = request.getMethod();
            if ("/error".equals(url)) {
                log.error(
                        "【SecurityInterceptor】请求接口不存在，请检查！ url={}, httpMethod={}", url, httpMethod);
                throw new AppException(
                        AppStatus.AUTH_ERROR,
                        "请求接口不存在，请检查！url=[" + url + "], httpMethod=[" + httpMethod + "]");
            } else {
                log.error(
                        "【SecurityInterceptor】请求接口存在，但不合法，请检查！ url={}, httpMethod={}",
                        url,
                        httpMethod);
                throw new AppException(
                        AppStatus.AUTH_ERROR,
                        "请求接口不合法，请检查！url=[" + url + "], httpMethod=[" + httpMethod + "]");
            }*/
        }
        return super.preHandle(request, response, handler);
    }

    private boolean checkPlatformToken(String platformToken) {
        CheckVerifyCodeRequest request = new CheckVerifyCodeRequest();
        String mobile;
        try {
            mobile = JwtTokenUtil.rsaDecrypt(JwtSecurityConstant.PLATFORM_TOKEN_DECODER);
        } catch (Exception e) {
            log.error("【调用平台服务】验证码归属的手机号解析错误", e);
            return false;
        }
        request.setMobile(mobile);
        request.setNowVerifyCode(platformToken);

        try {
            smsService.checkVerifyCode(request);
        } catch (Exception e) {
            log.error("【调用平台服务】验证码校验错误", e);
            return false;
        }
        return true;
    }
}
