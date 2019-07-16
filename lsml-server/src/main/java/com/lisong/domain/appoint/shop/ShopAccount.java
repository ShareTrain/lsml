/*
 * 文件名称：ShopAccount.java
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 开发人员：Groovy自动生成
 * 创建日期：20190717 07:09:35
 ********************************************************************************/
package com.lisong.domain.appoint.shop;

import com.lisong.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 商家提现账户表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "shop_account")
@Getter
@Setter
public class ShopAccount extends BaseEntity {

	private static final long serialVersionUID = 285258273261625771L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 用户ID
	 */
	@Column(name = "user_id")
	private Long userId;

	/**
	 * 介质类型
            1-银行卡
            2-微信
            3-支付宝
	 */
	@Column(name = "media_type")
	private Integer mediaType;

	/**
	 * 介质缩写
            比如：ICBC、alipay、wxpay
	 */
	@Column(name = "media_code")
	private String mediaCode;

	/**
	 * 介质名称
            比如：银行卡名称、微信、支付宝
	 */
	@Column(name = "media_name")
	private String mediaName;

	/**
	 * 介质编号
            比如：银行卡号、支付宝账号、微信账号
	 */
	@Column(name = "media_no")
	private String mediaNo;

	/**
	 * 用户名
	 */
	@Column(name = "name")
	private String name;

	/**
	 * 与介质相关的安全手机号
	 */
	@Column(name = "mobile")
	private String mobile;

	/**
	 * 开户行
	 */
	@Column(name = "open_bank")
	private String openBank;


}
