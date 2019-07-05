package com.lisong.component.sms;

import com.lisong.component.sms.req.CheckVerifyCodeRequest;
import com.lisong.component.sms.req.MatchVerifyCodeRequest;
import com.lisong.component.sms.req.SendMessageRequest;
import com.lisong.component.sms.res.CheckVerifyCodeResponse;
import com.lisong.component.sms.res.MatchVerifyCodeResponse;
import com.lisong.component.sms.res.SendMessageResponse;

public interface SmsService {

    /**
     * 手机号验证码匹配
     *
     * @param request
     * @return
     */
    MatchVerifyCodeResponse matchCode(MatchVerifyCodeRequest request);

    /**
     * 短信发送入口
     *
     * @param request
     * @return
     */
    SendMessageResponse sendMessage(SendMessageRequest request);

    /**
     * 校验验证码
     *
     * @param request
     * @return
     */
    CheckVerifyCodeResponse checkVerifyCode(CheckVerifyCodeRequest request);
}
