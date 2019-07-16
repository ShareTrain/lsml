/*
 * 文件名称：Customer.java
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 开发人员：Groovy自动生成
 * 创建日期：20190717 07:08:47
 ********************************************************************************/
package com.lisong.domain.appoint.customer;

import com.lisong.domain.BaseEntity;;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 会员信息表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "customer")
@Getter
@Setter
public class Customer extends BaseEntity {

	private static final long serialVersionUID = 4876217180359623272L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 姓名
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 本人身份证ID
	 */
	@Column(name = "id_card")
	private String idCard;

	/**
	 * 会员等级
            0-游客
            1-普通会员，表示已注册
            2-掌柜
            3-主管
            4-经理
	 */
	@Column(name = "vip_level")
	private Integer vipLevel;

	/**
	 * 会员等级变更ID
            如果vip_level>=2时，才会有该值
	 */
	@Column(name = "level_change_id")
	private Long levelChangeId;

	/**
	 * 上级会员
            
	 */
	@Column(name = "up_vip")
	private Long upVip;

	/**
	 * 注册状态
            0-未注册
            1-已注册
	 */
	@Column(name = "regist_status")
	private Integer registStatus;

	/**
	 * 注册时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "regist_time")
	private java.util.Date registTime;

	/**
	 * 是否同意会员卡协议
            0-否
            1-是
	 */
	@Column(name = "agreed")
	private Integer agreed;

	/**
	 * 开通会员协议的时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "open_agree_time")
	private java.util.Date openAgreeTime;

	/**
	 * 昵称
	 */
	@Column(name = "nickname")
	private String nickname;

	/**
	 * 性别
	 */
	@Column(name = "sex")
	private Integer sex;

	/**
	 * 头像
	 */
	@Column(name = "avatar")
	private String avatar;

	/**
	 * 手机号码
	 */
	@Column(name = "mobile")
	private String mobile;

	/**
	 * 是否柜员
            0-否
            1-是
	 */
	@Column(name = "is_counter")
	private Integer isCounter;

	/**
	 * 柜员所属门店
            如果小程序用户也是门店柜员，这里记录可以核销的门店
	 */
	@Column(name = "belong_shops")
	private String belongShops;

	/**
	 * 邮箱
	 */
	@Column(name = "email")
	private String email;

	/**
	 * 会员生日
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "birthday")
	private java.util.Date birthday;

	/**
	 * 小程序openid
	 */
	@Column(name = "open_id")
	private String openId;

	/**
	 * 包含小程序的公众平台的unionid
	 */
	@Column(name = "union_id")
	private String unionId;

	/**
	 * 国家
	 */
	@Column(name = "country")
	private String country;

	/**
	 * 省
	 */
	@Column(name = "province")
	private String province;

	/**
	 * 市
	 */
	@Column(name = "city")
	private String city;

	/**
	 * 地区
	 */
	@Column(name = "district")
	private String district;

	/**
	 * 街道
	 */
	@Column(name = "street")
	private String street;

	/**
	 * 详细地址
	 */
	@Column(name = "address")
	private String address;


}
