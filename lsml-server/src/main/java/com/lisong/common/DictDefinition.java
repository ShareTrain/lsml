/*
 * 文件名称：Dicts.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190309 19:59
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190309-01         Rushing0711     M201903091959 新建文件
 ********************************************************************************/
package com.lisong.common;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.lisong.exception.AppStatus;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定义字典.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190309 19:59</font><br>
 * 有两种定义形式：1、接口；2、枚举。如果需要校验数据是否合法，推荐枚举的定义方式。
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public interface DictDefinition {

    Logger log = LoggerFactory.getLogger(DictDefinition.class);

    interface BaseEnum<T> {
        T getValue();
    }

    /** 根据String值获取枚举实例，如果找不到则返回null. */
    static <T extends BaseEnum> T getByValue(Class<T> enumClazz, String value) {
        for (T each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        return null;
    }

    /** 根据Integer值获取枚举实例，如果找不到则返回null. */
    static <T extends BaseEnum> T getByValue(Class<T> enumClazz, Integer value) {
        for (T each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        return null;
    }

    /** 根据String值获取枚举实例，如果找不到则抛异常. */
    static <T extends BaseEnum> T getByValueNoisy(Class<T> enumClazz, String value) {
        for (T each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        log.error("【字典查询】根据字典值找不到对应字典, enumClazz={}, value={}", enumClazz, value);
        throw new AppException(AppStatus.OBJECT_NOT_EXIST);
    }

    /** 根据Integer值获取枚举实例，如果找不到则抛异常. */
    static <T extends BaseEnum> T getByValueNoisy(Class<T> enumClazz, Integer value) {
        for (T each : enumClazz.getEnumConstants()) {
            if (each.getValue().equals(value)) {
                return each;
            }
        }
        log.error("【字典查询】根据字典值找不到对应字典, enumClazz={}, value={}", enumClazz, value);
        throw new AppException(AppStatus.OBJECT_NOT_EXIST);
    }

    // ==================================================华丽的分割线==================================================

    /** 【代码和数据库】数据是否已删除，1-已删除； 0-未删除 */
    interface Deleted {
        String NAME = "deleted";
        /** 已删除. */
        Integer YES = 1;
        /** 未删除. */
        Integer NO = 0;
    }

    /** 【仅定义在代码】是与否；1-是;0-否. */
    interface YesOrNo {
        String NAME = "yes_or_no";
        /** 是. */
        Integer YES = 1;
        /** 否. */
        Integer NO = 0;
    }

    /** 【代码和数据库】是否启用系统参数 */
    interface Enabled {
        String NAME = "enabled";
        /** 启用 */
        Integer ENABLED = 1;
        /** 停用 */
        Integer DISABLED = 0;
    }

    /** 【仅定义在代码】商品上下架状态. */
    @Getter
    @RequiredArgsConstructor
    enum ProductStatus implements BaseEnum<Integer> {
        /** 在架. */
        UP(0),
        /** 下架. */
        DOWN(1),
        ;
        public static final String NAME = "product_status";
        @NonNull private Integer value;
    }

    /** 【仅定义在代码】租户类型. */
    interface TenantType {
        String NAME = "tenant_type";
        /** 平台级别的租户（系统只有一个）. */
        Integer PLATFORM = 0;

        /** 正常租户（真实入住的租户）. */
        Integer NORMAL = 1;

        /** 特殊租户（比如内部测试租户等特殊用途）. */
        Integer SPECIAL = 2;

        /** 临时租户（比如试用租户）. */
        Integer TEMPORARY = 9;
    }

    /** 【仅定义在代码】审核状态. */
    enum MakeCardType implements BaseEnum<Integer> {
        /** 畅玩卡. */
        AUDITING(1, "CW"),
        /** 畅学卡. */
        PASSED(2, "CX"),
        ;

        public static String NAME = "make_card_type";

        MakeCardType(Integer value, String code) {
            this.value = value;
            this.code = code;
        }

        private Integer value;

        private String code;

        @Override
        public Integer getValue() {
            return value;
        }

        public String getCode() {
            return code;
        }
    }

    /** 【代码和数据库】门店类型. */
    enum ShopType implements BaseEnum<Integer> {
        /** 平台门店. */
        PLAT_SHOP(0),
        /** 总部门店. */
        HQ_SHOP(1),
        /** 普通门店. */
        NORMAL_SHOP(2),
        ;

        public static String NAME = "shop_type";

        ShopType(Integer value) {
            this.value = value;
        }

        private Integer value;

        @Override
        public Integer getValue() {
            return value;
        }
    }

    /** 【代码和数据库】分销商级别 */
    interface CustomerLevel {
        String NAME = "customer_level";
        /** 非分销商 */
        Integer DEFAULT = 0;
        /** 掌柜 */
        Integer SHOPKEEPER = 1;
        /** 主管 */
        Integer DIRECTOR = 2;
        /** 经理 */
        Integer MANAGER = 3;
    }
}
