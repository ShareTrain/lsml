package com.lisong.domain.customer;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/** 会员信息表 */
@Entity
@Table(name = "customer")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 8676497958056167122L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
    private Long id;

    /** 是否会员 */
    @Column(name = "is_vip")
    private Integer isVip;

    /** 成为会员时间. */
    @Column(name = "vip_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vipTime;

    /** 是否分销商 0.不是 1.是 */
    @Column(name = "is_spreader")
    private Integer isSpreader;

    /** 上级分销商ID */
    @Column(name = "up_spreader")
    private Long upSpreader;

    /** 分销商当前级别 0.非分销商 1.掌柜 2.主管 3.经理 */
    @Column(name = "current_level")
    private Integer currentLevel;

    /** 姓名 */
    @Column(name = "name")
    private String name;

    /** 昵称 */
    @Column(name = "nickname")
    private String nickname;

    /** 性别 */
    @Column(name = "sex")
    private Integer sex;

    /** 头像. */
    @Column(name = "avatar")
    private String avatar;

    /** 手机号 */
    @Column(name = "mobile")
    private String mobile;

    /** 是否柜员,参见字典. */
    @Column(name = "is_counter")
    private Integer isCounter;

    /** 柜员所属门店. */
    @Column(name = "belong_shops")
    private String belongShops;

    /** 邮箱 */
    @Column(name = "email")
    private String email;

    /** 生日 */
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;

    /** 微信openid */
    @Column(name = "open_id")
    private String openId;

    /** 微信unionid */
    @Column(name = "union_id")
    private String unionId;

    /** 国家. */
    @Column(name = "country")
    private String country;

    /** 省 */
    @Column(name = "province")
    private String province;

    /** 市 */
    @Column(name = "city")
    private String city;

    /** 区 */
    @Column(name = "area")
    private String area;

    /** 街道 */
    @Column(name = "street")
    private String street;

    /** 详细地址 */
    @Column(name = "addr")
    private String addr;
}
