package com.lisong.domain.helper.attachruleitem;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class SpuRuleItem implements Serializable {
    private static final long serialVersionUID = 415915833647246774L;
    private String fieldLabel;
    private String fieldName;

    public static SpuRuleItem build(String fieldLabel, String fieldName) {
        return SpuRuleItem.builder().fieldLabel(fieldLabel).fieldName(fieldName).build();
    }
}
