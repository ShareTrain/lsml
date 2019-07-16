package com.lisong.domain.helper.attachrule.buy;

import com.lisong.domain.helper.attachrule.BaseAttachInfo;
import com.lisong.domain.helper.attachrule.BaseAttachRule;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EquityBuyAttachRule extends BaseAttachRule {

    private static final long serialVersionUID = -603404550866373665L;
    private FormInfo formInfo;

    private SpuInfo spuInfo;

    @Getter
    @Builder
    public static class FormInfo extends BaseAttachInfo {
        private static final long serialVersionUID = 3742237009348698010L;

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
        private static final long serialVersionUID = 3936455804429699775L;

        @Override
        public SpuInfo validate() {
            return this;
        }
    }
}
