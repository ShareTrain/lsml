package com.lisong.component.sms;

import com.lisong.component.sms.req.CheckVerifyCodeRequest;
import com.lisong.component.sms.req.MatchVerifyCodeRequest;
import com.lisong.component.sms.req.SendMessageRequest;
import com.lisong.component.sms.res.CheckVerifyCodeResponse;
import com.lisong.component.sms.res.MatchVerifyCodeResponse;
import com.lisong.component.sms.res.SendMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/** Created by Alex on 2018/11/2 */
@RestController
public class SmsController {

    @Autowired private SmsService smsService;

    /**
     * 手机号验证码匹配
     *
     * @param request
     * @return
     */
    @RequestMapping("/sms/matchCode")
    @ResponseBody
    public MatchVerifyCodeResponse matchCode(@RequestBody MatchVerifyCodeRequest request) {
        MatchVerifyCodeResponse response;
        response = smsService.matchCode(request);
        return response;
    }

    /**
     * 短信发送统一入口
     *
     * @param request
     * @return
     */
    @RequestMapping("/sms/sendMessage")
    @ResponseBody
    public SendMessageResponse sendMessage(@RequestBody SendMessageRequest request) {
        SendMessageResponse response;
        response = smsService.sendMessage(request);
        return response;
    }

    /**
     * 校验验证码
     *
     * @param request
     * @return
     */
    @RequestMapping("/sms/checkVerifyCode")
    @ResponseBody
    public CheckVerifyCodeResponse checkVerifyCode(@RequestBody CheckVerifyCodeRequest request) {
        CheckVerifyCodeResponse response;
        response = smsService.checkVerifyCode(request);
        return response;
    }
}
