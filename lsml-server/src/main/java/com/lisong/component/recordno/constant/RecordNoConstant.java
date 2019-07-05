/*
 * 文件名称：RecordNoConstant.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20181222 14:33
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20181222-01         Rushing0711     M201812221433 新建文件
 ********************************************************************************/
package com.lisong.component.recordno.constant;

import lombok.Getter;

public interface RecordNoConstant {

    // 平台租户:10000   正常租户: (10000,90000)     备用租户:[90000,99999]      临时租户:(99999, +∞)

    /** 平台租户. */
    Long PLAT_TENANT = 10000L;

    /** 正常和特殊租户的临界值. */
    Long NORMAL_SPECIAL_BOUND_TENANT = 90000L;

    /** 非临时租户的最大租户ID. */
    Long MAX_TENANT = 99999L;

    @Getter
    enum RecordType {
        MAX_RECORD_NO(1, "[5-8]位max_record_no"),
        RECORD_NO_MAX_RECORD_NO(2, "3位table_no+[5-8]位max_record_no"),
        WORKDATE_MAX_RECORD_NO(3, "8位workdate+[5-8]位max_record_no"),
        RECORD_NO_WORKDATE_MAX_RECORD_NO(4, "3位table_no+8位workdate+[5-8]位max_record_no"),
        ;

        RecordType(Integer recordType, String description) {
            this.recordType = recordType;
            this.description = description;
        }

        private Integer recordType;

        private String description;

        public static RecordType getByRecordType(Integer type) {
            for (RecordType recordType : RecordType.class.getEnumConstants()) {
                if (recordType.recordType.equals(type)) {
                    return recordType;
                }
            }
            return RECORD_NO_WORKDATE_MAX_RECORD_NO;
        }

        @Override
        public String toString() {
            return "RecordType{"
                    + "recordType="
                    + recordType
                    + ", description='"
                    + description
                    + '\''
                    + '}';
        }
    }

    @Getter
    enum RecordLen {
        LEN_FIVE(5, 10000),
        LEN_SIX(6, 100000),
        LEN_SEVEN(7, 1000000),
        LEN_EIGHT(8, 10000000),
        ;

        RecordLen(Integer recordLen, Integer recordValue) {
            this.recordLen = recordLen;
            this.recordValue = recordValue;
        }

        private Integer recordLen;
        private Integer recordValue;

        public static RecordLen getByRecordLen(Integer len) {
            for (RecordLen recordLen : RecordLen.class.getEnumConstants()) {
                if (recordLen.recordLen.equals(len)) {
                    return recordLen;
                }
            }
            return LEN_EIGHT;
        }

        @Override
        public String toString() {
            return "RecordLen{" + "recordLen=" + recordLen + ", recordValue=" + recordValue + '}';
        }
    }

    @Getter
    enum RecordNameEn {
        TENANT("tenant", "租户编号"),
        SHOP("shop", "门店编号"),
        GOODS("supercard", "商品取票号"),
        USERINFO("userinfo", "用户编号"),
        CUSTOMER("customer", "会员编号"),
        ORDER("order", "订单编号."),
        GOODS_SPU_NO("goods_spu_no", "商品SPU编号"),
        GOODS_SKU_NO("goods_sku_no", "商品SKU编号"),
        ;

        RecordNameEn(String nameEn, String nameCn) {
            this.nameEn = nameEn;
            this.nameCn = nameCn;
        }

        private String nameEn;
        private String nameCn;
    }
}
