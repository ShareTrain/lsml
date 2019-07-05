package com.lisong.component.jwt;

import com.lisong.component.jwt.context.ManageTokenContext;
import com.lisong.component.jwt.context.MpTokenContext;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;

public class JwtTokenContextHolder {

    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    public static final String MP_PATH_PATTERN = "/spread/mp/**";

    public static final String MANAGE_PATH_PATTERN = "/spread/manage/**";

    public static boolean isMpRequest(String url) {
        return antPathMatcher.match(MP_PATH_PATTERN, url);
    }

    public static boolean isManageRequest(String url) {
        return antPathMatcher.match(MANAGE_PATH_PATTERN, url);
    }

    private static final ThreadLocal<MpTokenContext> mpContextHolder =
            new InheritableThreadLocal<>();

    public static void clearMpContext() {
        mpContextHolder.remove();
    }

    public static MpTokenContext getMpContext() {
        MpTokenContext ctx = mpContextHolder.get();

        if (ctx == null) {
            ctx = createEmptyMpContext();
            mpContextHolder.set(ctx);
        }

        return ctx;
    }

    public static void setMpContext(MpTokenContext context) {
        Assert.notNull(context, "Only non-null MpTokenContext instances are permitted");
        mpContextHolder.set(context);
    }

    private static MpTokenContext createEmptyMpContext() {
        return new MpTokenContext();
    }

    // ============================================================

    private static final ThreadLocal<ManageTokenContext> manageContextHolder =
            new InheritableThreadLocal<>();

    public static void clearManageContext() {
        manageContextHolder.remove();
    }

    public static ManageTokenContext getManageContext() {
        ManageTokenContext ctx = manageContextHolder.get();

        if (ctx == null) {
            ctx = createEmptyManageContext();
            manageContextHolder.set(ctx);
        }

        return ctx;
    }

    public static void setManageContext(ManageTokenContext context) {
        Assert.notNull(context, "Only non-null ManageTokenContext instances are permitted");
        manageContextHolder.set(context);
    }

    private static ManageTokenContext createEmptyManageContext() {
        return new ManageTokenContext();
    }
}
