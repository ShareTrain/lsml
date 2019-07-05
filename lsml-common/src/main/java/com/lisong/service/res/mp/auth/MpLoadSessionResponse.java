package com.lisong.service.res.mp.auth;

import com.coding.helpers.tool.cmp.api.AppResponse;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lisong.common.JsonCustomSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MpLoadSessionResponse extends AppResponse {

    private static final long serialVersionUID = -838721739600219251L;

    /** 客户ID. */
    @JsonSerialize(using = JsonCustomSerializer.Long2StringSerializer.class)
    private Long custId;

    /** 是否会员. */
    private Integer isVip;

    /** 是否分销商. */
    private Integer isSpreader;

    /** 上级分销商ID. */
    @JsonSerialize(using = JsonCustomSerializer.Long2StringSerializer.class)
    private Long upSpreader;

    /** 分销商当前级别. */
    private Integer currentLevel;

    /** 客户姓名. */
    private String name;

    /** 昵称. */
    private String nickname;

    /** 性别. */
    private Integer sex;

    /** 头像. */
    private String avatar;

    /** 手机号. */
    private String mobile;

    /** 是否柜员,参见字典. */
    private Integer isCounter;

    /** 柜员所属门店. */
    private String belongShops;

    /** 用户唯一标识. */
    private String openId;

    /** 用户在开放平台的唯一标识符. */
    private String unionId;

    /** 会话密钥. */
    private String sessionKey;
}
