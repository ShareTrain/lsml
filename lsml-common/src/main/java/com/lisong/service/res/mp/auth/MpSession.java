package com.lisong.service.res.mp.auth;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MpSession implements Serializable {
    private static final long serialVersionUID = -1674777404664102506L;

    /** 【一定存在】客户ID. */
    private Long custId;

    /** 【一定存在】是否会员. */
    private Integer isVip;

    /** 【一定存在】是否分销商. */
    private Integer isSpreader;

    /** 上级分销商ID. */
    private Long upSpreader;

    /** 【一定存在】分销商当前级别. */
    private Integer currentLevel;

    /** 【一定存在】客户姓名. */
    private String name;

    /** 【一定存在】昵称. */
    private String nickname;

    /** 【一定存在】性别. */
    private Integer sex;

    /** 【一定存在】头像. */
    private String avatar;

    /** 手机号. */
    private String mobile;

    /** 【一定存在】是否柜员,参见字典. */
    private Integer isCounter;

    /** 柜员所属门店. */
    private String belongShops;

    /** 【一定存在】用户唯一标识. */
    private String openId;

    /** 用户在开放平台的唯一标识符. */
    private String unionId;

    /** 经度. */
    private String lon;

    /** 纬度. */
    private String lat;

    /** 【一定存在】会话密钥. */
    private String sessionKey;
}
