package com.lisong.component.sms;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.fasterxml.jackson.databind.JsonNode;
import com.lisong.common.ConfigDefinition;
import com.lisong.common.ConstantDefinition;
import com.lisong.common.DictDefinition;
import com.lisong.common.JsonConverter;
import com.lisong.component.http.HttpTools;
import com.lisong.component.sms.domain.SmsVerify;
import com.lisong.component.sms.req.CheckVerifyCodeRequest;
import com.lisong.component.sms.req.MatchVerifyCodeRequest;
import com.lisong.component.sms.req.SendMessageRequest;
import com.lisong.component.sms.res.CheckVerifyCodeResponse;
import com.lisong.component.sms.res.MatchVerifyCodeResponse;
import com.lisong.component.sms.res.SendMessageResponse;
import com.lisong.exception.AppStatus;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信服务实现
 *
 * @author wtf 2018年5月2日
 */
@Service
@Transactional
public class SmsServiceImpl implements SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    private static ConfigDefinition commonConfig;

    @Autowired private SmsVerifyRepository smsVerifyRepository;

    @Autowired
    public void setCommonConfig(ConfigDefinition commonConfig) {
        SmsServiceImpl.commonConfig = commonConfig;
    }

    /** 手机号验证码匹配 */
    @Override
    public MatchVerifyCodeResponse matchCode(MatchVerifyCodeRequest request) {
        MatchVerifyCodeResponse response = new MatchVerifyCodeResponse();
        String code = RandomStringUtils.randomNumeric(4);
        SmsVerify sv =
                smsVerifyRepository.findByMobileAndDeleted(
                        request.getMobile(), DictDefinition.Deleted.NO);
        if (sv == null) {
            sv = new SmsVerify();
            sv.setMobile(request.getMobile());
            sv.setVerifyCode(code);
        } else {
            sv.setVerifyCode(code);
        }
        LocalDateTime ldt = LocalDateTime.now();
        ldt = ldt.plusMinutes(5);
        sv.setExpireTime(getDate(ldt));
        smsVerifyRepository.save(sv);
        return response;
    }

    /** 短信入口 */
    @Override
    public SendMessageResponse sendMessage(SendMessageRequest request) {
        SendMessageResponse response = new SendMessageResponse();
        try {
            logger.info("发送短信sendMessage入参：" + JsonConverter.toJson(request));
            if (ConstantDefinition.C_SMS.SEND_VERIFY_CODE.equals(request.getMsgCode())) {
                String code = RandomStringUtils.randomNumeric(4);
                SmsVerify sv =
                        smsVerifyRepository.findByMobileAndDeleted(
                                request.getReceiveMobile(), DictDefinition.Deleted.NO);
                if (sv == null) {
                    sv = new SmsVerify();
                    sv.setMobile(request.getReceiveMobile());
                    sv.setVerifyCode(code);
                } else {
                    sv.setVerifyCode(code);
                }
                LocalDateTime ldt = LocalDateTime.now();
                ldt = ldt.plusMinutes(5);
                sv.setExpireTime(getDate(ldt));
                smsVerifyRepository.save(sv);

                Map<String, String> map = new HashMap<>();
                map.put("verifyCode", sv.getVerifyCode());

                String param = JsonConverter.toJson(map);
                request.setParam(param);
                return sendVerifyCode(request, response);
            }
        } catch (Exception e) {
            logger.error("发送短信入口出错：", e);
            response.setErrorCode(AppStatus.BUSINESS_CHECK_ERROR.getErrorCode());
            response.setErrorMessage("短信服务端程序出错。");
        }
        return response;
    }

    /**
     * 发送验证码
     *
     * @param request
     * @param response
     * @return
     */
    private SendMessageResponse sendVerifyCode(
            SendMessageRequest request, SendMessageResponse response) {
        String param = request.getParam();
        JsonNode jsonNode = JsonConverter.fromJson(param);
        if (jsonNode.get("verifyCode") == null) {
            response.setErrorCode(AppStatus.BUSINESS_CHECK_ERROR.getErrorCode());
            response.setErrorMessage("短信参数：验证码不能为空!");
            return response;
        }
        boolean flag =
                sendMsg(
                        request.getReceiveMobile(),
                        "感谢您使用乐园系统,您获取的验证码为:" + jsonNode.get("verifyCode") + "(5分钟内有效),请勿泄露给其他人。");
        response.setSuccess(flag);
        return response;
    }

    /**
     * 开始发送短信
     *
     * @param mobile
     * @param content
     * @return
     */
    private static boolean sendMsg(String mobile, String content) {
        String method = "MongateCsSpSendSmsNew";
        Map<String, String> params = new HashMap<>();
        params.put("userId", commonConfig.getSms().getUserId());
        params.put("password", commonConfig.getSms().getPassword());
        params.put("pszMobis", mobile);
        params.put("pszMsg", content);
        params.put("iMobiCount", mobile.split(",").length + "");
        params.put("pszSubPort", "*");
        // params.put("MsgId", RandomUtils.randomNum(10));
        String ret = ConstantDefinition.C_COMMON.EMPTY;
        try {
            ret = HttpTools.doPost(commonConfig.getSms().gateUrl + method, params);
            logger.info(ret);

        } catch (IOException e) {
            logger.error("【发送短信异常】", e);
        }
        return ret.replace(
                                "<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"http://tempuri.org/\">",
                                "")
                        .replace("</string>", "")
                        .length()
                >= 10;
    }

    /**
     * 校验验证码
     *
     * @param request
     * @return
     */
    @Override
    public CheckVerifyCodeResponse checkVerifyCode(CheckVerifyCodeRequest request) {
        CheckVerifyCodeResponse response = new CheckVerifyCodeResponse();
        SmsVerify sv =
                smsVerifyRepository.findByMobileAndDeleted(
                        request.getMobile(), DictDefinition.Deleted.NO);
        String oriVerifyCode = sv.getVerifyCode();
        String nowVerifyCode = request.getNowVerifyCode();
        if (!oriVerifyCode.equals(nowVerifyCode)) {
            logger.error(
                    "【校验验证码】验证码输入错误,oriVerifyCode = {},nowVerifyCode = {}",
                    oriVerifyCode,
                    nowVerifyCode);
            throw new AppException(AppStatus.BUSINESS_CHECK_ERROR);
        }
        Date expireDate = sv.getExpireTime();
        Date now = new Date();
        if (now.after(expireDate)) {
            logger.error(
                    "【校验验证码】验证码已过期,请重新获取,expireTime = {}",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            throw new AppException(AppStatus.BUSINESS_CHECK_ERROR);
        }
        response.setSuccess(true);
        return response;
    }

    /**
     * LocalDateTime转Date
     *
     * @param ldt
     * @return
     */
    private Date getDate(LocalDateTime ldt) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = ldt.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }
}
