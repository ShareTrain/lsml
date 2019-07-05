package com.lisong.component.jwt.context;

import com.lisong.service.res.manage.auth.ManageSession;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ManageTokenContext implements Serializable {

    private static final long serialVersionUID = 6719173466994839422L;

    private ManageSession manageSession;
}
