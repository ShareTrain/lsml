package com.lisong.domain.helper;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lisong.common.DictDefinition;
import com.lisong.common.JsonConverter;
import com.lisong.domain.helper.attachrule.BaseAttachRule;
import com.lisong.domain.helper.attachrule.appoint.EquityAppointAttachRule;
import com.lisong.domain.helper.attachrule.appoint.NonEquityAppointAttachRule;
import com.lisong.domain.helper.attachrule.buy.EquityBuyAttachRule;
import com.lisong.domain.helper.attachrule.buy.NonEquityBuyAttachRule;
import com.lisong.domain.helper.attachrule.buy.VipBuyAttachRule;
import com.lisong.domain.helper.attachrule.refund.EquityRefundAttachRule;
import com.lisong.domain.helper.attachrule.refund.NonEquityRefundAttachRule;
import com.lisong.domain.helper.attachruleitem.FormRuleItem;
import com.lisong.domain.helper.attachruleitem.SpuRuleItem;
import com.lisong.exception.AppStatus;
import com.lisong.util.BigDecimalUtils;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/*
定义的预约附加规则:
{
	"formRule": [
		{"vtype":"text","vrule":"mobile","fieldLabel":"手机号","fieldName":"mobile","required":true},
		{"vtype":"text","vrule":"text","fieldLabel":"姓名","fieldName":"name","required":true},
		{"vtype":"text","vrule":"text","fieldLabel":"地址","fieldName":"address","required":false}
	],
	"formInfo": {},
	"spuRule": [
		{"fieldLabel":"是否需要身份证；1-是；0-否","fieldName":"needIdCard"},
		{"fieldLabel":"是否需要限制一个周期使用次数；1-是；0-否","fieldName":"needPeriodLimit"},
		{"fieldLabel":"权益商品每一个周期可使用的次数，0-无限制","fieldName":"periodTimes"}
	],
	"spuInfo":{"needIdCard":1,"needPeriodLimit":1,"periodTimes":3}
}
解释：
formRule:定义需要用户提交的表单内容，具体信息在发生用户行为时保存在formInfo属性中；
示例：formInfo{"mobile":"18767188240","name":"问秋"}
spuRule:定义归属商品固有规则，具体内容在商品维护时保存在spuInfo属性中，用来校验用户行为。

一个完整示例：
{"formRule":[],"formInfo":null,"spuRule":[{"fieldLabel":"是否需要身份证；1-是；0-否","fieldName":"needIdCard"},{"fieldLabel":"是否需要限制一个周期使用次数；1-是；0-否","fieldName":"needPeriodLimit"},{"fieldLabel":"权益商品每一个周期可使用的次数，0-无限制","fieldName":"periodTimes"}],"spuInfo":{"needIdCard":1,"needPeriodLimit":1,"periodTimes":3}}
 */
@Component
@Slf4j
public class DomainHelper {

