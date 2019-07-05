/*
 * 文件名称：BeanUtilsHelper.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20180803 17:19
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180803-01         Rushing0711     M201808031719 新建文件
 ********************************************************************************/
package com.lisong.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * BeanUtils扩展方法.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180803 17:22</font><br>
 * 提供忽略null属性的拷贝
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            if (src.isReadableProperty(pd.getName())) {
                Object srcValue = src.getPropertyValue(pd.getName());
                if (srcValue == null) emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
