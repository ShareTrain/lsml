package com.lisong.component.jwt.context;

import com.lisong.service.res.mp.auth.MpSession;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MpTokenContext implements Serializable {

    private static final long serialVersionUID = 6719173466994839422L;

    private MpSession mpSession;


}
