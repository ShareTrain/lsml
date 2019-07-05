/*
 * 文件名称：RedisKeyCapturer.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20181112 09:51
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20181112-01         Rushing0711     M201811120951 新建文件
 ********************************************************************************/
package com.lisong.common.cache.redis.annotation;

import com.lisong.common.cache.redis.AppRedisKey;

import java.lang.annotation.*;

/**
 * 缘起于高韩.
 *
 * <p>创建时间: <font style="color:#00FFFF">20181112 09:51</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisKeyCapturer {

    String value();

    AppRedisKey prefix();
}
