package com.lisong.domain.helper.attachrule.appoint;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.lisong.common.ConstantDefinition;
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
public class EquityAppointAttachRule extends BaseAttachRule {
    private static final long serialVersionUID = 1786528131970390597L;

    private FormInfo formInfo;

    private SpuInfo spuInfo;

    @Getter
    @Builder
    public static class FormInfo extends BaseAttachInfo {
        private static final long serialVersionUID = 6827355043764078894L;

        /** 身份证. */
        private String idCard;

        @Override
        public FormInfo validate() {
            return this;
        }
    }

    @Getter
    @Builder
    public static class SpuInfo extends BaseAttachInfo {
        private static final long serialVersionUID = -7709769517876543014L;
        /** 是否需要限制一个周期使用次数；1-是；0-否. */
        private Integer needPeriodLimit;

        /** 权益商品每一个周期可使用的次数，0-无限制. */
        private Integer periodTimes;

        @Override
        public SpuInfo validate() {
            this.needPeriodLimit =
                    DictDefinition.YesOrNo.YES.equals(needPeriodLimit)
                            ? DictDefinition.YesOrNo.YES
                            : DictDefinition.YesOrNo.NO;

            if (DictDefinition.YesOrNo.YES.equals(needPeriodLimit)
                    && periodTimes <= ConstantDefinition.C_COMMON.INTEGER_ZERO) {
                log.error(
                        "【buildEquityAppointAttachRule】参数periodTimes必须大于0, needPeriodLimit={}, periodTimes={}",
                        needPeriodLimit,
                        periodTimes);
                throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "参数periodTimes必须大于0");
            }
            return this;
        }
    }
}
