package com.lisong.domain.helper.attachrule.appoint;

import com.lisong.domain.helper.attachrule.BaseAttachInfo;
import com.lisong.domain.helper.attachrule.BaseAttachRule;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NonEquityAppointAttachRule extends BaseAttachRule {

    private static final long serialVersionUID = 8799540117655857379L;
    private FormInfo formInfo;

    private SpuInfo spuInfo;

    @Getter
    @Builder
    public static class FormInfo extends BaseAttachInfo {
        private static final long serialVersionUID = 7264922939360453783L;
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
        private static final long serialVersionUID = 7288332909014481868L;

        @Override
        public SpuInfo validate() {
            return this;
        }
    }
}
