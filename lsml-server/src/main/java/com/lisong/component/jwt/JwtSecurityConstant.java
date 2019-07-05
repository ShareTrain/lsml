package com.lisong.component.jwt;

public interface JwtSecurityConstant {

    String delimiter = ":";

    String EMPTY = "";

    String ADMIN = "admin";

    String ROLE_LOGIN = "ROLE_LOGIN";

    String PLATFORM_URL_PATTERN = "/platform/env/**";

    String PLATFORM_URL_PATTERN_IGNORE = "/platform/env/ignore/**";

    String PLATFORM_TOKEN_PARAMETER = "platformToken";

    String ROLE_PLATFORM = "ROLE_PLATFORM";

    String ROLE_PLATFORM_TOKEN_ERROR = "ROLE_PLATFORM_TOKEN_ERROR";

    String PLATFORM_TOKEN_DECODER =
            "FCK6E+lPCZZWfQ7QACm8OGfEE/Vr8Qwn7gtiAgXcE47hts2N5217m8qB9M3cHSyA5rTkN2j5kPbJ\nQvvD2mcH5nP9kRumT/SrCsO1vlGtd6c2THs0Wh81LzGqF7Z4cqiuc7nkKDZh0f411ZeyoPMzEwLO\nvIT1UrdH4tmiIh0aCJk=";

    String WEBSOCKET_URL_PATTERN = "/websocket/**";

    /** 用户被强制登出. */
    String ROLE_FORCED_LOGOUT = "ROLE_FORCED_LOGOUT";
}
