package com.lisong.component.jwt;

import com.lisong.common.cache.redis.AppRedisKey;
import com.lisong.util.UrlUtils;
import com.lisong.service.api.manage.auth.ManageAuthService;
import com.lisong.service.api.manage.mp.MpAuthService;
import com.lisong.service.res.manage.auth.ManageSession;
import com.lisong.service.res.mp.auth.MpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired private JwtTokenUtil jwtTokenUtil;

    @Autowired private ManageAuthService manageAuthService;

    @Autowired private MpAuthService mpAuthService;

    @Autowired private StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String url = UrlUtils.buildRequestUrl(request);
        try {
            String authToken = jwtTokenUtil.fetchToken(request);
            if (!StringUtils.isEmpty(authToken)) {
                String username = jwtTokenUtil.getUsernameFromToken(authToken);
                logger.info("checking authentication " + username);
                if (username != null) {
                    if (JwtTokenContextHolder.isManageRequest(url)) {
                        // Redis中是否还存在（比如登出删除/过期丢弃等）
                        String redisKey = AppRedisKey.MANAGE_USER_TOKEN.getKey(username);
                        boolean existAuthToken =
                                stringRedisTemplate.opsForValue().getOperations().hasKey(redisKey);
                        if (existAuthToken
                                && JwtTokenContextHolder.getManageContext().getManageSession()
                                        == null) {
                            ManageSession manageSession = manageAuthService.loadSession(username);
                            JwtTokenContextHolder.getManageContext()
                                    .setManageSession(manageSession);
                            logger.info(
                                    "authenticated manage user "
                                            + username
                                            + ", setting manage token context");
                        }
                    } else if (JwtTokenContextHolder.isMpRequest(url)) {
                        // Redis中是否还存在（比如登出删除/过期丢弃等）
                        String redisKey = AppRedisKey.MP_CUST_TOKEN.getKey(username);
                        boolean existAuthToken =
                                stringRedisTemplate.opsForValue().getOperations().hasKey(redisKey);
                        if (existAuthToken
                                && JwtTokenContextHolder.getMpContext().getMpSession() == null) {
                            MpSession mpSession = mpAuthService.loadSession(username);
                            JwtTokenContextHolder.getMpContext().setMpSession(mpSession);
                            logger.info(
                                    "authenticated mp customer "
                                            + username
                                            + ", setting mp token context");
                        }
                    }
                }
            }
            chain.doFilter(request, response);
        } finally {
            if (JwtTokenContextHolder.isManageRequest(url)) {
                JwtTokenContextHolder.clearManageContext();
            } else if (JwtTokenContextHolder.isMpRequest(url)) {
                JwtTokenContextHolder.clearMpContext();
            }
        }
    }
}
