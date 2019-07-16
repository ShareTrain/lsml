package com.lisong.domain.helper.attachrule.buy;

import com.lisong.domain.helper.attachrule.BaseAttachInfo;
import com.lisong.domain.helper.attachrule.BaseAttachRule;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VipBuyAttachRule extends BaseAttachRule {
    private static final long serialVersionUID = 2806100862192254157L;
    private FormInfo formInfo;

    private SpuInfo spuInfo;

    @Getter
    @Builder
    public static class FormInfo extends BaseAttachInfo {

        private static final long serialVersionUID = 5087873545586565423L;

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
        private static final long serialVersionUID = 232229952885265412L;

        @Override
        public SpuInfo validate() {
            return this;
        }
    }
}
