package com.lisong.component.sms.req;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.lisong.component.regex.RegexDefine;
import com.lisong.component.sms.res.SendMessageResponse;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SendMessageRequest extends AppRequest<SendMessageResponse> {

    private static final long serialVersionUID = 2446632467427675007L;

    /** 短信模板Code */
    @NotBlank(message = "短信模板不能为空！")
    private String msgCode;

    /** 接收方手机号 */
    @NotBlank(message = "接收方手机号不能为空！")
    @Pattern(message = "手机号码不合法", regexp = RegexDefine.MOBILE_REGEX)
    private String receiveMobile;

    /** 短信参数 */
    private String param;
}
