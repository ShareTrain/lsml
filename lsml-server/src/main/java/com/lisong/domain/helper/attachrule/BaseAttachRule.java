package com.lisong.domain.helper.attachrule;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.lisong.domain.helper.attachruleitem.FormRuleItem;
import com.lisong.domain.helper.attachruleitem.SpuRuleItem;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@JsonPropertyOrder({"formRule", "formInfo", "spuRule", "spuInfo"})
public abstract class BaseAttachRule implements Serializable {
    private static final long serialVersionUID = 9035067944728241779L;

    public static final String FROM_RULE = "formRule";
    public static final String FORM_INFO = "formInfo";
    public static final String SPU_RULE = "spuRule";
    public static final String SPU_INFO = "spuInfo";

    private List<FormRuleItem> formRule = new ArrayList<>();

    private List<SpuRuleItem> spuRule = new ArrayList<>();

    public BaseAttachRule formRule(FormRuleItem formRule) {
        this.formRule.add(formRule);
        return this;
    }

    public BaseAttachRule formRule(Collection<? extends FormRuleItem> formRule) {
        this.formRule.addAll(formRule);
        return this;
    }

    public BaseAttachRule clearFormRule() {
        if (this.formRule != null) {
            this.formRule.clear();
        }
        return this;
    }

    public BaseAttachRule spuRule(SpuRuleItem spuRule) {
        this.spuRule.add(spuRule);
        return this;
    }

    public BaseAttachRule spuRule(Collection<? extends SpuRuleItem> spuRule) {
        this.spuRule.addAll(spuRule);
        return this;
    }

    public BaseAttachRule clearSpuRule() {
        if (this.spuRule != null) {
            this.spuRule.clear();
        }
        return this;
    }

    /*public BaseAttachRule.BaseAttachRuleBuilder superBuilder() {
        return new BaseAttachRule.BaseAttachRuleBuilder();
    }

    public List<FormRuleItem> getFormRule() {
        return this.formRule;
    }

    public List<SpuRuleItem> getSpuRule() {
        return this.spuRule;
    }

    public BaseAttachRule(List<FormRuleItem> formRule, List<SpuRuleItem> spuRule) {
        this.formRule = formRule;
        this.spuRule = spuRule;
    }

    public BaseAttachRule() {}

    public static class BaseAttachRuleBuilder {
        private ArrayList<FormRuleItem> formRule;
        private ArrayList<SpuRuleItem> spuRule;

        BaseAttachRuleBuilder() {}

        public BaseAttachRule.BaseAttachRuleBuilder formRule(FormRuleItem formRule) {
            if (this.formRule == null) {
                this.formRule = new ArrayList<>();
            }

            this.formRule.add(formRule);
            return this;
        }

        public BaseAttachRule.BaseAttachRuleBuilder formRule(
                Collection<? extends FormRuleItem> formRule) {
            if (this.formRule == null) {
                this.formRule = new ArrayList<>();
            }

            this.formRule.addAll(formRule);
            return this;
        }

        public BaseAttachRule.BaseAttachRuleBuilder clearFormRule() {
            if (this.formRule != null) {
                this.formRule.clear();
            }

            return this;
        }

        public BaseAttachRule.BaseAttachRuleBuilder spuRule(SpuRuleItem spuRule) {
            if (this.spuRule == null) {
                this.spuRule = new ArrayList<>();
            }

            this.spuRule.add(spuRule);
            return this;
        }

        public BaseAttachRule.BaseAttachRuleBuilder spuRule(
                Collection<? extends SpuRuleItem> spuRule) {
            if (this.spuRule == null) {
                this.spuRule = new ArrayList<>();
            }

            this.spuRule.addAll(spuRule);
            return this;
        }

        public BaseAttachRule.BaseAttachRuleBuilder clearSpuRule() {
            if (this.spuRule != null) {
                this.spuRule.clear();
            }

            return this;
        }

        public BaseAttachRule build() {
            List formRule;
            switch (this.formRule == null ? 0 : this.formRule.size()) {
                case 0:
                    formRule = Collections.emptyList();
                    break;
                case 1:
                    formRule = Collections.singletonList(this.formRule.get(0));
                    break;
                default:
                    formRule = Collections.unmodifiableList(new ArrayList(this.formRule));
            }

            List spuRule;
            switch (this.spuRule == null ? 0 : this.spuRule.size()) {
                case 0:
                    spuRule = Collections.emptyList();
                    break;
                case 1:
                    spuRule = Collections.singletonList(this.spuRule.get(0));
                    break;
                default:
                    spuRule = Collections.unmodifiableList(new ArrayList(this.spuRule));
            }

            return new BaseAttachRule(formRule, spuRule);
        }

        public String toString() {
            return "BaseAttachRule.BaseAttachRuleBuilder(formRule="
                    + this.formRule
                    + ", spuRule="
                    + this.spuRule
                    + ")";
        }
    }*/
}
