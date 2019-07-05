package com.lisong.service.res.manage.auth;

import com.coding.helpers.tool.cmp.api.AppResponse;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lisong.common.JsonCustomSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManageLoadSessionResponse extends AppResponse {
    private static final long serialVersionUID = -711524815290973062L;

    @JsonSerialize(using = JsonCustomSerializer.Long2StringSerializer.class)
    private Long userId;

    private String name;

    private String mobile;

    private String mgrShops;

    @JsonSerialize(using = JsonCustomSerializer.Long2StringSerializer.class)
    private Long roleId;

    private String acct;
}
