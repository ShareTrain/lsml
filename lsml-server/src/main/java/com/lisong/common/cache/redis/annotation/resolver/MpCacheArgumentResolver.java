/*
 * 文件名称：MpCacheArgumentResolver.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190604 07:35
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190604-01         Rushing0711     M201906040735 新建文件
 ********************************************************************************/
package com.lisong.common.cache.redis.annotation.resolver;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.lisong.common.cache.redis.RedisCache;
import com.lisong.common.cache.redis.annotation.MpCache;
import com.lisong.component.jwt.JwtTokenContextHolder;
import com.lisong.exception.AppStatus;
import com.lisong.service.res.mp.auth.MpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class MpCacheArgumentResolver implements HandlerMethodArgumentResolver {

    private RedisCache redisCache;

    public MpCacheArgumentResolver(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    // 判断是否支持要转换的参数类型
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasMpCacheAnnotation = parameter.hasParameterAnnotation(MpCache.class);
        boolean isMpSession = parameter.getParameterType().isAssignableFrom(MpSession.class);
        return hasMpCacheAnnotation && isMpSession;
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory)
            throws Exception {
        MpSession mpSession = JwtTokenContextHolder.getMpContext().getMpSession();
        if (mpSession != null) {
            return mpSession;
        } else {
            log.error("【MpCacheArgumentResolver】mp登录信息丢失或尚未登录！");
            throw new AppException(AppStatus.AUTH_LOGIN_EXPIRED, "登录信息丢失或尚未登录！");
        }
    }
}
