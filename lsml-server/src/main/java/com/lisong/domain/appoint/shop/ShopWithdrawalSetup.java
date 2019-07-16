/*
 * 文件名称：ShopWithdrawalSetup.java
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
 * 商家提现设置表
 * @author Groovy自动生成
 * 
 */
@Entity
@Table(name = "shop_withdrawal_setup")
@Getter
@Setter
public class ShopWithdrawalSetup extends BaseEntity {

	private static final long serialVersionUID = 6467674148786515443L;

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy")
	@Column(name = "id")
	private Long id;

	/**
	 * 门店ID
	 */
	@Column(name = "shop_id")
	private Long shopId;

	/**
	 * 门店费率
	 */
	@Column(name = "shop_rate")
	private java.math.BigDecimal shopRate;


}