    /**
     * 构建权益商品预约附加规则.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190714 13:47</font><br>
     * [请在此输入功能详述]
     *
     * @param needIdCard - 是否需要身份证
     * @param needPeriodLimit - 是否需要限制一个周期使用次数
     * @param periodTimes - 权益商品每一个周期可使用的次数
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String buildEquityAppointAttachRule(
            Integer needIdCard, Integer needPeriodLimit, Integer periodTimes) {
        BaseAttachRule rule =
                EquityAppointAttachRule.builder()
                        // .formInfo(EquityAppointAttachRule.FormInfo.builder().build())
                        .spuInfo(
                                EquityAppointAttachRule.SpuInfo.builder()
                                        .needPeriodLimit(
                                                DictDefinition.YesOrNo.YES.equals(needPeriodLimit)
                                                        ? DictDefinition.YesOrNo.YES
                                                        : DictDefinition.YesOrNo.NO)
                                        .periodTimes(periodTimes)
                                        .build()
                                        .validate())
                        .build()
                        .spuRule(SpuRuleItem.build("是否需要限制一个周期使用次数；1-是；0-否", "needPeriodLimit"))
                        .spuRule(SpuRuleItem.build("权益商品每一个周期可使用的次数，0-无限制", "periodTimes"));
        if (DictDefinition.YesOrNo.YES.equals(needIdCard)) {
            rule.formRule(
                    FormRuleItem.build("身份证", "idCard")
                            .toBuilder()
                            .vrule(FormRuleItem.VRule.ID_CARD.getVRule())
                            .build());
        }
        return JsonConverter.toJson(rule);
    }

    /**
     * 构建非权益商品预约附加规则.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190714 13:54</font><br>
     * [请在此输入功能详述]
     *
     * @param needIdCard - 是否需要身份证
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String buildNonEquityAppointAttachRule(Integer needIdCard) {
        BaseAttachRule rule =
                NonEquityAppointAttachRule.builder()
                        // .formInfo(NonEquityAppointAttachRule.FormInfo.builder().build())
                        .build();
        if (DictDefinition.YesOrNo.YES.equals(needIdCard)) {
            rule.formRule(
                    FormRuleItem.build("身份证", "idCard")
                            .toBuilder()
                            .vrule(FormRuleItem.VRule.ID_CARD.getVRule())
                            .build());
        }
        return JsonConverter.toJson(rule);
    }

    /**
     * 构建VIP商品购买附加规则.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190714 13:56</font><br>
     * [请在此输入功能详述]
     *
     * @param needIdCard - 是否需要身份证
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String buildVipBuyAttachRule(Integer needIdCard, Integer needIdCardName) {
        BaseAttachRule rule =
                VipBuyAttachRule.builder()
                        // .formInfo(VipBuyAttachRule.FormInfo.builder().build())
                        .build();
        if (DictDefinition.YesOrNo.YES.equals(needIdCard)) {
            rule.formRule(
                    FormRuleItem.build("身份证", "idCard")
                            .toBuilder()
                            .vrule(FormRuleItem.VRule.ID_CARD.getVRule())
                            .build());
        }
        if (DictDefinition.YesOrNo.YES.equals(needIdCardName)) {
            rule.formRule(
                    FormRuleItem.build("姓名", "name")
                            .toBuilder()
                            .vrule(FormRuleItem.VRule.NAME.getVRule())
                            .build());
        }
        return JsonConverter.toJson(rule);
    }

    /**
     * 构建权益商品购买附加规则.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190714 13:56</font><br>
     * [请在此输入功能详述]
     *
     * @param needIdCard - 是否需要身份证
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String buildEquityBuyAttachRule(Integer needIdCard) {
        BaseAttachRule rule =
                EquityBuyAttachRule.builder()
                        // .formInfo(EquityBuyAttachRule.FormInfo.builder().build())
                        .build();
        if (DictDefinition.YesOrNo.YES.equals(needIdCard)) {
            rule.formRule(
                    FormRuleItem.build("身份证", "idCard")
                            .toBuilder()
                            .vrule(FormRuleItem.VRule.ID_CARD.getVRule())
                            .build());
        }
        return JsonConverter.toJson(rule);
    }

    /**
     * 构建非权益商品购买附加规则.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190714 13:39</font><br>
     * [请在此输入功能详述]
     *
     * @param needIdCard - 是否需要身份证
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String buildNonEquityBuyAttachRule(Integer needIdCard) {
        BaseAttachRule rule =
                NonEquityBuyAttachRule.builder()
                        // .formInfo(NonEquityBuyAttachRule.FormInfo.builder().build())
                        .build();
        if (DictDefinition.YesOrNo.YES.equals(needIdCard)) {
            rule.formRule(
                    FormRuleItem.build("身份证", "idCard")
                            .toBuilder()
                            .vrule(FormRuleItem.VRule.ID_CARD.getVRule())
                            .build());
        }
        return JsonConverter.toJson(rule);
    }

    /**
     * 构建权益商品退款附加规则.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190713 09:46</font><br>
     *
     * @param allowCustRefund - 是否允许用户主动退款
     * @param refundWaitDay - 退款等待天数
     * @param sectionArgs - geHour,ltHour,refundRate三个参数一组，多个区间需要多组参数
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String buildEquityRefundAttachRule(
            Integer allowCustRefund, Integer refundWaitDay, Object... sectionArgs) {
        if (sectionArgs.length % 3 != 0) {
            log.error(
                    "【buildEquityRefundAttachRule】sectionArgs参数数量错误, sectionArgs={}", sectionArgs);
            throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "sectionArgs参数数量错误");
        }

        int selectCount = sectionArgs.length / 3;
        List<Object> sectionArgList = Arrays.asList(sectionArgs);
        List<EquityRefundAttachRule.SpuInfo.Section> sectionList = new ArrayList<>();
        try {
            for (int i = 0; i < selectCount; i += 3) {
                sectionList.add(
                        EquityRefundAttachRule.SpuInfo.build(
                                Integer.valueOf(sectionArgList.get(i).toString()),
                                Integer.valueOf(sectionArgList.get(i + 1).toString()),
                                Double.valueOf(sectionArgList.get(i + 2).toString())));
            }
        } catch (NumberFormatException e) {
            log.error("【buildEquityRefundAttachRule】sectionArgs参数类型错误", e);
            throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "sectionArgs参数类型错误");
        }

        BaseAttachRule rule =
                EquityRefundAttachRule.builder()
                        // .formInfo(EquityRefundAttachRule.FormInfo.builder().build())
                        .spuInfo(
                                EquityRefundAttachRule.SpuInfo.builder()
                                        .allowCustRefund(allowCustRefund)
                                        .refundWaitDay(refundWaitDay)
                                        .sections(sectionList)
                                        .build()
                                        .validate())
                        .build()
                        .spuRule(SpuRuleItem.build("是否允许用户主动退款；1-是；0-否", "allowCustRefund"))
                        .spuRule(SpuRuleItem.build("退款等待天数：默认值3天", "refundWaitDay"))
                        .spuRule(SpuRuleItem.build("允许用户主动退款的阶梯退款比例", "sections"));
        return JsonConverter.toJson(rule);
    }

    /**
     * 构建非权益商品退款附加规则.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190713 09:46</font><br>
     *
     * @param allowCustRefund - 是否允许用户主动退款
     * @param refundWaitDay - 退款等待天数
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String buildNonEquityRefundAttachRule(
            Integer allowCustRefund, Integer refundWaitDay) {
        BaseAttachRule rule =
                NonEquityRefundAttachRule.builder()
                        // .formInfo(NonEquityRefundAttachRule.FormInfo.builder().build())
                        .spuInfo(
                                NonEquityRefundAttachRule.SpuInfo.builder()
                                        .allowCustRefund(allowCustRefund)
                                        .refundWaitDay(refundWaitDay)
                                        .build()
                                        .validate())
                        .build()
                        .spuRule(SpuRuleItem.build("是否允许用户主动退款；1-是；0-否", "allowCustRefund"))
                        .spuRule(SpuRuleItem.build("退款等待天数：默认值3天", "refundWaitDay"));
        return JsonConverter.toJson(rule);
    }

    public static String buildEquityRefundTips(String attachRule) {
        String refundMode9_1 =
                "1、保证金将在您按时完成预约核销后的1个工作日全额退还。\n"
                        + "2、若您无法按预约时间前往，请提前${ltHour}小时取消预约，取消后保证金将在${refundWaitDay}个工作日全额退还。\n"
                        + "3、若未能提前${ltHour}小时取消预约，保证金将扣除${loosRate}后在${refundWaitDay}个工作日退还。\n"
                        + "4、若超过预约时间未能完成核销，保证金将全额扣除。";
        String refundMode9_2 = "1、该商品为特殊景区乐园所属，取消预约将无法退还保证金。请您慎重选择预约时间，提前安排出行计划。";
        String spuInfoJson = extractSpuInfo(attachRule);
        // "spuInfo":{"allowCustRefund":1,"sections":[{"geHour":0,"ltHour":48,"refundRate":1.0}],"refundWaitDay":1}
        JsonNode jsonNode = JsonConverter.fromJson(spuInfoJson);
        if (!jsonNode.has("allowCustRefund")
                || !jsonNode.has("refundWaitDay")
                || jsonNode.findValue("ltHour") == null
                || jsonNode.findValue("refundRate") == null) {
            log.error("【buildEquityRefundTips】spuInfo参数错误");
            throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "spuInfo参数错误");
        }
        String ltHour = jsonNode.findValue("ltHour").asText();
        String loosRate =
                String.valueOf(
                        BigDecimalUtils.getPercent(
                                1 - jsonNode.findValue("refundRate").asDouble()));
        String refundWaitDay = jsonNode.get("refundWaitDay").asText();
        if (DictDefinition.YesOrNo.YES.equals(jsonNode.get("allowCustRefund").asInt())) {
            refundMode9_1 =
                    refundMode9_1
                            .replaceAll("\\$\\{ltHour}", ltHour)
                            .replaceAll("\\$\\{loosRate}", loosRate)
                            .replaceAll("\\$\\{refundWaitDay}", refundWaitDay);
            return refundMode9_1;
        } else {
            return refundMode9_2;
        }
    }

    public static List<FormRuleItem> extractFormRuleItem(String attachRule) {
        JsonNode jsonNode = JsonConverter.fromJson(attachRule);
        if (!jsonNode.isObject()) {
            log.error("【extractFormRuleItem】jsonNode判断isObject=false, attachRule={}", attachRule);
            throw new AppException(
                    AppStatus.BUSINESS_CHECK_ERROR, "jsonNode判断isObject=false");
        }
        ObjectNode objectNode = (ObjectNode) jsonNode;
        if (!objectNode.has(BaseAttachRule.FROM_RULE)
                || !objectNode.has(BaseAttachRule.FORM_INFO)
                || !objectNode.has(BaseAttachRule.SPU_RULE)
                || !objectNode.has(BaseAttachRule.SPU_INFO)) {
            log.error(
                    "【extractFormRuleItem】jsonNode判断不是BaseAttachRule对象, attachRule={}", attachRule);
            throw new AppException(
                    AppStatus.BUSINESS_CHECK_ERROR, "jsonNode判断不是BaseAttachRule对象");
        }
        String formRule = objectNode.get(BaseAttachRule.FROM_RULE).toString();
        return JsonConverter.fromJson(formRule, new TypeToken<List<FormRuleItem>>() {}.getType());
    }

    public static Map<String, Boolean> existSomeFormRule(String attachRule, String... fieldNames) {
        Map<String, Boolean> fieldNameMap = new HashMap<>();
        List<FormRuleItem> formRuleItemList = extractFormRuleItem(attachRule);
        Map<String, FormRuleItem> formRuleItemMap =
                formRuleItemList
                        .stream()
                        .collect(Collectors.toMap(FormRuleItem::getFieldName, e -> e));
        for (String fieldName : fieldNames) {
            fieldNameMap.put(fieldName, formRuleItemMap.containsKey(fieldName));
        }
        return fieldNameMap;
    }

    /**
     * 抽取附加规则中的spuInfo信息.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190714 14:53</font><br>
     * 抽取出spuInfo，用于业务判断。
     *
     * @param attachRule - 可以是商品中的购买规则、预约规则或者退款规则
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String extractSpuInfo(String attachRule) {
        JsonNode jsonNode = JsonConverter.fromJson(attachRule);
        if (!jsonNode.isObject()) {
            log.error("【extractSpuInfo】jsonNode判断isObject=false, attachRule={}", attachRule);
            throw new AppException(
                    AppStatus.BUSINESS_CHECK_ERROR, "jsonNode判断isObject=false");
        }
        ObjectNode objectNode = (ObjectNode) jsonNode;
        if (!objectNode.has(BaseAttachRule.FROM_RULE)
                || !objectNode.has(BaseAttachRule.FORM_INFO)
                || !objectNode.has(BaseAttachRule.SPU_RULE)
                || !objectNode.has(BaseAttachRule.SPU_INFO)) {
            log.error("【extractSpuInfo】jsonNode判断不是BaseAttachRule对象, attachRule={}", attachRule);
            throw new AppException(
                    AppStatus.BUSINESS_CHECK_ERROR, "jsonNode判断不是BaseAttachRule对象");
        }
        JsonNode spuInfo = objectNode.get(BaseAttachRule.SPU_INFO);
        return spuInfo.toString();
    }

    /**
     * 检查并优化formInfo信息.
     *
     * <p>创建时间: <font style="color:#00FFFF">20190715 10:20</font><br>
     * [请在此输入功能详述]
     *
     * @param attachRule - 可以是商品中的购买规则、预约规则或者退款规则
     * @param formInfoJson - 前端传递的json格式的forminfo信息
     * @return java.lang.String
     * @author Rushing0711
     * @since 1.0.0
     */
    public static String checkAndOptimizeFormInfo(String attachRule, String formInfoJson) {
        JsonNode formInfoNode = JsonConverter.fromJson(formInfoJson);

        JsonNode jsonNode = JsonConverter.fromJson(attachRule);
        if (!jsonNode.isObject()) {
            log.error("【checkFormInfo】jsonNode判断isObject=false, attachRule={}", attachRule);
            throw new AppException(
                    AppStatus.BUSINESS_CHECK_ERROR, "jsonNode判断isObject=false");
        }
        ObjectNode objectNode = (ObjectNode) jsonNode;
        if (!objectNode.has(BaseAttachRule.FROM_RULE)
                || !objectNode.has(BaseAttachRule.FORM_INFO)
                || !objectNode.has(BaseAttachRule.SPU_RULE)
                || !objectNode.has(BaseAttachRule.SPU_INFO)) {
            log.error("【checkFormInfo】jsonNode判断不是BaseAttachRule对象, attachRule={}", attachRule);
            throw new AppException(
                    AppStatus.BUSINESS_CHECK_ERROR, "jsonNode判断不是BaseAttachRule对象");
        }
        String formRule = objectNode.get(BaseAttachRule.FROM_RULE).toString();
        List<FormRuleItem> formRuleItemList =
                JsonConverter.fromJson(formRule, new TypeToken<List<FormRuleItem>>() {}.getType());

        LinkedHashMap<String, Object> formInfoMap = new LinkedHashMap<>();
        for (FormRuleItem formRuleItem : formRuleItemList) {
            FormRuleItem.VRule vRule = FormRuleItem.VRule.valueOf(formRuleItem.getVrule());
            JsonNode field = formInfoNode.get(formRuleItem.getFieldName());
            if (field == null) {
                log.error("【checkFormInfo】{}不存在", formRuleItem.getFieldName());
                throw new AppException(
                        AppStatus.BUSINESS_CHECK_ERROR,
                        String.format("%s不允许为空", formRuleItem.getFieldName()));
            }

            if (formRuleItem.getRequired()) {
                if (StringUtils.isEmpty(field.toString())) {
                    log.error("【checkFormInfo】{}不允许为空", formRuleItem.getFieldName());
                    throw new AppException(
                            AppStatus.BUSINESS_CHECK_ERROR,
                            String.format("%s不允许为空", formRuleItem.getFieldName()));
                }
                vRule.validate(field.asText());
                formInfoMap.put(formRuleItem.getFieldName(), field.asText());
            } else {
                if (StringUtils.isEmpty(field.asText())) {
                    vRule.validate(field.asText());
                }
                formInfoMap.put(formRuleItem.getFieldName(), field.asText());
            }
        }
        return JsonConverter.toJson(formInfoMap);
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();
        /*BaseAttachRule rule =
                EquityAppointAttachRule.builder()
                        // .formInfo(EquityAppointAttachRule.FormInfo.builder().build())
                        .spuInfo(
                                EquityAppointAttachRule.SpuInfo.builder()
                                        .needIdCard(
                                                DictDefinition.YesOrNo.YES.equals(1)
                                                        ? DictDefinition.YesOrNo.YES
                                                        : DictDefinition.YesOrNo.NO)
                                        .needPeriodLimit(
                                                DictDefinition.YesOrNo.YES.equals(1)
                                                        ? DictDefinition.YesOrNo.YES
                                                        : DictDefinition.YesOrNo.NO)
                                        .periodTimes(3)
                                        .build())
                        .build()
                        .spuRule(SpuRuleItem.build("是否需要身份证；1-是；0-否", "needIdCard"))
                        .spuRule(SpuRuleItem.build("是否需要限制一个周期使用次数；1-是；0-否", "needPeriodLimit"))
                        .spuRule(SpuRuleItem.build("权益商品每一个周期可使用的次数，0-无限制", "periodTimes"));
        String json = objectMapper.writeValueAsString(rule);
        System.out.println(json);
        JsonNode jsonNode = new ObjectMapper().readTree(json);
        if (!jsonNode.isObject()) {
            throw new RuntimeException("jsonNode判断isObject=false");
        }
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.remove(Lists.newArrayList(BaseAttachRule.FROM_RULE, BaseAttachRule.SPU_RULE));
        System.out.println(objectNode.put(BaseAttachRule.FORM_INFO, "{}"));*/

        /*List<Object> sectionArgList = Lists.newArrayList(0, 48, 0.70, 46, Integer.MAX_VALUE, 1);
        List<EquityRefundAttachRule.SpuInfo.Section> sectionList = new ArrayList<>();
        try {
            for (int i = 0; i < sectionArgList.size(); i += 3) {
                sectionList.add(
                        EquityRefundAttachRule.SpuInfo.build(
                                Integer.valueOf(sectionArgList.get(i).toString()),
                                Integer.valueOf(sectionArgList.get(i + 1).toString()),
                                Double.valueOf(sectionArgList.get(i + 2).toString())));
            }
        } catch (NumberFormatException e) {
            log.error("【buildEquityRefundAttachRule】sectionArgs参数类型错误", e);
            throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "sectionArgs参数类型错误");
        }

        BaseAttachRule rule =
                EquityRefundAttachRule.builder()
                        // .formInfo(EquityAppointAttachRule.FormInfo.builder().build())
                        .spuInfo(
                                EquityRefundAttachRule.SpuInfo.builder()
                                        .allowCustRefund(1)
                                        .refundWaitDay(3)
                                        .sections(sectionList)
                                        .build()
                                        .validate())
                        .build()
                        .spuRule(SpuRuleItem.build("是否允许用户主动退款；1-是；0-否", "allowCustRefund"))
                        .spuRule(SpuRuleItem.build("退款等待天数：默认值3天", "refundWaitDay"))
                        .spuRule(SpuRuleItem.build("允许用户主动退款的阶梯退款比例", "sections"));
        String json = objectMapper.writeValueAsString(rule);
        System.out.println(json);*/

        /*String json =
                "{\"formRule\":[],\"formInfo\":null,\"spuRule\":[{\"fieldLabel\":\"是否需要身份证；1-是；0-否\",\"fieldName\":\"needIdCard\"},{\"fieldLabel\":\"是否需要限制一个周期使用次数；1-是；0-否\",\"fieldName\":\"needPeriodLimit\"},{\"fieldLabel\":\"权益商品每一个周期可使用的次数，0-无限制\",\"fieldName\":\"periodTimes\"}],\"spuInfo\":{\"needIdCard\":1,\"needPeriodLimit\":1,\"periodTimes\":3}}";
        EquityAppointAttachRule rule = gson.fromJson(json, EquityAppointAttachRule.class);
        System.out.println(rule);*/

        /*BaseAttachRule rule =
                VipBuyAttachRule.builder()
                        .build()
                        .formRule(
                                FormRuleItem.build("身份证", "idCard")
                                        .toBuilder()
                                        .vrule(FormRuleItem.VRule.ID_CARD.getVRule())
                                        .build())
                        .formRule(
                                FormRuleItem.build("年龄", "age")
                                        .toBuilder()
                                        .vrule(FormRuleItem.VRule.AGE.getVRule())
                                        .required(false)
                                        .build())
                        .formRule(
                                FormRuleItem.build("手机号", "mobile")
                                        .toBuilder()
                                        .vrule(FormRuleItem.VRule.MOBILE.getVRule())
                                        .build());
        String json = objectMapper.writeValueAsString(rule);
        System.out.println(json);

        JsonNode jsonNode = objectMapper.readTree(json);

        //       formInfo: "{"idCard":1,"age":"天帆}"
        List<FormRuleItem> formRuleItemList =
                gson.fromJson(
                        jsonNode.get(BaseAttachRule.FROM_RULE).toString(),
                        new TypeToken<List<FormRuleItem>>() {}.getType());
        for (FormRuleItem formRuleItem : formRuleItemList) {
            System.out.println(formRuleItem);
        }*/

        /*String refundMode9_1 =
                "1、保证金将在您按时完成预约核销后的1个工作日全额退还。\n"
                        + "2、若您无法按预约时间前往，请提前${ltHour}小时取消预约，取消后保证金将在${refundWaitDay}个工作日全额退还。\n"
                        + "3、若未能提前${ltHour}小时取消预约，保证金将扣除${loosRate}后在${refundWaitDay}个工作日退还。\n"
                        + "4、若超过预约时间未能完成核销，保证金将全额扣除。";
        String refundMode9_2 = "1、该商品为特殊景区乐园所属，取消预约将无法退还保证金。请您慎重选择预约时间，提前安排出行计划。";
        String spuInfoJson =
                "{\"allowCustRefund\":1,\"sections\":[{\"geHour\":0,\"ltHour\":48,\"refundRate\":0.70}],\"refundWaitDay\":1}";
        // "spuInfo":{"allowCustRefund":1,"sections":[{"geHour":0,"ltHour":48,"refundRate":1.0}],"refundWaitDay":1}
        JsonNode jsonNode = objectMapper.readTree(spuInfoJson);
        if (!jsonNode.has("allowCustRefund")
                || !jsonNode.has("refundWaitDay")
                || jsonNode.findValue("ltHour") == null
                || jsonNode.findValue("refundRate") == null) {
            log.error("【buildEquityRefundTips】spuInfo参数错误");
            throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "spuInfo参数错误");
        }
        String ltHour = jsonNode.findValue("ltHour").asText();
        String loosRate = String.valueOf(BigDecimalUtils.getPercent(1 - jsonNode.findValue("refundRate").asDouble()));
        String refundWaitDay = jsonNode.get("refundWaitDay").asText();
        if (DictDefinition.YesOrNo.YES.equals(jsonNode.get("allowCustRefund").asInt())) {
            refundMode9_1 =
                    refundMode9_1
                            .replaceAll("\\$\\{ltHour}", ltHour)
                            .replaceAll("\\$\\{loosRate}", loosRate)
                            .replaceAll("\\$\\{refundWaitDay}", refundWaitDay);
            System.out.println(refundMode9_1);
        } else {
            System.out.println(refundMode9_2);
        }*/
        /*Long a = 1L;
        Double b = 0.12;
        System.out.println(-a);
        System.out.println(-b);*/

        /*DateTime dateTime = new DateTime(new Date());
        Long timeDiff = TimeUnit.MINUTES.toSeconds(5) - 5;
        String expireTime =
                dateTime.plusSeconds(Math.toIntExact(timeDiff))
                        .toString(DateUtils.PATTERN_TIME)
                        .replace("-", "")
                        .replace(":", "")
                        .replace(" ", "");
        System.out.println(expireTime);*/

        /*String json =
                "{           \"touser\":\"o18FG52UN6n74CIjaOqNCYBvDxTA\",           \"template_id\":\"pgBOvjOK88EfE0V1ySjzNy2lJmwC__D5XdBVA_jkTxk\",           \"url\":\"http://weixin.qq.com/download\",             \"miniprogram\":{             \"appid\":\"wxb0ed859563bee6df\",             \"pagepath\":\"pages/index/index?from=tplMsg\"           },                     \"data\":{                   \"first\": {                       \"value\":\"恭喜你购买成功！\",                       \"color\":\"#173177\"                   },                   \"orderMoneySum\": {                       \"value\":\"39.8元\",                       \"color\":\"#173177\"                   },                   \"orderProductName\": {                       \"value\":\"买了一辆游艇\",                       \"color\":\"#173177\"                   },                   \"remark\":{                       \"value\":\"欢迎再次购买！\",                       \"color\":\"#173177\"                   }           }}";
        HttpTools.doPost(
                "http://www.ishanshan.com/wxproxy/api/msg/tplmsg/wx7c44bb2354440737/wxba9672b942796256",
                json);*/
    }
}
