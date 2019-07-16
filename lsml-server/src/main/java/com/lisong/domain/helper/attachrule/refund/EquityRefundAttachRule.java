package com.lisong.domain.helper.attachrule.refund;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.lisong.common.DictDefinition;
import com.lisong.common.RegexDefine;
import com.lisong.domain.helper.attachrule.BaseAttachInfo;
import com.lisong.domain.helper.attachrule.BaseAttachRule;
import com.lisong.exception.AppStatus;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@Slf4j
public class EquityRefundAttachRule extends BaseAttachRule {

    private static final long serialVersionUID = -4361293098937298882L;

    private FormInfo formInfo;

    private SpuInfo spuInfo;

    @Getter
    @Builder
    public static class FormInfo extends BaseAttachInfo {
        private static final long serialVersionUID = 7436552454161458598L;

        @Override
        public FormInfo validate() {
            return this;
        }
    }

    @Getter
    @ToString
    @Builder
    public static class SpuInfo extends BaseAttachInfo {
        private static final long serialVersionUID = -8335386251667485682L;
        /** 是否允许用户主动退款；1-是；0-否. */
        private Integer allowCustRefund;

        /** 允许用户主动退款的阶梯退款比例. */
        @Singular private List<Section> sections;

        /** 退款等待天数：默认值3天. */
        private Integer refundWaitDay = 3;

        public static Section build(int geHour, int ltHour, double refundRate) {
            return new Section(geHour, ltHour, refundRate);
        }

        @Data
        public static class Section implements Serializable {
            private static final long serialVersionUID = 1927306451304071065L;
            /** 大于等于. */
            private int geHour;

            /** 小于. */
            private int ltHour;

            /** 退款比例. */
            private double refundRate;

            private Section(Integer geHour, Integer ltHour, double refundRate) {
                this.geHour = geHour;
                this.ltHour = ltHour;
                this.refundRate = refundRate;
            }
        }

        @Override
        public SpuInfo validate() {
            if (refundWaitDay < 0) {
                log.error(
                        "【EquityRefundAttachRule】refundWaitDay不允许小于0, refundWaitDay={}",
                        refundWaitDay);
                throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "refundWaitDay不允许小于0");
            }
            this.allowCustRefund =
                    DictDefinition.YesOrNo.YES.equals(allowCustRefund)
                            ? DictDefinition.YesOrNo.YES
                            : DictDefinition.YesOrNo.NO;
            if (DictDefinition.YesOrNo.YES.equals(allowCustRefund)) {
                if (CollectionUtils.isEmpty(sections)) {
                    log.error(
                            "【EquityRefundAttachRule】sections未设置, allowCustRefund={}, sections={}",
                            allowCustRefund,
                            sections);
                    throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "sections未设置");
                }
                for (Section section : sections) {
                    if (section.getGeHour() < 0
                            || section.getLtHour() < 0
                            || !RegexDefine.NON_NEGATIVE_LESS_EQUAL_1_FLOATING_3_POINT_REGEX_PATTERN
                                    .matcher(String.valueOf(section.getRefundRate()))
                                    .matches()) {
                        log.error(
                                "【EquityRefundAttachRule】sections不合法, allowCustRefund={}, sections={}",
                                allowCustRefund,
                                sections);
                        throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "sections不合法");
                    }
                    for (Section other : sections) {
                        if (section.equals(other)) {
                            continue;
                        }
                        // 如果存在区间交叉，抛出异常
                        if (Math.max(section.geHour, other.geHour)
                                < Math.min(section.ltHour, other.ltHour)) {
                            log.error(
                                    "【EquityRefundAttachRule】sections不合法, allowCustRefund={}, sections={}",
                                    allowCustRefund,
                                    sections);
                            throw new AppException(
                                    AppStatus.BUSINESS_CHECK_ERROR, "sections不合法");
                        }
                    }
                }
            }
            return this;
        }
    }
}
