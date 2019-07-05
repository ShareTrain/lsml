/*
 * 文件名称：RecordNoMgrServiceImpl.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20181107 00:00
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20181107-01         Rushing0711     M201811070000 新建文件
 ********************************************************************************/
package com.lisong.component.recordno.service.impl;

import com.coding.helpers.tool.cmp.generator.SnowFlakeIdGenerator;
import com.lisong.common.DictDefinition;
import com.lisong.component.recordno.DO.RecordNoMgr;
import com.lisong.component.recordno.constant.RecordNoConstant;
import com.lisong.component.recordno.repository.RecordNoMgrRepository;
import com.lisong.component.recordno.service.RecordNoMgrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RecordNoMgrServiceImpl implements RecordNoMgrService {

    @Autowired private RecordNoMgrRepository recordNoMgrRepository;

    private static final String EMPTY = "";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Transactional
    @Override
    public String getTenantRecordNo(Integer tenantType) {
        // 100000
        if (DictDefinition.TenantType.PLATFORM.equals(tenantType)) {
            log.error("【记录号获取】不允许添加平台级别的租户");
            throw new RuntimeException("不允许添加平台级别的租户");
        }
        // (10000, 90000)
        if (DictDefinition.TenantType.NORMAL.equals(tenantType)) {
            String tenantId =
                    getRecordNoByTableName(
                                    EMPTY, RecordNoConstant.RecordNameEn.TENANT.getNameEn(), 1)
                            .get(0);
            if (RecordNoConstant.NORMAL_SPECIAL_BOUND_TENANT <= Long.parseLong(tenantId)) {
                log.error("【记录号获取】租户超限, tenantId={}", tenantId);
                throw new RuntimeException(
                        String.format(
                                "表 %s 记录号获取失败！ 租户超限, tenantId=%s",
                                RecordNoConstant.RecordNameEn.TENANT.getNameEn(), tenantId));
            }
            return tenantId;
        }
        // [90000,99999]
        if (DictDefinition.TenantType.SPECIAL.equals(tenantType)) {
            log.error("【记录号获取】不允许添加特殊级别的租户");
            throw new RuntimeException("不允许添加特殊级别的租户");
        }
        // (99999, +∞) 无限的雪花ID
        if (DictDefinition.TenantType.TEMPORARY.equals(tenantType)) {
            return String.valueOf(SnowFlakeIdGenerator.getInstance().nextId());
        }

        log.error("【记录号获取】不支持的租户类型, tenantType={}", tenantType);
        throw new RuntimeException("不支持的租户类型");
    }

    @Transactional
    @Override
    public List<String> getBatchTenantRecordNo(Integer tenantType, Integer recordNoNum) {

        // 100000
        if (DictDefinition.TenantType.PLATFORM.equals(tenantType)) {
            log.error("【记录号获取】不允许添加平台级别的租户");
            throw new RuntimeException("不允许添加平台级别的租户");
        }
        // (10000, 90000)
        if (DictDefinition.TenantType.NORMAL.equals(tenantType)) {
            List<String> tenantIdList =
                    getRecordNoByTableName(
                            EMPTY, RecordNoConstant.RecordNameEn.TENANT.getNameEn(), recordNoNum);
            String tenantId = tenantIdList.get(tenantIdList.size() - 1);
            if (RecordNoConstant.NORMAL_SPECIAL_BOUND_TENANT <= Long.parseLong(tenantId)) {
                log.error("【记录号获取】租户超限, tenantId={}", tenantId);
                throw new RuntimeException(
                        String.format(
                                "表 %s 记录号获取失败！ 租户超限, tenantId=%s",
                                RecordNoConstant.RecordNameEn.TENANT.getNameEn(), tenantId));
            }
            return tenantIdList;
        }
        // [90000,99999]
        if (DictDefinition.TenantType.SPECIAL.equals(tenantType)) {
            log.error("【记录号获取】不允许添加特殊级别的租户");
            throw new RuntimeException("不允许添加特殊级别的租户");
        }
        // (99999, +∞) 无限的雪花ID
        if (DictDefinition.TenantType.TEMPORARY.equals(tenantType)) {
            if (recordNoNum <= 0 || recordNoNum > 1000) {
                log.error("【记录号获取】一次获取的记录号数量不合法，合法范围[1-1000]");
                throw new RuntimeException(
                        String.format(
                                "表 %s 记录号获取失败！", RecordNoConstant.RecordNameEn.TENANT.getNameEn()));
            }
            List<String> tenantIdList = new ArrayList<>();
            for (int i = 0; i < recordNoNum; i++) {
                tenantIdList.add(String.valueOf(SnowFlakeIdGenerator.getInstance().nextId()));
            }
            return tenantIdList;
        }

        log.error("【记录号获取】不支持的租户类型, tenantType={}", tenantType);
        throw new RuntimeException("不支持的租户类型");
    }

    private Integer getTenantType(String prefix) {
        try {
            Long tenantId = Long.parseLong(prefix);
            if (tenantId < RecordNoConstant.PLAT_TENANT) {
                log.error("【解析租户类型错误】租户不合法, tenantId={}", prefix);
                throw new RuntimeException("租户不合法");
            } else if (tenantId.equals(RecordNoConstant.PLAT_TENANT)) {
                return DictDefinition.TenantType.PLATFORM;
            } else if (RecordNoConstant.PLAT_TENANT < tenantId
                    && tenantId < RecordNoConstant.NORMAL_SPECIAL_BOUND_TENANT) {
                return DictDefinition.TenantType.NORMAL;
            } else if (RecordNoConstant.NORMAL_SPECIAL_BOUND_TENANT <= tenantId
                    && tenantId <= RecordNoConstant.MAX_TENANT) {
                return DictDefinition.TenantType.NORMAL;
            } else {
                return DictDefinition.TenantType.TEMPORARY;
            }
        } catch (NumberFormatException e) {
            log.error("【解析租户类型错误】解析租户类型错误, tenantId={}", prefix);
            throw new RuntimeException(String.format("解析租户类型错误, tenantId=%s", prefix));
        }
    }

    @Transactional
    @Override
    public String getShopRecordNo(String prefix) {
        Integer tenantType = getTenantType(prefix);
        if (DictDefinition.TenantType.TEMPORARY.equals(tenantType)) {
            return String.valueOf(SnowFlakeIdGenerator.getInstance().nextId());
        }
        return getRecordNoByTableName(
                        EMPTY, RecordNoConstant.RecordNameEn.SHOP.getNameEn().concat(prefix), 1)
                .get(0);
    }

    @Transactional
    @Override
    public List<String> getBatchShopRecordNo(String prefix, Integer recordNoNum) {
        Integer tenantType = getTenantType(prefix);
        if (DictDefinition.TenantType.TEMPORARY.equals(tenantType)) {
            if (recordNoNum <= 0 || recordNoNum > 1000) {
                log.error("【记录号获取】一次获取的记录号数量不合法，合法范围[1-1000]");
                throw new RuntimeException(
                        String.format(
                                "表 %s 记录号获取失败！",
                                RecordNoConstant.RecordNameEn.SHOP.getNameEn().concat(prefix)));
            }
            List<String> tenantIdList = new ArrayList<>();
            for (int i = 0; i < recordNoNum; i++) {
                tenantIdList.add(String.valueOf(SnowFlakeIdGenerator.getInstance().nextId()));
            }
            return tenantIdList;
        }
        return getRecordNoByTableName(
                EMPTY, RecordNoConstant.RecordNameEn.SHOP.getNameEn().concat(prefix), recordNoNum);
    }

    @Transactional
    @Override
    public String getGoodsRecordNo(String prefix) {
        return getRecordNoByTableName(prefix, RecordNoConstant.RecordNameEn.GOODS.getNameEn(), 1)
                .get(0);
    }

    @Transactional
    @Override
    public List<String> getBatchGoodsRecordNo(String prefix, Integer recordNoNum) {
        return getRecordNoByTableName(
                prefix, RecordNoConstant.RecordNameEn.GOODS.getNameEn(), recordNoNum);
    }

    @Transactional
    @Override
    public String getUserinfoRecordNo(String prefix) {
        Integer tenantType = getTenantType(prefix);
        if (DictDefinition.TenantType.TEMPORARY.equals(tenantType)) {
            return String.valueOf(SnowFlakeIdGenerator.getInstance().nextId());
        }
        return getRecordNoByTableName(
                        EMPTY, RecordNoConstant.RecordNameEn.USERINFO.getNameEn().concat(prefix), 1)
                .get(0);
    }

    @Transactional
    @Override
    public List<String> getBatchUserinfoRecordNo(String prefix, Integer recordNoNum) {
        Integer tenantType = getTenantType(prefix);
        if (DictDefinition.TenantType.TEMPORARY.equals(tenantType)) {
            if (recordNoNum <= 0 || recordNoNum > 1000) {
                log.error("【记录号获取】一次获取的记录号数量不合法，合法范围[1-1000]");
                throw new RuntimeException(
                        String.format(
                                "表 %s 记录号获取失败！",
                                RecordNoConstant.RecordNameEn.USERINFO.getNameEn().concat(prefix)));
            }
            List<String> userIdList = new ArrayList<>();
            for (int i = 0; i < recordNoNum; i++) {
                userIdList.add(String.valueOf(SnowFlakeIdGenerator.getInstance().nextId()));
            }
            return userIdList;
        }
        return getRecordNoByTableName(
                EMPTY,
                RecordNoConstant.RecordNameEn.USERINFO.getNameEn().concat(prefix),
                recordNoNum);
    }

    @Transactional
    @Override
    public String getCustomerRecordNo() {
        return getRecordNoByTableName(EMPTY, RecordNoConstant.RecordNameEn.CUSTOMER.getNameEn(), 1)
                .get(0);
    }

    @Transactional
    @Override
    public List<String> getBatchCustomerRecordNo(Integer recordNoNum) {
        return getRecordNoByTableName(
                EMPTY, RecordNoConstant.RecordNameEn.CUSTOMER.getNameEn(), recordNoNum);
    }

    @Transactional
    @Override
    public String getOrderRecordNo() {
        return getRecordNoByTableName(EMPTY, RecordNoConstant.RecordNameEn.ORDER.getNameEn(), 1)
                .get(0);
    }

    @Transactional
    @Override
    public List<String> getBatchOrderRecordNo(Integer recordNoNum) {
        return getRecordNoByTableName(
                EMPTY, RecordNoConstant.RecordNameEn.ORDER.getNameEn(), recordNoNum);
    }

    @Override
    public String getGoodsSpuNo() {
        return getRecordNoByTableName(
                        EMPTY, RecordNoConstant.RecordNameEn.GOODS_SPU_NO.getNameEn(), 1)
                .get(0);
    }

    @Override
    public List<String> getBatchGoodsSpuNo(Integer recordNoNum) {
        return getRecordNoByTableName(
                EMPTY, RecordNoConstant.RecordNameEn.GOODS_SPU_NO.getNameEn(), recordNoNum);
    }

    @Override
    public String getGoodsSkuNo(String prefix) {
        return getRecordNoByTableName(
                        prefix, RecordNoConstant.RecordNameEn.GOODS_SKU_NO.getNameEn(), 1)
                .get(0);
    }

    @Override
    public List<String> getBatchGoodsSkuNo(String prefix, Integer recordNoNum) {
        return getRecordNoByTableName(
                prefix, RecordNoConstant.RecordNameEn.GOODS_SKU_NO.getNameEn(), recordNoNum);
    }

    private List<String> getRecordNoByTableName(String prefix, String tableName, int recordNoNum) {
        // 悲观锁锁定数据库数据
        recordNoMgrRepository.lockRecord(tableName);

        RecordNoMgr recordNoMgr = recordNoMgrRepository.findByRecordNameEn(tableName);
        if (recordNoMgr == null) {
            log.error("【记录号获取】记录号生成管理表不存在名称为 {} 的表", tableName);
            throw new RuntimeException(String.format("表 %s 记录号获取失败！", tableName));
        }
        if (recordNoNum <= 0 || recordNoNum > 1000) {
            log.error("【记录号获取】一次获取的记录号数量不合法，合法范围[1-1000]");
            throw new RuntimeException(String.format("表 %s 记录号获取失败！", tableName));
        }
        if (recordNoNum + recordNoMgr.getMaxRecordNo() > Integer.MAX_VALUE) {
            log.error("【记录号获取】一次获取的记录号数量不合法，记录值超限");
            throw new RuntimeException(String.format("表 %s 记录号获取失败！", tableName));
        }

        RecordNoConstant.RecordLen recordLen =
                RecordNoConstant.RecordLen.getByRecordLen(recordNoMgr.getRecordLen());
        if (!recordLen.getRecordLen().equals(recordNoMgr.getRecordLen())) {
            recordNoMgr.setRecordLen(recordLen.getRecordLen());
        }

        RecordNoConstant.RecordType recordType =
                RecordNoConstant.RecordType.getByRecordType(recordNoMgr.getRecordType());
        if (!recordType.getRecordType().equals(recordNoMgr.getRecordType())) {
            recordNoMgr.setRecordType(recordType.getRecordType());
        }

        switch (recordType) {
            case MAX_RECORD_NO:
            case RECORD_NO_MAX_RECORD_NO:
                if (String.valueOf(recordNoMgr.getMaxRecordNo()).length()
                        != recordLen.getRecordLen()) {
                    recordNoMgr.setWorkdate(new Date());
                    recordNoMgr.setMaxRecordNo(recordLen.getRecordValue());
                }
                break;
            case WORKDATE_MAX_RECORD_NO:
            case RECORD_NO_WORKDATE_MAX_RECORD_NO:
                // 如果日期不是当前日期，则更新
                if (!new Date(recordNoMgr.getWorkdate().getTime())
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                        .equals(LocalDate.now())) {
                    recordNoMgr.setWorkdate(new Date());
                    recordNoMgr.setMaxRecordNo(0);
                }
                break;
            default:
        }

        // 当前值
        Integer currentMaxRecordNo = recordNoMgr.getMaxRecordNo();

        // 更新并获取
        recordNoMgr.setMaxRecordNo(recordNoMgr.getMaxRecordNo() + recordNoNum);
        recordNoMgrRepository.save(recordNoMgr);

        prefix = StringUtils.isEmpty(prefix) ? EMPTY : StringUtils.trimWhitespace(prefix);

        List<String> recordNoList = new ArrayList<>();
        StringBuilder recordNo = new StringBuilder();
        for (int i = 1; i <= recordNoNum; i++) {
            recordNo.delete(0, recordNo.length());
            recordNo.append(prefix);
            switch (recordType) {
                case MAX_RECORD_NO:
                    break;
                case RECORD_NO_MAX_RECORD_NO:
                    recordNo.append(recordNoMgr.getRecordNo());
                    break;
                case WORKDATE_MAX_RECORD_NO:
                    recordNo.append(LocalDate.now().format(formatter));
                    break;
                case RECORD_NO_WORKDATE_MAX_RECORD_NO:
                    recordNo.append(recordNoMgr.getRecordNo());
                    recordNo.append(LocalDate.now().format(formatter));
                    break;
                default:
            }
            recordNo.append(integerToString(++currentMaxRecordNo, recordLen.getRecordLen()));
            recordNoList.add(recordNo.toString());
        }

        log.info(
                "【记录号获取】表 {} 记录号 {}",
                tableName,
                Arrays.toString(recordNoList.toArray(new String[] {})));

        return recordNoList;
    }

    private String integerToString(Integer number, int len) {
        StringBuilder sb = new StringBuilder();
        String temp = Long.toString(number);
        int templen = temp.length();
        if (templen < len) {
            for (int i = 0; i < len - templen; i++) {
                sb.append(0);
            }
            sb.append(temp);
        } else {
            sb.append(temp.substring(templen - len));
        }
        return sb.toString();
    }
}
