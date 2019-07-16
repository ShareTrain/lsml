package com.lisong.domain.helper.attachrule.buy;

import com.lisong.domain.helper.attachrule.BaseAttachInfo;
import com.lisong.domain.helper.attachrule.BaseAttachRule;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NonEquityBuyAttachRule extends BaseAttachRule {

    private static final long serialVersionUID = 551548630395400507L;
    private FormInfo formInfo;

    private SpuInfo spuInfo;

    @Getter
    @Builder
    public static class FormInfo extends BaseAttachInfo {
        private static final long serialVersionUID = 4283080917091513015L;

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
        private static final long serialVersionUID = -3332066945469836059L;

        @Override
        public SpuInfo validate() {
            return this;
        }
    }
}
