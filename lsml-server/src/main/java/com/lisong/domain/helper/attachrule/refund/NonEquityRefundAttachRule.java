package com.lisong.domain.helper.attachrule.refund;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.lisong.common.DictDefinition;
import com.lisong.domain.helper.attachrule.BaseAttachInfo;
import com.lisong.domain.helper.attachrule.BaseAttachRule;
import com.lisong.exception.AppStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Builder
@Slf4j
public class NonEquityRefundAttachRule extends BaseAttachRule {

    private static final long serialVersionUID = -3268374229715632359L;
    private FormInfo formInfo;

    private SpuInfo spuInfo;

    @Getter
    @Builder
    public static class FormInfo extends BaseAttachInfo {
        private static final long serialVersionUID = -2575773134950504821L;

        @Override
        public FormInfo validate() {
            return this;
        }
    }

    @Getter
    @Builder
    public static class SpuInfo extends BaseAttachInfo {
        private static final long serialVersionUID = -1928975851936801587L;
        /** 是否允许用户主动退款；1-是；0-否. */
        private Integer allowCustRefund;

        /** 退款等待天数：默认值3天. */
        private Integer refundWaitDay = 3;

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
            return this;
        }
    }
}
