/*
 * 文件名称：StatusDefinition.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190318 06:46
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190318-01         Rushing0711     M201903180646 新建文件
 ********************************************************************************/
package com.lisong.exception;

import com.coding.helpers.tool.cmp.exception.AppBaseStatus;
import lombok.Getter;

/**
 * 异常状态的定义.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190318 06:49</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
public enum AppStatus implements AppBaseStatus {
    PARAM_ERROR(1010000, "请求参数错误"),
    PARAM_EMPTY(1011000, "请求参数为空"),
    PARAM_TYPE_ERROR(1012000, "请求参数类型错误"),
    PARAM_OVER_BOUND(1013000, "请求参数范围超限"),
    PARAM_FORMAT_ERROR(1014000, "请求参数格式不正确"),

    AUTH_ERROR(1020000, "授权错误"),
    AUTH_UNAUTHENTICATION(1021000, "未认证！"),
    AUTH_USERNAME_PASSWORD_ERROR(1022000, "用户名或密码错误"),
    AUTH_USERNAME_ERROR(1022001, "用户不存在"),
    AUTH_PASSWORD_ERROR(1022002, "密码错误"),
    // 账号封停、过期、锁定等
    AUTH_PERMISSION_ERROR(1023000, "登录权限控制"),
    AUTH_USERNAME_DISABLED(1023001, "账号封停"),
    AUTH_USERNAME_LOCKED(1023002, "账号锁定"),
    AUTH_USERNAME_EXPIRED(1023003, "账号已过期"),
    AUTH_ACCOUNT_UN_ACTIVATIOIN(1023004, "用户账号未激活"),
    AUTH_LOGIN_EXPIRED(1024000, "登录信息超时"),
    AUTH_PERMISSION_FORBIDDEN(1025000, "用户权限不足"),

    // 一般指在Service中发生的涉及业务逻辑校验的错误，比如对象不存在、已存在、序列化/反序列化异常、格式转换异常、类型转换异常等
    SERVER_ERROR(1030000, "服务端错误"),
    // 根据检索条件查询不到记录等
    OBJECT_NOT_EXIST(1031000, "对象不存在"),
    // 序列化/反序列化异常、格式转换异常、类型转换异常等
    OBJECT_CONVERT_ERROR(1032000, "对象转换异常"),
    FROM_JSON_ERRPR(1032001, "JSON转换到对象错误"),
    TO_JSON_ERRPR(1032002, "对象转换到JSON错误"),
    // 对象已存在、重复等等
    OBJECT_EXIST(1033000, "对象已存在"),
    // 逻辑校验错误，指代那些在请求校验正常的参数，在实际业务处理时发生的逻辑错误
    BUSINESS_CHECK_ERROR(1034000, "业务检查异常"),
    // 调用内部服务错误，非远程错误
    INNER_SERVER_ERROR(1035000, "调用内部服务错误"),

    OUTER_SERVER_ERROR(1080000, "上传下载短信等外部服务错误"),
    SMS_ERROR(1081000, "短信错误"),
    SMS_VERIFY_CODE_ERROR(1081001, "验证码输入错误"),
    SMS_VERIFY_CODE_EXPIRE(1081002, "验证码已过期"),

    VERSION_ERROR(1090000, "版本错误"),

    SUCCESS(9000, "成功"),

    SYSTEM_UNEXPECTED_ERROR(1099999, "系统意料之外的异常"),
    ;

    /**
     * 错误码设定由3部分组成：2位(10-99)系统编号 + 2位(00-99)系统模块编号或者错误类型编号 + 3位自定义编号.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190327 16:33</font><br>
     * 错误码第2部分，如果用来表示错误类型编号，可以定义如下：<br>
     *
     * <ul>
     *   <li>XX10000-普通校验错误，在BindingResult判断的错误，该错误包含XX1X表示的错误
     *   <li>XX11XXX-请求参数为空
     *   <li>XX12XXX-请求参数类型错误
     *   <li>XX13XXX-请求参数范围超限
     *   <li>XX14XXX-请求参数格式不正确（比如手机号的校验）
     *   <li>XX20000-统称登录校验错误
     *   <li>XX21000-未登录、未认证、401:unauthorized
     *   <li>XX22000-登录信息不正确（用户名密码等）
     *   <li>XX22001-用户不存在
     *   <li>XX22002-密码错误
     *   <li>XX23000-登录权限控制（账号封停、过期、锁定等）
     *   <li>XX23001-账号封停
     *   <li>XX23002-账号锁定
     *   <li>XX23003-账号已过期
     *   <li>XX24000-登录信息超时
     *   <li>XX25000-用户权限不足，403:forbidden
     *   <li>XX300000-服务端错误（一般指在Service中发生的涉及业务逻辑校验的错误，比如对象不存在、已存在、序列化/反序列化异常、格式转换异常、类型转换异常等）
     *   <li>XX31XXX-对象不存在（根据检索条件查询不到记录等）
     *   <li>XX32XXX-序列化/反序列化异常、格式转换异常、类型转换异常等
     *   <li>XX33XXX-对象已存在、重复等等
     *   <li>XX34XXX-逻辑校验错误，指代那些在请求校验正常的参数，在实际业务处理时发生的逻辑错误
     *   <li>XX35XXX-调用内部服务错误，非远程错误
     *   <li>XX80XXX-上传下载短信等外部服务错误
     *   <li>XX99XXX版本错误
     * </ul>
     *
     * @return java.lang.Integer
     * @author Rushing0711
     * @since 1.0.0
     */
    private Integer errorCode;
    private String errorMessage;

    AppStatus(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
