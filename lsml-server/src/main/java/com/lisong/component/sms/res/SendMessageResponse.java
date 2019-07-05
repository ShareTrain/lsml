package com.lisong.component.sms.res;

import com.coding.helpers.tool.cmp.api.AppResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMessageResponse extends AppResponse {

    private static final long serialVersionUID = 6662536380868257686L;

    private boolean success = false;
}
