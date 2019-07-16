package com.lisong.domain.helper.attachruleitem;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.lisong.component.regex.RegexDefine;
import com.lisong.exception.AppStatus;
import com.lisong.util.IdCardUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Getter
@ToString
@Builder(toBuilder = true)
@Slf4j
public class FormRuleItem implements Serializable {
    private static final long serialVersionUID = -6786535380501899853L;

    @Builder.Default private String vtype = "text";
    @Builder.Default private String vrule = "text";
    private String fieldLabel;
    private String fieldName;
    @Builder.Default private Boolean required = true;

    public static FormRuleItem build(String fieldLabel, String fieldName) {
        return FormRuleItem.builder().fieldLabel(fieldLabel).fieldName(fieldName).build();
    }

    public static FormRuleItemBuilder toBuilder(String fieldLabel, String fieldName) {
        return FormRuleItem.builder()
                .fieldLabel(fieldLabel)
                .fieldName(fieldName)
                .build()
                .toBuilder();
    }

    interface BaseVRule<V> {
        String getVRule();

        void validate(V value);
    }

    public enum VRule implements BaseVRule<String> {
        ID_CARD("idCard"),
        MOBILE("mobile"),
        NAME("name"),
        AGE("age"),
        ADDRESS("address"),
        ;
        private String vrule;

        VRule(String vrule) {
            this.vrule = vrule;
        }

        @Override
        public String getVRule() {
            return vrule;
        }

        @Override
        public void validate(String value) {
            if (StringUtils.isEmpty(value)) {
                log.error("【vrule校验】value不允许为空, value={}", value);
                throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "value不允许为空");
            }
            switch (this) {
                case ID_CARD:
                    if (!IdCardUtils.checkIdCard(value)) {
                        log.error("【vrule校验】idCard不合法, idCard={}", value);
                        throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "idCard不合法");
                    }
                    break;
                case MOBILE:
                    if (!RegexDefine.MOBILE_REGEX_PATTERN.matcher(value).matches()) {
                        log.error("【vrule校验】mobile不合法, mobile={}", value);
                        throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "mobile不合法");
                    }
                    break;
                case NAME:
                    if (value.length() > 64) {
                        log.error("【vrule校验】name过长, name={}", value);
                        throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "name过长");
                    }
                    break;
                case AGE:
                    int age;
                    try {
                        age = Integer.valueOf(value);
                    } catch (NumberFormatException e) {
                        log.error("【vrule校验】age类型错误, age={}", value);

                        throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "age类型错误");
                    }
                    if (age <= 0 || age > 100) {
                        log.error("【vrule校验】name过长, name={}", value);
                        throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "name过长");
                    }
                    break;
                case ADDRESS:
                    if (value.length() > 256) {
                        log.error("【vrule校验】address过长, address={}", value);
                        throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "address过长");
                    }
                    break;
                default:
            }
        }
    }
}
